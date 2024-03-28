package org.repository;
import org.configuration.OracleDatabase;
import org.entities.Suporte;
import org.utils._Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SuporteRepository implements _BaseRepository<Suporte>, _Logger<Suporte> {
    OracleDatabase oracleDatabase = new OracleDatabase();
    private static final PaisRepository paisRepository = new PaisRepository();
    private static final Map<String, String> TABLE_COLUMNS = Map.of(
            "ID", "ID_SUPORTE",
            "NOME_EMPRESA", "NOME_EMPRESA",
            "NOME_PESSOA", "NOME_PESSOA",
            "SOBRENOME_PESSOA", "SOBRENOME_PESSOA",
            "DESCRICAO", "DESCRICAO",
            "ID_PAIS", "ID_PAIS"
    );

    private static final String TABLE_NAME = "SUPORTE";
    @Override
    public void Create(Suporte entidade) {
        try(var connection = oracleDatabase.getConnection()) {
            var st  = connection.prepareStatement("INSERT INTO " + TABLE_NAME +
                    " (" + TABLE_COLUMNS.get("NOME_EMPRESA") + ", " + TABLE_COLUMNS.get("NOME_PESSOA") + ", " +
                    TABLE_COLUMNS.get("SOBRENOME_PESSOA") + ", " + TABLE_COLUMNS.get("DESCRICAO") + ", " +
                    TABLE_COLUMNS.get("ID_PAIS") + ") VALUES (?, ?, ?, ?, ?)");
            st.setString(1, entidade.getNomeEmpresa());
            st.setString(2, entidade.getNomePessoa());
            st.setString(3, entidade.getSobrenomePessoa());
            st.setString(4, entidade.getDescricao());
            st.setInt(5, entidade.getPais().getId());
            var returnInsert = st.executeUpdate();
            logInfo("Inserção de suporte feita com sucesso, linhas afetadas: " + returnInsert);
        }catch (SQLException e) {
            logError("Erro ao inserir suporte: " + e.getMessage());
        }
    }

    @Override
    public List<Suporte> ReadAll() {
        List<Suporte> suportes = new ArrayList<>();
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("SELECT * FROM " + TABLE_NAME);
            var resultQuery = st.executeQuery();
            while (resultQuery.next()) {
                Suporte suporte = new Suporte(
                        resultQuery.getInt(TABLE_COLUMNS.get("ID")),
                        resultQuery.getString(TABLE_COLUMNS.get("NOME_EMPRESA")),
                        resultQuery.getString(TABLE_COLUMNS.get("NOME_PESSOA")),
                        resultQuery.getString(TABLE_COLUMNS.get("SOBRENOME_PESSOA")),
                        resultQuery.getString(TABLE_COLUMNS.get("DESCRICAO")),
                        paisRepository.Read(resultQuery.getInt(TABLE_COLUMNS.get("ID_PAIS")))
                );
                suportes.add(suporte);
            }
            logInfo("Recuperação de suportes feita com sucesso: " + suportes.size() + " linhas recuperados");
        }catch (Exception e) {
            logError("Erro ao recuperar suportes: " + e.getMessage());
        }
        return suportes;
    }

    @Override
    public Suporte Read(int id) {
        Suporte suporte = new Suporte();
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("SELECT * FROM " +
                    TABLE_NAME + " WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            st.setInt(1, id);
            var resultQuery = st.executeQuery();
            suporte = new Suporte(
                    resultQuery.getInt(TABLE_COLUMNS.get("ID")),
                    resultQuery.getString(TABLE_COLUMNS.get("NOME_EMPRESA")),
                    resultQuery.getString(TABLE_COLUMNS.get("NOME_PESSOA")),
                    resultQuery.getString(TABLE_COLUMNS.get("SOBRENOME_PESSOA")),
                    resultQuery.getString(TABLE_COLUMNS.get("DESCRICAO")),
                    paisRepository.Read(resultQuery.getInt(TABLE_COLUMNS.get("ID_PAIS")))
            );
            logInfo("Sucesso ao recuperar teste grátis");
        }catch (SQLException e) {
            logError("Erro ao recuperar suporte: " + e.getMessage());
        }
        return suporte;
    }

    @Override
    public void Update(Suporte entidade) {
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("UPDATE " + TABLE_NAME + " SET " +
                     TABLE_COLUMNS.get("NOME_EMPRESA") + " = ?" + TABLE_COLUMNS.get("NOME_PESSOA") + " = ?" +
                     TABLE_COLUMNS.get("SOBRENOME_PESSOA") + " = ?" + TABLE_COLUMNS.get("DESCRICAO") + " = ?" +
                     TABLE_COLUMNS.get("ID_PAIS") + " = ?" + " WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
                    st.setString(1, entidade.getNomeEmpresa());
                    st.setString(2, entidade.getNomePessoa());
                    st.setString(3, entidade.getSobrenomePessoa());
                    st.setString(4, entidade.getDescricao());
                    st.setInt(5, entidade.getPais().getId());
                    st.setInt(6, entidade.getId());
                    var returnUpdate = st.executeUpdate();
                    logInfo("Atualização de suporte feita com sucesso, linhas afetadas: " + returnUpdate);
        }catch (SQLException e){
            logError("Erro ao atualizar suporte : " + e.getMessage());
        }
    }

    @Override
    public void Delete(int id) {
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("DELETE FROM " + TABLE_NAME +
                    " WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            st.setInt(1, id);
            var returnDelete = st.executeUpdate();
            logInfo("Sucesso ao excluir suporte, linhas afetadas: " + returnDelete);
        }catch (SQLException e) {
            logError("Erro ao excluir suporte: " + e.getMessage());
        }
    }
}
