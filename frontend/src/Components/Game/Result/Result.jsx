import { useEffect, useState } from "react";
import { fetchData } from "../../fetch";

/* eslint-disable react/prop-types */
export default function Result({ selectedAnswer, onClose, onNext }) {

    const [isCorrect, setIsCorrect] = useState(null);

    useEffect(() => {
        fetchData(`/api/trivia/answerCheck?id=${selectedAnswer.id}`)
        .then((data) => {
          setIsCorrect(data.isCorrect)
        })
        .catch((error) => {
          console.error("Error loading question:", error);
        });
    }, []);

    return (
        <div className="answers">
            {isCorrect ? (
                <div>
                    <h2>Congratulation!</h2>
                    <p>Your answer is correct</p>
                    <button className="close-button" onClick={onClose}>
                        Close
                    </button>
                    <button className="next-button" onClick={onNext}>
                        Next
                    </button>
                </div>
            ) : (
                <div>
                    <h2>Sorry!</h2>
                    <p>Your answer is wrong</p>
                    <button className="close-button" onClick={onClose}>
                        Close
                    </button>
                    <button className="next-button" onClick={onNext}>
                        Next
                    </button>
                </div>
            )}
        </div>
    )
}