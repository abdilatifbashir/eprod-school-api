package com.eprod.school.repository;

import com.eprod.school.model.FormStreamEntity;
import com.eprod.school.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository  extends JpaRepository<StudentEntity,Long> {
    Optional<StudentEntity> findByRegNo(String regNo);
    Optional<StudentEntity> findByFirstName(String name);

    @Query(value = "SELECT tb_stduent.id,tb_student.created_on,tb_student.soft_delete,tb_student.dob,tb_student.form_stream,tb_stduent.last_name,tb_stduent.reg_no FROM tb_student INNER JOIN tb_student_stream ON tb_stduent.id = tb_student_stream WHERE tb_student_stream.group_id=:streamId",nativeQuery = true)
    List<StudentEntity> findStreamById(Long streamId);
}
