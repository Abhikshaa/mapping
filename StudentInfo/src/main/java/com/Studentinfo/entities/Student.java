package com.Studentinfo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Student {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private long studentId;
    private String title;
    private String description;
    private String content;

     @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,orphanRemoval = true)//mapped to student
     private  List<Comment> comment = new ArrayList<>();


}
