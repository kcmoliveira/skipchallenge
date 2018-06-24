package br.com.kleverton.oliveira.skipthedishes.commons.filters;

import io.jsonwebtoken.Jwts;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.util.Optional;

import static br.com.kleverton.oliveira.skipthedishes.commons.security.SecurityConstraints.*;

public class JWTAuthorizationFilter {
    public static void doFilter(Request req, Response res) {
        final Optional<String> token = Optional.ofNullable( req.headers( AUTHORIZATION_HEADER ) );

        if ( ! token.isPresent() ) {
            Spark.halt(401 );

            return;
        }

        final Optional<String> user = Optional.ofNullable(
                Jwts.parser()
                        .setSigningKey( SECRET )
                        .parseClaimsJws( token.get().replace( TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject()
        );

        if ( ! user.isPresent() ) {
            Spark.halt(401 );

            return;
        }
    }
}