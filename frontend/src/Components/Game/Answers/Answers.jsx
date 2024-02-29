import { useState } from "react";

/* eslint-disable react/prop-types */
export default function Answers({ quest }) {
  const [selectedAnswerId, setSelectedAnswerId] = useState(null);
  const [correctAnswerId, setCorrectAnswerId] = useState(null);

  const handleAnswerClick = (id) => {
    if (selectedAnswerId === id) {
      setSelectedAnswerId(null);
    } else {
      setSelectedAnswerId(id);
    }
  };

  function handleSelectClick() {
    if (selectedAnswerId !== null) {
      fetch(`/api/question/correct_answer?questionId=${quest.id}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${localStorage.getItem("jwt")}`,
        },
      })
        .then((response) => response.json())
        .then((data) => {
          setCorrectAnswerId(data.correctAnswerId);
          if (selectedAnswerId !== data.correctAnswerId) {
            document.getElementById(selectedAnswerId).style.backgroundColor = "red";
          } else if (selectedAnswerId === data.correctAnswerId) {
            givePointsToUser(quest.id);
          };
        })
        .catch((error) =>
          console.error("Error fetching correct answer:", error)
        );

    }
  }

  function givePointsToUser(questionId) {
    fetch(`/api/user/addpoints`, {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${localStorage.getItem("jwt")}`
      },
      body: JSON.stringify({
        name: localStorage.getItem("name"),
        questionId: questionId
      })
    });
  }

  return (
    <div className="answers">
      {quest.answers.map((answer) => (
        <div
          key={answer.id}
          onClick={() => handleAnswerClick(answer.id)}
          style={{
            backgroundColor:
              answer.id === selectedAnswerId
                ? "lightblue"
                : answer.id === correctAnswerId
                  ? "lightgreen"
                  : "transparent",
            cursor: "pointer",
          }}
        >
          {answer.description}
        </div>
      ))}
      <button
        className="button"
        onClick={handleSelectClick}
        disabled={selectedAnswerId === null}
      >
        Select
      </button>
    </div>
  );
}
