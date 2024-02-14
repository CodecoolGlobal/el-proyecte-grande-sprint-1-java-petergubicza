import { useState } from 'react';
import LeaderBoard from '../../Components/LeaderBoard';
import Game from '../../Components/Game';
import Popup from '../../Components/Popup/Popup';
import './Home.css';

export default function Home() {
	const [popup, setPopup] = useState(false);

	function handleClose() {
		setPopup(!popup);
	}

	return (
		<div className="homePage">
			<h1 className='header'>Home</h1>
			<div className="leaderBoard">
				<LeaderBoard />
			</div>
			<button type="button" onClick={(e) => handleClose(e)}>
				Casual
			</button>
			<button type="button" disabled={true}>
				Ranked
			</button>
			{popup && <Popup onClose={handleClose} childComponent={<Game onClose={handleClose}/>}></Popup>}
		</div>
	);
}
