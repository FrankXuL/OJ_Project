package com.example.oj_project.model;

import lombok.Data;

/**
 * @title: problem
 * @Author Xu
 * @Date: 9/11/2022 上午 9:36
 * @Version 1.0
 */
@SuppressWarnings({"all"})
@Data
public class problem {
    private int id;
    private String title;
    private String level;
    private String description;
    private String templateCode;
    private String testCode;
}