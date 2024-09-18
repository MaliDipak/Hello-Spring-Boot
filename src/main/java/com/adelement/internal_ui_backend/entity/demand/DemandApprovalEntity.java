package com.adelement.internal_ui_backend.entity.demand;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "demand_app_approval_status")
public class DemandApprovalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long demand_partner_id;
    private String app_bundle;
    private String status;

    private String line_inserted;

    @UpdateTimestamp
    private String date_updated;
}