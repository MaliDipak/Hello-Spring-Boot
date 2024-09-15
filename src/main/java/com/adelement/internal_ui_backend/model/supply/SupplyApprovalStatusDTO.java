package com.adelement.internal_ui_backend.model.supply;

import lombok.Data;

@Data
public class SupplyApprovalStatusDTO {
    private Long id;
    private Long supply_partner_id;
    private String app_bundle;
    private String status;
    private String line_inserted;
    private String date_updated;
    private String code;

    public SupplyApprovalStatusDTO(
            Long id, Long supply_partner_id, String app_bundle, String status, String line_inserted,
            String date_updated, String code) {
        this.id = id;
        this.supply_partner_id = supply_partner_id;
        this.app_bundle = app_bundle;
        this.status = status;
        this.line_inserted = line_inserted;
        this.date_updated = date_updated;
        this.code = code;
    }
}
