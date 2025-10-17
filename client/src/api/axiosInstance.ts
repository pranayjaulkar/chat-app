import { API_PATH } from "@/utils/constants";
import axios from "axios";

export const api = axios.create({
  baseURL: API_PATH,
  withCredentials: true,
});
