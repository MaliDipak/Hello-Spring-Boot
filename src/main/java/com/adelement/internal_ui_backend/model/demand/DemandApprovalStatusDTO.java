package com.adelement.internal_ui_backend.model.demand;

import lombok.Data;

@Data
public class DemandApprovalStatusDTO {
    private Long id;
    private Long demand_partner_id;
    private String app_bundle;
    private String status;
    private String line_inserted;
    private String date_updated;
    private String code;

    public DemandApprovalStatusDTO(
            Long id, Long demand_partner_id, String app_bundle, String status, String line_inserted,
            String date_updated, String code) {
        this.id = id;
        this.demand_partner_id = demand_partner_id;
        this.app_bundle = app_bundle;
        this.status = status;
        this.line_inserted = line_inserted;
        this.date_updated = date_updated;
        this.code = code;
    }
}
