import { Link } from 'react-router-dom';
import './Login.css';

export default function Login() {
	function test(e) {
		e.preventDefault();
	}
	return (
		<div>
			Login
			<form>
				<div>
					<label htmlFor="userName">Username</label>
					<input name="userName" type="text" />
				</div>
				<div>
					<label htmlFor="password">Password</label>
					<input name="password" type="password" />
				</div>
				<div className="buttons">
					<button type="submit" disabled={true}>
						Login
					</button>
				</div>
				<div className="buttons">
					<button type="submit" disabled={true} onClick={(e) => test(e)}>
						Sign up
					</button>
				</div>
				<div className="buttons">
					<Link to={'home'}>
						<button type="button" disabled={false}>
							Play as Guest
						</button>
					</Link>
				</div>
			</form>
		</div>
	);
}
