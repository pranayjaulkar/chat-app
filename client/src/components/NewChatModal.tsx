import { useState } from "react";
import { Button } from "./ui/button";
import { Input } from "./ui/input";
import { useUserStore } from "@/hooks/useUserStore";
import { api } from "@/api/axiosInstance";
import toast from "react-hot-toast";
import { AxiosError } from "axios";
import { isErrorResponse } from "@/types/";
import { GEN_ERROR_MESSAGE } from "@/utils/constants";
import { useRoomStore } from "@/hooks/useRoomsStore";
import { isRoom } from "@/types/room";

export default function NewChatModal() {
  const [chatId, setChatId] = useState("");
  const [chatName, setChatName] = useState("");
  const user = useUserStore((state) => state.user);
  const { setRooms } = useRoomStore();

  const handleNewChat = async () => {
    try {
      if (user?.id) {
        const roomData = {
          name: chatName,
          type: "DIRECT",
          participants: [user.id, chatId],
        };

        const res = await api.post("/rooms", roomData);
        toast.success(`Create chat ${chatName}`);
        if (isRoom(res.data)) setRooms((prev) => [...prev, res.data]);
      }
    } catch (error) {
      if (error instanceof AxiosError && isErrorResponse(error.response?.data)) {
        if (error.response.data.errorCode !== "UNKNOWN_ERROR") {
          toast.error(error.response.data.message);
          return;
        }
      }
      toast.error(GEN_ERROR_MESSAGE);
    }
  };
  return (
    <div className="fixed top-0 left-0 w-screen h-screen flex items-center justify-center z-50 bg-[rgba(0,0,0,0.4)]">
      <div className="text-white p-4 w-96 rounded-lg border border-gray-700 bg-zinc-800">
        <form className="flex flex-col space-y-4">
          <div className="flex flex-col space-y-2">
            <h3 className="text-lg">Chat ID</h3>
            <Input type="text" value={chatId} onChange={(e) => setChatId(e.target.value)} />
          </div>
          <div className="flex flex-col space-y-2">
            <label>Chat name</label>
            <Input type="text" value={chatName} onChange={(e) => setChatName(e.target.value)} />
          </div>
          <Button onClick={handleNewChat} className="cursor-pointer" type="submit">
            Submit
          </Button>
        </form>
      </div>
    </div>
  );
}
