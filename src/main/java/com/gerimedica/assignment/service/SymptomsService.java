package com.gerimedica.assignment.service;

import com.gerimedica.assignment.domain.model.dto.SymptomDto;
import com.gerimedica.assignment.domain.model.entity.Symptom;
import com.gerimedica.assignment.domain.model.exception.CsvFileFormatException;
import com.gerimedica.assignment.domain.model.exception.SymptomNotFoundException;
import com.gerimedica.assignment.domain.repository.SymptomRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SymptomsService {

    private final CsvApacheHelper csvServiceApache;
    private final SymptomRepository symptomRepository;

    public void save(MultipartFile csvFile) throws IOException, CsvFileFormatException {

        List<SymptomDto> symptomDtos = csvServiceApache.readCsv(csvFile);
        JsonMapper mapper = new JsonMapper();
        List<Symptom> symptomEntities = mapper.convertValue(symptomDtos, new TypeReference<>() {
        });
        symptomRepository.saveAll(symptomEntities);
    }

    public List<SymptomDto> getAllSymptoms() {
        JsonMapper mapper = new JsonMapper();
        List<SymptomDto> symptomDtos = mapper.convertValue(symptomRepository.findAll(), new TypeReference<>() {
        });
        return symptomDtos;
    }

    public SymptomDto getSymptomByCode(String code) throws SymptomNotFoundException {
        Optional<Symptom> symptom = symptomRepository.findByCode(code);
        if (symptom.isEmpty()) throw new SymptomNotFoundException();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(symptom.get(), SymptomDto.class);
    }

    public void deleteAll() {
        symptomRepository.deleteAll();
    }
}
