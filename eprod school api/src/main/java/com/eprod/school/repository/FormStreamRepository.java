package com.eprod.school.repository;

import com.eprod.school.model.FormStreamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormStreamRepository extends JpaRepository<FormStreamEntity, Long> {
    Optional<FormStreamEntity> findByForm(String formName);
}
