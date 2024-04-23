package org.repository;
import org.configuration.OracleDatabase;
import org.configuration._Logger;
import org.entities.AtividadeDoSite;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AtividadeDoSiteRepository implements  _BaseRepository<AtividadeDoSite>, _Logger<AtividadeDoSite> {
    OracleDatabase oracleDatabase = new OracleDatabase();
    private static final TesteGratisRepository testeGratisRepository = new TesteGratisRepository();
    private static final SuporteRepository suporteRepository = new SuporteRepository();
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
        try(var connection = oracleDatabase.getConnection()){
            var st = connection.prepareStatement("INSERT INTO " + TABLE_NAME + " (" +
                    TABLE_COLUMNS.get("OPORTUNIDADE") + ", " + TABLE_COLUMNS.get("DATA") + ", " +
                    TABLE_COLUMNS.get("ID_SUPORTE") + ", " +
                    TABLE_COLUMNS.get("ID_TESTE_GRATIS") + ") VALUES (?, ?, ?, ?)");
            st.setString(1, String.valueOf(entidade.getOportunidade()));
            st.setDate(2, new java.sql.Date(entidade.getData().getTime()));
            if (entidade.getSuporte() != null) {
                st.setInt(3, entidade.getSuporte().getId());
                st.setNull(4, java.sql.Types.INTEGER);
            }
            else {
                st.setNull(3, java.sql.Types.INTEGER);
                st.setInt(4, entidade.getTesteGratis().getId());
            }
        }catch (SQLException e) {
            logError("Erro ao inserir atividade do site: " + e.getMessage());
        }
    }

    public void Create(int id, String tipoAtividade){
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("INSERT INTO " +
                    TABLE_NAME + " (" + TABLE_COLUMNS.get("OPORTUNIDADE") + ", " + TABLE_COLUMNS.get("DATA") + ", " +
                    TABLE_COLUMNS.get("ID_SUPORTE") + ", " + TABLE_COLUMNS.get("ID_TESTE_GRATIS") + ") VALUES (?, ?, ?, ?)");
            st.setString(1, tipoAtividade.equalsIgnoreCase("teste") ? "S" : "N");
            st.setDate(2, new java.sql.Date(System.currentTimeMillis()));
            if (tipoAtividade.equalsIgnoreCase("teste")) {
                st.setNull(3, java.sql.Types.INTEGER);
                st.setInt(4, id);
            }
            else {
                st.setInt(3, id);
                st.setNull(4, java.sql.Types.INTEGER);
            }
            var returnInsert = st.executeUpdate();
            logInfo("Inserção de atividade do site feita com sucesso, linhas afetadas: " + returnInsert);
        }catch (SQLException e) {
            logError("Erro ao inserir atividade do site: " + e.getMessage());
        }
    }

    @Override
    public List<AtividadeDoSite> ReadAll() {
        List<AtividadeDoSite> atividadesDoSite = new ArrayList<>();
        try(var connection = oracleDatabase.getConnection()){
            var st = connection.prepareStatement("SELECT * FROM " + TABLE_NAME);
            var resultQuery = st.executeQuery();
            while(resultQuery.next()) {
                AtividadeDoSite atividadeDoSite = new AtividadeDoSite(
                        resultQuery.getInt(TABLE_COLUMNS.get("ID")),
                        resultQuery.getString(TABLE_COLUMNS.get("OPORTUNIDADE")).charAt(0),
                        resultQuery.getDate(TABLE_COLUMNS.get("DATA")),
                        suporteRepository.Read(resultQuery.getInt(TABLE_COLUMNS.get("ID_SUPORTE"))),
                        testeGratisRepository.Read(resultQuery.getInt(TABLE_COLUMNS.get("ID_TESTE_GRATIS")))
                );
                atividadesDoSite.add(atividadeDoSite);
            }
            logInfo("Recuperação de dados feita com sucesso" + resultQuery.getFetchSize() + " registros encontrados.");

        }catch (SQLException e) {
            logError("Erro ao recuperar dados: " + e.getMessage());
        }
        return atividadesDoSite;
    }

    @Override
    public AtividadeDoSite Read(int id) {
        AtividadeDoSite atividadeDoSite = new AtividadeDoSite();
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("SELECT * FROM " + TABLE_NAME +
                    " WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            st.setInt(1, id);
            var resultQuery = st.executeQuery();
            if (resultQuery.next()) {
                atividadeDoSite = new AtividadeDoSite(
                        resultQuery.getInt(TABLE_COLUMNS.get("ID")),
                        resultQuery.getString(TABLE_COLUMNS.get("OPORTUNIDADE")).charAt(0),
                        resultQuery.getDate(TABLE_COLUMNS.get("DATA")),
                        suporteRepository.Read(resultQuery.getInt(TABLE_COLUMNS.get("ID_SUPORTE"))),
                        testeGratisRepository.Read(resultQuery.getInt(TABLE_COLUMNS.get("ID_TESTE_GRATIS")))
                );
            }
            logInfo("Recuperação de dados feita com sucesso: " + atividadeDoSite);
        }catch (SQLException e) {
            logError("Erro ao recuperar dados: " + e.getMessage());
        }
        return atividadeDoSite;
    }

    @Override
    public void Update(AtividadeDoSite entidade) {
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("UPDATE " + TABLE_NAME + " SET " +
                    TABLE_COLUMNS.get("OPORTUNIDADE") + " = ?, " + TABLE_COLUMNS.get("DATA") + " = ?, " +
                    TABLE_COLUMNS.get("ID_SUPORTE") + " = ?, " + TABLE_COLUMNS.get("ID_TESTE_GRATIS") + " = ? " +
                    " WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            st.setString(1, String.valueOf(entidade.getOportunidade()));

        }catch (SQLException e) {
            logError("Erro ao atualizar atividade do site: " + e.getMessage());
        }
    }

    @Override
    public void Delete(int id) {
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("DELETE FROM " + TABLE_NAME +
                    " WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            st.setInt(1, id);
            var returnDelete = st.executeUpdate();
            logInfo("Deleção de atividade do site feita com sucesso, linhas afetadas: " + returnDelete);
        }catch (SQLException e) {
            logError("Erro ao deletar atividade do site: " + e.getMessage());
        }
    }
}
