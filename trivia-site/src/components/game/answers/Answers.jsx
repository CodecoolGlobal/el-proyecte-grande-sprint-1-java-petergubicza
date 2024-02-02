import { fetchData } from "../fetch.js";

/* eslint-disable react/prop-types */
export default function Answers({
  answers,
  questionId,
  displayResult,
  result,
  onAnswerSelected,
}) {

  async function isSelectedAnswerCorrect(selectedAnswer) {
    const data = await fetchData(`/api/trivia/answer/${questionId}`);
    result(data.correctAnswer === selectedAnswer);
    displayResult();
  }

  return (
    <div className="answers">
      {answers.map((answer, index) => (
        <div
          key={index}
          id={`answer-${index}`}
          onClick={
            () => {
                onAnswerSelected(answer)
                isSelectedAnswerCorrect(answer)
            }
        }
        >
          {answer}
        </div>
      ))}
    </div>
  );
}
