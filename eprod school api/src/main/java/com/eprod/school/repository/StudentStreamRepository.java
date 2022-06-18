package com.eprod.school.repository;

import com.eprod.school.model.StudentStreamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentStreamRepository extends JpaRepository<StudentStreamEntity,Long> {
    List<StudentStreamEntity> findAllByStreamIdAndStudentId(Long streamId, Long studentId);
}
