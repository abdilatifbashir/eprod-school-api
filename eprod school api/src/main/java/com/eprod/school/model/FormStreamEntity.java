package com.eprod.school.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_stream_form")
public class FormStreamEntity extends BaseEntity {
    private String form;
    private Long timestamp;

}


