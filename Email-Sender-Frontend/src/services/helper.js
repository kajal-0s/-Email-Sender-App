import axios from "axios";

export const customAxios = axios.create({
  baseURL: "http://localhost:8080/api/v1/email", // backend base URL
});

export async function sendEmail(emailData) {
  return (await customAxios.post("/send", emailData)).data; // ONLY "/send"
}
