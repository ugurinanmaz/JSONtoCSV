package resources;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CsvReader {
    public static void main(String[] args) throws IOException, CsvException {
        // CSV dosyasını oku
        CSVReader reader = new CSVReader(new FileReader("src/main/java/datasoruce/jsondata.csv"));
        List<String[]> rows = reader.readAll();

        // Header ve değerleri ayır
        String[] headers = rows.get(0);
        String[] values = rows.get(1);

        // Yeni CSV dosyasını oluştur
        CSVWriter writer = new CSVWriter(new FileWriter("src/main/java/datasoruce/jsondata_output.csv"));

        // Yeni kolon için başlığı ekle
        String[] newHeaders = new String[headers.length + 1];
        for (int i = 0; i < headers.length; i++) {
            newHeaders[i] = headers[i];
        }
        newHeaders[headers.length] = "description";


        writer.writeNext(newHeaders);
        writer.writeNext(values);
        rows.get(1)[headers.length] = "positive";


        int numCols = newHeaders.length;
        int numRows = values.length / numCols;
        Random rand = new Random();

        String[] valueTypes = new String[numCols];
        for (int i = 0; i < numCols; i++) {
            if (isInteger(rows.get(1)[i])) {
                valueTypes[i] = "integer";
            } else {
                valueTypes[i] = "string";
            }
        }

        // Satırları yazdır
        for (int i = 0; i < numCols; i++) {
            String[] outputRow = new String[numCols];
            outputRow[numCols] = i == 0 ? "positive" : "";
            for (int j = 0; j < numRows; j++) {
                int valueIndex = i + j * numCols;
                if (valueTypes[i].equals("integer")) {
                    for (int z = 0; z < numCols; z++) {
                        outputRow[i] = getRandomString(10);
                        outputRow[z] = rows.get(1)[z];
                        outputRow[numCols] = i == 0 ? "positive" :  rows.get(0)[z] + ">>" + outputRow[i];
                    }

                } else {
                    for (int k = 0 ; k <numCols ; k++) {
                        outputRow[i] = String.valueOf((int)(Math.random() * 1000000));
                        outputRow[k] = rows.get(1)[k];
                        outputRow[numCols] = i == 0 ? "positive" :  rows.get(0)[k] + ">>" + outputRow[i];
                    }
                }
            }
            writer.writeNext(outputRow);
        }


        // Dosyaları kapat
        reader.close();
        writer.close();
    }
    // Bir string'in integer olup olmadığını kontrol et
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static String getRandomString(int length) {
        String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(rand.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

}
