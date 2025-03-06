package com.csq.fweb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csq.fweb.entity.Student;
import com.csq.fweb.mapper.StudentMapper;
import com.csq.fweb.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
}