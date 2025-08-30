import axios from "axios";

export const api = axios.create({
  baseURL: import.meta.env.PROD ? "/api" : "http://localhost:8080/api",
  withCredentials: true,
});
