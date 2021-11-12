package com.example.springmvc.basic;

import lombok.Data;

/**
 * 요청받을 파라미터 바인딩 받을 객체
 */
@Data //Getter Setter ToString 등등을 포함함
public class HelloData {
    private String username;
    private int age;
}
