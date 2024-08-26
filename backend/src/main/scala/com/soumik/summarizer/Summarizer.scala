package com.soumik.summarizer

import org.springframework.stereotype.Component
import com.soumik.summarizer.DatabaseService
import scalaj.http.Http

@Component
class Summarizer {

  // Summarizes content using the FastAPI LLM service
  def summarizeContent(url: String): String = {
    // Simulate calling the Python FastAPI service (HTTP call to get summary)
    var summarizedText = callPythonFastAPI(url)

    // Remove the leading and trailing quotes from the response
    summarizedText = summarizedText.substring(1, summarizedText.length - 1)

    // Log the request and summary to the database
    DatabaseService.logRequest(url, summarizedText)
    summarizedText
  }

  // Dummy function simulating an API call to FastAPI
  private def callPythonFastAPI(url: String): String = {
    val apiUrl = s"http://localhost:8000/summarize?url=$url"
    val response = Http(apiUrl).asString
    if (response.is2xx) {
      response.body
    } else {
      s"Failed to summarize $url"
    }
  }
}
