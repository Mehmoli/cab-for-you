package com.novi.cabforyou.controllers;

import com.novi.cabforyou.dtos.FeedbackDto;
import com.novi.cabforyou.services.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("")
    public ResponseEntity<List<FeedbackDto>> getAllFeedbacks() {
        List<FeedbackDto> dtos = feedbackService.getAllFeedbacks();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackDto> getFeedback(@PathVariable("id") Long id) {

        FeedbackDto dto = feedbackService.getFeedback(id);

        return ResponseEntity.ok(dto);
    }

    @PostMapping("")
    public ResponseEntity<FeedbackDto> addFeedback(@RequestBody FeedbackDto dto) {
        FeedbackDto dto1 = feedbackService.addFeedback(dto);
        return ResponseEntity.created(null).body(dto1);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<FeedbackDto> updateFeedback(@PathVariable("id") long id, @RequestBody FeedbackDto dto) {

        feedbackService.updateFeedback(id, dto);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteFeedback(@PathVariable("id") long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
}
