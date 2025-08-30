export type User = {
  id: string;
  username: string;
  email: string;
  firstName: string;
  lastName: string;
  fullName: string;
};

export type UserFormData = Omit<User, "id" | "fullName"> & { password: string };
