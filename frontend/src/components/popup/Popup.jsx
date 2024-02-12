/* eslint-disable react/prop-types */
import React from 'react';
import './popup.css';

export default function Popup({ onClose, childComponent }) {
    return (
        <div className="popup">
            I am the popup page
            <button className="close-button" onClick={onClose}>
                Close
            </button>
            <div className="popup-content">
                {childComponent}
            </div>
        </div>
    );
};