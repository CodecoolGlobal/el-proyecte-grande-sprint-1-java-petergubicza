import "./Leaderboard.css";
import { useState, useEffect } from "react";

export default function Leaderboard({ answerPublisher }) {
  const [championsWithPoints, setChampionsWithPoints] = useState([]);

  useEffect(() => {
    const callback = () => {
      fetchLeaderboard();
    };

    answerPublisher.addSubscriber(callback);

    return () => {
      answerPublisher.removeSubscriber(callback);
    }
  }, []);

  useEffect(() => {
    fetchLeaderboard();
  }, []);

  function fetchLeaderboard() {
    fetch(`/api/leaderboard/leaderboard`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${localStorage.getItem("jwt")}`,
      },
    })
      .then((res) => res.json())
      .then((data) => {
        setChampionsWithPoints(data.userScores);
      })
      .catch((error) => {
        console.error("Error fetching leaderboard", error);
      });
  }

  return (
    <div>
      <h1 id="header">Top 5 players:</h1>
      <ol>
        {championsWithPoints.map((champion) => {
          return <li key={champion.name} className='playerList'>{champion.name + " - " + champion.points}</li>
        })}
      </ol>
    </div>
  );
}
