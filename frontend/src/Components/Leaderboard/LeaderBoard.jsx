import { players } from '../../exampleLeaderBoard';
import './LeaderBoard.css';

export default function LeaderBoard() {
	return (
		<div>
			<h1 id="header">I am the Leader Board</h1>
			<ol>
				{players.map((p) => {
					return <li className='playerList'>{p}</li>;
				})}
			</ol>
		</div>
	);
}
