export type ErrorResponse = {
  timestamp: string;
  error: string;
  message: string;
  errorCode: string;
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
