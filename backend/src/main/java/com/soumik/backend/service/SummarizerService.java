package com.soumik.backend.service;

import com.soumik.backend.bean.SummaryResponse;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SummarizerService {
    public String getSummary(String url) {
        // TODO: Call the Scala service here
        return "This is a summary";
    }

    public List<SummaryResponse> getHistory() {
        // TODO: Fetch history from DB
        return new ArrayList<SummaryResponse>();
    }
}
