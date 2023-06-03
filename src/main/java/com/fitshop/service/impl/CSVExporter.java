package com.fitshop.service.impl;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fitshop.service.Exporter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStreamWriter;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CSVExporter implements Exporter {

    @Override
    public StreamingResponseBody export(List<?> data, Class<?> targetType, String fileName, HttpServletResponse response) {
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s.csv\"", fileName));

        CsvMapper csvMapper = new CsvMapper();
        csvMapper.registerModule(new JavaTimeModule());
        return outputStream -> {
            CsvSchema schema = csvMapper.schemaFor(targetType).withHeader();
            csvMapper.writer(schema).writeValues(new OutputStreamWriter(outputStream)).writeAll(data);
        };
    }

}
