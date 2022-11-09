package com.example.oj_project.controller;

import com.example.oj_project.model.problem;
import com.example.oj_project.service.ProblemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @title: ProblemController
 * @Author Xu
 * @Date: 9/11/2022 上午 10:18
 * @Version 1.0
 */
@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Resource
    private ProblemService problemService;
    /**
     * @description: 查询所有题目
     * @return: java.util.HashMap<java.lang.String,java.lang.Object>
     * @author Xu
     * @date: 9/11/2022 上午 10:24
     */
    @RequestMapping("/selectall")
    public HashMap<String, Object> SelectAll() {
        HashMap<String, Object> result = new HashMap<>();
        List<problem> data = null;
        String message = "未知错误";
        data = problemService.SelectAll();
        if (data != null) {
            message = "";
        }
        result.put("success", 200);
        result.put("data", data);
        result.put("message", message);
        return result;
    }
    /** 
     * @description: 查询题目详情
     * @param: id 
     * @return: java.util.HashMap<java.lang.String,java.lang.Object> 
     * @author Xu
     * @date: 9/11/2022 上午 10:26
     */ 
    @RequestMapping("/selectone")
    public HashMap<String,Object> SelectOne(Integer id){
        HashMap<String, Object> result = new HashMap<>();
        problem data = null;
        String message = "未知错误";
        problem problem = problemService.SelectOne(id);
        if (problem != null) {
            data = problem;
            message = "";
        }
        result.put("success", 200);
        result.put("data", data);
        result.put("message", message);
        return result;
    }

}