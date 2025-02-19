package com.waseeq.question_service.service;


import com.waseeq.question_service.model.Question;
import com.waseeq.question_service.model.QuestionWraper;
import com.waseeq.question_service.model.Responses;
import com.waseeq.question_service.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public ResponseEntity<List<Question>> getAllQuestion() {
        try{
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try{
            return new ResponseEntity<>(questionRepository.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionRepository.save(question);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questions = questionRepository.findRandomQuestionCategory(categoryName, numQuestions);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWraper>> getQuestionsFromId(List<Integer> questionsId) {
        List<QuestionWraper> wrapper = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        for(Integer i: questionsId) {
            questions.add(questionRepository.findById(i).get());
        }
        for(Question q: questions) {
            QuestionWraper wq = new QuestionWraper(q.getId(),q.getQuestionTitle(),q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            wrapper.add(wq);
        }
        return new ResponseEntity<>(wrapper,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Responses> responses) {
        int score = 0;
        for(Responses r: responses) {
            Question question = questionRepository.findById(r.getId()).get();
            if(r.getResponse().equals(question.getRightAnswer())) {
                score++;
            }
        }
        return new ResponseEntity<>(score,HttpStatus.OK);
    }
}
