package br.viniciusjomori.mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.viniciusjomori.mockito.Course.CourseBusiness;
import br.viniciusjomori.mockito.Course.CourseService;

@ExtendWith(MockitoExtension.class)
public class CourseBusinessTest {
    
    @Mock
    CourseService mockService;

    @InjectMocks
    CourseBusiness business;

    @Captor
    ArgumentCaptor<String> argumentCaptor;

    List<String> courses;

    @BeforeEach
    void setup() {
        courses =  Arrays.asList(
            "REST API's RESTFul do 0 à Azure com ASP.NET Core 5 e Docker",
            "Agile Desmistificado com Scrum, XP, Kanban e Trello",
            "Spotify Engineering Culture Desmistificado",
            "REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker",
            "Docker do Zero à Maestria - Contêinerização Desmistificada",
            "Docker para Amazon AWS Implante Apps Java e .NET com Travis CI",
            "Microsserviços do 0 com Spring Cloud, Spring Boot e Docker",
            "Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#",
            "REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Docker",
            "Kotlin para DEV's Java: Aprenda a Linguagem Padrão do Android",
            "Microsserviços do 0 com Spring Cloud, Kotlin e Docker"
        );
    }

    @Test
    @DisplayName("Test with Mock")
    void testCoursesRelatedToSpring_When_UsingMock() {
        // Given
        when(mockService.retrieveCources("student")).thenReturn(courses);
        
        // When
        var filtredCourses = business.retriveCoursesRelatedToSpring("student");

        // Then
        assertEquals(4, filtredCourses.size());
    }

    @Test
    @DisplayName("Test verify")
    void test() {
        // Given
        when(mockService.retrieveCources(anyString())).thenReturn(courses);

        // When
        business.deleteCoursesNotRelatedToSpring("student");

        // Then
        verify(mockService, times(7))
            .deleteCourse(anyString());
        
        verify(mockService, never())
            .deleteCourse("REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker");
    }

    @Test
    @DisplayName("Test ArgumentCaptor")
    void testdeleteCoursesNotRelatedToSpring_When_UsingMock() {
        // Given
        when(mockService.retrieveCources(anyString())).thenReturn(courses);

        // When
        business.deleteCoursesNotRelatedToSpring("student");

        // Then
        verify(mockService, times(7)).deleteCourse(argumentCaptor.capture()); 

        List<String> deletedCourses = argumentCaptor.getAllValues();

        assertTrue(deletedCourses.contains("Agile Desmistificado com Scrum, XP, Kanban e Trello"));
        assertTrue(deletedCourses.contains("Kotlin para DEV's Java: Aprenda a Linguagem Padrão do Android"));
        assertTrue(deletedCourses.contains("Spotify Engineering Culture Desmistificado"));
    }

}

