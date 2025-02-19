package br.viniciusjomori.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.viniciusjomori.mockito.Business.CourseBusiness;
import br.viniciusjomori.mockito.Service.CourseService;
import br.viniciusjomori.mockito.Stubs.CourseServiceStub;

public class CourseBusinessStubTest {
    
    @Test
    @DisplayName("Test with stub")
    void testCoursesRelatedToSpring_When_UsingStub() {
        // Given
        CourseService stubService = new CourseServiceStub();
        CourseBusiness business = new CourseBusiness(stubService);

        // When
        var filtredCourses = business.retriveCoursesRelatedToSpring("student");

        // Then
        assertEquals(4, filtredCourses.size());
    }
}
