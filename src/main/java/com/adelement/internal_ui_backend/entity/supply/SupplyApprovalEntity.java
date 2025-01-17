package com.adelement.internal_ui_backend.entity.supply;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "supply_app_approval_status")
public class SupplyApprovalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long supply_partner_id;
    private String app_bundle;
    private String status;
    private String line_inserted;

    @UpdateTimestamp
    private String date_updated;
}
