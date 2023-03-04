package com.example.neolabs.service.impl;

import com.example.neolabs.entity.User;
import com.example.neolabs.repository.UserRepository;
import com.example.neolabs.service.CsvExportService;
import org.apache.commons.csv.*;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

import static org.apache.logging.log4j.LogManager.getLogger;


@Service
public class CsvExportServiceImpl implements CsvExportService {

    private static final Logger log = getLogger(CsvExportServiceImpl.class);
    private final UserRepository userRepository;

    public CsvExportServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void writeUsersToCsv(Writer writer) {

        List<User> users = userRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("ID", "First Name" ,"Email");
            for (User user : users) {
                csvPrinter.printRecord(user.getId(), user.getFirstName(), user.getEmail());
            }
        } catch (IOException e) {
            log.error("Error While writing CSV ", e);
        }
    }
}