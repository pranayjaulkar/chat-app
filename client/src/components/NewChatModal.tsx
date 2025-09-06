import { Button } from "./ui/button";
import { Input } from "./ui/input";

export default function NewChatModal() {
  return (
    <div className="fixed top-0 left-0 w-screen h-screen flex items-center justify-center z-50 bg-[rgba(0,0,0,0.4)]">
      <div className="text-white p-4 w-96 rounded-lg border border-gray-700 bg-zinc-800">
        <form className="flex flex-col space-y-4">
          <h3 className="text-lg">Enter new chat ID</h3>
          <Input type="text" />
          <Button className="cursor-pointer" type="submit">
            Submit
          </Button>
        </form>
      </div>
    </div>
  );
}
