export type ErrorCode =
  | "UNKNOWN_ERROR"
  | "USER_NOT_FOUND"
  | "USER_ALREADY_EXISTS"
  | "USER_VALIDATION_ERRORS"
  | "ROOM_NOT_FOUND"
  | "ROOM_VALIDATION_ERROR";

export type ErrorResponse = {
  timestamp: string;
  error: string;
  message: string;
  errorCode: ErrorCode;
  status: number;
};

export function isErrorResponse(obj: any): obj is ErrorResponse {
  return (
    obj &&
    typeof obj.timestamp === "string" &&
    typeof obj.error === "string" &&
    typeof obj.message === "string" &&
    typeof obj.errorCode === "string" &&
    typeof obj.status === "number"
  );
}
