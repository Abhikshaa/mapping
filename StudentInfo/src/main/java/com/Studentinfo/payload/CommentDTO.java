package com.Studentinfo.payload;

import com.Studentinfo.entities.Student;
import lombok.Data;

@Data
public class CommentDTO {
    private long commentId;
    private String name;
    private String email;
    private String body;

    private Student student;
}
