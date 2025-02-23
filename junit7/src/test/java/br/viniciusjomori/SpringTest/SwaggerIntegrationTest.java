package br.viniciusjomori.SpringTest;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.viniciusjomori.SpringTest.Config.AbstractIntegrationTest;
import br.viniciusjomori.SpringTest.Config.TestConfig;
import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SwaggerIntegrationTest extends AbstractIntegrationTest {
    
    @Test
    @DisplayName("Junit Test should display a Swagger UI page")
    void testShouldDisplaySwaggerPage() {
        var content = RestAssured.given()
            .basePath("/swagger-ui/index.html")
            .port(TestConfig.SERVER_PORT)
            .when().get()
            .then()
                .statusCode(200)
                .extract().body().asString();
        
        assertTrue(content.contains("Swagger UI"));
    }

}
