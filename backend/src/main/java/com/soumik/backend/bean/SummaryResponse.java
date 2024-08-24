package com.soumik.backend.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SummaryResponse {
    private String summary;

    public SummaryResponse() {
    }

    public SummaryResponse(String summary) {
        this.summary = summary;
    }

}
