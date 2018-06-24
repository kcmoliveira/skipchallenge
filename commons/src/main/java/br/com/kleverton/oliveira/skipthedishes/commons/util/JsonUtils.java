package br.com.kleverton.oliveira.skipthedishes.commons.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        JsonUtils.objectMapper.findAndRegisterModules().configure( SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false );
    }

    public static <T> T convertFromJson(String json, Class<T> target) {
        try {
            return JsonUtils.objectMapper.readValue( json, target );
        } catch (IOException e) {
            throw new RuntimeException( String.format( "Error converting from JSON[%s] to %s.", json, target ) );
        }
    }

    public static String convertToPrettyJson(Object object) {
        try {
            return JsonUtils.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString( object );
        } catch (IOException e) {
            throw new RuntimeException( "Error converting object to JSON." );
        }
    }

    public static String convertToJson(Object object) {
        try {
            return JsonUtils.objectMapper.writeValueAsString( object );
        } catch (IOException e) {
            throw new RuntimeException( "Error converting object to JSON." );
        }
    }
}