package br.viniciusjomori.mockito.Service;

import java.util.List;

public interface CourseService {
    
    public List<String> retrieveCources(String student);
    public void deleteCourse(String course);

}
