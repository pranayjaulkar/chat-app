import { isErrorResponse } from "@/types/ErrorResponse";
import { AxiosError } from "axios";
import toast from "react-hot-toast";

export const handleError = (error: Error | AxiosError) => {
  if (
    error instanceof AxiosError &&
    error.response?.data &&
    isErrorResponse(error.response?.data)
  ) {
    const resData = error.response.data;
    if (resData.errorCode !== "UNKNOWN_ERROR") {
      toast.error(resData.message);
      return;
    }

    if (error.response.status >= 500) {
      toast.error("Server Error!");
      return;
    }
  }

  toast.error("An unexpected error occured !");
};
