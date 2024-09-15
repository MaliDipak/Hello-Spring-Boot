package com.adelement.internal_ui_backend.service.demand;

import java.util.HashMap;
import java.util.List;

import com.adelement.internal_ui_backend.entity.demand.DemandApprovalEntity;

public interface DemandApprovalService {

        long getTotalCount(String status, String app_bundle, String demand_partner_id);

        List<DemandApprovalEntity> getFilteredAndPaginatedData(String status, String app_bundle,
                        String demand_partner_id,
                        int page, int size);

        HashMap<String, Object> getDemandsApprovalStatusWithDemandPartnerCodeWithPaginationAndFilter(String status,
                        String app_bundle, String demand_partner_id, int page, int size);
}
