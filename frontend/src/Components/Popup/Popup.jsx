/* eslint-disable react/prop-types */
import './Popup';
import './Popup.css';

export default function Popup({ onClose, childComponent }) {
    return (
        <div className="popup">
            <button className="button" onClick={onClose}>
                Close
            </button>
            <div className="popup-content">
                {childComponent}
            </div>
        </div>
    );
}