import { fetchData } from '../fetch.js';

/* eslint-disable react/prop-types */
export default function Answers({ answers, questionId, displayResult, result }) {

async function isSelectedAnswerCorrect(selectedAnswer){
    const data = await fetchData(`/api/trivia/answer/${questionId}`);
    result(data.correctAnswer === selectedAnswer);
    displayResult();
}

    return (
        <div className="answers">
            {answers.map((answer, index) => (
                <div key={index} id={`answer-${index}`} onClick={() => isSelectedAnswerCorrect(answer)}>
                    {answer}
                </div>
            ))}
        </div>
    )
}