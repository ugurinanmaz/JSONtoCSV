package resources;
import java.io.*;
import java.util.*;

public class CSVFileWriter {

    public static void main(String[] args) {
        String inputFile = "input.csv"; // girdi dosyası adı
        String outputFile = "output.csv"; // çıktı dosyası adı
        String line = "";
        String csvSplitBy = ",";
        int rowNum = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {

            while ((line = br.readLine()) != null) {
                String[] values = line.split(csvSplitBy);
                if (rowNum == 0) {
                    // Başlık satırını doğrudan yazdır
                    bw.write(line + ",aciklama\n");
                } else if (rowNum == 1) {
                    // İkinci satırı doğrudan yazdır
                    bw.write(line + ",aciklama\n");
                } else {
                    // Rastgele sayı üretmek için Java Random sınıfını kullanın
                    Random rand = new Random();

                    // İlk sütunu yazdırın
                    bw.write(values[0] + ",");

                    // İkinci sütunu işleyin
                    String col2 = values[1];
                    int num = 0;
                    String aciklama = "";

                    if (isNumeric(col2)) {
                        num = Integer.parseInt(col2);
                        aciklama = "Integer değer okundu";
                    } else {
                        num = rand.nextInt(10001);
                        aciklama = "String değer random olarak değiştirildi";
                    }

                    bw.write(num + ",");

                    // Diğer sütunları yazdırın ve açıklamaları ekleyin
                    for (int i = 2; i < values.length; i++) {
                        bw.write(values[i] + ",");
                    }
                    bw.write(aciklama + "\n");
                }

                rowNum++;
            }

            System.out.println("Yeni CSV dosyası başarıyla oluşturuldu!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Bir dize sayısal bir değer içeriyorsa true değerini döndürün
    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
