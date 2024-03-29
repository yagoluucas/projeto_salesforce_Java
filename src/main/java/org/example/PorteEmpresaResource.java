package org.example;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.entities.PorteEmpresa;
import org.repository.PorteEmpresaRepository;

import java.util.List;

@Path("porteempresa")
public class PorteEmpresaResource {
    private static PorteEmpresaRepository portesEmpresa= new PorteEmpresaRepository();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public static List<PorteEmpresa> portesEmpresa() {
        return portesEmpresa.ReadAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static PorteEmpresa porteEmpresa(@PathParam("id") int id) {
        return portesEmpresa.Read(id);
    }
}
