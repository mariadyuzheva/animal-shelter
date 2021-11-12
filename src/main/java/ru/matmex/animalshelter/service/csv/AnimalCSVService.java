package ru.matmex.animalshelter.service.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.matmex.animalshelter.model.*;
import ru.matmex.animalshelter.repository.AnimalRepository;
import ru.matmex.animalshelter.repository.ClinicRepository;
import ru.matmex.animalshelter.repository.CuratorRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalCSVService {
    @Autowired
    AnimalRepository animalRepository;
    @Autowired
    ClinicRepository clinicRepository;
    @Autowired
    CuratorRepository curatorRepository;

    private static final String[] HEADERS = { "Name", "Type", "Breed", "AgeYears", "AgeMonths", "Clinic", "Curator", "Image" };

    public void save(String path) {
        try {
            InputStream fileIS = new FileInputStream(path);
            List<Animal> animals = csvToAnimals(fileIS);
            animalRepository.saveAll(animals);
        } catch (Exception e) {
            throw new RuntimeException("Fail to store csv data: " + e.getMessage());
        }
    }

    public List<Animal> csvToAnimals(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .withHeader(HEADERS)
                     .withDelimiter(';')
                     .withIgnoreHeaderCase()
                     .parse(fileReader)
        ) {

            List<Animal> animals = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                byte[] image = Files.readAllBytes(Paths.get(
                        "src/main/resources/static/images/" + csvRecord.get(HEADERS[7])));

                Animal animal = new Animal(
                        csvRecord.get(HEADERS[0]),
                        AnimalType.values()[Integer.parseInt(csvRecord.get(HEADERS[1]))],
                        csvRecord.get(HEADERS[2]),
                        Integer.parseInt(csvRecord.get(HEADERS[3])),
                        Integer.parseInt(csvRecord.get(HEADERS[4])),
                        clinicRepository.getOne(Long.parseLong(csvRecord.get(HEADERS[5]))),
                        curatorRepository.getOne(Long.parseLong(csvRecord.get(HEADERS[6]))),
                        image
                );

                animals.add(animal);
            }

            return animals;
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
        }
    }
}
