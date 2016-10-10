package com.lazurey.www.samples.foundation;

import javax.jcr.Node;

public class Achelous extends ReactComponent {

    public Achelous(Node component, Node current) {
        super(component, current);
    }

    @Override
    public String getDataAsJson() throws Exception {
        String message = current.hasProperty("message") ? current.getProperty("message").getString() : "No message for you.";
        return "{\"message\": \"" + message + "\"}";
    }
}
