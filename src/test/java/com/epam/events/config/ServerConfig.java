package com.epam.events.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:./application.properties", "classpath:default.properties"})
public interface ServerConfig extends Config {

    @Key("ui.testing.base.uri")
    @DefaultValue("https://events.epam.com")
    String getBaseUriProperties();


    @Key("ui.testing.waiting.time")
    @DefaultValue("10")
    int getWaitingTimeProperties();

    @Key("ui.testing.implicitly.waiting.time")
    @DefaultValue("4")
    int getImplicitlyWaitingTimeProperties();



}
