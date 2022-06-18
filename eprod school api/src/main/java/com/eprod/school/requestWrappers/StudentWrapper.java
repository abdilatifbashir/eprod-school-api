package com.eprod.school.requestWrappers;

import lombok.Data;

import java.util.Date;
@Data
public class StudentWrapper {
    private String firstName;
    private  String lastName;
    private String formStream;
    private Date dob;
    private String regNo;

}
