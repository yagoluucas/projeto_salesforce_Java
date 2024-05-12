package org.resource;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.entities.TesteGratis;
import org.repository.AtividadeDoSiteRepository;
import org.repository.TesteGratisRepository;
import org.services.TesteGratisService;
import java.util.List;
@Path("testegratis")
public class TesteGratisResource {
    TesteGratisRepository testeGratisRepository = new TesteGratisRepository();
    AtividadeDoSiteRepository atividadeDoSiteRepository = new AtividadeDoSiteRepository();
    TesteGratisService testeGratisService = new TesteGratisService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TesteGratis> getTesteGratis() {
        return testeGratisRepository.ReadAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TesteGratis getTesteGratis(@PathParam("id") int id) {
        return testeGratisRepository.Read(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public JsonObject addTesteGratis(TesteGratis testeGratis) {
        TesteGratis resultado = testeGratisService.Create(testeGratis);
        if (resultado == null) {

            return Json.createObjectBuilder()
                    .add("message", "Email já cadastrado!")
                    .build();
        }

        atividadeDoSiteRepository.Create(resultado.getId(), "teste");
        return Json.createObjectBuilder()
                .add("message", "Teste grátis cadastrado com sucesso! Iremos entrar em contato em breve")
                .build();

    }
}
