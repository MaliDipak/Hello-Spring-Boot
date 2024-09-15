package com.adelement.internal_ui_backend.entity.demand;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "demands")
public class DemandsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long demand_id;
    private String code;
}
