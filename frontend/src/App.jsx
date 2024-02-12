import { useState } from 'react';

import './App.css';
import Login from './Pages/Login/Login';

function App() {
	const [count, setCount] = useState(0);

	return (
		<div>
			Hey
			<Login />
		</div>
	);
}

export default App;
