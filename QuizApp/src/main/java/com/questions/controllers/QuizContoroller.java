package com.questions.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.questions.entities.QuestionsWrapper;
import com.questions.entities.Response;
import com.questions.services.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizContoroller {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){

       return quizService.createQuiz(category,numQ,title);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionsWrapper>> getQuizQuestions(@PathVariable Long id){
       return quizService.getQuizQuestions(id);
    }


    @PostMapping("/submit/{id}")
    public  ResponseEntity<Integer> submitQuiz(@PathVariable Long id, @RequestBody List<Response> responses){
      return quizService.calculateResult(id, responses);
    }

}
