package com.example.springstudy.entity;

import com.example.springstudy.entity.enums.Grade;

import java.util.ArrayList;
import java.util.List;

public class member {
    private Long id;
    private String name;
    private Grade grade;

    public member(Long id, String name, Grade grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Grade getGrade() {
        return grade;
    }
}

}
