package ru.matmex.animalshelter.service.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.matmex.animalshelter.model.Curator;
import ru.matmex.animalshelter.repository.CuratorRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class CuratorCSVService {
    @Autowired
    CuratorRepository curatorRepository;

    private static final String[] HEADERS = { "CuratorName", "CuratorPhone" };

    public void save(String path) {
        try {
            InputStream fileIS = new FileInputStream(path);
            List<Curator> curators = csvToCurators(fileIS);
            curatorRepository.saveAll(curators);
        } catch (Exception e) {
            throw new RuntimeException("Fail to store csv data: " + e.getMessage());
        }
    }

    public List<Curator> csvToCurators(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .withHeader(HEADERS)
                     .withDelimiter(';')
                     .withIgnoreHeaderCase()
                     .parse(fileReader)
        ) {

            List<Curator> curators = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Curator curator = new Curator(
                        csvRecord.get(HEADERS[0]),
                        csvRecord.get(HEADERS[1])
                );

                curators.add(curator);
            }

            return curators;
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
        }
    }
}
