package net.johnewart.tokoro;

import net.johnewart.tokoro.core.*;
import net.johnewart.tokoro.db.LocationDAO;
import net.johnewart.tokoro.resources.LocationResource;
import net.johnewart.tokoro.resources.LocationsResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import com.yammer.dropwizard.hibernate.HibernateBundle;
import com.yammer.dropwizard.migrations.MigrationsBundle;

import java.text.SimpleDateFormat;

public class TokoroService extends Service<TokoroConfiguration> {

    public static void main(String[] args) throws Exception {
        String current = new java.io.File( "." ).getCanonicalPath();
        System.out.println("Current dir:"+current);
        new TokoroService().run(args);
    }

    private final HibernateBundle<TokoroConfiguration> hibernateBundle =
            new HibernateBundle<TokoroConfiguration>(Location.class) {
                @Override
                public DatabaseConfiguration getDatabaseConfiguration(TokoroConfiguration configuration) {
                    return configuration.getDatabaseConfiguration();
                }
            };


    @Override
    public void initialize(Bootstrap<TokoroConfiguration> bootstrap) {
        bootstrap.setName("tokoro");
        bootstrap.addBundle(new MigrationsBundle<TokoroConfiguration>() {
            @Override
            public DatabaseConfiguration getDatabaseConfiguration(TokoroConfiguration configuration) {
                return configuration.getDatabaseConfiguration();
            }
        });
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(TokoroConfiguration configuration,
                    Environment environment) throws ClassNotFoundException {
        final LocationDAO locationDAO = new LocationDAO(hibernateBundle.getSessionFactory());


        environment.addResource(new LocationResource(locationDAO));
        environment.addResource(new LocationsResource(locationDAO));
    }

}