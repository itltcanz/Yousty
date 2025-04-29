package com.daila.yousty.modules;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class PythonModule {
    public List<String> getClothingImages(String path) {
        List<String> imageList = new ArrayList<>();
        String projectPath = System.getProperty("user.dir");
        String pythonScriptPath = projectPath + "/python/start.py";
        String imagePath = projectPath + path;

        ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath, imagePath);

        try {
            Process process = processBuilder.start();

            // Получение вывода из процесса Python
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                imageList.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imageList;
    }
}
