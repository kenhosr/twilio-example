package com.veeva.dc.qa;

import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.CallCreator;
import com.twilio.type.PhoneNumber;

import java.net.URI;

public class MakeCall {

    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC142f07e0c2b3252aea2d089dd3c09ba2";
    public static final String AUTH_TOKEN = "3d74714e9d1a4c1a98d596a99bfc4dea";

    public static void main(String[] args) {

        TwilioRestClient client = new TwilioRestClient.Builder(ACCOUNT_SID, AUTH_TOKEN).build();

        PhoneNumber to = new PhoneNumber("+14164070258"); // Replace with your phone number
        PhoneNumber from = new PhoneNumber("+16475575248"); // Replace with a Twilio number
        URI uri = URI.create("http://demo.twilio.com/welcome/voice/");

        // Make the call
        Call call = new CallCreator(to, from, uri).create(client);
        // Print the call SID (a 32 digit hex like CA123..)
        System.out.println(call.getSid());
    }
}