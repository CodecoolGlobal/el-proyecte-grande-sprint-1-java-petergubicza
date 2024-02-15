/* eslint-disable react/prop-types */
export default function Answers({ answers, onSelectAnswer }) {

  return (
    <div className="answers">
      {answers.map((answer) => (
        <div key={answer.id} onClick={() => onSelectAnswer(answer)}>
          {answer.description}
        </div>
      ))}
    </div>
  );
}
