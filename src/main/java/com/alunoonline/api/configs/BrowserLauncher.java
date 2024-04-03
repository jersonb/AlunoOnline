package com.alunoonline.api.configs;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Component

public class BrowserLauncher{

    @EventListener(ApplicationReadyEvent.class)

    public void launchBrowser() throws IOException, URISyntaxException {

        System.setProperty("java.awt.headless", "false");

        var desktop = Desktop.getDesktop();

        try{

            desktop.browse(new URI("http://localhost:8080"));

        }catch(Exception e){
            e.printStackTrace();
            throw e;

        }

    }

}
