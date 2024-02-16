import { useState } from 'react';
import { mockUser } from '../../mockUser';
import LeaderBoard from '../../Components/LeaderBoard';
import Game from '../../Components/Game';
import Popup from '../../Components/Popup/Popup';
import User from '../../Components/User/User';
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
			<div>
				<User triviaUser={mockUser}/>
			</div>
			<button type="button" class="button" onClick={(e) => handleClose(e)}>
				Casual
			</button>
			<button type="button" class="button" disabled={true}>
				Ranked
			</button>
			{popup && <Popup onClose={handleClose} childComponent={<Game onClose={handleClose}/>}></Popup>}
		</div>
	);
}
