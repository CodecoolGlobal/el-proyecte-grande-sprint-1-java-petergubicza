import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import { postData } from "../../post";
import "./Login.css";

export default function Login() {
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  async function signIn() {
    const body = {
      name,
      password,
    };

    const response = await postData(`/api/user/signin`, body);
    
    if (response.jwt) {
      console.log("ok");
      navigate("/home");
    }
  }

  return (
    <div>
      <h1>Login</h1>
      <form className="form">
        <div>
          <label htmlFor="userName">Username: </label>
          <input
            className="inputField"
            name="userName"
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </div>
        <div>
          <label htmlFor="password">Password: </label>
          <input
            className="inputField"
            name="password"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <div>
          <button
            className="button"
            type="button"
            disabled={false}
            onClick={signIn}
          >
            Sign in
          </button>
        </div>
        <div>
          <Link to={"register"}>
            <button className="button" type="button" disabled={false}>
              Register
            </button>
          </Link>
        </div>
        <div>
          <Link to={"home"}>
            <button className="button" type="button" disabled={false}>
              Play as Guest
            </button>
          </Link>
        </div>
      </form>
    </div>
  );
}
