package net.johnewart.tokoro.core.serialization;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vividsolutions.jts.geom.Point;

import java.io.IOException;

public class CustomPointSerializer extends JsonSerializer<Point> {
    public CustomPointSerializer() {
    }

    @Override
    public void serialize(Point value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonGenerationException {
        String result = "{'x': " + value.getX() + ", 'y': " + value.getY() + "}";
        jgen.writeString(result);
    }
}