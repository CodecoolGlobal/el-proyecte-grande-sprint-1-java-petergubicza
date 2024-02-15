import "./User.css";

/* eslint-disable react/prop-types */
export default function User({ triviaUser }) {
    
  const imageUrl = triviaUser.image
    ? triviaUser.image
    : "https://play.google.com/store/apps/details?id=com.triviaquiz.funtriviaquestions&hl=hu";

  return (
    <div className="user">
      <img src={imageUrl} alt="default" id="user_image"/>
      <h2 id="username">{triviaUser.name}</h2>
      <h3 id="points">{triviaUser.points}</h3>
    </div>
  );
}
