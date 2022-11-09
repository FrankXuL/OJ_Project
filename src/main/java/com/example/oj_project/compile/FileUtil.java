package com.example.oj_project.compile;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @title: FileUtil
 * @Author Xu
 * @Date: 09/11/2022 上午 11:06
 * @Version 1.0
 */
@SuppressWarnings({"all"})
public class  FileUtil {

    public static String readFile(String filePath) {
        StringBuilder result = new StringBuilder();
        try (FileReader fileReader = new FileReader(filePath)) {
            while (true) {
                int ch = fileReader.read();
                if (ch == -1) {
                    break;
                }
                result.append((char) ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }

    public static void writeFile(String filePath, String content) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}