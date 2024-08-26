import React, { useState, useEffect } from "react";
import axios from "axios";

const History = () => {
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
      <h1>History</h1>
      <p>View your past summaries here.</p>
      <ul>
        {history.map((entry, index) => (
          <li key={index}>
            {entry.url}: {entry.summary}
          </li>
        ))}
      </ul>
    </>
  );
};

export default History;
