import axios, { AxiosError } from "axios";
import { useState } from "react";
import { Button } from "@/components/ui/button";

export default function Auth() {
  const [isLogin, setIsLogin] = useState(true);
  const [form, setForm] = useState({
    username: "",
    password: "",
    email: "",
  });
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async () => {
    setLoading(true);
    setMessage("");

    try {
      const res = await axios.post(
        isLogin ? "/api/login" : "/api/signup",
        form,
        {
          headers: { "Content-Type": "application/json" },
        }
      );

      setMessage(res.data.message || "Success");
    } catch (err) {
      if (err instanceof AxiosError) {
        if (err.response) {
          setMessage(err.response.data.message || "Request failed");
        } else if (err.request) {
          setMessage("No response from server");
        } else {
          setMessage(err.message || "Something went wrong");
        }
      }
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="w-screen h-screen flex items-center justify-center bg-gray-50">
      <div className="w-md p-6 border rounded-2xl shadow-md bg-white min-w-[350px]">
        <h1 className="text-2xl font-medium text-center">
          {isLogin ? "Login" : "Sign Up"}
        </h1>

        <div className="flex flex-col space-y-4 mt-6 w-full">
          {!isLogin && (
            <div className="flex flex-col space-y-1">
              <label htmlFor="email">Email</label>
              <input
                type="email"
                name="email"
                placeholder="email@example.com"
                value={form.email}
                onChange={handleChange}
                className="border px-4 py-2 rounded-md"
              />
            </div>
          )}

          <div className="flex flex-col space-y-1">
            <label htmlFor="username">Username</label>
            <input
              type="text"
              name="username"
              placeholder="username"
              value={form.username}
              onChange={handleChange}
              className="border px-4 py-2 rounded-md"
            />
          </div>

          <div className="flex flex-col space-y-1">
            <label htmlFor="password">Password</label>
            <input
              type="password"
              name="password"
              placeholder="password"
              value={form.password}
              onChange={handleChange}
              className="border px-4 py-2 rounded-md"
            />
          </div>
        </div>

        {message && (
          <p className="mt-4 text-center text-sm text-gray-600">{message}</p>
        )}

        <div className="flex items-center justify-between mt-6">
          <Button
            variant="link"
            className="cursor-pointer"
            onClick={() => setIsLogin(!isLogin)}
          >
            {isLogin ? "Create an account?" : "Already have an account?"}
          </Button>

          <Button onClick={handleSubmit} disabled={loading}>
            {loading ? "Loading..." : isLogin ? "Login" : "Sign Up"}
          </Button>
        </div>
      </div>
    </div>
  );
}
