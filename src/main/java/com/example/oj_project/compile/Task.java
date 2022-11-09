package com.example.oj_project.compile;


import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @title: Task
 * @Author Xu
 * @Date: 09/11/2022 上午 11:19
 * @Version 1.0
 */
@SuppressWarnings({"all"})
@Component
public class Task {
    //目录
    private  String WORK_DIR = null;
    //编译代码的类名
    private  String CLASS = null;
    //存放编译代码的文件名
    private  String CODE = null;
    //存放编译错误信息的文文件名
    private  String COMPILE_ERROR = null;
    //存放标准输出的文件名
    private  String STDOUT = null;
    //存放运行时标准错误的文件名
    private  String STDERR = null;

    public Task() {
        // 在 Java 中使用 UUID 这个类就能生成一个 UUID 了
        WORK_DIR = "./tmp/" + UUID.randomUUID().toString() + "/";
        CLASS = "Solution";
        CODE = WORK_DIR + "Solution.java";
        COMPILE_ERROR = WORK_DIR + "compileError.txt";
        STDOUT = WORK_DIR + "stdout.txt";
        STDERR = WORK_DIR + "stderr.txt";
    }
    public Answer compileAndRun(Question question) {
        Answer answer = new Answer();
        File workDir = new File(WORK_DIR);
        if (!workDir.exists()) {
            workDir.mkdirs();
        }
        if (!checkCodeSafe(question.getCode())) {
            System.out.println("用户提交了不安全的代码!");
            answer.setError(3);
            answer.setReason("您提交的代码可能会危害到服务器, 禁止运行!");
            return answer;
        }
        FileUtil.writeFile(CODE, question.getCode());
        //编译
        String compileCmd = String.format("javac -encoding utf8 %s -d %s", CODE, WORK_DIR);
        System.out.println("编译命令: " + compileCmd);
        CommandUtil.run(compileCmd, null, COMPILE_ERROR);
        //编译出错
        String compileError = FileUtil.readFile(COMPILE_ERROR);
        if (!compileError.equals("")) {
            System.out.println("编译出错");
            answer.setError(1);
            answer.setReason(compileError);
            return answer;
        }
        //运行
        String runCmd = String.format("java -classpath %s %s", WORK_DIR, CLASS);
        System.out.println("运行命令: " + runCmd);
        CommandUtil.run(runCmd,STDOUT,STDERR);
        //运行出错
        String runError = FileUtil.readFile(STDERR);
        if(!runError.equals("")){
            System.out.println("运行出错!");
            answer.setError(2);
            answer.setReason(runError);
            return answer;
        }
        answer.setError(0);
        answer.setStdout(FileUtil.readFile(STDOUT));
        return answer;
    }

    private boolean checkCodeSafe(String code) {
        List<String> blackList = new ArrayList<>();
        blackList.add("Runtime");
        blackList.add("exec");
        blackList.add("java.io");
        blackList.add("java.net");
        for (String target : blackList) {
            int pos = code.indexOf(target);
            if (pos >= 0) {
                return false;
            }
        }
        return true;
    }

}