package net.johnewart.tokoro.core;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import net.johnewart.tokoro.core.serialization.CustomPointSerializer;
import com.vividsolutions.jts.geom.Point;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "locations", schema = "tokoro")
@NamedQueries({
        @NamedQuery(
                name = "net.johnewart.tokoro.core.Location.findAll",
                query = "SELECT l FROM Location l"
        ),
        @NamedQuery(
                name = "net.johnewart.tokoro.core.Location.findById",
                query = "SELECT l FROM Location l WHERE l.id = :id"
        )
})

public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "locations_id_seq_name")
    @SequenceGenerator(name = "locations_id_seq_name", sequenceName = "tokoro.locations_id_seq", allocationSize = 1)
    private long id;

    @Column(name = "street1", nullable = false)
    private String street1;

    @Column(name = "street2", nullable = false)
    private String street2;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "zipcode", nullable = false)
    private int zipcode;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "point", nullable = true)
    @Type(type = "org.hibernate.spatial.GeometryType")
    @JsonSerialize(using = CustomPointSerializer.class)
    private Point point;

    public String toString()
    {
        return street1 + " " + street2 + " " + city + ", " + state + " " + zipcode;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


}




