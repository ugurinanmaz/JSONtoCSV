package resources;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;

public class JsonToCsvConverter {
    public static void main(String[] args) throws IOException {
        // Load JSON data from file
        File jsonFile = new File("src/main/java/datasoruce/jsondata.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonFile);

        // Extract column names from JSON data
        List<String> columnNames = new ArrayList<>();
        extractColumnNames(rootNode, "", columnNames);

        // Create CSV writer and write header row
        File csvFile = new File("src/main/java/datasoruce/jsondata.csv");
        FileWriter fileWriter = new FileWriter(csvFile);
        CSVWriter csvWriter = new CSVWriter(fileWriter);
        csvWriter.writeNext(columnNames.toArray(new String[0]));

        // Write data rows
        List<String> dataRow = new ArrayList<>();
        extractData(rootNode, dataRow);
        csvWriter.writeNext(dataRow.toArray(new String[0]));

        // Close CSV writer and file writer
        csvWriter.close();
        fileWriter.close();
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
