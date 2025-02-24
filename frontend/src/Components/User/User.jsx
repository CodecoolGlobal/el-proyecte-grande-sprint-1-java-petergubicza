import "./User.css";

/* eslint-disable react/prop-types */
export default function User({ triviaUser }) {
  
  const imageUrl =
    triviaUser?.image ||
    "https://play-lh.googleusercontent.com/bmGXUIx8ZiRgPSJI5eMOaUEafXVXiFiUJVx1siq1vVm1u-bbn4brIaYRehelsfy594c=w240-h480-rw";

  if (!triviaUser) { 
    return <div>Loading...</div>;
  }

  return (
    <div className="user">
      <img src={imageUrl} alt="default" id="user_image" />
      <h2 id="username">{triviaUser.userName}</h2>
      <h3 id="points"> points: {triviaUser.points}</h3>
    </div>
  );
}
