package org.example;
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

    // metodo responsavel por retornar todos os testes gratis cadastrados
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TesteGratis> getTesteGratis() {
        return testeGratisRepository.ReadAll();
    }

    // metodo responsavel por retornar um teste gratis especifico
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public TesteGratis getTesteGratis(@PathParam("id") int id) {
        return testeGratisRepository.Read(id);
    }

    // metodo responsavel por adicionar um teste gratis
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public JsonObject addTesteGratis(TesteGratis testeGratis) {
        // envia uma mensagem de erro caso o email ou telefone sejam invalidos
        if(!testeGratis.isEmailValid(testeGratis.getEmail()))
            return Json.createObjectBuilder()
                    .add("message", "Email inválido!")
                    .build();
        if(!testeGratis.isPhoneValid(testeGratis.getTelefone()))
            return Json.createObjectBuilder()
                    .add("message", "Telefone inválido!")
                    .build();
        TesteGratis resultado = testeGratisService.Create(testeGratis);
        if (resultado != null) {
            // cria uma ativiade no site para o teste gratis de forma automatica
            atividadeDoSiteRepository.Create(resultado.getId(), "teste");
            return Json.createObjectBuilder()
                    .add("message", "Teste grátis cadastrado com sucesso! Iremos entrar em contato em breve")
                    .build();
        }
        // envia uma mensagem de erro caso o email já esteja cadastrado
        return Json.createObjectBuilder()
                .add("message", "Email já cadastrado!")
                .build();
    }
}
