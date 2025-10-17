import type { Room } from "@/types/room";
import { create } from "zustand";

type RoomStore = {
  rooms: Room[] | null;
  setRooms: (value: Room[] | ((state: Room[]) => Room[])) => void;
};

export const useRoomStore = create<RoomStore>((set) => ({
  rooms: [],
  setRooms: (newValue) => {
    if (typeof newValue === "function") {
      set((prev) => ({ ...prev, rooms: newValue(prev.rooms || []) }));
    } else {
      set({ rooms: newValue });
    }
  },
}));
