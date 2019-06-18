package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplication;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.model.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
public class CourseServiceImplTest {

    @Autowired
    private CourseService courseService;

    @Autowired
    private InstructorService instructorService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void getCountStudentsInCourse() {
    }

    @Test
    public void deleteFound() {
        courseService.delete(7);
        assertEquals(5, courseService.findAll().size());
    }

    @Test(expected = EntityNotFoundException.class)
    public void deleteNotFound() {
        courseService.delete(200);
        assertEquals(5, courseService.findAll().size());
    }

    @Test
    public void findCourseById() {
        assertEquals("Course 1", courseService.findCourseById(7).getCoursename());
    }

    @Test
    public void save() {
        ArrayList<Student> newStudents = new ArrayList<>();
        String courseName = "Added Course";
        Course newCourse = new Course(courseName);
        newCourse.setStudents(newStudents);
        newCourse.getStudents().add(new Student("Some kid"));

        Course addedCourse = courseService.save(newCourse);

        assertNotNull(newCourse);

        Course foundCourse = courseService.findCourseById(addedCourse.getCourseid());
        assertEquals(addedCourse.getCoursename(), foundCourse.getCoursename());
    }
}