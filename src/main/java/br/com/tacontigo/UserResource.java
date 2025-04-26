package br.com.tacontigo;

import java.util.List;

import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/users")
public class UserResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PanacheMongoEntityBase> hello() {
        return User.listAll();
    }
}
