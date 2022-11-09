package com.example.oj_project.compile;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class CommandUtilTest {

    @Test
    public void TestCommand() {
        CommandUtil.run("javac", "stdout.txt", "stderr.txt");
    }
}