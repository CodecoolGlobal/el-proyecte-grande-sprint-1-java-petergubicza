/* eslint-disable react/prop-types */
import { useCallback, useEffect, useState } from "react";
import { fetchData } from "../fetch";
import Question from "./Questions";
import Answers from "./Answers";
import Result from "./Result";
import "./Game.css";

export default function Game({ onClose }) {
  const [quizQuest, setQuizQuest] = useState(null);
  const [showResult, setShowResult] = useState(false);
  const [selectedAnswer, setSelectedAnswer] = useState(null);

  const getNewQuestion = useCallback((locked = () => false) => {
    fetchData("/api/trivia/randomQuestion")
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
    setShowResult(false);
    getNewQuestion();
  }, [getNewQuestion]);

  const handleAnswerSelection = (answer) => {
    setSelectedAnswer(answer);
    setShowResult(true);
  }

  return (
    <div className="game">
      {showResult ? (
        <Result selectedAnswer={selectedAnswer} onClose={onClose} onNext={handleNextQuestion} />
      ) : (
        <div>
          <Question question={quizQuest.question} />
          <Answers
            answers={quizQuest.answers}
            onSelectAnswer={(answer) => handleAnswerSelection(answer)}
          />
        </div>
      )}
    </div>
  );
}
