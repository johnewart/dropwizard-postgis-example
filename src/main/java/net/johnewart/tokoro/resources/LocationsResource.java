package net.johnewart.tokoro.resources;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.LatLng;
import net.johnewart.tokoro.core.Location;
import net.johnewart.tokoro.db.LocationDAO;
import com.vividsolutions.jts.geom.*;
import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/locations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LocationsResource {
    private final LocationDAO locationDAO;

    public LocationsResource(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    @GET
    @Timed
    public String fetch(@PathParam("location_id") Integer location_id) {
        return "{'error': 'No data... sorry!'}";
    }

    @POST
    @UnitOfWork
    public Location createLocation(Location location) {
        System.err.println("Geocoding " + location.toString());
        // Geocode address here.
        final Geocoder geocoder = new Geocoder();
        GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().setAddress(location.toString()).setLanguage("en").getGeocoderRequest();
        GeocodeResponse geocoderResponse = geocoder.geocode(geocoderRequest);
        LatLng latLong = geocoderResponse.getResults().get(0).getGeometry().getLocation();
        // Hard-coded to use EPSG:4326
        GeometryFactory gf = new GeometryFactory(new PrecisionModel(), 4326);
        Point p = gf.createPoint(new Coordinate(latLong.getLat().doubleValue(), latLong.getLng().doubleValue()));
        location.setPoint(p);
        return locationDAO.create(location);
    }

}


