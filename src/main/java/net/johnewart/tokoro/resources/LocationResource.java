package net.johnewart.tokoro.resources;

import com.google.common.base.Optional;
import com.sun.jersey.api.NotFoundException;
import net.johnewart.tokoro.core.Location;
import net.johnewart.tokoro.db.LocationDAO;
import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/locations/{locationId}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LocationResource {
    private final LocationDAO locationDAO;

    public LocationResource(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    @GET
    @Timed
    @UnitOfWork
    public Location fetch(@PathParam("locationId") LongParam locationId) {
        final Optional<Location> location = locationDAO.findById(locationId.get());
        if (!location.isPresent()) {
            throw new NotFoundException("No such location.");
        }
        return location.get();
    }
}
