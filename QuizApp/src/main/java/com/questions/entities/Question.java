package com.questions.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;
    private  String questionsTitle;
    private  String options1;
    private  String options2;
    private  String options3;
    private  String options4;
    private  String rightAnswer;
    private  String diffcultyLevel;
    private  String category;

}
