package com.questions.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.questions.dao.QuestionDao;
import com.questions.entities.Question;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @SuppressWarnings("CallToPrintStackTrace")
    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    @SuppressWarnings("CallToPrintStackTrace")
    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    @SuppressWarnings("CallToPrintStackTrace")
    public ResponseEntity<String> addQuestion(Question question) {
        Question q = new Question();
        try {
            q.setId(question.getId());
            q.setQuestionsTitle(question.getQuestionsTitle());
            q.setOptions1(question.getOptions1());
            q.setOptions2(question.getOptions2());
            q.setOptions3(question.getOptions3());
            q.setOptions4(question.getOptions4());
            q.setRightAnswer(question.getRightAnswer());
            q.setCategory(question.getCategory());
            q.setDiffcultyLevel(question.getDiffcultyLevel());
            questionDao.save(q);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Question is not Add in Database", HttpStatus.BAD_REQUEST);
    }
}
