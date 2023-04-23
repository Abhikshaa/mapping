package com.Studentinfo.service.impl;

import com.Studentinfo.entities.Student;
import com.Studentinfo.exception.ResourceNotFoundException;
import com.Studentinfo.payload.StudentDTO;

import com.Studentinfo.payload.StudentResponse;
import com.Studentinfo.repositories.StudentRepository;
import com.Studentinfo.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDTO createstudent(StudentDTO studentDTO) {
        Student student = mapToEntity(studentDTO);

        Student save = studentRepository.save(student);

        StudentDTO dto = mapToDto(save);

        return dto;
    }

    private StudentDTO mapToDto(Student save) {
        StudentDTO dto = modelMapper.map(save, StudentDTO.class);
        return dto;
    }

    private Student mapToEntity(StudentDTO studentDTO) {
        Student student = modelMapper.map(studentDTO, Student.class);
        return student;
    }


    @Override
    public List<StudentDTO> getAllStudent(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Student> content = studentRepository.findAll(pageable);
        List<Student> list = content.getContent();
        List<StudentDTO> dto=list.stream().map(l1->mapToDto(l1)).collect(Collectors.toList());

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setContent(dto);
        studentResponse.setPageNo(content.getNumber());
        studentResponse.setTotalElement((int)content.getTotalElements());
        studentResponse.setTotalPage(content.getTotalPages());
        studentResponse.setPageSize(content.getSize());
        studentResponse.setLast(content.isLast());

        return (List<StudentDTO>) studentResponse;


    }

    @Override
    public StudentDTO getByStudent(long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student", "ID", studentId));
        return mapToDto(student);
    }

    @Override
    public StudentDTO UpdateByStudent(long studentId, StudentDTO studentDTO) {

        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student", "ID", studentId));
       student.setTitle(studentDTO.getTitle());
       student.setDescription(studentDTO.getDescription());
       student.setContent(studentDTO.getContent());

        Student save = studentRepository.save(student);

        return mapToDto(save);
    }

    @Override
    public void deleteStudent(long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student", "ID", studentId));

        studentRepository.deleteById(student.getStudentId());
    }
}