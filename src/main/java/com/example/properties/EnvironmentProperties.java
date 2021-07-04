package com.example.properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("file:src/main/resources/${env}_environment.properties")
public interface EnvironmentProperties extends Config {
    String url();

    String home_page();

    String username();

    String password();  

    String username_locked();

    String browser();

    Boolean headless();
}
