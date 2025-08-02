import "./FeatureCard.css";

interface FeatureCardProps {
	title: string;
	description: string;
}

const FeatureCard = ({ title, description }: FeatureCardProps) => (
	<div className="feature-card">
		<h3 className="geist-700">{title}</h3>
		<p className="geist-500">{description}</p>
	</div>
);

export default FeatureCard;
