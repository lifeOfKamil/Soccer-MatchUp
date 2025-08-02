import AuthForm from "../../components/AuthForm/AuthForm";
import FeatureCard from "../../components/FeatureCard/FeatureCard";
import "./Home.css";

const Home = () => {
	return (
		<div className="home-container">
			<header className="header">FIXTURE</header>

			<div className="hero-section">
				<div className="hero-left">
					<h1 className="hero-title font-bold px-6 geist-700">
						Organize Soccer
						<br /> Matches with Ease
					</h1>
					<p className="opacity-70 geist-300">
						Create matches, invite players, track scores, and manage your soccer group all in one place.
					</p>
					<button className="learn-more-btn mt-8 geist-500">Learn More</button>
				</div>
				<div className="hero-right">
					<AuthForm />
				</div>
			</div>

			<section className="features-section">
				<h2 className="features-title text-3xl font-bold mt-4 geist-700">Features for Organizers</h2>
				<div className="feature-cards">
					<FeatureCard
						title="Create Matches"
						description="Easily set up soccer matches with details like date, time, location, and player limits"
					/>
					<FeatureCard
						title="Invite Players"
						description="Send invitations via email or text message and track RSVPs in real-time"
					/>
					<FeatureCard
						title="Track Results"
						description="Record match score and keep a history of all games played by your group"
					/>
				</div>
			</section>

			<footer className="footer">© 2025 Fixture. All Rights reserved</footer>
		</div>
	);
};

export default Home;
