package com.Studentinfo.service;

import com.Studentinfo.payload.StudentDTO;

import java.util.List;

public interface StudentService {

    StudentDTO createstudent(StudentDTO studentDTO);

    List<StudentDTO> getAllStudent(int pageNo,int pageSize,String sortBy,String sortDir);

    StudentDTO getByStudent(long studentId);

    StudentDTO UpdateByStudent(long studentId, StudentDTO studentDTO);

    void deleteStudent(long studentId);
}
