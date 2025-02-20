package br.viniciusjomori.mockito.Course;

import java.util.List;

public interface CourseService {
    
    public List<String> retrieveCources(String student);
    public void deleteCourse(String course);

}
