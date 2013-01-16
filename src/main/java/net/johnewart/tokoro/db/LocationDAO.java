package net.johnewart.tokoro.db;

import com.google.common.base.Optional;
import net.johnewart.tokoro.core.Location;
import com.yammer.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class LocationDAO extends AbstractDAO<Location> {
    public LocationDAO(SessionFactory factory)
    {
        super(factory);
    }

    public Optional<Location> findById(Long id)
    {
        return Optional.fromNullable(get(id));
    }

    public Location create(Location location)
    {
        return persist(location);
    }

    public List<Location> findAll()
    {
        return list(namedQuery("net.johnewart.tokoro.core.Location.findAll"));
    }
}
