package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity // DB 가 해당 객체를 인식 가능
@AllArgsConstructor
@NoArgsConstructor  // 디폴트 생성자 추가 annotation
@ToString
@Getter
public class Article {

    @Id //대표값을 지정, 주민등록번호 같은 것
    @GeneratedValue // 1,2,3.. 자동 생성 annotation
    private Long id;

    @Column // DB에서 관리하는 table과 연결해주는 단위위
    private String title;

    @Column
    private String content;
}
