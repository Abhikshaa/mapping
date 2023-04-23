package com.Studentinfo.service;

import com.Studentinfo.payload.CommentDTO;

import java.util.List;

public interface CommentService {

    CommentDTO createcomment(long studentId,CommentDTO commentDTO);

    List<CommentDTO> getAllStudent();

    CommentDTO getById(long studentId);

    CommentDTO updateById(long studentId,CommentDTO commentDTO);

    void deletbyId(long studentId);
}
