package com.app.ELearning;

import com.app.ELearning.model.Class;
import com.app.ELearning.repository.ClassRepository;
import com.app.ELearning.service.ClassService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class ClassServiceTest {

    @Autowired
    private ClassService classService;
    @MockBean
    private ClassRepository classRepository;

    @Test
    public void createClassTest() {
        Class classObject = new Class();
        classObject.setClassId(1);
        classObject.setClassName("Science Class");
        classObject.setClassTime("07:00");
        classObject.setTutorName("Salman");
        classObject.setDaysPerWeek(5);

        when(classRepository.save(classObject)).thenReturn(classObject);
        assertEquals(classObject, classService.save(classObject));
    }

    @Test
    public void listOfClassesTest() {
        Class classObject = new Class();
        classObject.setClassId(1);
        classObject.setClassName("Science Class");
        classObject.setClassTime("07:00");
        classObject.setTutorName("Ashley");
        classObject.setDaysPerWeek(5);

        List<Class> classList = new ArrayList<>();
        classList.add(classObject);

        when(classRepository.findAllClasses()).thenReturn(classList);
        assertEquals(classList, classService.list());
    }
}
