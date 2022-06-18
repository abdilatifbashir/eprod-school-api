package com.eprod.school.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


@Data
@Entity
@Table(name = "tb_student")
public class StudentEntity extends BaseEntity{
    private String firstName;
    private  String lastName;
    private String formStream;
    private Date dob;
    private String regNo;
}
