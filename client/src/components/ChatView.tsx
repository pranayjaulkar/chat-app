import { User } from "lucide-react";

export default function ChatView() {
  const currentChat = {
    user: {
      name: "Pranay",
      bio: "Sometimes you need to distance yourself to see things clearly.",
    },
  };
  return (
    <div className="flex flex-col flex-grow bg-zinc-800">
      <div className="flex items-center py-2 px-4">
        <div className="flex items-center space-x-4">
          <div className="size-10 bg-zinc-600 text-white rounded-full flex items-center justify-center">
            <User />
          </div>
          <div className="flex flex-col">
            <h3 className="text-white">{currentChat.user.name}</h3>
            <p className="text-gray-400 text-sm">{currentChat.user.bio}</p>
          </div>
        </div>
      </div>

      <div className="w-full h-full flex-1 bg-zinc-900"></div>
    </div>
  );
}
