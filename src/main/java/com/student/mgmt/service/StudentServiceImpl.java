package com.student.mgmt.service;

import com.student.mgmt.exception.StudentException;
import com.student.mgmt.repo.StudentRepository;
import com.student.mgmt.util.StudentDto;
import com.student.mgmt.util.StudentEntity;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Builder
@Service
public class StudentServiceImpl {

    private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);
    @Autowired
    StudentRepository studentRepository;

    public StudentDto createStudent(StudentDto studentDto) {
        log.info(" create method :");
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
        log.info("get all students method : ");
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

    public StudentDto getStudent(Integer studentId) {

        Optional<StudentEntity> student = studentRepository.findById(studentId);
        if(student.isPresent()){
            StudentDto studentDto = new StudentDto();
            studentDto.setfName(student.get().getfName());
            studentDto.setlName(student.get().getlName());
            studentDto.setCity(student.get().getCity());
            studentDto.setCourse(student.get().getCourse());
            return studentDto;
        }
        else {
            throw new StudentException("Invalid Student id");
        }
    }

    public String deleteStudent(Integer studentId) {
        Optional<StudentEntity> student = studentRepository.findById(studentId);
        if(student.isPresent()){
            studentRepository.deleteById(studentId);
            return "Successfully deleted";
        }
        else {
            throw new StudentException("Invalid Student Id");
        }
    }
}
