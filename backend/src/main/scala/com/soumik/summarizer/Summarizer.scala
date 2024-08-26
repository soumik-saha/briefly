package com.soumik.summarizer

import org.springframework.stereotype.Component
import com.soumik.summarizer.DatabaseService
import scalaj.http.Http
import io.github.cdimascio.dotenv.Dotenv

@Component
class Summarizer {

  // Load environment variables
  private val dotenv = Dotenv.load()
  private val apiUrlBase = dotenv.get("API_URL_BASE")

  // Summarizes content using the FastAPI LLM service
  def summarizeContent(url: String): String = {
    // Simulate calling the Python FastAPI service (HTTP call to get summary)
    var summarizedText = callPythonFastAPI(url)

    // Log the request and summary to the database
    DatabaseService.logRequest(url, summarizedText)
    summarizedText
  }

  // Function to call the FastAPI service
  private def callPythonFastAPI(url: String): String = {
    val apiUrl = s"$apiUrlBase/summarize?url=$url"
    val response = Http(apiUrl).asString
    if (response.is2xx) {
      response.body
    } else {
      s"Failed to summarize $url"
    }
  }
}