import React, { useState } from "react";
import axios from "axios";

const Home = () => {
  const [url, setUrl] = useState("");
  const [summary, setSummary] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:5000/summarize", {
        url,
      });
      setSummary(response.data.summary);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <>
      <h1>Briefly</h1>
      <p>Summarize any websites with Briefly.</p>

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          value={url}
          onChange={(e) => setUrl(e.target.url)}
          placeholder="Enter website URL"
          required
        />
        <button type="submit">Summarize</button>
      </form>
      {summary && <p>Summary: {summary}</p>}
    </>
  );
};

export default Home;
