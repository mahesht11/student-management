package com.student.mgmt.service;

import com.student.mgmt.repo.StudentRepository;
import com.student.mgmt.util.StudentDto;
import com.student.mgmt.util.StudentEntity;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl {

    @Autowired
    StudentRepository studentRepository;

    public StudentDto createStudent(StudentDto studentDto) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setfName(studentDto.getfName());
        studentEntity.setlName(studentDto.getlName());
        studentEntity.setCity(studentDto.getCity());
        studentEntity.setCourse(studentDto.getCourse());
        StudentEntity student =  studentRepository.saveAndFlush(studentEntity);
        StudentDto studentDto1 = new StudentDto();
        studentDto1.setfName(student.getfName());
        studentDto1.setlName(student.getlName());
        studentDto1.setCity(student.getCity());
        studentDto1.setCourse(student.getCourse());
        return studentDto1;
    }

    public List<StudentDto> getStudents() {

        List<StudentEntity> studentEntities = studentRepository.findAll();
        List<StudentDto> studentDtos = new ArrayList<>();
        for(StudentEntity s : studentEntities){
            StudentDto studentDto = new StudentDto();
            studentDto.setfName(s.getfName());
            studentDto.setlName(s.getlName());
            studentDto.setCourse(s.getCourse());
            studentDto.setCity(s.getCity());
            studentDtos.add(studentDto);
        }
        return studentDtos;
    }
}
