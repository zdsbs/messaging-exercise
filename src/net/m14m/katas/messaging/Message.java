package net.m14m.katas.messaging;

import java.io.PrintWriter;

public class Message {
    private final Address address;
    private final Body body;

    public Message(Address address, Body body) {
        this.address = address;
        this.body = body;
    }

    public boolean isValid() {
        return address.isValid();
    }

    public void writeTo(PrintWriter connection) {
        connection.println("connect smtp");
        connection.println("To: " + address);
        connection.println();
        connection.println(body);
        connection.println();
        connection.println("disconnect");
    }
}