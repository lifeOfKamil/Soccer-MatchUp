import React, { useState } from "react";

interface ToggleButtonProps {
	leftLabel: string;
	rightLabel: string;
	onToggle?: (selected: "left" | "right") => void;
}

const ToggleButton: React.FC<ToggleButtonProps> = ({ leftLabel, rightLabel, onToggle }) => {
	const [active, setActive] = useState<"left" | "right">("left");

	const handleClick = (side: "left" | "right") => {
		setActive(side);
		onToggle?.(side);
	};

	return (
		<div className="flex w-full mb-4 rounded-lg bg-[#313131] p-1">
			<button
				onClick={() => handleClick("left")}
				className={`w-1/2 py-2 rounded-l-md font-medium transition-colors ${
					active === "left" ? "bg-[#212121] text-white" : "bg-transparent text-[#bdbdbd] hover:bg-[#2a2a2a]"
				}`}
			>
				{leftLabel}
			</button>
			<button
				onClick={() => handleClick("right")}
				className={`w-1/2 py-2 rounded-r-md font-medium transition-colors ${
					active === "right" ? "bg-[#212121] text-white" : "bg-transparent text-[#bdbdbd] hover:bg-[#2a2a2a]"
				}`}
			>
				{rightLabel}
			</button>
		</div>
	);
};

export default ToggleButton;
