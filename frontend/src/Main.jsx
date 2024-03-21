import * as React from 'react';
import * as ReactDOM from 'react-dom/client';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import Home from './Pages/Home'
import Register from './Pages/Register/Register';
import './Main.css';
import Login from './Pages/Login';

const router = createBrowserRouter([
	{
		path: '/',
		element: <Login />
	},
	{
		path: '/register',
		element: <Register />
	},
	{
		path: '/home',
		element: <Home />
	},
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
	<React.StrictMode>
		<RouterProvider router={router} />
	</React.StrictMode>
);
