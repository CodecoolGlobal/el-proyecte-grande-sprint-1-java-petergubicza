import './LeaderBoard.css';
import { fetchData } from '../fetch';
import { useState, useEffect } from 'react';

export default function LeaderBoard() {
  const [championsWithPoints, setChampionsWithPoints] = useState([]);

  useEffect(() => {
    fetchData('/api/trivia/leaderboard')
      .then(res => {
        setChampionsWithPoints(concatData(res.names, res.points));
      })
      .catch(error => {
        console.error('Error fetching leaderboard', error);
      });
  }, []);

  function concatData(champ, point) {
    const champsWithPoints = [];
    for (let i = 0; i < 5; i++) {
      champsWithPoints[i] = champ[i] + ' - ' + point[i];
    }
    return champsWithPoints
  }

  return (
    <div>
      <h1 id="header">Top 5 players:</h1>
      <ol>
        {championsWithPoints.map((champion) => {
          return <li key={champion} className='playerList'>{champion}</li>
        })}
      </ol>
    </div>
  );
}
