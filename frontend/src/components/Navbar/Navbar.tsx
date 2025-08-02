import React, { useState } from "react";
import { FaBars, FaTimes, FaHome, FaCalendarAlt, FaPlus, FaTable, FaUsers, FaUser } from "react-icons/fa";
import "./Navbar.css";

const Navbar: React.FC = () => {
	const [menuOpen, setMenuOpen] = useState(false);

	const toggleMenu = () => {
		setMenuOpen(!menuOpen);
	};

	const navItems = [
		{ label: "Dashboard", icon: <FaHome />, path: "/", special: true },
		{ label: "Matches", icon: <FaCalendarAlt />, path: "/matches" },
		{ label: "Create", icon: <FaPlus />, path: "/create" },
		{ label: "Fields", icon: <FaTable />, path: "/fields" },
		{ label: "Group", icon: <FaUsers />, path: "/group" },
		{ label: "Profile", icon: <FaUser />, path: "/profile" },
	];

	return (
		<header className="navbar bg-[#1f1f1f] text-white shadow top-0 left-0 w-full z-50 relative">
			<div className="navbar-container mx-auto px-4 flex justify-between items-center h-[80px]">
				<h1 className="text-xl font-bold">FIXTURE</h1>

				{/* Desktop Menu */}
				<nav className="desktop hidden lg:flex space-x-4 items-center">
					{navItems.map(({ label, icon, special }, index) => (
						<a
							key={index}
							href="#"
							className={`flex items-center gap-2 px-3 py-1.5 rounded-md text-sm geist-300 transition-colors ${
								special ? "bg-red-500 text-white hover:bg-red-600" : "hover:bg-[#2a2a2a] text-gray-300"
							}`}
						>
							{icon}
							{label}
						</a>
					))}
				</nav>

				{/* Mobile Burger */}
				<div className="lg:hidden">
					<button onClick={toggleMenu} className="text-gray-300">
						{menuOpen ? <FaTimes size={20} /> : <FaBars size={20} />}
					</button>
				</div>
			</div>

			{/* Mobile Menu */}
			{menuOpen && (
				<nav className="mobile lg:hidden px-4 pb-4 mt-3 space-y-2 bg-[#1f1f1f]">
					{navItems.map(({ label, icon, special }, index) => (
						<a
							key={index}
							href="#"
							className={`flex items-center gap-2 px-3 py-2 rounded-md text-sm geist-500 transition-colors ${
								special ? "bg-red-500 text-white hover:bg-red-600" : "hover:bg-[#2a2a2a] text-gray-300"
							}`}
						>
							{icon}
							{label}
						</a>
					))}
				</nav>
			)}
		</header>
	);
};

export default Navbar;
