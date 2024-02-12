/* eslint-disable react/prop-types */
import { useEffect, useState } from 'react';
import { fetchData } from './fetch.js';
import Question from './Questions';
import Answers from './Answers';
import Result from './Result';
import './game.css';

export default function Game({ onClose }) {

    const [question, setQuestion] = useState('');
    const [answers, setAnswers] = useState([]);
    const [id, setId] = useState(0);
    const [showResult, setShowResult] = useState(false);
    const [isAnswerCorrect, setIsAnswerCorrect] = useState(false);

    useEffect(() => {
        fetchData('/api/trivia/randomQuestion')
            .then(data => {
                setQuestion(data.question);
                setAnswers(data.answers);
                setId(data.id);
            })
            .catch(error => {
                console.error('Error loading question:', error);
            });
    }, []);

    function displayResult() {
        setShowResult(true);
    }

    function checkIfAnswerCorrect(isCorrect) {
        setIsAnswerCorrect(isCorrect);
    }

    function getNewQuestion() {
        setShowResult(false);
        fetchData('/api/trivia/randomQuestion')
            .then(data => {
                setQuestion(data.question);
                setAnswers(data.answers);
                setId(data.id);
            })
            .catch(error => {
                console.error('Error loading question:', error);
            });
    }

    return (
        <div className="game">
            {showResult ? (
                <Result isCorrect={isAnswerCorrect} onClose={onClose} onNext={getNewQuestion} />
            ) : (
                <div>
                    <Question question={question} />
                    <Answers answers={answers} questionId={id} displayResult={displayResult} result={checkIfAnswerCorrect} />
                </div>
            )}
        </div>
    );
}