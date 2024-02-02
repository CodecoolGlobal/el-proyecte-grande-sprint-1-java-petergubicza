import * as React from 'react';

import MainPage from './Pages/MainPage/MainPage';
import * as ReactDOM from 'react-dom/client';

import {
	createBrowserRouter,
	RouterProvider
} from 'react-router-dom';
import App from './App';
import './App.css';

const router = createBrowserRouter([
	{
		path: '/',
		element: <App/>
	},
	{
		path: '/main',
		element: <MainPage />
	},
]);

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
	<React.StrictMode>
		<RouterProvider router={router}/>
	</React.StrictMode>
);

