/**
 * bianque.com
 * Copyright (C) 2013-2021 All Rights Reserved.
 */
package com.redis.example.demo.hessian;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import lombok.Data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author xuleyan
 * @version Student.java, v 0.1 2021-07-05 10:40 上午
 */
@Data
public class Student implements Serializable {
    private String name;
    public static String hobby = "eat";
    transient private String address;

    public static void main(String[] args) throws IOException {
        Student stu = new Student();
        stu.setAddress("屋子科");
        stu.setName("ymz");

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        Hessian2Output output = new Hessian2Output(os);
        output.writeObject(stu);
        output.close();

        Student.hobby = "drink";

        ByteArrayInputStream bis = new ByteArrayInputStream(os.toByteArray());
        Hessian2Input input = new Hessian2Input(bis);
        Student student = (Student) input.readObject();
        System.out.println(student.getAddress());
        System.out.println(student.getName());
        System.out.println(Student.hobby);
    }
}