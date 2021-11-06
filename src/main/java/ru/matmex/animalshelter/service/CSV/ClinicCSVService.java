package ru.matmex.animalshelter.service.CSV;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.matmex.animalshelter.model.Clinic;
import ru.matmex.animalshelter.repository.AddressRepository;
import ru.matmex.animalshelter.repository.ClinicRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClinicCSVService {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    ClinicRepository clinicRepository;

    private static final String[] HEADERS = { "ClinicName", "ClinicAddress", "ClinicPhone" };

    public void save(String path) {
        try {
            InputStream fileIS = new FileInputStream(path);
            List<Clinic> clinics = csvToClinics(fileIS);
            clinicRepository.saveAll(clinics);
        } catch (Exception e) {
            throw new RuntimeException("Fail to store csv data: " + e.getMessage());
        }
    }

    public List<Clinic> csvToClinics(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .withHeader(HEADERS)
                     .withDelimiter(';')
                     .withIgnoreHeaderCase()
                     .parse(fileReader)
        ) {

            List<Clinic> clinics = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Clinic clinic = new Clinic(
                        csvRecord.get(HEADERS[0]),
                        addressRepository.getOne(Long.parseLong(csvRecord.get(HEADERS[1]))),
                        csvRecord.get(HEADERS[2])
                );

                clinics.add(clinic);
            }

            return clinics;
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
        }
    }
}
