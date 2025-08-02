import React, { useState } from "react";
import {
	FaCalendarAlt,
	FaClock,
	FaMapMarkerAlt,
	FaUserFriends,
	FaUserPlus,
	FaThumbsUp,
	FaThumbsDown,
} from "react-icons/fa";
import Navbar from "../../components/Navbar/Navbar";
import ToggleButton from "../../components/ToggleButton/ToggleButton"; // adjust import if needed
import "./MatchDetails.css";

const MatchDetails: React.FC = () => {
	const [toggle, setToggle] = useState<"left" | "right">("left");

	const players = [
		{ name: "Tony Buljubasic", initial: "T" },
		{ name: "Tony Buljubasic", initial: "T" },
		{ name: "Tony Buljubasic", initial: "T" },
		{ name: "Kamil Lepkowski", initial: "K" },
		{ name: "Kamil Lepkowski", initial: "K" },
		{ name: "Kamil Lepkowski", initial: "K" },
	];

	return (
		<>
			<Navbar />
			<div className="match-details bg-[#1d1d1d] min-h-screen text-white px-4 py-6">
				<div className="match-details-container max-w-4xl mx-auto space-y-6">
					{/* Match Card */}
					<div className="bg-[#242424] rounded-xl p-6 shadow-sm">
						<div className="flex flex-col md:flex-row items-center justify-between flex-wrap gap-4">
							<div className="match-info mx-auto w-full px-12 md:px-28 lg:px-0 lg:mx-0">
								<h2 className="text-2xl geist-600 mb-6 text-left">Thursday Footy</h2>
								<div className="match-info-details flex flex-col space-y-2 text-sm text-gray-300">
									<div className="flex items-center gap-2">
										<FaCalendarAlt />
										<span className="geist-500">July 10, 2025</span>
									</div>
									<div className="flex items-center gap-2">
										<FaClock />
										<span className="geist-500">7:00 PM</span>
									</div>
									<div className="flex items-center gap-2">
										<FaMapMarkerAlt />
										<span className="geist-500">University of Chicago - South Field</span>
									</div>
									<div className="flex items-center gap-2">
										<FaUserFriends />
										<span className="geist-500">5 / 8 Players</span>
									</div>
								</div>

								<hr className="my-4 border-gray-600" />
								<div>
									<p className="text-xl text-white geist-600 text-left">Description</p>
									<p className="text-base text-left mt-2 text-white opacity-70 geist-500">
										Casual soccer match for all skill levels
									</p>
								</div>
							</div>

							{/* RSVP + Invite */}
							<div className="rsvp-invite flex flex-col gap-2 w-full sm:w-auto">
								<div className="flex gap-2">
									<button className="flex-1 bg-[#1d1d1d] px-4 py-2 rounded-md hover:bg-[#2a2a2a] flex items-center justify-center gap-2">
										<FaThumbsUp /> I'm Going
									</button>
									<button className="flex-1 bg-[#1d1d1d] px-4 py-2 rounded-md hover:bg-[#2a2a2a] flex items-center justify-center gap-2">
										<FaThumbsDown /> Can't Make It
									</button>
								</div>
								<button className="invite-btn bg-red-500 hover:bg-red-600 px-4 py-2 rounded-md flex items-center justify-center gap-2">
									<FaUserPlus /> Invite Players
								</button>
							</div>
						</div>
					</div>

					{/* Toggle Buttons */}
					<ToggleButton leftLabel="Players" rightLabel="Teams" active={toggle} onToggle={setToggle} />

					{/* Player List */}
					{toggle === "left" && (
						<div className="bg-[#242424] rounded-xl p-6 shadow-sm">
							<h3 className="text-lg font-semibold mb-4">Confirmed Players ({players.length})</h3>
							<div className="grid grid-cols-1 sm:grid-cols-2 gap-3">
								{players.map((player, idx) => (
									<div key={idx} className="flex items-center bg-[#121212] px-3 py-2 rounded-md border border-gray-700">
										<div className="w-8 h-8 rounded-full bg-[#2a2a2a] flex items-center justify-center text-sm font-bold mr-3">
											{player.initial}
										</div>
										<span>{player.name}</span>
									</div>
								))}
							</div>
						</div>
					)}

					{/* Teams View Placeholder */}
					{toggle === "right" && (
						<div className="bg-[#1e1e1e] rounded-xl p-6 shadow-sm text-center text-gray-400">
							<p>Team view coming soon...</p>
						</div>
					)}
				</div>
			</div>
		</>
	);
};

export default MatchDetails;
