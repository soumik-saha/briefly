package com.soumik.summarizer

import org.springframework.stereotype.Component
import com.soumik.summarizer.DatabaseService
import scalaj.http.Http
import io.github.cdimascio.dotenv.Dotenv
import scala.collection.mutable
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scalaj.http.HttpOptions

@Component
class Summarizer {

  // Load environment variables
  private val dotenv = Dotenv.load()
  private val apiUrlBase = dotenv.get("API_URL_BASE")

  object Cache {
    private val cache = mutable.Map[String, String]()

    def get(url: String): Option[String] = cache.get(url)

    def put(url: String, summary: String): Unit = {
      cache.put(url, summary)
    }
  }

  def summarizeContent(url: String): String = {
    Cache.get(url) match {
      case Some(summary) => summary
      case None =>
        val summarizedText = callPythonFastAPI(url)
        Cache.put(url, summarizedText)
        DatabaseService.logRequest(url, summarizedText)
        summarizedText
    }
  }

  // Function to call the FastAPI service
  private def callPythonFastAPI(url: String): String = {
    val apiUrl = s"$apiUrlBase?url=$url"
    val response = Http(apiUrl)
      .option(HttpOptions.connTimeout(10000)) // 10 seconds connection timeout
      .option(HttpOptions.readTimeout(30000)) // 30 seconds read timeout
      .asString

    if (response.is2xx) {
      response.body
    } else {
      s"Failed to summarize $url"
    }
  }
}