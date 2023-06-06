package com.example.neolabs.service.impl;

import com.example.neolabs.entity.User;
import com.example.neolabs.repository.UserRepository;
import com.example.neolabs.service.CsvExportService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import static org.apache.logging.log4j.LogManager.getLogger;


@Service
@RequiredArgsConstructor
public class CsvExportServiceImpl implements CsvExportService {

    private static final Logger log = getLogger(CsvExportServiceImpl.class);
    private final UserRepository userRepository;

    @Override
    public void writeUsersToCsv(Writer writer) {
        List<User> users = userRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("ID", "First Name", "Email", "Phone Number");
            for (User user : users) {
                csvPrinter.printRecord(user.getId(), user.getFirstName(), user.getEmail(), user.getPhoneNumber());
            }
        } catch (IOException e) {
            log.error("Error While writing CSV ", e);
        }
    }

}