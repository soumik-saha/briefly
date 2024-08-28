import React, { useState } from "react";
import axios from "axios";
import "./css/styles.css";

const Summarize = ({ toggleView }) => {
  const [url, setUrl] = useState("");
  const [summary, setSummary] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      const response = await axios.post(
        `${process.env.REACT_APP_BACKEND_API_URL}/summarize`,
        { url },
        {
          auth: {
            username: process.env.REACT_APP_USERNAME,
            password: process.env.REACT_APP_PASSWORD,
          },
        }
      );
      setSummary(response.data.summary);
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="home-container">
      <button onClick={toggleView} className="toggle-button">
        View History
      </button>
      <h1>Briefly</h1>
      <p>Summarize any websites with Briefly.</p>

      <form className="search-form" onSubmit={handleSubmit}>
        <input
          type="text"
          value={url}
          onChange={(e) => setUrl(e.target.value)}
          placeholder="Enter website URL"
          required
        />
        <button type="submit">Summarize</button>
      </form>

      {loading && <p>Loading...</p>}

      {summary && (
        <div className="summary-box">
          <p>{summary}</p>
        </div>
      )}
    </div>
  );
};

export default Summarize;
