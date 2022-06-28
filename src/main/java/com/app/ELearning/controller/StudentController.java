package com.app.ELearning.controller;

import com.app.ELearning.model.Class;
import com.app.ELearning.model.Student;
import com.app.ELearning.service.ClassService;
import com.app.ELearning.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class StudentController {

    private final StudentService studentService;
    private final ClassService classService;

    @Autowired
    public StudentController(StudentService studentService, ClassService classService) {
        this.studentService = studentService;
        this.classService = classService;
    }

    @GetMapping("/student/{studentId}")
    public String studentHomepage(@PathVariable(name = "studentId") Integer studentId, Model model) {
        Optional<Student> student = studentService.getStudentById(studentId);
        if (student.isPresent()) {
            model.addAttribute("studentId", studentId);
            model.addAttribute("studentBookedClasses", student.get().getStudentBookedClasses());
            return "student-homepage";
        }
        model.addAttribute("student-error", true);
        return "login";
    }

    @GetMapping("/student/view-all-classes/{studentId}")
    public String classObjectList(@PathVariable(name = "studentId") Integer studentId, Model model) {
        model.addAttribute("classesList", classService.list());
        model.addAttribute("studentId", studentId);
        return "view-classes";
    }

    @GetMapping("/student/book/class/{studentId}/{classId}")
    public String bookAClass(@PathVariable(name = "studentId") Integer studentId, @PathVariable(name = "classId") Integer classId, Model model) {
        model.addAttribute("studentId", studentId);
        Optional<Class> classOptional = classService.getClassById(classId);
        Optional<Student> student = studentService.getStudentById(studentId);
        if (classOptional.isPresent() && student.isPresent()) {
            for (Class cls : student.get().getStudentBookedClasses()
            ) {
                if (classOptional.get().equals(cls)) {
                    return "redirect:/student/" + studentId;
                }
            }
            student.get().getStudentBookedClasses().add(classOptional.get());
            studentService.save(student.get());
        }

        return "redirect:/student/" + studentId;
    }

    @GetMapping("/student/cancel/class/{studentId}/{classId}")
    public String cancelBookedClass(@PathVariable(name = "studentId") Integer studentId, @PathVariable(name = "classId") Integer classId, Model model) {
        Optional<Class> classOptional = classService.getClassById(classId);
        Optional<Student> student = studentService.getStudentById(studentId);
        if (classOptional.isPresent() && student.isPresent()) {
            for (Class studentClass : student.get().getStudentBookedClasses()
            ) {
                if (studentClass.equals(classOptional.get())) {
                    student.get().getStudentBookedClasses().remove(studentClass);
                    break;
                }
            }
            studentService.save(student.get());
        }
        model.addAttribute("studentId", studentId);
        return "redirect:/student/" + studentId;
    }

    @GetMapping("/student/lesson/view/{studentId}/{classId}")
    public String viewCourseLessons(@PathVariable(name = "studentId") Integer studentId, @PathVariable(name = "classId") Integer classId, Model model) {
        Optional<Class> classOptional = classService.getClassById(classId);
        if (classOptional.isPresent()) {
            model.addAttribute("studentId", studentId);
            model.addAttribute("classId", classId);
            model.addAttribute("lessonsList", classOptional.get().getClassLessons());
            return "view-lesson-students";
        } else {
            return "redirect:/student/" + studentId;
        }
    }

}
