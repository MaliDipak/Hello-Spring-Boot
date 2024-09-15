package com.adelement.internal_ui_backend.service.supply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adelement.internal_ui_backend.entity.supply.SupplyPartnerEntity;
import com.adelement.internal_ui_backend.repository.supply.SupplyPartnerRepository;

@Service
public class SupplyPartnerServiceImpl implements SupplyPartnerService {

    @Autowired
    SupplyPartnerRepository supplyPartnerRepository;

    @Override
    public List<SupplyPartnerEntity> findAllSupplyPartners() {
        List<SupplyPartnerEntity> supplyPartnerList = supplyPartnerRepository.findAll();
        return supplyPartnerList;
    }
}
