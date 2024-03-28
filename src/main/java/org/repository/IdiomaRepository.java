package org.repository;

import org.configuration.OracleDatabase;
import org.entities.Idioma;
import org.utils._Logger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IdiomaRepository implements _Logger<Idioma>, _BaseRepository<Idioma> {
    OracleDatabase oracleDatabase = new OracleDatabase();
    private static final Map<String, String> TABLE_COLUMNS = Map.of(
            "ID", "ID_IDIOMA",
            "IDIOMA", "IDIOMA"
    );

    private static final String TABLE_NAME = "IDIOMA";
    @Override
    public void Create(Idioma entidade) {
        try (var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("INSERT INTO " + TABLE_NAME
                    + " (" + TABLE_COLUMNS.get("IDIOMA") + ") VALUES ( ? )");
            st.setString(1, entidade.getIdioma());
            var returnInsert = st.executeUpdate();
            logInfo("Inserção de idioma feita com sucesso, linhas afetadas : " + returnInsert);
        }catch (SQLException e) {
            logError("Erro ao inserir idioma: " + e.getMessage());
        }
    }

    @Override
    public List<Idioma> ReadAll() {
        List<Idioma> idiomas = new ArrayList<>();
        try (var connection = oracleDatabase.getConnection()) {
            var statement = connection.prepareStatement("SELECT * FROM " + TABLE_NAME);
            var resultQuery = statement.executeQuery();
            while(resultQuery.next()) {
                Idioma idioma = new Idioma(resultQuery.getInt(TABLE_COLUMNS.get("ID")),
                        resultQuery.getString(TABLE_COLUMNS.get("IDIOMA")));
                idiomas.add(idioma);
            }
            logInfo("Recuperação de idiomas feita com sucesso, total de idiomas: " + idiomas.size());
        }catch (SQLException e) {
            logError("Erro ao recuperar idiomas: "+ e.getMessage());
        }
        return idiomas;
    }

    @Override
    public Idioma Read(int id) {
        Idioma idioma = new Idioma();
        try(var connection = oracleDatabase.getConnection()) {
            var statement = connection.prepareStatement("SELECT * FROM "
                    + TABLE_NAME + " WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            statement.setInt(1, id);
            var resultQuery = statement.executeQuery();
            if(!resultQuery.next()) {
                logInfo("Sem nenhum idioma com este ID!");
                return null;
            }
            idioma.setId(resultQuery.getInt(TABLE_COLUMNS.get("ID")));
            idioma.setIdioma(resultQuery.getString(TABLE_COLUMNS.get("IDIOMA")));
            logInfo("Recuperação de idioma feita com sucesso");
        }catch (SQLException e) {
            logError("Erro ao recuperar idioma: " + e.getMessage());
        }
        return idioma;
    }

    @Override
    public void Update(Idioma entidade) {
        try(var connection = oracleDatabase.getConnection()) {
            var statement = connection.prepareStatement(
                    "UPDATE " + TABLE_NAME + " SET " + TABLE_COLUMNS.get("IDIOMA") + " = ? WHERE "
                            + TABLE_COLUMNS.get("ID") + " = ?");
            statement.setString(1,entidade.getIdioma());
            statement.setInt(2, entidade.getId());
            var returnUpdate = statement.executeUpdate();
            logInfo("Atualização de idioma feita com sucesso, linhas afetadas: " + returnUpdate);
        }catch (SQLException e) {
            logError("Erro ao atualizar idioma: " + e.getMessage());
        }
    }

    @Override
    public void Delete(int id) {
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("DELETE FROM " + TABLE_NAME +
                    " WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            st.setInt(1, id);
            var returnDelete = st.executeUpdate();
            logInfo("Sucesso ao excluir pais, linhas afetadas: " + returnDelete);
        }catch (SQLException e){
            logError("Erro ao excluir idioma: " + e.getMessage());
        }
    }
}
