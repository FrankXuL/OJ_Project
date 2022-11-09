package com.example.oj_project.mapper;

import com.example.oj_project.model.problem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @title: ProblemMapper
 * @Author Xu
 * @Date: 9/11/2022 上午 9:36
 * @Version 1.0
 */
@Mapper
public interface ProblemMapper {
    List<problem> SelectAll();
    problem SelectOne(@Param("id") Integer id);
    Integer delete (@Param("id") Integer id);
    Integer insert(problem problem);
}