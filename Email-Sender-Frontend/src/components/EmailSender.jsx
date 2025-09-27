import React, { useState } from "react";
import toast from "react-hot-toast";
import { sendEmail } from "../services/helper"; // make sure path is correct


function EmailSender() {
  const [emailData, setEmailData] = useState({ to: "", subject: "", message: "" });


  async function handleFieldChange(event, name) {
    setEmailData({ ...emailData, [name]: event.target.value });
  }

  async function handleSubmit(event) {
    event.preventDefault();
    if (emailData.to == '' || emailData.subject == '' || emailData.message == '') {
      toast.error("Invalid Field !!");
      return;
    }
    toast.success("Email data ready to send ");
    //  send email using api 

    try {
      await sendEmail(emailData)
      toast.success("Email Send successfully.");
      setEmailData({to: "",
         subject: "",
          message: "" });
      toast.success("send another one.");

    } catch (error) {
      console.log(error)
      toast.error("Email not send");

    }


    console.log(emailData);
  }

  function handleClear() {
    setEmailData({ to: "", subject: "", message: "" });
  }

  return (
    <div className="w-full min-h-screen flex justify-center items-center">
      <div className="email_card md:w-1/3 w-full mx-4 bg-white -mt-10 p-4 rounded-lg border shadow">
        <h1 className="text-gray-900 text-3xl">Email Sender</h1>
        <p className="text-gray-700">
          Send email to your favourite person with your own app...
        </p>
        <form onSubmit={handleSubmit}>
          {/* To */}
          <div className="input_field mt-4">
            <label htmlFor="to" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
              To
            </label>
            <input
              type="text"
              id="to"
              placeholder="Enter here"
              className="block w-full p-4 text-gray-900 border border-gray-300 rounded-lg bg-gray-50"
              value={emailData.to}
              onChange={(e) => handleFieldChange(e, "to")}
            />
          </div>

          {/* Subject */}
          <div className="input_field mt-4">
            <label htmlFor="subject" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
              Subject
            </label>
            <input
              type="text"
              id="subject"
              placeholder="Enter here"
              className="block w-full p-4 text-gray-900 border border-gray-300 rounded-lg bg-gray-50"
              value={emailData.subject}
              onChange={(e) => handleFieldChange(e, "subject")}
            />
          </div>

          {/* Message */}
          <div className="form_field mt-4">
            <label htmlFor="message" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
              Your message
            </label>
            <textarea
              id="message"
              rows="8"
              placeholder="Write your thoughts here..."
              className="block p-2.5 w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300"
              value={emailData.message}
              onChange={(e) => handleFieldChange(e, "message")}
            />
          </div>

          <div className="button_container flex justify-center gap-3 mt-4">
            <button type="submit" className="hover:bg-blue-900 text-white bg-blue-700 px-3 py-2 rounded">
              Send Email
            </button>
            <button type="button" onClick={handleClear} className="hover:bg-gray-900 text-white bg-gray-700 px-3 py-2 rounded">
              Clear
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default EmailSender;
