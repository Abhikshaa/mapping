package com.Studentinfo.Controller;

import com.Studentinfo.payload.StudentDTO;
import com.Studentinfo.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trust")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    //http://localhost:8080/api/trust
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentDTO> saveData(@RequestBody StudentDTO studentDTO){
        StudentDTO dto = studentService.createstudent(studentDTO);
     return  new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/trust?pageNo=0&pageSize=5&studentId&sortDir=desc
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<StudentDTO> getAllStudent(@RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
                                          @RequestParam(value="pageSize",defaultValue = "5") int pageSize,
                                          @RequestParam(value="sortBy",defaultValue = "studentId",required = false) String sortBy,
                                          @RequestParam(value="sortDir",defaultValue = "sortDir",required = false) String sortDir){

        return studentService.getAllStudent(pageNo,pageSize,sortBy,sortDir);
    }
    @GetMapping("/{student_id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<StudentDTO> getByIdstudent(@PathVariable(value = "student_id") long student_id){
        StudentDTO dto = studentService.getByStudent(student_id);
        return  new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{student_id}")
    public ResponseEntity<StudentDTO> updatetByIdstudent(@PathVariable(value = "student_id") long student_id,
                                                         @RequestBody StudentDTO studentDTO){
        StudentDTO dto = studentService.UpdateByStudent(student_id,studentDTO);
        return  new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @DeleteMapping("/{student_id}")
    public ResponseEntity<String> deletebystudent(@PathVariable(value = "student_id") long student_id){
         studentService.deleteStudent(student_id);
        return  new ResponseEntity<String>("deleted!!", HttpStatus.OK);
    }
}
