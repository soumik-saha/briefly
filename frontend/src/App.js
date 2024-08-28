import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import History from './pages/History.jsx';
import Summarize from './pages/Summarize.jsx';

function App() {
  const [view, setView] = useState('summarize');

  const toggleView = () => {
    setView(view === 'summarize' ? 'history' : 'summarize');
  };

  return (
    <Router>
      <Routes>
        <Route path="/" element={view === 'summarize' ? <Summarize toggleView={toggleView} /> : <History toggleView={toggleView} />} />
      </Routes>
    </Router>
  );
}

export default App;