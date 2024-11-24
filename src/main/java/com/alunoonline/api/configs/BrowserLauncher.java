package com.alunoonline.api.configs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URISyntaxException;

@Component
@Slf4j
public class BrowserLauncher{

    @Value("${server.port:8080}")
    private int port;

    @EventListener(ApplicationReadyEvent.class)

    public void startBrowser() {

        String url = "http://localhost:" + port;
        try {
            if (Desktop.isDesktopSupported()) {
                // java with desktop support
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(new URI(url));
            } else {
                // no java desktop support
                // fall back into dark ages
                String osName = System.getProperty("os.name");

                if (osName.toLowerCase().contains("linux")) {
                    // probably linux/unix
                    Runtime runtime = Runtime.getRuntime();
                    runtime.exec("xdg-open " + url);
                } else if (osName.toLowerCase().contains("windows")) {
                    // older windows
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
                } else if (osName.toLowerCase().contains("mac")) {
                    // probably mac os
                    Class.forName("com.apple.eio.FileManager").getDeclaredMethod("openURL", String.class).invoke(null,
                            url);
                }
            }
        } catch (IOException | URISyntaxException | IllegalAccessException | IllegalArgumentException
                 | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            log.error("Could not start browser!", e);
            e.fillInStackTrace();
        }
    }

}
