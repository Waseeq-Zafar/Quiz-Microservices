package com.waseeq.quiz_service.feign;


import com.waseeq.quiz_service.model.QuestionWraper;
import com.waseeq.quiz_service.model.Responses;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

    // generate

    @GetMapping("question/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
            (@RequestParam String categoryName, @RequestParam Integer numQuestions);

    // getQuestions (questionID)

    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionWraper>> getQuestionsFromId(@RequestBody List<Integer> questionsId);

    //getScore

    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Responses> responses);

}
