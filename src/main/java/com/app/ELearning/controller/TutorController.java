package com.app.ELearning.controller;

import com.app.ELearning.model.Class;
import com.app.ELearning.model.Lesson;
import com.app.ELearning.model.Student;
import com.app.ELearning.model.Tutor;
import com.app.ELearning.service.ClassService;
import com.app.ELearning.service.StudentService;
import com.app.ELearning.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class TutorController {
    private final TutorService tutorService;
    private final ClassService classService;
    private final StudentService studentService;

    @Autowired
    public TutorController(TutorService tutorService, ClassService classService, StudentService studentService) {
        this.tutorService = tutorService;
        this.classService = classService;
        this.studentService = studentService;
    }

    @GetMapping("/tutor/{tutorId}")
    public String getTutorById(@PathVariable(name = "tutorId") Integer tutorId, Model model) {
        Optional<Tutor> tutor = tutorService.getTutorById(tutorId);
        if (tutor.isPresent()) {
            model.addAttribute("tutorId", tutorId);
            model.addAttribute("tutorClasses", tutor.get().getTutorCreatedClasses());
            return "tutor-homepage";
        }
        model.addAttribute("tutor-error", true);
        return "login";
    }

    @GetMapping("/tutor/class/create/{tutorId}")
    public String createClassForm(@PathVariable(name = "tutorId") Integer tutorId, Model model) {
        model.addAttribute("classObject", new Class());
        model.addAttribute("tutorId", tutorId);
        return "create-class";
    }

    @PostMapping("/tutor/class/add/{tutorId}")
    public String createClass(@Valid Class classObject, @PathVariable(name = "tutorId") Integer tutorId) {
        Optional<Tutor> tutor = tutorService.getTutorById(tutorId);
        if (tutor.isPresent()) {
            classObject.setTutorName(tutor.get().getTutorName());
            tutor.get().getTutorCreatedClasses().add(classObject);
            tutorService.save(tutor.get());
        }
        return "redirect:/tutor/" + tutorId;
    }

    @GetMapping("/tutor/lesson/create/{tutorId}/{classId}")
    public String createLessonForm(@PathVariable(name = "tutorId") Integer tutorId, @PathVariable(name = "classId") Integer classId, Model model) {
        model.addAttribute("lesson", new Lesson());
        model.addAttribute("tutorId", tutorId);
        model.addAttribute("classId", classId);
        return "create-lesson";
    }

    @PostMapping("/tutor/lesson/add/{tutorId}/{classId}")
    public String createLessonFormSubmission(@Valid Lesson lesson, @PathVariable(name = "tutorId") Integer tutorId, @PathVariable(name = "classId") Integer classId) {
        Optional<Class> classOptional = classService.getClassById(classId);
        if (classOptional.isPresent()) {
            classOptional.get().getClassLessons().add(lesson);
            classService.save(classOptional.get());
        }
        return "redirect:/tutor/" + tutorId;
    }

    @GetMapping("/tutor/lesson/view/{tutorId}/{classId}")
    public String viewCourseLessons(@PathVariable(name = "tutorId") Integer tutorId, @PathVariable(name = "classId") Integer classId, Model model) {
        Optional<Class> classOptional = classService.getClassById(classId);
        if (classOptional.isPresent()) {
            model.addAttribute("tutorId", tutorId);
            model.addAttribute("classId", classId);
            model.addAttribute("lessonsList", classOptional.get().getClassLessons());
            return "view-lessons";
        } else {
            return "redirect:/tutor/" + tutorId;
        }
    }

    @GetMapping("/tutor/ViewClassStudents/{tutorId}/{classId}")
    public String getStudentsOfClass(@PathVariable(name = "tutorId") Integer tutorId, @PathVariable(name = "classId") Integer classId, Model model) {
        List<Student> studentList = studentService.getAllStudentsByClassId(classId);
        if (studentList.isEmpty()) {
            model.addAttribute("student", true);
        } else {
            model.addAttribute("studentList", studentList);
        }
        model.addAttribute("tutorId", tutorId);
        model.addAttribute("classId", classId);
        return "view-students";
    }

    @GetMapping("/tutor/cancel/class/{tutorId}/{classId}")
    public String cancelClassFromTutor(@PathVariable(name = "tutorId") Integer tutorId, @PathVariable(name = "classId") Integer classId, Model model) {
        Optional<Class> classOptional = classService.getClassById(classId);
        if (classOptional.isPresent()) {
            Optional<Tutor> tutor = tutorService.getTutorById(tutorId);
            for (Class cls : tutor.get().getTutorCreatedClasses()
            ) {
                if (classOptional.get().equals(cls)) {
                    tutor.get().getTutorCreatedClasses().remove(cls);
                    break;
                }
            }
            tutorService.save(tutor.get());
        }
        List<Student> studentList = studentService.list();
        if (!studentList.isEmpty()) {
            for (Student std : studentList
            ) {
                for (Class stdCls : std.getStudentBookedClasses()
                ) {
                    if (classOptional.get().equals(stdCls)) {
                        std.getStudentBookedClasses().remove(stdCls);
                        studentService.save(std);
                    }
                    if (std.getStudentBookedClasses().isEmpty()) {
                        break;
                    }
                }
            }
        }
        classOptional.get().setClassLessons(null);
        classService.delete(classOptional.get().getClassId());
        model.addAttribute("tutorId", tutorId);
        model.addAttribute("classId", classId);
        return "redirect:/tutor/" + tutorId;
    }

}
