import type { User } from "@/types/user";
import { create } from "zustand";

type UserStore = {
  user: User | Partial<UserStore> | null;
  setUser: (
    value:
      | UserStore
      | Partial<UserStore>
      | ((state: UserStore) => UserStore | Partial<UserStore>)
  ) => void;
};

export const useUserStore = create<UserStore>((set) => ({
  user: null,
  setUser: (newValue) => {
    if (typeof newValue === "function") {
      set(newValue);
    } else {
      set({ user: newValue });
    }
  },
}));
