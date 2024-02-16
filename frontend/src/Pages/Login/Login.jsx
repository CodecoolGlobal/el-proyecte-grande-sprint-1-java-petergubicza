import { Link } from 'react-router-dom';
import './Login.css';

export default function Login() {
	function test(e) {
		e.preventDefault();
	}
	return (
		<div>
			<h1>Login</h1>
			<form class="form">
				<div>
					<label htmlFor="userName">Username: </label>
					<input class="inputField" name="userName" type="text" />
				</div>
				<div>
					<label htmlFor="password">Password: </label>
					<input class="inputField" name="password" type="password" />
				</div>
				<div>
					<button className="button" type="submit" disabled={true}>
						Log in
					</button>
				</div>
				<div>
					<button className="button" type="submit" disabled={true} onClick={(e) => test(e)}>
						Sign up
					</button>
				</div>
				<div>
					<Link to={'home'}>
						<button className="button" type="button" disabled={false}>
							Play as Guest
						</button>
					</Link>
				</div>
			</form>
		</div>
	);
}
