import React from "react";
import { Link } from "react-router-dom";
import { IoMdCalendar } from "react-icons/io";
import { FaRegClock } from "react-icons/fa6";
import { MdLocationOn } from "react-icons/md";
import { FiUsers } from "react-icons/fi";
import "./MatchCard.css";

interface MatchCardProps {
	id: string;
	name: string;
	date: string;
	time: string;
	location: string;
	playerCount: string;
	score?: string;
}

const MatchCard = ({ id, name, date, time, location, players }: MatchCardProps) => {
	return (
		<div className="match-card">
			<div className="match-card-title text-white geist-700">
				<h3>{name}</h3>
			</div>
			<div className="match-card-info">
				<div className="match-card-date geist-400">
					<IoMdCalendar />
					<p>{date}</p>
				</div>
				<div className="match-card-time geist-400">
					<FaRegClock />
					<p>{time}</p>
				</div>
				<div className="match-card-location geist-400">
					<MdLocationOn />
					<p>{location}</p>
				</div>
				<div className="match-card-players geist-400">
					<FiUsers />
					<p>{players} Players</p>
				</div>
			</div>
			<Link to={`/matches/${id}`} className="match-card-link">
				<button className="match-card-rsvp geist-500 mt-2">RSVP</button>
			</Link>
		</div>
	);
};

export default MatchCard;
