package com.veeva.dc.qa;


import com.twilio.twiml.Body;
import com.twilio.twiml.Message;
import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.TwiMLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


public class IncomingMessage extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Create a dict of people we know.
        HashMap<String, String> callers = new HashMap<String, String>();
        callers.put("+14169533717", "Edward");
        callers.put("+14168309420", "Keith");
        callers.put("+14162705499", "Winnie");
        callers.put("+16479077528", "Sihem");
        callers.put("+14164070258", "Ken");

        String fromNumber = request.getParameter("From");
        String knownCaller = callers.get(fromNumber);
        String messageString;
        if (knownCaller == null) {
            // Use a generic message
            messageString = "Hello Monkey";
        } else {
            // Use the caller's name
            messageString = "Hello " + knownCaller;
        }


        Message message = new Message.Builder()
                .body(new Body(messageString))
                .build();
        // Create a TwiML response and add our friendly message.
        MessagingResponse twiml = new MessagingResponse.Builder()
                .message(message).build();

        response.setContentType("application/xml");
        try {
            response.getWriter().print(twiml.toXml());
        } catch (TwiMLException e) {
            e.printStackTrace();
        }

    }
}
