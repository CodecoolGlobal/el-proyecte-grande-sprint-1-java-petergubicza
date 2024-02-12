import { useState } from 'react';
import Popup from '../../components/Popup/Popup';
import LeaderBoard from '../../components/Leaderboard/LeaderBoard';
import './MainPage.css';
import Game from '../../components/Game/Game';
export default function MainPage() {
	const [popup, setPopup] = useState(false);

	function handleClose() {
		setPopup(!popup);
	}

	return (
		<div className="mainPage">
			<h1 className='header'>I am the main page</h1>
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
