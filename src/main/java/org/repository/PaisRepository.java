package org.repository;
import org.configuration.OracleDatabase;
import org.configuration._Logger;
import org.entities.Pais;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PaisRepository implements _BaseRepository<Pais>, _Logger<Pais> {
    OracleDatabase conecaoBanco = new OracleDatabase();

    private static final Map<String, String> TABLE_COLUMNS = Map.of(
        "ID", "ID_PAIS",
        "DESCRICAO", "DESCRICAO"
    );
    private static final String TABLE_NAME = "PAIS";
    @Override
    public void Create(Pais entidade) {
        try(var connection = conecaoBanco.getConnection()) {
            var statement = connection.prepareStatement(
                    "INSERT INTO " + TABLE_NAME + " (" + TABLE_COLUMNS.get("DESCRICAO") + ") VALUES (?)");
            statement.setString(1, entidade.getDescricao());
            var returnInsert = statement.executeUpdate();
            logInfo("Inserção feita com sucesso, linhas afetadas : " + returnInsert);
        } catch (SQLException e) {
            logError("Erro ao inserir :" + e.getMessage());
        }
    }

    @Override
    public List<Pais> ReadAll() {
        List<Pais> paises = new ArrayList<>();
        try(var connection = conecaoBanco.getConnection()) {
            var st = connection.prepareStatement("SELECT * FROM " + TABLE_NAME);
            var resultQuery = st.executeQuery();
            while(resultQuery.next()) {
                Pais pais = new Pais(
                        resultQuery.getInt(TABLE_COLUMNS.get("ID")),
                        resultQuery.getString(TABLE_COLUMNS.get("DESCRICAO"))
                );
                paises.add(pais);
            }
            logInfo("Recuperação de Paises feita com sucesso");
        }catch (SQLException e) {
            logError("Erro ao recuperar dados: " + e.getMessage());
        }
        return paises;
    }

    @Override
    public Pais Read(int id) {
        Pais pais = new Pais();
        try (var connection = conecaoBanco.getConnection()) {
            var st = connection.prepareStatement("SELECT * FROM " + TABLE_NAME +" WHERE " + TABLE_COLUMNS.get("ID") +" = ?");
            st.setInt(1, id);
            var resultQuery = st.executeQuery();
            if(!resultQuery.next()) {
                logInfo("Nenhum pais encontrado com este id");
                return null;
            }
            pais.setId(resultQuery.getInt((TABLE_COLUMNS.get("ID"))));
            pais.setDescricao(resultQuery.getString(TABLE_COLUMNS.get("DESCRICAO")));
            logInfo("Recuperação de Pais feita com sucesso");
        }catch (SQLException e) {
            logError("Erro ao recuperar Pais: " + e);
        }
        return pais;
    }

    @Override
    public void Update(Pais entidade) {
        try(var connection = conecaoBanco.getConnection()) {
            var st = connection.prepareStatement("UPDATE " + TABLE_NAME +
                    " SET " + TABLE_COLUMNS.get("DESCRICAO") + " = ? WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            st.setString(1, entidade.getDescricao());
            st.setInt(2,entidade.getId());
            var resultUpdate = st.executeUpdate();
            logInfo("Atualização de País feita com sucesso, linhas afetadas : " + resultUpdate);
        }catch (SQLException e){
            logError("Erro ao atualizar País : " + e);
        }
    }

    @Override
    public void Delete(int id) {
        try (Connection connection = conecaoBanco.getConnection()) {
            var st = connection.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE "+ TABLE_COLUMNS.get("ID") +" = ?");
            st.setInt(1, id);
            var resultDelete = st.executeUpdate();
            logInfo("Deleção de País feita com sucesso, linhas afetadas : " + resultDelete);
        }catch (SQLException e) {
            logError("Erro ao deletar País : " + e);
        }
    }
}
