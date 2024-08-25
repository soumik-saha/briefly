package com.soumik.backend.service;

import com.soumik.backend.bean.SummaryResponse;
import com.soumik.summarizer.DatabaseService;
import com.soumik.summarizer.Summarizer;
import org.springframework.stereotype.Service;
import scala.collection.JavaConverters;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SummarizerService {

    private final Summarizer summarizer = new Summarizer();

    public String getSummary(String url) {
        return summarizer.summarizeContent(url);
    }

    public List<SummaryResponse> getHistory() {
        return JavaConverters.asJavaCollection(DatabaseService.getRequestHistory()).stream()
                .map(record -> new SummaryResponse(record._1, record._2))
                .collect(Collectors.toList());
    }
}