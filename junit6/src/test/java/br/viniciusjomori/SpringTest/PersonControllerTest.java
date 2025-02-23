package br.viniciusjomori.SpringTest;

import static org.hamcrest.CoreMatchers.is;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.viniciusjomori.SpringTest.App.ResourceNotFoundException;
import br.viniciusjomori.SpringTest.Person.PersonEntity;
import br.viniciusjomori.SpringTest.Person.PersonService;

@WebMvcTest
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockitoBean
    private PersonService service;

    private PersonEntity person1;
    private PersonEntity person2;

    @BeforeEach
    public void setup() {
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
    @DisplayName("Save person should return saved person")
    void testPersonController_WhenCreate_ThenReturnSavedPerson() throws Exception {
        // Given
        when(service.create(person1)).thenReturn(person1);

        // When
        ResultActions response = mockMvc.perform(post("/person")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(person1)));

        // Then
        response.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.email", is(person1.getEmail())))
            .andExpect(jsonPath("$.firstName", is(person1.getFirstName())))
            .andExpect(jsonPath("$.lastName", is(person1.getLastName())));
    }

    @Test
    @DisplayName("findAll() should return person list")
    void testPersonController_WhenFindAll_ThenReturnPersonList() throws Exception {
        // Given
        List<PersonEntity> personList = Arrays.asList(person1, person2);

        when(service.findAll()).thenReturn(personList);

        // When
        ResultActions response = mockMvc.perform(get("/person"));

        // Then
        response.andExpect(status().isOk())
            .andExpect(jsonPath("$.size()", is(personList.size())));
    }

    @Test
    @DisplayName("findById() person should return correct person")
    void testPersonController_WhenFindById_ThenReturnCorrectPerson() throws Exception {
        // Given
        long id = 1L;
        when(service.findById(id)).thenReturn(person1);

        // When
        ResultActions response = mockMvc.perform(get("/person/{id}", id));

        // Then
        response.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.email", is(person1.getEmail())))
            .andExpect(jsonPath("$.firstName", is(person1.getFirstName())))
            .andExpect(jsonPath("$.lastName", is(person1.getLastName())));
    }

    @Test
    @DisplayName("findById() person should return error")
    void testPersonController_WhenFindById_ThenReturnError() throws Exception {
        // Given
        long id = 1L;
        when(service.findById(id)).thenThrow(ResourceNotFoundException.class);

        // When
        ResultActions response = mockMvc.perform(get("/person/{id}", id));

        // Then
        response.andDo(print())
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Update person should return saved person")
    void testPersonController_WhenUpdate_ThenReturnSavedPerson() throws Exception {
        // Given
        when(service.update(person1)).thenReturn(person1);

        // When
        ResultActions response = mockMvc.perform(put("/person")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(person1)));

        // Then
        response.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.email", is(person1.getEmail())))
            .andExpect(jsonPath("$.firstName", is(person1.getFirstName())))
            .andExpect(jsonPath("$.lastName", is(person1.getLastName())));
    }

    @Test
    @DisplayName("Update person should return error")
    void testPersonController_WhenUpdate_ThenReturnError() throws Exception {
        // Given
        when(service.update(person1)).thenThrow(ResourceNotFoundException.class);

        // When
        ResultActions response = mockMvc.perform(put("/person")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(person1)));

        // Then
        response.andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Delete person should return no content")
    void testPersonController_WhenDelete_ThenReturnNoContent() throws Exception {
        // Given
        long id = 1;
        doNothing().when(service).delete(id);

        // When
        ResultActions response = mockMvc.perform(delete("/person/{id}", id));

        // Then
        response.andDo(print()).andExpect(status().isNoContent());
    }
}
