package ru.matmex.animalshelter.service.CSV;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.matmex.animalshelter.model.Address;
import ru.matmex.animalshelter.repository.AddressRepository;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddressCSVService {
    @Autowired
    AddressRepository addressRepository;

    private static final String[] HEADERS = { "City", "Street", "House" };

    public void save(String path) {
        try {
            InputStream fileIS = new FileInputStream(path);
            List<Address> addresses = csvToAddresses(fileIS);
            addressRepository.saveAll(addresses);
        } catch (Exception e) {
            throw new RuntimeException("Fail to store csv data: " + e.getMessage());
        }
    }

    public List<Address> csvToAddresses(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .withHeader(HEADERS)
                     .withDelimiter(';')
                     .withIgnoreHeaderCase()
                     .parse(fileReader)
        ) {

            List<Address> addresses = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Address address = new Address(
                        csvRecord.get(HEADERS[0]),
                        csvRecord.get(HEADERS[1]),
                        csvRecord.get(HEADERS[2])
                );

                addresses.add(address);
            }

            return addresses;
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse CSV file: " + e.getMessage());
        }
    }
}
