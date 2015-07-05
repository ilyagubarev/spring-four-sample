package com.ilyagubarev.samples.springfour.storage.server.repositories;

import java.io.Serializable;

public class Bag implements Serializable {

    private String value = null;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
