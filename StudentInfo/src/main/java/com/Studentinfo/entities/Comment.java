package com.Studentinfo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;
    private String name;
    private String email;
    private String body;
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="id_student")//foreigh key
    private Student student;

}
