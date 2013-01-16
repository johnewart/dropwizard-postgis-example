package net.johnewart.tokoro;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class TokoroConfiguration extends Configuration {
    @NotEmpty
    @JsonProperty
    private String environment;

    @Valid
    @NotNull
    @JsonProperty("database")
    private DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration();

    public String getEnvironment() {
        return environment;
    }

    public DatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }

    public void setDatabaseConfiguration(DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }
}
