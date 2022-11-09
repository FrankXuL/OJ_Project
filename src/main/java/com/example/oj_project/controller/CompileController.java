package com.example.oj_project.controller;

import com.example.oj_project.Exception.CodeInValidException;
import com.example.oj_project.Exception.ProblemNotFoundException;
import com.example.oj_project.compile.Answer;
import com.example.oj_project.compile.Question;
import com.example.oj_project.compile.Task;
import com.example.oj_project.model.problem;
import com.example.oj_project.service.ProblemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * @title: CompileController
 * @Author Xu
 * @Date: 9/11/2022 上午 10:19
 * @Version 1.0
 */
@SuppressWarnings({"all"})
@RestController
public class CompileController {
    static class CompileRequest {
        public int id;
        public String code;
    }

    static class CompileResponse {
        public int error;
        public String reason;
        public String stdout;
    }

    private static ObjectMapper objectMapper = new ObjectMapper();
    @Resource
    private ProblemService problemService;
    @Resource
    private Task task;

    /**
     * @description: 编译题解, 获得结果
     * @param: request
     * @return: java.util.HashMap<java.lang.String, java.lang.Object>
     * @author Xu
     * @date: 9/11/2022 上午 10:40
     */
    @RequestMapping("/compile")
    public HashMap<String, Object> compile(HttpServletRequest request) throws JsonProcessingException {
        CompileRequest compileRequest = null;
        CompileResponse compileResponse = new CompileResponse();
        HashMap<String, Object> result = new HashMap<>();
        CompileResponse data = null;
        String message = "未知错误";
        try {
            String body = readBody(request);
            compileRequest = objectMapper.readValue(body, CompileRequest.class);
            problem problem = problemService.SelectOne(compileRequest.id);
            if (problem == null) {
                throw new ProblemNotFoundException();
            }
            String testCode = problem.getTestCode();
            String requestCode = compileRequest.code;
            String finalCode = mergeCode(requestCode, testCode);
            if (finalCode == null) {
                throw new CodeInValidException();
            }
            Question question = new Question();
            question.setCode(finalCode);
            Answer answer = task.compileAndRun(question);
            compileResponse.error = answer.getError();
            compileResponse.reason = answer.getReason();
            compileResponse.stdout = answer.getStdout();
        } catch (ProblemNotFoundException e) {
            compileResponse.error = 3;
            compileResponse.reason = "没有找到指定的题目! id=" + compileRequest.id;
        } catch (CodeInValidException e) {
            compileResponse.error = 3;
            compileResponse.reason = "提交的代码不符合要求!";
        } finally {
            if (compileResponse != null) {
                data = compileResponse;
                message = "";
            }
            result.put("success", 200);
            result.put("data", data);
            result.put("message", message);
            return result;
        }
    }

    private static String mergeCode(String requestCode, String testCode) {
        int pos = requestCode.lastIndexOf("}");
        if (pos == -1) {
            return null;
        }
        String subStr = requestCode.substring(0, pos);
        return subStr + testCode + "\n}";
    }

    private static String readBody(HttpServletRequest req) {
        int contentLength = req.getContentLength();
        byte[] bytes = new byte[contentLength];
        try (InputStream inputStream = req.getInputStream()) {
            inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }
}