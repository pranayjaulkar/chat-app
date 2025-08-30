import ChatListRow from "@/components/ChatListRow";
import ChatView from "@/components/ChatView";
import type { Chat } from "@/types/chat";
export default function Home() {
  const chats: Chat[] = [
    {
      user: {
        name: "Pranay",
        bio: "The journey of a thousand miles begins with a single step.",
      },
    },
    {
      user: {
        name: "Jayesh",
        bio: "The journey of a thousand miles begins with a single step.",
      },
    },
    {
      user: {
        name: "Pranay",
        bio: "The journey of a thousand miles begins with a single step.",
      },
    },
    {
      user: {
        name: "Pranay",
        bio: "The journey of a thousand miles begins with a single step.",
      },
    },
    {
      user: {
        name: "Pranay",
        bio: "The journey of a thousand miles begins with a single step.",
      },
    },
    {
      user: {
        name: "Pranay",
        bio: "The journey of a thousand miles begins with a single step.",
      },
    },
    {
      user: {
        name: "Pranay",
        bio: "The journey of a thousand miles begins with a single step.",
      },
    },
    {
      user: {
        name: "Pranay",
        bio: "The journey of a thousand miles begins with a single step.",
      },
    },
    {
      user: {
        name: "Pranay",
        bio: "The journey of a thousand miles begins with a single step.",
      },
    },
    {
      user: {
        name: "Pranay",
        bio: "The journey of a thousand miles begins with a single step.",
      },
    },
    {
      user: {
        name: "Pranay",
        bio: "The journey of a thousand miles begins with a single step.",
      },
    },
    {
      user: {
        name: "Pranay",
        bio: "The journey of a thousand miles begins with a single step.",
      },
    },
    {
      user: {
        name: "Pranay",
        bio: "The journey of a thousand miles begins with a single step.",
      },
    },
    {
      user: {
        name: "Pranay",
        bio: "The journey of a thousand miles begins with a single step.",
      },
    },
    {
      user: {
        name: "Pranay",
        bio: "The journey of a thousand miles begins with a single step.",
      },
    },
  ];
  return (
    <div className="w-screen h-screen flex ">
      {
        // ==================  Left Chat list side bar  ==================
      }
      <div className="md:w-2/6 bg-zinc-900 flex flex-col">
        <div className="w-full flex items-center text-white px-4 py-2 border-b border-zinc-700">
          <h1>Chat App</h1>
        </div>

        <div className="flex flex-col space-y-1 flex-grow overflow-y-auto">
          {chats.map((chat, i) => (
            <ChatListRow chat={chat} key={i} />
          ))}
        </div>
      </div>

      {
        // ==================  Chat View  ==================
      }

      <ChatView />
    </div>
  );
}
