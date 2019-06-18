package com.lambdaschool.school;

import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.model.Student;
import com.lambdaschool.school.repository.CourseRepository;
import com.lambdaschool.school.repository.InstructorRepository;
import com.lambdaschool.school.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner {

    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private InstructorRepository instructorRepository;

    public SeedData(CourseRepository courseRepository, StudentRepository studentRepository, InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.instructorRepository = instructorRepository;
    }

    @Override
    public void run(String[] args) throws Exception {
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

        instructorRepository.save(instructor1);
        instructorRepository.save(instructor2);
        instructorRepository.save(instructor3);

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);

        courseRepository.save(course1);
        courseRepository.save(course2);
        courseRepository.save(course3);
        courseRepository.save(course4);
        courseRepository.save(course5);
        courseRepository.save(course6);
    }
}
