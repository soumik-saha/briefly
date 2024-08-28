import React, { useState, useEffect } from "react";
import axios from "axios";
import "./css/styles.css";

const History = ({ toggleView }) => {
  const [history, setHistory] = useState([]);

  useEffect(() => {
    const fetchHistory = async () => {
      try {
        const response = await axios.get(
          `${process.env.REACT_APP_BACKEND_API_URL}/history`,
          {
            auth: {
              username: process.env.REACT_APP_USERNAME,
              password: process.env.REACT_APP_PASSWORD,
            },
          }
        );
        setHistory(response.data);
      } catch (error) {
        console.error(error);
      }
    };
    fetchHistory();
  }, []);

  return (
    <>
      <button onClick={toggleView} className="toggle-button">
        Summarize Website
      </button>
      <h1>History</h1>
      <p>View your past summaries here.</p>
      <div className="history">
        <ul>
          {history.map((entry, index) => (
            <li key={index} className="entry">
              <div className="url">{entry.url}</div>
              <div className="summary">{entry.summary}</div>
            </li>
          ))}
        </ul>
      </div>
    </>
  );
};

export default History;
