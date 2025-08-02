import React from "react";
import { FaPlus } from "react-icons/fa";
import ToggleButton from "../../components/ToggleButton/ToggleButton";
import Navbar from "../../components/Navbar/Navbar";
import MatchCard from "../../components/MatchCard/MatchCard";
import "./Dashboard.css";

const Dashboard = () => {
	const handleToggle = (selected: "left" | "right") => {
		console.log("Selected:", selected);
	};

	return (
		<div className="dashboard h-screen">
			<Navbar />
			<h1 className="dashboard-title text-left mx-4 mt-8 geist-600 text-white">Dashboard</h1>
			<div className="dashboard-container mx-4">
				<button className="create-match-button flex items-center gap-2 text-white bg-[#d23f3f] geist-600 mb-4 mt-8 lg:ml-auto">
					<FaPlus />
					Create Match
				</button>
				<ToggleButton leftLabel="Upcoming" rightLabel="Past" onToggle={handleToggle} />
				<div className="match-card-container grid grid-cols-1 lg:grid-cols-2 2xl:grid-cols-3 mt-8 gap-6">
					<MatchCard
						id="1"
						name="Thursday Footy"
						date="July 10, 2025"
						time="7:00 PM"
						location="University of Chicago - South Field"
						players="5/8"
						score="3-2"
					/>
					<MatchCard
						id="2"
						name="Thursday Footy"
						date="July 10, 2025"
						time="7:00 PM"
						location="University of Chicago - South Field"
						players="5/8"
						score="3-2"
					/>
					<MatchCard
						name="Thursday Footy"
						date="July 10, 2025"
						time="7:00 PM"
						location="University of Chicago - South Field"
						players="5/8"
						score="3-2"
					/>
				</div>
			</div>
		</div>
	);
};

export default Dashboard;
