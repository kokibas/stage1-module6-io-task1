package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;

public class FileReader {
    public static Profile getDataFromFile(File file) {
        String name = null;
        int age = 0;
        String email = null;
        Long phone = null;
        try {
            java.io.FileReader fileReader = new java.io.FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
            }
            String fileContent = stringBuilder.toString();

            String[] lines = fileContent.split(System.lineSeparator());

            for (String l : lines) {
                String[] parts = l.split(":");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    switch (key) {
                        case "Name":
                            name = value;
                            break;
                        case "Age":
                            age = Integer.parseInt(value);
                            break;
                        case "Email":
                            email = value;
                            break;
                        case "Phone":
                            phone = Long.parseLong(value);
                            break;
                    }
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Profile(name, age, email, phone);
    }
}