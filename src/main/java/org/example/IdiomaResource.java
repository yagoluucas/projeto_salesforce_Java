package org.example;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.entities.Idioma;
import org.repository.IdiomaRepository;

import java.util.List;

@Path("idioma")
public class IdiomaResource {
    private static final IdiomaRepository idiomaRepository = new IdiomaRepository();

    // no front end é necessário pegar os idiomas cadastrados para construir o objeto de teste gratis
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public static List<Idioma> getIdiomas() {
        return idiomaRepository.ReadAll();
    }

    @GET
    @Produces
    @Path("{id}")
    public static Idioma getIdioma(@PathParam("id") int id) {
        return idiomaRepository.Read(id);
    }
}
