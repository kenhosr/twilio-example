package com.veeva.dc.qa;


import com.twilio.twiml.Dial;
import com.twilio.twiml.Number;
import com.twilio.twiml.Say;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class TwilioHandleKeyServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(TwilioHandleKeyServlet.class.getName());

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String digits = request.getParameter("Digits");
        VoiceResponse twiml;
        LOGGER.info("switched to handle-key");
        // Check if the user pressed "1" on their phone
        if (digits != null && digits.equals("1")) {
            LOGGER.info("digits=" + digits);
            // Connect 310 555 1212 to the incoming caller.
            Number number = new Number.Builder("+14164070258").build();
            Dial dial = new Dial.Builder().number(number).build();

            // If the above dial failed, say an error message.
            Say say = new Say.Builder("The call failed, or the remote party hung up. Goodbye.").build();

            twiml = new VoiceResponse.Builder().dial(dial).say(say).build();
        } else {
            // If the above dial failed, say an error message.
            Say say = new Say.Builder("The call failed, or the remote party hung up. Goodbye.").build();

            twiml = new VoiceResponse.Builder().say(say).build();
        }

        response.setContentType("application/xml");
        try {
            response.getWriter().print(twiml.toXml());
        } catch (TwiMLException e) {
            e.printStackTrace();
        }
    }
}
