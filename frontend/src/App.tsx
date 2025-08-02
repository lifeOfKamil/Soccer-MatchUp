import React from "react";
import { Routes, Route } from "react-router-dom";
import Home from "./pages/Home/Home";
import Dashboard from "./pages/Dashboard/Dashboard";
import MatchDetails from "./pages/MatchDetails/MatchDetails";
import { NotFoundPage } from "./pages/NotFoundPage";
import "./App.css";

function App() {
	return (
		<div className="App">
			<main>
				<Routes>
					<Route path="/" element={<Home />} />
					<Route path="/dashboard" element={<Dashboard />} />
					<Route path="/matches/:matchId" element={<MatchDetails />} />
					<Route path="*" element={<NotFoundPage />} />
				</Routes>
			</main>
		</div>
	);
}

export default App;
