package org.example;
import jakarta.json.Json;
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

    // metodo responsavel por retornar todos os testes gratis cadastrados
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
    public String addTesteGratis(TesteGratis testeGratis) {
        TesteGratis resultado = testeGratisService.Create(testeGratis);
        if (resultado != null) {
            atividadeDoSiteRepository.Create(resultado.getId(), "teste");
            return "Teste grátis cadastrado com sucesso!";
        }
        return "Email já cadastrado!";
    }
}
