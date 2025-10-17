import type { Room } from "@/types/room";
import { User } from "lucide-react";

export default function ChatListRow({ chat }: { chat: Room }) {
  return (
    <div className="flex items-center py-1.5 px-4 border-b border-b-zinc-800 hover:bg-zinc-700 cursor-pointer">
      <div className="pr-4 flex items-center h-full">
        <div className="size-10 bg-zinc-600 overflow-hidden rounded-full flex items-center justify-center">
          <User className="text-white size-8" />
        </div>
      </div>
      <div className="flex flex-col">
        <h3 className="text-white text-sm font-semibold">{chat.user.name}</h3>
        <h5 className="text-gray-400 text-[13px]">{chat.user.bio}</h5>
      </div>
    </div>
  );
}
