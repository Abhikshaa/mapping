package com.Studentinfo.service.impl;

import com.Studentinfo.entities.Comment;
import com.Studentinfo.entities.Student;
import com.Studentinfo.exception.ResourceNotFoundException;
import com.Studentinfo.payload.CommentDTO;
import com.Studentinfo.repositories.CommentRepository;
import com.Studentinfo.repositories.StudentRepository;
import com.Studentinfo.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private StudentRepository studentRepository;

    public CommentServiceImpl(CommentRepository commentRepository, StudentRepository studentRepository) {
        this.commentRepository = commentRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public CommentDTO createcomment(long studentId, CommentDTO commentDTO) {
       Comment comment=mapToEntity(commentDTO);

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student", "studentId", studentId));
                            comment.setStudent(student);
        Comment save = commentRepository.save(comment);

        return mapToDto(save);
    }

    @Override
    public List<CommentDTO> getAllStudent() {
        List<Comment> list = commentRepository.findAll();
        return list.stream().map(l1->mapToDto(l1)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO getById(long studentId) {
        Comment comment = commentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student", "studentId", studentId));

        return mapToDto(comment);
    }

    @Override
    public CommentDTO updateById(long studentId,CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student", "student", studentId));
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setBody(commentDTO.getBody());
        Comment update = commentRepository.save(comment);
        return mapToDto(update);
    }

    @Override
    public void deletbyId(long studentId) {
        Comment comment = commentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student", "student", studentId));
        commentRepository.deleteById(studentId);
    }

    private CommentDTO mapToDto(Comment save) {
        CommentDTO dto = new CommentDTO();
        dto.setCommentId(save.getCommentId());
        dto.setName(save.getName());
        dto.setEmail(save.getEmail());
        dto.setBody(save.getBody());

        return dto;
    }

    Comment mapToEntity(CommentDTO commentDTO) {

        Comment comment= new Comment();
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setBody(commentDTO.getBody());
        return comment;

    }
}
