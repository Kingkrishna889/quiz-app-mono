package com.questions.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionsWrapper {

    
    private  Long id;
    private  String questionsTitle;
    private  String options1;
    private  String options2;
    private  String options3;
    private  String options4;
}
