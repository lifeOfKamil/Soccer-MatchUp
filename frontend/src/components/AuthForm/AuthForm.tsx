import { useState } from "react";
import { Link } from "react-router-dom";
import "./AuthForm.css";

const AuthForm = () => {
	const [email, setEmail] = useState("");
	//const [password, setPassword] = useState("");
	//const [mode, setMode] = useState<"login" | "register">("login");
	const [activeTab, setActiveTab] = useState<"login" | "register">("login");

	return (
		<div className="auth-form">
			<h2 className="font-bold">Welcome to Fixture</h2>
			<p>Sign in or create an account to get started</p>

			<div className="auth-buttons flex w-full mb-4 rounded-lg bg-[#313131] p-1">
				<button
					onClick={() => setActiveTab("login")}
					className={`w-1/2 py-2 rounded-l-md font-medium transition-colors ${
						activeTab === "login" ? "bg-[#212121] text-white" : "bg-transparent text-[#bdbdbd] hover:bg-[#2a2a2a]"
					}`}
				>
					Login
				</button>
				<button
					onClick={() => setActiveTab("register")}
					className={`w-1/2 py-2 rounded-r-md font-medium transition-colors ${
						activeTab === "register" ? "bg-[#212121] text-white" : "bg-transparent text-[#bdbdbd] hover:bg-[#2a2a2a]"
					}`}
				>
					Register
				</button>
			</div>

			<form className="text-left">
				<label className="email-label font-semibold geist-500 tracking-wide">Email</label>
				<input
					className="mt-1 w-full px-4 py-2 rounded-md bg-[#1e1e1e] text-sm text-white focus:outline-none focus:ring-1 focus:ring-gray-400"
					type="email"
					placeholder="name@example.com"
					value={email}
					onChange={(e) => setEmail(e.target.value)}
				/>
				<div className="flex justify-between items-center mb-1">
					<label className="text-sm geist-500 tracking-wide">Password</label>
					<a href="#" className="text-red-400 text-xs hover:underline">
						Forgot Password?
					</a>
				</div>
				<input
					className="w-full px-4 py-2 rounded-md bg-[#1e1e1e] text-sm text-white focus:outline-none focus:ring-1 focus:ring-gray-400"
					type="password"
					placeholder="•••••••••"
				/>
				<Link to="/dashboard" className="no-underline">
					<button type="submit" className="sign-in-btn mt-4 mb-8 geist-500 tracking-wide">
						Sign In
					</button>
				</Link>
			</form>
		</div>
	);
};

export default AuthForm;
