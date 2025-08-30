import { useState } from "react";
import { Button } from "@/components/ui/button";
import { loginUser, signupUser } from "@/api/user";
import type { UserFormData } from "@/types/user";
import { Input } from "@/components/ui/input";
import { useUserStore } from "@/hooks/useUserStore";
import { useNavigate } from "react-router";
import { handleError } from "@/utils/lib";

export default function Auth() {
  const setUser = useUserStore((state) => state.setUser);
  const navigate = useNavigate();

  const [isLogin, setIsLogin] = useState(true);
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");
  const [errors, setErrors] = useState<Record<string, string>>({});
  const [formData, setFormData] = useState<UserFormData>({
    username: "",
    password: "",
    email: "",
    firstName: "",
    lastName: "",
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
    setErrors((prev) => ({ ...prev, [e.target.name]: "" })); // clear field error
  };

  const validate = (): boolean => {
    const newErrors: Record<string, string> = {};

    if (!formData.username.trim()) newErrors.username = "Username is required";
    if (!formData.password.trim()) newErrors.password = "Password is required";
    if (!isLogin) {
      if (!formData.email.trim()) {
        newErrors.email = "Email is required";
      } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(formData.email)) {
        newErrors.email = "Invalid email format";
      }

      if (!formData.firstName.trim())
        newErrors.firstName = "First name is required";
      if (!formData.lastName.trim())
        newErrors.lastName = "Last name is required";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async () => {
    if (!validate()) return;

    setLoading(true);
    setMessage("");

    try {
      const res = await (isLogin
        ? loginUser({
            username: formData.username,
            password: formData.password,
          })
        : signupUser(formData));

      setUser(res.data);
      setMessage("Success 🎉 Redirecting...");
      setTimeout(() => navigate("/"), 1000);
    } catch (err) {
      setMessage(
        "❌ " + (err instanceof Error ? err.message : "Something went wrong")
      );
      handleError(err as Error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="w-screen h-screen flex items-center text-sm justify-center bg-gray-50">
      <div className="w-md p-4 border rounded-lg shadow-md bg-white min-w-[350px]">
        <h1 className="text-2xl font-medium text-center">
          {isLogin ? "Login" : "Sign Up"}
        </h1>

        <div className="flex flex-col space-y-4 mt-6 w-full">
          {!isLogin && (
            <>
              <div className="flex flex-col space-y-0.5">
                <label htmlFor="firstName">First Name</label>
                <Input
                  id="firstName"
                  name="firstName"
                  placeholder="John"
                  value={formData.firstName}
                  onChange={handleChange}
                />
                {errors.firstName && (
                  <p className="text-red-500 text-xs">{errors.firstName}</p>
                )}
              </div>

              <div className="flex flex-col space-y-0.5">
                <label htmlFor="lastName">Last Name</label>
                <Input
                  id="lastName"
                  name="lastName"
                  placeholder="Doe"
                  value={formData.lastName}
                  onChange={handleChange}
                />
                {errors.lastName && (
                  <p className="text-red-500 text-xs">{errors.lastName}</p>
                )}
              </div>

              <div className="flex flex-col space-y-0.5">
                <label htmlFor="email">Email</label>
                <Input
                  id="email"
                  type="email"
                  name="email"
                  placeholder="email@example.com"
                  value={formData.email}
                  onChange={handleChange}
                />
                {errors.email && (
                  <p className="text-red-500 text-xs">{errors.email}</p>
                )}
              </div>
            </>
          )}

          <div className="flex flex-col space-y-0.5">
            <label htmlFor="username">Username</label>
            <Input
              id="username"
              name="username"
              placeholder="username"
              value={formData.username}
              onChange={handleChange}
            />
            {errors.username && (
              <p className="text-red-500 text-xs">{errors.username}</p>
            )}
          </div>

          <div className="flex flex-col space-y-0.5">
            <label htmlFor="password">Password</label>
            <Input
              id="password"
              type="password"
              name="password"
              placeholder="password"
              value={formData.password}
              onChange={handleChange}
            />
            {errors.password && (
              <p className="text-red-500 text-xs">{errors.password}</p>
            )}
          </div>
        </div>

        {message && (
          <p
            className={`mt-4 text-center text-sm ${
              message.startsWith("❌") ? "text-red-600" : "text-green-600"
            }`}
          >
            {message}
          </p>
        )}

        <div className="flex items-center justify-between mt-6">
          <Button
            variant="link"
            className="cursor-pointer"
            onClick={() => {
              setIsLogin(!isLogin);
              setErrors({});
              setMessage("");
            }}
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
