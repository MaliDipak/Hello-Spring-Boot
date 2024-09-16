package com.adelement.internal_ui_backend.repository.demand;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adelement.internal_ui_backend.entity.demand.DemandApprovalEntity;

public interface DemandApprovalRepository extends JpaRepository<DemandApprovalEntity, Long> {

}
