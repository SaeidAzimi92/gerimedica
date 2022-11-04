package com.gerimedica.assignment.service;

import com.gerimedica.assignment.domain.model.dto.SymptomDto;
import com.gerimedica.assignment.domain.model.exception.CsvFileFormatException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvApacheHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = {"source", "codeListCode", "code", "displayValue",
            "longDescription", "fromDate", "toDate", "sortingPriority"};


    public List<SymptomDto> readCsv(MultipartFile csvFile) throws IOException, CsvFileFormatException {
        if (!isCsvFormat(csvFile)) throw new CsvFileFormatException();

        return parseCsv(csvFile);

    }

    private List<SymptomDto> parseCsv(MultipartFile file) throws IOException {
        try (
                BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
                CSVParser csvParser = new CSVParser(fileReader,
                        CSVFormat.DEFAULT.builder()
                                .setHeader(HEADERs)
                                .setSkipHeaderRecord(true)
                                .setIgnoreHeaderCase(true)
                                .setTrim(true)
                                .build());
        ) {
            List<SymptomDto> symptomDtoList = new ArrayList<>();
            for (CSVRecord csvRecord : csvParser) {
                SymptomDto symptomDto = new SymptomDto();
                symptomDto.setSource(csvRecord.get("source"));
                symptomDto.setCodeListCode(csvRecord.get("codeListCode"));
                symptomDto.setCode(csvRecord.get("code"));
                symptomDto.setDisplayValue(csvRecord.get("displayValue"));
                symptomDto.setLongDescription(csvRecord.get("longDescription"));
                symptomDto.setFromDate(csvRecord.get("fromDate"));
                symptomDto.setToDate(csvRecord.get("toDate"));
                symptomDto.setSortingPriority(csvRecord.get("sortingPriority"));

                symptomDtoList.add(symptomDto);
            }
            return symptomDtoList;
        }
    }

    private boolean isCsvFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }
}
