package org.repository;
import org.configuration.OracleDatabase;
import org.entities.AtividadeDoSite;
import org.utils._Logger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AtividadeDoSiteRepository implements  _BaseRepository<AtividadeDoSite>, _Logger<AtividadeDoSite> {
    OracleDatabase oracleDatabase = new OracleDatabase();

    private static final TesteGratisRepository testeGratisRepository = new TesteGratisRepository();
//    private static final SuporteRepository suporteRepository = new SuporteRepository();
    private static final String TABLE_NAME = "ATIVIDADE_DO_SITE";

    private static final Map<String, String> TABLE_COLUMNS = Map.of(
        "ID", "ID_ATIVIDADE",
        "OPORTUNIDADE", "OPORTUNIDADE",
        "DATA", "DATA",
        "ID_SUPORTE", "ID_SUPORTE",
        "ID_TESTE_GRATIS", "ID_TESTE_GRATIS"
    );

    @Override
    public void Create(AtividadeDoSite entidade) {
    }

    @Override
    public List<AtividadeDoSite> ReadAll() {
        List<AtividadeDoSite> atividadesDoSite = new ArrayList<>();
        try(var connection = oracleDatabase.getConnection()){

        }catch (SQLException e) {
            logError("Erro ao recuperar dados: " + e.getMessage());
        }
        return atividadesDoSite;
    }

    @Override
    public AtividadeDoSite Read(int id) {
        return null;
    }

    @Override
    public void Update(AtividadeDoSite entidade) {

    }

    @Override
    public void Delete(int id) {

    }
}
