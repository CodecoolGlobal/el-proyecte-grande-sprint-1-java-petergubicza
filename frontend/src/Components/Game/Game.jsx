/* eslint-disable react/prop-types */
import { useCallback, useEffect, useState } from "react";
import Question from "./Questions";
import Answers from "./Answers";
import "./Game.css";

export default function Game({ onClose }) {
  const [quizQuest, setQuizQuest] = useState(null);

  const getNewQuestion = useCallback((locked = () => false) => {
    fetch(`/api/question/random_question`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${localStorage.getItem("jwt")}`,
      },
    })
      .then((response) => response.json())
      .then((data) => {
        if (locked() === true) {
          return;
        }
        setQuizQuest(data);
      })
      .catch((error) => {
        console.error("Error loading question:", error);
      });
  }, []);

  useEffect(() => {
    let lock = false;

    getNewQuestion(() => lock);

    return () => {
      lock = true;
    };
  }, [getNewQuestion]);

  const handleNextQuestion = useCallback(() => {
    getNewQuestion();
  }, [getNewQuestion]);

  return (
    <div className="game">
      {quizQuest === null ? (
        <div>Loading...</div>
      ) : (
        <div className="quiz">
          <Question question={quizQuest.questionDescription} />
          <Answers quest={quizQuest} />
          <button className="button" onClick={onClose}>
            Close
          </button>
          <button className="button" onClick={handleNextQuestion}>
            Next
          </button>
        </div>
      )}
    </div>
  );
}
