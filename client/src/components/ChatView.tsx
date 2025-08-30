export default function ChatView() {
  const currentChat = { user: { name: "Pranay", bio: "" } };
  return (
    <div className="flex flex-col flex-grow bg-zinc-800">
      <div className="flex items-center py-3 px-4">
        div
        <div className="flex flex-col">
          <h3 className="text-lg text-white">{currentChat.user.name}</h3>
        </div>
      </div>
    </div>
  );
}
