/* eslint-disable react/prop-types */
import { useCallback, useEffect, useState } from "react";
import { fetchData } from "./fetch.js";
import Question from "./question/Question";
import Answers from "./answers/Answers";
import Result from "./result/Result";
import "./game.css";

export default function Game({ onClose }) {
  const [question, setQuestion] = useState("");
  const [answers, setAnswers] = useState([]);
  const [id, setId] = useState(0);
  const [showResult, setShowResult] = useState(false);
  const [isAnswerCorrect, setIsAnswerCorrect] = useState(false);
  const [answerSelected, setAnswerSelected] = useState(null);

  const getNewQuestion = useCallback((locked = () => false) => {
    fetchData("/api/trivia/randomQuestion")
      .then((data) => {
        if (locked() === true) {
          return;
        }
        setQuestion(data.question);
        setAnswers(data.answers);
        setId(data.id);
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

  function displayResult() {
    setShowResult(true);
  }

  function checkIfAnswerCorrect(isCorrect) {
    setIsAnswerCorrect(isCorrect);
  }

  const handleNextQuestion = useCallback(() => {
    setShowResult(false);
    getNewQuestion();
  }, [getNewQuestion]);

  return (
    <div className="game">
      {showResult ? (
        <Result
          isCorrect={isAnswerCorrect}
          onClose={onClose}
          onNext={handleNextQuestion}
        />
      ) : (
        <div>
          <Question question={question} />
          <Answers
            answers={answers}
            questionId={id}
            displayResult={displayResult}
            result={checkIfAnswerCorrect}
            onAnswerSelected={(answer) => setAnswerSelected(answer)}
          />
        </div>
      )}
    </div>
  );
}
