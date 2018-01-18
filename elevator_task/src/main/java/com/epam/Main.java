package com.epam;

import org.apache.log4j.Logger;


public class Main {

    private final static Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {

        Initializer init = new Initializer();
        init.start();


    }
}
