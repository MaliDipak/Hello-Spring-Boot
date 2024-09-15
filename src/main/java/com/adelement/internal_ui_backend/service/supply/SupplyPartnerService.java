package com.adelement.internal_ui_backend.service.supply;

import java.util.List;

import com.adelement.internal_ui_backend.entity.supply.SupplyPartnerEntity;

public interface SupplyPartnerService {
    List<SupplyPartnerEntity> findAllSupplyPartners();
}
