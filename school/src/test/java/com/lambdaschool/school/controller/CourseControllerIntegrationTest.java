package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Student;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.junit.Assert.*;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.number.OrderingComparison.lessThan;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void whenMeasuredResponseTime() {
        given().when().get("/courses/courses").then().time(lessThan(5000L));
    }

    @Test
    public void givenPostCourse() throws Exception{
        ArrayList<Student> newStudents = new ArrayList<>();
        String courseName = "Added Course";
        Course newCourse = new Course(courseName);
        newCourse.setStudents(newStudents);
        newCourse.getStudents().add(new Student("Some kid"));

        ObjectMapper mapper = new ObjectMapper();
        String stringCourse = mapper.writeValueAsString(newCourse);

        given().contentType("application/json").body(stringCourse).when().post("/courses/course/add").then().statusCode(201);
    }

    @Test
    public void listAllCourses() {
    }

    @Test
    public void getCountStudentsInCourses() {
    }

    @Test
    public void deleteCourseById() {
    }
}