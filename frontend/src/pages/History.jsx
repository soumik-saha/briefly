import React, { useState, useEffect } from "react";

const History = () => {
  const [history, setHistory] = useState([]);

  useEffect(() => {
    const fetchHistory = async () => {
      try {
        const response = await fetch("http://localhost:5000/history");
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
