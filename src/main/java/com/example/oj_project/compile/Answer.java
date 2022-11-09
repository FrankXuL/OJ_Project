package com.example.oj_project.compile;

import lombok.Data;

/**
 * @title: Answer
 * @Author Xu
 * @Date: 09/11/2022 上午 11:23
 * @Version 1.0
 */
@Data
public class Answer {
    //错误码
    private int error;
    //错误信息
    private String reason;
    //标准输出结果
    private String stdout;
    //标准错误结果
    private String stderr;

}