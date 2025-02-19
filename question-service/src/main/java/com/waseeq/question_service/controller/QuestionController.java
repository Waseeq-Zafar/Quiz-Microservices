package com.waseeq.question_service.controller;

import com.waseeq.question_service.model.Question;
import com.waseeq.question_service.model.QuestionWraper;
import com.waseeq.question_service.model.Responses;
import com.waseeq.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestion();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category) {
        return questionService.getQuestionByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    // generate

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numQuestions) {
        return questionService.getQuestionsForQuiz(categoryName,numQuestions);
    }

    // getQuestions (questionID)

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWraper>> getQuestionsFromId(@RequestBody List<Integer> questionsId) {
        return questionService.getQuestionsFromId(questionsId);
    }

    //getScore

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Responses> responses) {
        return questionService.getScore(responses);
    }

}
