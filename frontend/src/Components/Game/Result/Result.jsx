/* eslint-disable react/prop-types */
import './Result.css';

export default function Result({ isCorrect, onClose, onNext }) {
    return (
        <div className="answers">
            {isCorrect ? (
                <div>
                    <h2>Congratulation!</h2>
                    <p>Your answer is correct</p>
                    <button className="button" onClick={onClose}>
                        Close
                    </button>
                    <button className="button" onClick={onNext}>
                        Next
                    </button>
                </div>
            ) : (
                <div>
                    <h2>Sorry!</h2>
                    <p>Your answer is wrong</p>
                    <button className="button" onClick={onClose}>
                        Close
                    </button>
                    <button className="button" onClick={onNext}>
                        Next
                    </button>
                </div>
            )}
        </div>
    )
}