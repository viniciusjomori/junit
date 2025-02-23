package br.viniciusjomori.SpringTest;

import br.viniciusjomori.SpringTest.Config.AbstractIntegrationTest;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.viniciusjomori.SpringTest.Config.TestConfig;
import br.viniciusjomori.SpringTest.Person.PersonEntity;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class PersonIntegrationTest extends AbstractIntegrationTest {
    
    private static RequestSpecification specification;
    private static ObjectMapper mapper;
    private static PersonEntity person1;
    private static PersonEntity person2;

    @BeforeAll
    public static void setup() {
        specification = new RequestSpecBuilder()
            .setBasePath("/person")
            .setPort(TestConfig.SERVER_PORT)
            .setContentType(TestConfig.CONTENT_TYPE)
            .addFilter(new RequestLoggingFilter(LogDetail.ALL))
            .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
            .build();

        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        person1 = PersonEntity.builder()
            .firstName("firstName1")
            .lastName("lastName1")
            .address("address1")
            .gender("MALE")
            .email("test1@email.com")
            .build();

        person2 = PersonEntity.builder()
            .firstName("firstName2")
            .lastName("lastName2")
            .address("address2")
            .gender("MALE")
            .email("test2@email.com")
            .build();
    }

    @Test
    @DisplayName("Create person should return saved object")
    @Order(1)
    void testIntegration_WhenCreate_ShouldReturnSavedObject() throws Exception {
        var content = RestAssured
            .given()
                .spec(specification)
                .body(person1)
            .when().post()
            .then()
                .statusCode(200)
                .extract().body().asString();
            
        PersonEntity saved = mapper.readValue(content, PersonEntity.class);
        
        assertNotNull(saved);
        assertTrue(saved.getId() > 0);
        assertEquals(person1.getFirstName(), saved.getFirstName());
        assertEquals(person1.getLastName(), saved.getLastName());
        
        person1 = saved;
    }

    @Test
    @DisplayName("Update person should return saved object")
    @Order(2)
    void testIntegration_WhenUpdate_ShouldReturnSavedObject() throws Exception {
        person1.setEmail("updated@email.com");
        person1.setAddress("updated_address");

        var content = RestAssured
            .given()
                .spec(specification)
                .body(person1)
            .when().put()
            .then()
                .statusCode(200)
                .extract().body().asString();
            
        PersonEntity saved = mapper.readValue(content, PersonEntity.class);
        
        assertNotNull(saved);
        assertTrue(saved.getId() > 0);
        assertEquals(person1.getEmail(), saved.getEmail());
        assertEquals(person1.getAddress(), saved.getAddress());
        
        person1 = saved;
    }

    @Test
    @DisplayName("findById() should return correct object")
    @Order(3)
    void testIntegration_WhenFindById_ShouldReturnCorrectObject() throws Exception {
        var content = RestAssured
            .given()
                .spec(specification)
            .when().get("/{id}", person1.getId())
            .then()
                .statusCode(200)
                .extract().body().asString();
            
        PersonEntity saved = mapper.readValue(content, PersonEntity.class);
        
        assertNotNull(saved);
        assertTrue(saved.getId() > 0);
        assertEquals(person1.getEmail(), saved.getEmail());
        assertEquals(person1.getAddress(), saved.getAddress());   
    }

    @Test
    @DisplayName("findAll() should return person list")
    @Order(4)
    void testIntegration_WhenFindAll_ShouldReturnPersonList() throws Exception {
        RestAssured
            .given()
                .spec(specification)
                .body(person2)
            .when().post();
        
        var content = RestAssured
            .given()
                .spec(specification)
            .when().get()
            .then()
                .statusCode(200)
                .extract().body().asString();
            
        PersonEntity[] response = mapper.readValue(content, PersonEntity[].class);
        List<PersonEntity> personList = Arrays.asList(response);
        
        assertEquals(2, personList.size());  
    }

    @Test
    @DisplayName("delete should return no content")
    @Order(5)
    void testIntegration_WhenDelete_ShouldReturnNoContent() throws Exception {
        RestAssured
            .given()
                .spec(specification)
            .when().delete("/{id}", person1.getId())
            .then()
                .statusCode(204)
                .extract().body().asString();
        
    }
}
