export type RoomType = "DIRECT" | "GROUP";

export interface User {
  id: string;
  name: string;
  email?: string;
}

export interface Room {
  id: string;
  name: string | null;
  type: RoomType;
  createdAt: string;
  createdBy: string;
  updatedAt: string;
  participants: User[];
}

export function isRoom(obj: any): obj is Room {
  return (
    obj !== null &&
    typeof obj === "object" &&
    typeof obj.id === "string" &&
    (typeof obj.name === "string" || obj.name === null) &&
    (obj.type === "DIRECT" || obj.type === "GROUP") &&
    typeof obj.createdAt === "string" &&
    typeof obj.updatedAt === "string" &&
    typeof obj.createdBy === "string" &&
    Array.isArray(obj.participants) &&
    obj.participants.every(
      (p: any) => p && typeof p === "object" && typeof p.id === "string" && typeof p.name === "string"
    )
  );
}
