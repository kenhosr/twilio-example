package com.veeva.dc.qa;


import com.twilio.twiml.Gather;
import com.twilio.twiml.Method;
import com.twilio.twiml.Play;
import com.twilio.twiml.Say;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


public class HelloWorld extends HttpServlet {

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
        String message;
        if (knownCaller == null) {
            // Use a generic message
            message = "Hello Monkey";
        } else {
            // Use the caller's name
            message = "Hello " + knownCaller;
        }

        // Create a TwiML response and add our friendly message.
        VoiceResponse twiml = new VoiceResponse.Builder()
                // Play an MP3 for incoming callers.
                .play(new Play.Builder("http://appsenseca.cloudapp.net/calls_confirmation_v2.mp3").build())
                .play(new Play.Builder("http://appsenseca.cloudapp.net/custom_voice.mp3").build())
                .say(new Say.Builder(message).build())
                .gather(new Gather.Builder()
                        .action("/handle-key")
                        .method(Method.POST)
                        .numDigits(1)
                        .say(new Say
                                .Builder("To speak to a real monkey, press 1. Press any other key to start over.")
                                .build())
                        .build()
                )
                .build();

        response.setContentType("application/xml");
        try {
            response.getWriter().print(twiml.toXml());
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
    }
}
