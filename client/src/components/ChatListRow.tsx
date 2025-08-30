import type { Chat } from "@/types/chat";
import { User } from "lucide-react";

export default function ChatListRow({chat}:{chat:Chat}) {
  return (
    <div className="flex items-start py-3 px-4 border-b border-b-zinc-800">
      <div className="pr-4">
        <div className="size-12 bg-zinc-600 overflow-hidden rounded-full flex items-center justify-center">
          <User className="text-white size-12" />
        </div>
      </div>
      <div className="flex flex-col space-y-1">
        <h3 className="text-white">{chat.user.name}</h3>
        <h5 className="text-gray-400 text-sm">{chat.user.bio}</h5>
      </div>
    </div>
  );
}
