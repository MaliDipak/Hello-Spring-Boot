package com.adelement.internal_ui_backend.service.demand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adelement.internal_ui_backend.entity.demand.DemandApprovalEntity;
import com.adelement.internal_ui_backend.model.demand.DemandApprovalStatusDTO;
import com.adelement.internal_ui_backend.repository.demand.DemandsRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Service
public class DemandApprovalServiceImpl implements DemandApprovalService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    DemandsRepository demandsRepository;

    @Override
    public List<DemandApprovalEntity> getFilteredAndPaginatedData(String status, String app_bundle,
            String demand_partner_id, int page, int size) {
        // Base query
        StringBuilder queryStr = new StringBuilder("SELECT * FROM demand_app_approval_status WHERE 1=1");

        // Dynamically add conditions
        if (status != null && !status.isEmpty()) {
            queryStr.append(" AND status = :status");
        }
        if (app_bundle != null && !app_bundle.isEmpty()) {
            queryStr.append(" AND app_bundle = :app_bundle");
        }
        if (demand_partner_id != null && !demand_partner_id.isEmpty()) {
            queryStr.append(" AND demand_partner_id = :demand_partner_id");
        }

        queryStr.append(" ORDER BY date_updated DESC");

        // Create query from the constructed SQL
        Query query = entityManager.createNativeQuery(queryStr.toString(), DemandApprovalEntity.class);

        // Set parameters dynamically
        if (status != null && !status.isEmpty()) {
            query.setParameter("status", status);
        }
        if (app_bundle != null && !app_bundle.isEmpty()) {
            query.setParameter("app_bundle", app_bundle);
        }
        if (demand_partner_id != null && !demand_partner_id.isEmpty()) {
            query.setParameter("demand_partner_id", demand_partner_id);
        }

        // Pagination: set the offset and limit (equivalent to "OFFSET" and "LIMIT")
        query.setFirstResult(page * size); // Set the starting row (OFFSET)
        query.setMaxResults(size); // Set the number of rows (LIMIT)

        // Execute query and return results
        @SuppressWarnings("unchecked")
        List<DemandApprovalEntity> results = query.getResultList();

        return results;
    }

    @Override
    public long getTotalCount(String status, String app_bundle, String demand_partner_id) {
        // Base query for count
        StringBuilder countQueryStr = new StringBuilder("SELECT COUNT(*) FROM demand_app_approval_status WHERE 1=1");

        // Dynamically add conditions
        if (status != null && !status.isEmpty()) {
            countQueryStr.append(" AND status = :status");
        }
        if (app_bundle != null && !app_bundle.isEmpty()) {
            countQueryStr.append(" AND app_bundle = :app_bundle");
        }
        if (demand_partner_id != null && !demand_partner_id.isEmpty()) {
            countQueryStr.append(" AND demand_partner_id = :demand_partner_id");
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
        if (demand_partner_id != null && !demand_partner_id.isEmpty()) {
            countQuery.setParameter("demand_partner_id", demand_partner_id);
        }

        // Execute count query and return the total count
        return ((Number) countQuery.getSingleResult()).longValue();
    }

    @Override
    public HashMap<String, Object> getDemandsApprovalStatusWithDemandPartnerCodeWithPaginationAndFilter(String status,
            String app_bundle, String demand_partner_id, int page, int size) {

        List<DemandApprovalEntity> results = getFilteredAndPaginatedData(status, app_bundle,
                demand_partner_id, page, size);

        List<DemandApprovalStatusDTO> demandApprovalStatusDTOsList = new ArrayList<>();

        for (DemandApprovalEntity demandApprovalEntityItem : results) {

            String code = demandsRepository.findById(demandApprovalEntityItem.getDemand_partner_id()).get().getCode();
            Long id = demandApprovalEntityItem.getId();
            Long demandPartnerID = demandApprovalEntityItem.getDemand_partner_id();
            String appBundle = demandApprovalEntityItem.getApp_bundle();
            String lineStatus = demandApprovalEntityItem.getStatus();
            String line_inserted = demandApprovalEntityItem.getLine_inserted();
            String date_updated = demandApprovalEntityItem.getDate_updated();

            DemandApprovalStatusDTO demandApprovalStatusDTO = new DemandApprovalStatusDTO(id, demandPartnerID,
                    appBundle, lineStatus, line_inserted, date_updated, code);

            demandApprovalStatusDTOsList.add(demandApprovalStatusDTO);
        }

        long totalRecords = getTotalCount(status, app_bundle, demand_partner_id);
        int totalPages = (int) Math.ceil((double) totalRecords / size);

        HashMap<String, Object> response = new HashMap<>();
        response.put("totalPages", totalPages);
        response.put("currentPage", page);
        response.put("pageSize", size);
        response.put("totalRecords", totalRecords);
        response.put("data", demandApprovalStatusDTOsList);

        return response;
    }

}
