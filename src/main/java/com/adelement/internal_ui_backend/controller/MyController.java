package com.adelement.internal_ui_backend.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adelement.internal_ui_backend.entity.DemandsEntity;
import com.adelement.internal_ui_backend.service.DemandApprovalService;
import com.adelement.internal_ui_backend.service.DemandsService;

@RestController
public class MyController {
    @Autowired
    DemandsService demandsService;

    @Autowired
    DemandApprovalService demandApprovalService;

    @GetMapping("demand-partners")
    public ResponseEntity<List<DemandsEntity>> getDemandPartners() {
        return ResponseEntity.ok(demandsService.findAllDemandPartners());
    }

    @GetMapping("/native")
    public ResponseEntity<HashMap<String, Object>> getNativeProducts(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String app_bundle,
            @RequestParam(required = false) String demand_partner_id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        HashMap<String, Object> data = demandApprovalService
                .getDemandsApprovalStatusWithDemandPartnerCodeWithPaginationAndFilter(status, app_bundle,
                        demand_partner_id, page, size);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
