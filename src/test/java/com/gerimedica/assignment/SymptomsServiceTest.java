package com.gerimedica.assignment;

import com.gerimedica.assignment.domain.model.dto.SymptomDto;
import com.gerimedica.assignment.domain.model.entity.Symptom;
import com.gerimedica.assignment.domain.model.exception.SymptomNotFoundException;
import com.gerimedica.assignment.service.SymptomsService;
import com.gerimedica.assignment.domain.model.exception.CsvFileFormatException;
import com.gerimedica.assignment.domain.repository.SymptomRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@DirtiesContext
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SymptomsServiceTest {

    @Autowired
    private SymptomsService symptomsService;
    @Autowired
    SymptomRepository symptomRepository;

    @Test
    @Order(1)
    void beanAutowiredIsNotNull() {
        Assertions.assertNotNull(symptomsService);
    }

    @Test
    @Order(2)
    void givenCsv_WhenCallingSave_ThenStoredInDB() throws IOException, CsvFileFormatException {

        InputStream csvInputStream = readCsvAsInputStream();
        MultipartFile file = new MockMultipartFile("file", "file", "text/csv", csvInputStream);

        symptomsService.save(file);

        List<Symptom> symptoms = symptomRepository.findAll();
        int lines = getFileLines(readCsvAsInputStream());

        Assertions.assertNotNull(symptoms);
        Assertions.assertEquals(lines, symptoms.size());
    }

    @Test
    @Order(3)
    void whenCalledGetAllSymptoms_ThenAllSymptomsInDbReturn() {
        List<SymptomDto> actualSymptoms = symptomsService.getAllSymptoms();
        List<Symptom> expectedSymptoms = symptomRepository.findAll();

        Assertions.assertNotNull(actualSymptoms);
        Assertions.assertEquals(expectedSymptoms.size(), actualSymptoms.size());
    }

    @Test
    @Order(4)
    void whenCalledGetSymptomByCode_ThenReturnSymptom() throws SymptomNotFoundException {
        String code = "271636001";
        SymptomDto actualSymptoms = symptomsService.getSymptomByCode(code);

        Assertions.assertNotNull(actualSymptoms);
        Assertions.assertEquals(code, actualSymptoms.getCode());
    }

    @Test
    @Order(5)
    void givenInvalidCode_whenCalledGetSymptomByCode_ThenThrowsSymptomNotFoundException() throws SymptomNotFoundException {
        String code = "27163";
        Assertions.assertThrowsExactly(SymptomNotFoundException.class, () -> symptomsService.getSymptomByCode(code));
    }

    @Test
    @Order(6)
    void whenCalledDeleteAllSymptoms_ThenDbIsEmpty() {
        symptomsService.deleteAll();

        List<Symptom> symptoms = symptomRepository.findAll();

        Assertions.assertEquals(0, symptoms.size());
    }

    private InputStream readCsvAsInputStream() throws IOException {
        Path path = Path.of("csv/exercise.csv");
        return Files.newInputStream(path);
    }

    private int getFileLines(InputStream stream) {
        List<String> csvContent =
                new BufferedReader(new InputStreamReader(stream,
                        StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
        return csvContent.size() - 1;
    }

}
