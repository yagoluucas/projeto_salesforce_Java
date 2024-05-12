package org.resource;
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

    // no front end é necessário pegar os paises cadastrados para construir o objeto de teste gratis
    private static final PaisRepository paisRepository = new PaisRepository();
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
