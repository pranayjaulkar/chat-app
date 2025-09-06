import ChatListRow from "@/components/ChatListRow";
import ChatView from "@/components/ChatView";
import NewChatModal from "@/components/NewChatModal";
import type { Chat } from "@/types/chat";
import { Plus } from "lucide-react";
import { useState } from "react";

export default function Home() {
  const [isNewChatModalOpen, setIsNewChatModalOpen] = useState(true);
  const [newChatId, setNewChatId] = useState("");
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

  const handleNewChatClick = () => {
    setIsNewChatModalOpen(true);
    setNewChatId("");
  };

  return (
    <div className="w-screen h-screen flex ">
      {
        // ==================  Left Side Bar  ==================
      }
      <div className="md:w-2/6 bg-zinc-800 flex flex-col">
        <div className="w-full flex items-center justify-between text-white px-4 py-1 border-b border-zinc-700">
          <h1>Chat App</h1>
          <button
            onClick={handleNewChatClick}
            className="cursor-pointer hover:bg-zinc-700 rounded-full size-8 p-1 flex items-center justify-center"
          >
            <Plus className="size-5" />
          </button>
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

      {isNewChatModalOpen && <NewChatModal />}
    </div>
  );
}
