package com.example.neolabs.service.impl;

import com.example.neolabs.entity.Application;
import com.example.neolabs.entity.User;
import com.example.neolabs.repository.ApplicationRepository;
import com.example.neolabs.repository.UserRepository;
import com.example.neolabs.service.CsvExportService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.*;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

import static org.apache.logging.log4j.LogManager.getLogger;


@Service
@RequiredArgsConstructor
public class CsvExportServiceImpl implements CsvExportService {

    private static final Logger log = getLogger(CsvExportServiceImpl.class);
    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;

    @Override
    public void writeUsersToCsv(Writer writer) {
        List<User> users = userRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("ID", "First Name" ,"Email", "Phone Number");
            for (User user : users) {
                csvPrinter.printRecord(user.getId(), user.getFirstName(), user.getEmail(), user.getPhoneNumber());
            }
        } catch (IOException e) {
            log.error("Error While writing CSV ", e);
        }
    }

    @Override
    public void writeApplicationsToCsv(Writer writer) {
        List<Application> applications = applicationRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("ID", "First name", "Last name", "Email", "Phone number", "Education",
                    "Has laptop?", "Marketing strategy", "Reason of applying", "Course", "Application status",
                    "Appl. status last update date", "Is archived?");
            for (Application app : applications) {
                csvPrinter.printRecord(
                        app.getId(),
                        app.getFirstName(),
                        app.getLastName(),
                        app.getEmail(),
                        app.getPhoneNumber(),
                        app.getEducation(),
                        app.getHasLaptop() ? "YES" : "NO",
                        app.getMarketingStrategy(),
                        app.getReason(),
                        app.getCourse().getName(),
                        app.getApplicationStatus(),
                        app.getApplicationStatusUpdateDate(),
                        app.getIsArchived() ? "YES" : "NO");
            }
        } catch (IOException e) {
            log.error("Error while writing CSV ", e);
        }
    }

    @Override
    public void writeStudentsToCsv(Writer writer) {

    }
}