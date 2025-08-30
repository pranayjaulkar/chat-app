import type { UserFormData } from "@/types/user";
import { api } from "./axiosInstance";

export const getUser = async () => {
  try {
    const res = await api.get("/users");
    return res.data;
  } catch (error) {
    throw error;
  }
};

export const loginUser = async (data: {
  username: string;
  password: string;
}) => {
  try {
    const res = await api.post("/auth/login", data);
    return res.data;
  } catch (error) {
    throw error;
  }
};

export const signupUser = async (data: UserFormData) => {
  try {
    const res = await api.post("/auth/signup", data);
    return res.data;
  } catch (error) {
    throw error;
  }
};
