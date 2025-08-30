import { getUser } from "@/api/user";
import { useUserStore } from "@/hooks/useUserStore";
import { handleError } from "@/utils/lib";
import { AxiosError } from "axios";
import { useEffect, useState } from "react";
import Spinner from "./Spinner";
import { useNavigate } from "react-router";

type Props = {
  children: React.ReactNode;
};

export default function AppProvider({ children }: Props) {
  const [loading, setLoading] = useState(true);
  const setUser = useUserStore((state) => state.setUser);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchUser = async () => {
      try {
        setLoading(true);
        const user = await getUser();
        if (!user) throw new Error("User data missing");
        setUser(user);
      } catch (error) {
        if (error instanceof AxiosError) {
          if (error?.response?.data?.errorCode === "USER_ALREADY_EXISTS") {
            //
          }
          if (
            error?.response?.status &&
            [404, 403, 401].includes(error?.response?.status)
          ) {
            console.log("navigating to login page");
            navigate("/auth?login=true");
          }
        } else {
          handleError(error as Error);
        }
        console.error("error: ", error);
      } finally {
        setLoading(false);
      }
    };

    fetchUser();
  }, []);

  if (loading) return <Spinner />;
  return <>{children}</>;
}
