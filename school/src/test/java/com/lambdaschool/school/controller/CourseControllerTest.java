package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.service.CourseService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CourseController.class, secure = false)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    private ArrayList<Course> courseList;

    @Before
    public void setUp() throws Exception {

        courseList = new ArrayList<>();

        Instructor instructor1 = new Instructor("Instructor 1");
        Instructor instructor2 = new Instructor("Instructor 2");
        Instructor instructor3 = new Instructor("Instructor 3");

        Course course1 = new Course("Course 1", instructor1);
        Course course2 = new Course("Course 2", instructor1);
        Course course3 = new Course("Course 3", instructor1);
        Course course4 = new Course("Course 4", instructor2);
        Course course5 = new Course("Course 5", instructor2);
        Course course6 = new Course("Course 6", instructor3);

        Student student1 = new Student("Student 1");
        Student student2 = new Student("Student 2");
        Student student3 = new Student("Student 3");

        ArrayList<Student> studentsInCourseList1 = new ArrayList<>();
        ArrayList<Student> studentsInCourseList2 = new ArrayList<>();
        ArrayList<Student> studentsInCourseList3 = new ArrayList<>();

        studentsInCourseList1.add(student1);

        studentsInCourseList2.add(student1);
        studentsInCourseList2.add(student2);

        studentsInCourseList3.add(student1);
        studentsInCourseList3.add(student3);

        course1.setStudents(studentsInCourseList1);
        course2.setStudents(studentsInCourseList1);
        course3.setStudents(studentsInCourseList2);
        course4.setStudents(studentsInCourseList2);
        course5.setStudents(studentsInCourseList2);
        course6.setStudents(studentsInCourseList3);

        ArrayList<Course> courseList1 = new ArrayList<>();
        ArrayList<Course> courseList2 = new ArrayList<>();
        ArrayList<Course> courseList3 = new ArrayList<>();

        courseList1.add(course1);
        courseList1.add(course2);
        courseList1.add(course3);

        courseList2.add(course4);
        courseList2.add(course5);

        courseList3.add(course6);

        instructor1.setCourses(courseList1);
        instructor2.setCourses(courseList2);
        instructor3.setCourses(courseList3);


        ArrayList<Course> studCourseList1 = new ArrayList<>();
        ArrayList<Course> studCourseList2 = new ArrayList<>();
        ArrayList<Course> studCourseList3 = new ArrayList<>();

        studCourseList1.add(course1);
        studCourseList1.add(course2);
        studCourseList1.add(course3);
        studCourseList1.add(course4);
        studCourseList1.add(course5);
        studCourseList1.add(course6);

        studCourseList2.add(course3);
        studCourseList2.add(course4);
        studCourseList2.add(course5);

        studCourseList3.add(course6);

        student1.setCourses(studCourseList1);
        student2.setCourses(studCourseList2);
        student3.setCourses(studCourseList3);

        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);
        courseList.add(course4);
        courseList.add(course5);
        courseList.add(course6);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void listAllCourses() throws Exception {

        String apiUrl = "/courses/courses";

        Mockito.when(courseService.findAll()).thenReturn(courseList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);

        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(courseList);

        assertEquals("Rest API Returns List", er, tr);
    }

    @Test
    public void getCountStudentsInCourses() {
    }

    @Test
    public void deleteCourseById() {
    }

    @Test
    public void addNewCourse() throws Exception{
        String apiUrl = "/courses/course/add";

        ArrayList<Student> newStudents = new ArrayList<>();
        String courseName = "New Course";
        Course newCourse = new Course(courseName);
        newCourse.setStudents(newStudents);
        newCourse.getStudents().add(new Student("New Student"));

        ObjectMapper mapper = new ObjectMapper();
        String courseString = mapper.writeValueAsString(newCourse);

        Mockito.when(courseService.save(any(Course.class))).thenReturn(newCourse);

        RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(courseString);
        mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }
}