package com.example.oj_project.service;

import com.example.oj_project.mapper.ProblemMapper;
import com.example.oj_project.model.problem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @title: ProblemService
 * @Author Xu
 * @Date: 9/11/2022 上午 10:05
 * @Version 1.0
 */
@SuppressWarnings({"all"})
@Service
public class ProblemService {
    @Resource
    private ProblemMapper problemMapper;

    public List<problem> SelectAll() {
        return problemMapper.SelectAll();
    }

    public problem SelectOne(@Param("id") Integer id) {
        return problemMapper.SelectOne(id);
    }

    public Integer delete(@Param("id") Integer id) {
        return problemMapper.delete(id);
    }

    public Integer insert(problem problem) {
        return problemMapper.insert(problem);
    }
}