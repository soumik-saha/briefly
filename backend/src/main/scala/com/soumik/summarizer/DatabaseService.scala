package com.soumik.summarizer

import java.sql.{Connection, DriverManager, PreparedStatement, ResultSet}
import scala.collection.mutable.ListBuffer
import io.github.cdimascio.dotenv.Dotenv

object DatabaseService {

  // Load environment variables
  private val dotenv = Dotenv.load()

  private val url = dotenv.get("DB_URL")
  private val username = dotenv.get("DB_USERNAME")
  private val password = dotenv.get("DB_PASSWORD")

  private def connect(): Connection = {
    DriverManager.getConnection(url, username, password)
  }

  def logRequest(url: String, summary: String): Unit = {
    val connection = connect()
    val query = "INSERT INTO request_history (url, summary) VALUES (?, ?)"
    val statement: PreparedStatement = connection.prepareStatement(query)
    statement.setString(1, url)
    statement.setString(2, summary)
    statement.executeUpdate()
    statement.close()
    connection.close()
  }

  def getRequestHistory: List[(String, String)] = {
    val connection = connect()
    val query = "SELECT url, summary FROM request_history"
    val statement: PreparedStatement = connection.prepareStatement(query)
    val resultSet: ResultSet = statement.executeQuery()

    val history = ListBuffer[(String, String)]()
    while (resultSet.next()) {
      val url = resultSet.getString("url")
      val summary = resultSet.getString("summary")
      history += ((url, summary))
    }

    resultSet.close()
    statement.close()
    connection.close()

    history.toList
  }
}