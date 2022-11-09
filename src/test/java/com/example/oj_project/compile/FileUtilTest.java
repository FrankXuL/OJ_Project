package com.example.oj_project.compile;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class FileUtilTest {
    @Test
    public void TestFileUtil() {
        FileUtil.writeFile("D:/test1.txt", FileUtil.readFile("D:/test.txt"));
    }
}