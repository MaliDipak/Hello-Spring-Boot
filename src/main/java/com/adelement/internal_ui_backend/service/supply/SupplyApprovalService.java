package com.adelement.internal_ui_backend.service.supply;

import java.util.HashMap;
import java.util.List;

import com.adelement.internal_ui_backend.entity.supply.SupplyApprovalEntity;

public interface SupplyApprovalService {

        long getTotalCount(String status, String app_bundle, String supply_partner_id);

        List<SupplyApprovalEntity> getFilteredAndPaginatedData(String status, String app_bundle,
                        String supply_partner_id,
                        int page, int size);

        HashMap<String, Object> getSupplyApprovalStatusWithSupplyPartnerCodeWithPaginationAndFilter(String status,
                        String app_bundle, String supply_partner_id, int page, int size);

}
