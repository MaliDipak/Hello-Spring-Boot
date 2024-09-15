package com.adelement.internal_ui_backend.repository.supply;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adelement.internal_ui_backend.entity.supply.SupplyPartnerEntity;

public interface SupplyPartnerRepository extends JpaRepository<SupplyPartnerEntity, Long> {
    SupplyPartnerEntity findByCode(String code);
}
