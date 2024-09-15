package com.adelement.internal_ui_backend.service.demand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adelement.internal_ui_backend.entity.DemandsEntity;
import com.adelement.internal_ui_backend.repository.DemandsRepository;

@Service
public class DemandsServiceImpl implements DemandsService {

    @Autowired
    DemandsRepository demandsRepository;

    @Override
    public List<DemandsEntity> findAllDemandPartners() {
        List<DemandsEntity> demandPartnerList = demandsRepository.findAll();
        return demandPartnerList;
    }

}
