import { useEffect, useState } from "react";
import LeaderBoard from "../../Components/Leaderboard";
import Game from "../../Components/Game";
import Popup from "../../Components/Popup/Popup";
import User from "../../Components/User/User";
import "./Home.css";

export default function Home() {
  const [popup, setPopup] = useState(false);
  const [userStats, setUserStats] = useState(null);

  useEffect(() => {
    fetch(`/api/user/stats`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${localStorage.getItem("jwt")}`,
      },
    })
      .then((response) => response.json())
      .then((data) => {
        setUserStats(data);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  function handleClose() {
    setPopup(!popup);
  }

  return (
    <div className="homePage">
      <h1 className="header">Home</h1>
      <div className="leaderBoard">
        <LeaderBoard />
      </div>
      <div className="userSection">
        <User triviaUser={userStats} />
      </div>
      <div className="buttons">
        <button
          type="button"
          className="button"
          onClick={(e) => handleClose(e)}
        >
          Ranked
        </button>
        <button type="button" className="button" disabled={false}>
          Casual
        </button>
      </div>
      {popup && (
        <div className="popupContainer">
          <div className="popupContent">
            <Popup
              onClose={handleClose}
              childComponent={<Game onClose={handleClose} />}
            />
          </div>
        </div>
      )}
    </div>
  );
}
