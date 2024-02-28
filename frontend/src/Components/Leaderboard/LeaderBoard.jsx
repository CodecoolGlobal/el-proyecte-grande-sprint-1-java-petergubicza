import './LeaderBoard.css';
import { fetchData } from '../../fetch';
import { useState, useEffect } from 'react';

export default function LeaderBoard() {
  const [championsWithPoints, setChampionsWithPoints] = useState([]);

  useEffect(() => {
    fetchData('/api/leaderboard/leaderboard')
      .then(res => {
        setChampionsWithPoints(res.userScores);
      })
      .catch(error => {
        console.error('Error fetching leaderboard', error);
      });
  }, []);

  return (
    <div>
      <h1 id="header">Top 5 players:</h1>
      <ol>
        {championsWithPoints.map((champion) => {
          return <li key={champion} className='playerList'>{champion.name + " - " + champion.points}</li>
        })}
      </ol>
    </div>
  );
}
