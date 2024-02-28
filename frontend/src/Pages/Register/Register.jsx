import { Link, useNavigate } from "react-router-dom";
import { useState } from "react";
import { postData } from "../../post";
import "./Register.css";

export default function Register() {
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  async function register(e) {
    e.preventDefault();

    const body = {
      name,
      password,
    };

    const response = await postData(`/api/user/register`, body);
    
    if (response) {
        navigate("/");
      }
  }

  return (
    <div>
      <h1>Register</h1>
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
              onClick={register}
            >
              Register
            </button>
        </div>
        <div>
          <Link to={"/"}>
            <button className="button" type="button" disabled={false}>
              Back
            </button>
          </Link>
        </div>
      </form>
    </div>
  );
}
