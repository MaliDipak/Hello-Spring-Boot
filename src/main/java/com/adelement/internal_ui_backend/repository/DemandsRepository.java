package com.adelement.internal_ui_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adelement.internal_ui_backend.entity.DemandsEntity;

@Repository
public interface DemandsRepository extends JpaRepository<DemandsEntity, Long> {

}
