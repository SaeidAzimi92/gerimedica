package com.gerimedica.assignment.controller;

import com.gerimedica.assignment.domain.model.dto.SymptomDto;
import com.gerimedica.assignment.domain.model.exception.CsvFileFormatException;
import com.gerimedica.assignment.domain.model.exception.SymptomNotFoundException;
import com.gerimedica.assignment.service.SymptomsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("symptoms")
@RequiredArgsConstructor
public class SymptomController {

    private final SymptomsService symptomsService;

    @PostMapping
    public ResponseEntity<Void> uploadCsv(@RequestParam("file") MultipartFile csvFile) {
        try {
            symptomsService.save(csvFile);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not read csv file: ".concat(csvFile.getName()));
        } catch (CsvFileFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File form is not accepted: ".concat(csvFile.getName()));

        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<SymptomDto>> getAllSymptoms() {
        return ResponseEntity.ok(symptomsService.getAllSymptoms());
    }

    @GetMapping("/{code}")
    public ResponseEntity<SymptomDto> getSymptomByCode(@PathVariable("code") String code) {
        try {
            return ResponseEntity.ok(symptomsService.getSymptomByCode(code));
        } catch (SymptomNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Symptom not found for this code: ".concat(code));
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllSymptoms() {
        symptomsService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
