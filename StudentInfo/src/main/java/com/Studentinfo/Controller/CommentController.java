package com.Studentinfo.Controller;

import com.Studentinfo.payload.CommentDTO;
import com.Studentinfo.payload.StudentDTO;
import com.Studentinfo.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    //http://localhost:8080/api/comments/student/2/comment
    @PostMapping("/student/{studentId}/comment")
    @PreAuthorize("hasRole('NORMAL')")
    public ResponseEntity<CommentDTO> saveData(@PathVariable(value = "studentId") long studentId ,
                                               @RequestBody CommentDTO commentDTO){
        CommentDTO dto = commentService.createcomment(studentId, commentDTO);
        return  new ResponseEntity<>(dto,HttpStatus.CREATED);
    }
    @GetMapping
    @PreAuthorize("hasRole('NORMAL')")
    public List<CommentDTO> getAllComment(){
        return commentService.getAllStudent();
    }
    @GetMapping("/{studentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommentDTO> getById(@PathVariable("studentId") long studentId){

        CommentDTO dto = commentService.getById(studentId);

        return ResponseEntity.ok(dto);
    }
    @PutMapping("/{studentId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CommentDTO> updateById(@PathVariable(value="studentId") long studentId,
                                                 @RequestBody CommentDTO commentDTO){

        CommentDTO dto = commentService.updateById(studentId,commentDTO);

        return ResponseEntity.ok(dto);
    }
    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "studentId") long studentId){
        commentService.deletbyId(studentId);
        return new ResponseEntity<String>("deleted!!",HttpStatus.OK);
    }

}
