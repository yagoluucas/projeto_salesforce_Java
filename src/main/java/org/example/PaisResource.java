package org.example;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.entities.Pais;
import org.repository.PaisRepository;

import java.util.List;

@Path("pais")
public class PaisResource {

    public static PaisRepository paisRepository = new PaisRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public static List<Pais> getPaises() {
        return paisRepository.ReadAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static Pais getPais(@PathParam("id") int id) {
        return paisRepository.Read(id);
    }
}
