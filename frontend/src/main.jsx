import * as React from 'react';
import * as ReactDOM from 'react-dom/client';
import {
	createBrowserRouter,
	RouterProvider
} from 'react-router-dom';
import App from './App';
import Home from './Pages/Home'
import './Main.css';

const router = createBrowserRouter([
	{
		path: '/',
		element: <App/>
	},
	{
		path: '/home',
		element: <Home />
	},
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
	<React.StrictMode>
		<RouterProvider router={router}/>
	</React.StrictMode>
);

