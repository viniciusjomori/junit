package br.viniciusjomori.mockito.Business;

import java.util.ArrayList;
import java.util.List;

import br.viniciusjomori.mockito.Service.CourseService;

// Method under test
public class CourseBusiness {
    
    private CourseService service;

    public CourseBusiness(CourseService service) {
        this.service = service;
    }

    public List<String> retriveCoursesRelatedToSpring(String student) {
        
        var filteredCourses = new ArrayList<String>();
        var allCourses = service.retrieveCources(student);

        for (String course: allCourses) {
            if(course.contains("Spring"))
                filteredCourses.add(course);
        }

        return filteredCourses;

    }

    public void deleteCoursesNotRelatedToSpring(String student) {
        
        var allCourses = service.retrieveCources(student);

        for (String course: allCourses) {
            if(!course.contains("Spring"))
                service.deleteCourse(course);
        }

    }
}
