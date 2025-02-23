package br.viniciusjomori.SpringTest.Config;

import java.util.Map;
import java.util.stream.Stream;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.lifecycle.Startables;

@ContextConfiguration(initializers = AbstractIntegrationTest.Initializer.class)
public class AbstractIntegrationTest {
    
    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        static MySQLContainer<?> mySql = new MySQLContainer<>("mysql:8.0.28");

        private static void startContainers() {
            Startables.deepStart(Stream.of(mySql)).join();
        }

        private static Map<String, String> createConnectionConfiguration() {
            return Map.of(
                "spring.datasource.url", mySql.getJdbcUrl(),
                "spring.datasource.username", mySql.getUsername(),
                "spring.datasource.password", mySql.getPassword()
            );
        }

        @Override
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public void initialize(ConfigurableApplicationContext applicationContext) {
            startContainers();
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            
            MapPropertySource testContainers = new MapPropertySource(
                "testcontainer",
                (Map) createConnectionConfiguration()
            );
            
            environment.getPropertySources().addFirst(testContainers);

        }

    }
}
