package com.csq.fweb.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csq.fweb.entity.Student;
import com.csq.fweb.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController extends BaseController<Student> {

    public StudentController(StudentService studentService) {
        super(studentService);
    }
}