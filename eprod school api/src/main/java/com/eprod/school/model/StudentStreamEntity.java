package com.eprod.school.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


@Data
@Entity
@Table(name = "tb_student_stream")
public class StudentStreamEntity extends BaseEntity{
    private Long streamId;
    private Long studentId;
}
