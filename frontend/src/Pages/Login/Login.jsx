import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import { postData } from "../../post";
import "./Login.css";

export default function Login() {
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(""); // Állapot a hibaüzenetnek
  const navigate = useNavigate();

  async function login() {
    const body = {
      name,
      password,
    };

    try {
      const response = await postData(`/api/user/login`, body);
    
      if (response.jwt) {
        localStorage.setItem("jwt", response.jwt);
        localStorage.setItem("name", response.userName);
        navigate("/home");
      }
    } catch (error) {
      setError("Username or password is incorrect");
    }
  }

  return (
    <div>
      <h1>Login</h1>
      {error && <p style={{ color: "red" }}>{error}</p>}
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
            onClick={login}
          >
            Login
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
            <button className="button" type="button" disabled={true}>
              Play as Guest
            </button>
          </Link>
        </div>
      </form>
    </div>
  );
}
