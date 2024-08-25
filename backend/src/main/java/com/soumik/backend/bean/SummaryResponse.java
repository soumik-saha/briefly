package com.soumik.backend.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SummaryResponse {
    private String url;
    private String summary;

    public SummaryResponse() {
    }

    public SummaryResponse(String url, String summary) {
        this.url = url;
        this.summary = summary;
    }
}