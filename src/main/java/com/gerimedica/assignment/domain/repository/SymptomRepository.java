package com.gerimedica.assignment.domain.repository;

import com.gerimedica.assignment.domain.model.entity.Symptom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SymptomRepository extends CrudRepository<Symptom, Long> {
    List<Symptom> findAll();

    Optional<Symptom> findByCode(String code);
}
