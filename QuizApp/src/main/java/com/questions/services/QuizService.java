package com.questions.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.questions.dao.QuestionDao;
import com.questions.dao.QuizDao;
import com.questions.entities.Question;
import com.questions.entities.QuestionsWrapper;
import com.questions.entities.Quiz;
import com.questions.entities.Response;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        
        List<Question> questions = questionDao.findRandomQuestionByCategory(category,numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestion(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Sucessfully Quiz is Created", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionsWrapper>> getQuizQuestions(Long id) {
    
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDb = quiz.get().getQuestion();
        List<QuestionsWrapper> questionForUser = new ArrayList<>();

        for(Question q : questionsFromDb){
            QuestionsWrapper qw = new QuestionsWrapper();
            qw.setId(q.getId());
            qw.setQuestionsTitle(q.getQuestionsTitle());
            qw.setOptions1(q.getOptions1());
            qw.setOptions2(q.getOptions2());
            qw.setOptions3(q.getOptions3());
            qw.setOptions4(q.getOptions4());
            
            questionForUser.add(qw);
        }

        return new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Long id, List<Response> responses) {

        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questions = quiz.get().getQuestion();
        
        int right = 0;
        int i = 0;
        for(Response response : responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }

        return new  ResponseEntity<>(right,HttpStatus.OK);

    }

}
