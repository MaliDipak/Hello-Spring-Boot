package com.adelement.internal_ui_backend.service.demand;

import java.util.List;

import com.adelement.internal_ui_backend.entity.demand.DemandsEntity;

public interface DemandsService {
    List<DemandsEntity> findAllDemandPartners();
}
