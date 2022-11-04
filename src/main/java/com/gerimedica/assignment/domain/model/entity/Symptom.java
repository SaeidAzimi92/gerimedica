package com.gerimedica.assignment.domain.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Setter
@Getter
public class Symptom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String source;
    @Column
    private String codeListCode;
    @Column(unique = true)
    private String code;
    @Column
    private String displayValue;
    @Column
    private String longDescription;
    @Column
    private String fromDate;
    @Column
    private String toDate;
    @Column
    private String sortingPriority;
}
