package resources;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

public class JsonToCsvConverter {
    public static void main(String[] args) throws IOException, CsvException {

        // Convert JSON to CSV
        File jsonFile = new File("src/main/java/datasoruce/jsondata.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonFile);

        List<String> columnNames = new ArrayList<>();
        extractColumnNames(rootNode, "", columnNames);

        File csvFile = new File("src/main/java/datasoruce/jsondata.csv");
        FileWriter fileWriter = new FileWriter(csvFile);
        CSVWriter csvWriter = new CSVWriter(fileWriter);
        csvWriter.writeNext(columnNames.toArray(new String[0]));

        List<String> dataRow = new ArrayList<>();
        extractData(rootNode, dataRow);
        csvWriter.writeNext(dataRow.toArray(new String[0]));

        csvWriter.close();
        fileWriter.close();

        // Modify CSV file to add a new row with random value
        CSVReader reader = new CSVReaderBuilder(new FileReader("src/main/java/datasoruce/jsondata.csv")).withSkipLines(1).build();
        List<String[]> csvData = null;
        try {
            csvData = reader.readAll();
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
        reader.close();

        String[] newRow = new String[csvData.get(0).length];
        newRow[0] = String.valueOf((int) (Math.random() * 100)); // Set random value in first column

        fileWriter = new FileWriter("src/main/java/datasoruce/jsandata_testcases.csv");
        csvWriter = new CSVWriter(fileWriter);

        csvWriter.writeNext(csvData.get(0)); // Write header row
        csvWriter.writeNext(newRow); // Write new row

        for (int i = 1; i < csvData.size(); i++) {
            csvWriter.writeNext(csvData.get(i)); // Write remaining data rows
        }

        csvWriter.close();
        fileWriter.close();

        modifyCsv();

    }

    private static void modifyCsv() {
    }

    private static void extractColumnNames(JsonNode node, String prefix, List<String> columnNames) {
        if (node.isObject()) {
            Iterator<String> fieldNames = node.fieldNames();
            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                JsonNode childNode = node.get(fieldName);
                extractColumnNames(childNode, prefix + fieldName + ".", columnNames);
            }
        } else if (node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                JsonNode childNode = node.get(i);
                extractColumnNames(childNode, prefix + i + ".", columnNames);
            }
        } else {
            columnNames.add(prefix.substring(0, prefix.length() - 1));
        }
    }

    private static void extractData(JsonNode node, List<String> dataRow) {
        if (node.isObject()) {
            Iterator<String> fieldNames = node.fieldNames();
            while (fieldNames.hasNext()) {
                String fieldName = fieldNames.next();
                JsonNode childNode = node.get(fieldName);
                extractData(childNode, dataRow);
            }
        } else if (node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                JsonNode childNode = node.get(i);
                extractData(childNode, dataRow);
            }
        } else {
            dataRow.add(node.asText());
        }
    }
}
