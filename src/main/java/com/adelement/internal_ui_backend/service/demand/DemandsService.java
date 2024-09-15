package com.adelement.internal_ui_backend.service.demand;

import java.util.List;

import com.adelement.internal_ui_backend.entity.DemandsEntity;

public interface DemandsService {
    List<DemandsEntity> findAllDemandPartners();
}
