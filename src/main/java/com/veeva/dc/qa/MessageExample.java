package com.veeva.dc.qa;

/**
 * Created by kenveeva on 2016-10-12.
 */
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class MessageExample {
    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "AC142f07e0c2b3252aea2d089dd3c09ba2";
    public static final String AUTH_TOKEN = "3d74714e9d1a4c1a98d596a99bfc4dea";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+14164070258"), new PhoneNumber("+16475575248"),
                        "This is the ship that made the Kessel Run in fourteen parsecs?")
                .create();

        System.out.println(message.getSid());
    }
}