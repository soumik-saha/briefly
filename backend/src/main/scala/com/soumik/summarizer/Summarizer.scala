package com.soumik.summarizer

import org.springframework.stereotype.Component
import com.soumik.summarizer.DatabaseService

@Component
class Summarizer {

  // Summarizes content using the FastAPI LLM service
  def summarizeContent(url: String): String = {
    // Simulate calling the Python FastAPI service (HTTP call to get summary)
    val summarizedText = callPythonFastAPI(url)
    // Log the request and summary to the database
    DatabaseService.logRequest(url, summarizedText)
    summarizedText
  }

  // Dummy function simulating an API call to FastAPI
  private def callPythonFastAPI(url: String): String = {
    // In a real scenario, you'd make an HTTP call to the FastAPI service
    s"Summary of $url: This is the summarized content..."
  }
}
