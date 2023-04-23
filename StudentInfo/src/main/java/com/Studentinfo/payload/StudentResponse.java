package com.Studentinfo.payload;

import com.Studentinfo.entities.Student;
import lombok.Data;

import java.util.List;
@Data
public class StudentResponse {

    private List<StudentDTO> content;
    private int totalPage;
    private  int pageNo;
    private int pageSize;
    private int totalElement;
    private boolean isLast;

}
