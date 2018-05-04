package com.example.databindingdemo.bean;

/**
 * 教师信息
 * Created by HuangBin on 2018/5/4.
 */
public class TeacherBean {

    private String name;
    private String age;

    public TeacherBean(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TeacherBean{" +
                "name='" + name +
                "', age='" + age +
                "'}";
    }

}
