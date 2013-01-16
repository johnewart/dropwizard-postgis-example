package net.johnewart.tokoro.db;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.db.DatabaseConfiguration;

public class GISDatabaseConfiguration extends DatabaseConfiguration {

    @JsonProperty
    private String dialect;
}
