package com.adelement.internal_ui_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adelement.internal_ui_backend.entity.demand.DemandsEntity;
import com.adelement.internal_ui_backend.entity.supply.SupplyPartnerEntity;
import com.adelement.internal_ui_backend.service.demand.DemandApprovalService;
import com.adelement.internal_ui_backend.service.demand.DemandsService;
import com.adelement.internal_ui_backend.service.supply.SupplyApprovalService;
import com.adelement.internal_ui_backend.service.supply.SupplyPartnerService;

@RestController
public class MyController {
    @Autowired
    DemandsService demandsService;

    @Autowired
    DemandApprovalService demandApprovalService;

    @Autowired
    SupplyPartnerService supplyPartnerService;

    @Autowired
    SupplyApprovalService supplyApprovalService;

    @GetMapping("demand-partners")
    public ResponseEntity<List<DemandsEntity>> getDemandPartners() {
        return ResponseEntity.ok(demandsService.findAllDemandPartners());
    }

    @GetMapping("supply-partners")
    public ResponseEntity<List<SupplyPartnerEntity>> getSupplyPartners() {
        return ResponseEntity.ok(supplyPartnerService.findAllSupplyPartners());
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("demand")
    public ResponseEntity<HashMap<String, Object>> getNativeProducts(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String app_bundle,
            @RequestParam(required = false) String code,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        HashMap<String, Object> data = demandApprovalService
                .getDemandsApprovalStatusWithDemandPartnerCodeWithPaginationAndFilter(status, app_bundle,
                        code, page, size);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("supply")
    public ResponseEntity<HashMap<String, Object>> getSupplyApprovalStatusRecords(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String app_bundle,
            @RequestParam(required = false) String code,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        HashMap<String, Object> data = supplyApprovalService
                .getSupplyApprovalStatusWithSupplyPartnerCodeWithPaginationAndFilter(status, app_bundle,
                        code, page, size);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:3000")
    @PutMapping("demand/{id}")
    public ResponseEntity<HashMap<String, Object>> updateDemandStatus(@PathVariable Long id,
            @RequestParam String status) {
        List<String> allowedStatuses = Arrays.asList("APPROVED", "APPROVAL_REQUESTED", "LINE_PRESENT", "REJECTED");
        HashMap<String, Object> response = new HashMap<>();
        if (!allowedStatuses.contains(status)) {
            response.put("isUpdated", false);
            response.put("message",
                    "Invalid status value. Allowed values are: \"APPROVED\", \"APPROVAL_REQUESTED\", \"LINE_PRESENT\", \"REJECTED\"");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(demandApprovalService.updateStatus(id, status), HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:3000")
    @PutMapping("supply/{id}")
    public ResponseEntity<HashMap<String, Object>> updateSupplyStatus(@PathVariable Long id,
            @RequestParam String status) {
        List<String> allowedStatuses = Arrays.asList("APPROVED", "APPROVAL_REQUESTED", "LINE_PRESENT", "REJECTED");
        HashMap<String, Object> response = new HashMap<>();
        if (!allowedStatuses.contains(status)) {
            response.put("isUpdated", false);
            response.put("message",
                    "Invalid status value. Allowed values are: \"APPROVED\", \"APPROVAL_REQUESTED\", \"LINE_PRESENT\", \"REJECTED\"");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(supplyApprovalService.updateStatus(id, status), HttpStatus.OK);
    }
}
