package com.waseeq.quiz_service.service;


import com.waseeq.quiz_service.feign.QuizInterface;
import com.waseeq.quiz_service.model.QuestionWraper;
import com.waseeq.quiz_service.model.Quiz;
import com.waseeq.quiz_service.model.Responses;
import com.waseeq.quiz_service.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    QuizInterface quizInterface;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Integer> quesitionId = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(quesitionId);
        quizRepository.save(quiz);
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionWraper>> getQuizQuestions(Integer id) {
        List<QuestionWraper> questionsForUser = new ArrayList<>();
        Quiz quiz = quizRepository.findById(id).get();
        questionsForUser = quizInterface.getQuestionsFromId(quiz.getQuestionIds()).getBody();
        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }


    public ResponseEntity<Integer> calculateResult(Integer id, List<Responses> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }
}
