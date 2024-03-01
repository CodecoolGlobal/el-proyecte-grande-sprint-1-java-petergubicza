import { useState, useEffect } from "react";

/* eslint-disable react/prop-types */
export default function Answers({ quest, isSubmitted }) {
  const [selectedAnswerId, setSelectedAnswerId] = useState(null);
  const [correctAnswerId, setCorrectAnswerId] = useState(null);
  const [isAnswerSubmitted, setIsAnswerSubmitted] = useState(false);

  useEffect(() => {
    setSelectedAnswerId(null);
    setCorrectAnswerId(null);
    setIsAnswerSubmitted(false);
  }, [quest]);

  const handleAnswerClick = (id) => {
    if (!isAnswerSubmitted) {
      setSelectedAnswerId(id);
    }
  };

  function handleSubmitClick() {
    if (selectedAnswerId !== null) {
      fetch(`/api/question/correct_answer?questionId=${quest.id}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${localStorage.getItem("jwt")}`,
        },
      })
        .then((response) => response.json())
        .then((data) => {
          setCorrectAnswerId(data.correctAnswerId);
          if (selectedAnswerId === data.correctAnswerId) {
            document.getElementById(selectedAnswerId).style.backgroundColor =
              "lightgreen";
            givePointsToUser(quest.id);
          }
          if (selectedAnswerId !== data.correctAnswerId) {
            document.getElementById(selectedAnswerId).style.backgroundColor =
              "red";
          }
          isSubmitted();
          setIsAnswerSubmitted(true);
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
        Authorization: `Bearer ${localStorage.getItem("jwt")}`,
      },
      body: JSON.stringify({
        name: localStorage.getItem("name"),
        questionId: questionId,
      }),
    });
  }

  return (
    <div className="answers">
      {quest.answers.map((answer) => (
        <div
          id={answer.id}
          key={answer.id}
          onClick={() => handleAnswerClick(answer.id)}
          style={{
            backgroundColor:
              answer.id === selectedAnswerId
                ? "lightblue"
                : answer.id === correctAnswerId
                ? "lightgreen"
                : "transparent",
            cursor: isAnswerSubmitted ? "not-allowed" : "pointer",
          }}
        >
          {answer.description}
        </div>
      ))}
      <button
        className="button"
        onClick={handleSubmitClick}
        disabled={selectedAnswerId === null || isAnswerSubmitted}
      >
        Submit
      </button>
    </div>
  );
}
