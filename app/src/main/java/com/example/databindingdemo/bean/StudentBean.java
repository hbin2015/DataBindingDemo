package com.example.databindingdemo.bean;

/**
 * 学生信息
 * Created by HuangBin on 2018/5/4.
 */
public class StudentBean {

    private String name;

    public StudentBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "StudentBean{" +
                "name='" + name + "'}";
    }

}
