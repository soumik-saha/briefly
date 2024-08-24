package com.soumik.backend.bean;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SummaryRequest {
    private String url;

    public SummaryRequest() {
    }

    public SummaryRequest(String url) {
        this.url = url;
    }

}
