package com.example.neolabs.service;

import java.io.Writer;

public interface CsvExportService {

    void writeUsersToCsv(Writer writer);
}
