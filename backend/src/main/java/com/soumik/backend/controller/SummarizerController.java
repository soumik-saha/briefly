package com.soumik.backend.controller;

import com.soumik.backend.bean.SummaryRequest;
import com.soumik.backend.bean.SummaryResponse;
import com.soumik.backend.service.SummarizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SummarizerController {

    @Autowired
    private SummarizerService summarizerService;

    @PostMapping("/summarize")
    public ResponseEntity<SummaryResponse>  summariizeURL(@RequestBody SummaryRequest request) {
        String summary = summarizerService.getSummary(request.getUrl());
        return ResponseEntity.ok(new SummaryResponse(summary));
    }

    @GetMapping("/history")
    public ResponseEntity<List<SummaryResponse>> getHistory() {
        List<SummaryResponse> history = summarizerService.getHistory();
        return ResponseEntity.ok(history);
    }
}
