package com.adelement.internal_ui_backend.service.supply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adelement.internal_ui_backend.entity.supply.SupplyApprovalEntity;
import com.adelement.internal_ui_backend.model.supply.SupplyApprovalStatusDTO;

import com.adelement.internal_ui_backend.repository.supply.SupplyPartnerRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class SupplyApprovalServiceImpl implements SupplyApprovalService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    SupplyPartnerRepository supplyPartnerRepository;

    @Override
    public long getTotalCount(String status, String app_bundle, String supply_partner_id) {
        // Base query for count
        StringBuilder countQueryStr = new StringBuilder("SELECT COUNT(*) FROM supply_app_approval_status WHERE 1=1");

        // Dynamically add conditions
        if (status != null && !status.isEmpty()) {
            countQueryStr.append(" AND status = :status");
        }
        if (app_bundle != null && !app_bundle.isEmpty()) {
            countQueryStr.append(" AND app_bundle = :app_bundle");
        }
        if (supply_partner_id != null && !supply_partner_id.isEmpty()) {
            countQueryStr.append(" AND supply_partner_id = :supply_partner_id");
        }

        // Create query from the constructed SQL
        Query countQuery = entityManager.createNativeQuery(countQueryStr.toString());

        // Set parameters dynamically
        if (status != null && !status.isEmpty()) {
            countQuery.setParameter("status", status);
        }
        if (app_bundle != null && !app_bundle.isEmpty()) {
            countQuery.setParameter("app_bundle", app_bundle);
        }
        if (supply_partner_id != null && !supply_partner_id.isEmpty()) {
            countQuery.setParameter("supply_partner_id", supply_partner_id);
        }

        // Execute count query and return the total count
        return ((Number) countQuery.getSingleResult()).longValue();
    }

    @Override
    public List<SupplyApprovalEntity> getFilteredAndPaginatedData(String status, String app_bundle,
            String supply_partner_id, int page, int size) {
        // Base query
        StringBuilder queryStr = new StringBuilder("SELECT * FROM supply_app_approval_status WHERE 1=1");

        // Dynamically add conditions
        if (status != null && !status.isEmpty()) {
            queryStr.append(" AND status = :status");
        }
        if (app_bundle != null && !app_bundle.isEmpty()) {
            queryStr.append(" AND app_bundle = :app_bundle");
        }
        if (supply_partner_id != null && !supply_partner_id.isEmpty()) {
            queryStr.append(" AND supply_partner_id = :supply_partner_id");
        }

        queryStr.append(" ORDER BY date_updated DESC");

        // Create query from the constructed SQL
        Query query = entityManager.createNativeQuery(queryStr.toString(), SupplyApprovalEntity.class);

        // Set parameters dynamically
        if (status != null && !status.isEmpty()) {
            query.setParameter("status", status);
        }
        if (app_bundle != null && !app_bundle.isEmpty()) {
            query.setParameter("app_bundle", app_bundle);
        }
        if (supply_partner_id != null && !supply_partner_id.isEmpty()) {
            query.setParameter("supply_partner_id", supply_partner_id);
        }

        // Pagination: set the offset and limit (equivalent to "OFFSET" and "LIMIT")
        query.setFirstResult(page * size); // Set the starting row (OFFSET)
        query.setMaxResults(size); // Set the number of rows (LIMIT)

        // Execute query and return results
        @SuppressWarnings("unchecked")
        List<SupplyApprovalEntity> results = query.getResultList();

        return results;
    }

    @Override
    public HashMap<String, Object> getSupplyApprovalStatusWithSupplyPartnerCodeWithPaginationAndFilter(String status,
            String app_bundle, String supply_partner_id, int page, int size) {

        List<SupplyApprovalEntity> results = getFilteredAndPaginatedData(status, app_bundle, supply_partner_id, page,
                size);

        List<SupplyApprovalStatusDTO> supplyApprovalStatusDTOsList = new ArrayList<>();

        for (SupplyApprovalEntity supplyApprovalEntityItem : results) {

            String code = supplyPartnerRepository.findById(supplyApprovalEntityItem.getSupply_partner_id()).get()
                    .getCode();
            Long id = supplyApprovalEntityItem.getId();
            Long supplyPartnerID = supplyApprovalEntityItem.getSupply_partner_id();
            String appBundle = supplyApprovalEntityItem.getApp_bundle();
            String lineStatus = supplyApprovalEntityItem.getStatus();
            String line_inserted = supplyApprovalEntityItem.getLine_inserted();
            String date_updated = supplyApprovalEntityItem.getDate_updated();

            SupplyApprovalStatusDTO supplyApprovalStatusDTO = new SupplyApprovalStatusDTO(id, supplyPartnerID,
                    appBundle, lineStatus, line_inserted, date_updated, code);

            supplyApprovalStatusDTOsList.add(supplyApprovalStatusDTO);
        }

        long totalRecords = getTotalCount(status, app_bundle, supply_partner_id);
        int totalPages = (int) Math.ceil((double) totalRecords / size);

        HashMap<String, Object> response = new HashMap<>();
        response.put("totalPages", totalPages);
        response.put("currentPage", page);
        response.put("pageSize", size);
        response.put("totalRecords", totalRecords);
        response.put("data", supplyApprovalStatusDTOsList);

        return response;
    }

}
