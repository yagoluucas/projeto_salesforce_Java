package org.repository;

import org.entities.EmailResponsavel;
import org.utils._Logger;
import org.configuration.OracleDatabase;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmailResponsavelRepository implements _Logger<EmailResponsavel>, _BaseRepository<EmailResponsavel>{
    private static final String TABLE_NAME = "EMAIL_RESPONSAVEL";

    OracleDatabase oracleDatabase = new OracleDatabase();
    private static final ResponsavelRepository responsavelRepository = new ResponsavelRepository();
    private static final Map<String, String> TABLE_COLUMNS = Map.of(
            "ID", "ID_EMAIL_RESPONSAVEL",
            "EMAIL", "EMAIL",
            "PRINCIPAL", "PRINCIPAL",
            "VALIDO", "VALIDO",
            "ID_RESPONSAVEL", "ID_RESPONSAVEL"
    );
    @Override
    public void Create(EmailResponsavel entidade) {
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("INSERT INTO " +
                    TABLE_NAME + " (" + TABLE_COLUMNS.get("EMAIL") + ", " +
                    TABLE_COLUMNS.get("PRINCIPAL") + ", " + TABLE_COLUMNS.get("VALIDO") + ", " +
                    TABLE_COLUMNS.get("ID_RESPONSAVEL") + ") VALUES (?, ?, ?, ?)");
            st.setString(1, entidade.getEmail());
            st.setString(2, String.valueOf(entidade.getPrincipal()));
            st.setString(3, String.valueOf(entidade.getValido()));
            st.setInt(4, entidade.getResponsavel().getId());
            var returnInsert = st.executeUpdate();
            logInfo("Inserção de email do responsável feita com sucesso, linhas afetadas: " + returnInsert);
        }catch (SQLException e) {
            logError("Erro ao inserir email do responsável: " + e.getMessage());
        }
    }

    @Override
    public List<EmailResponsavel> ReadAll() {
        List<EmailResponsavel> emailResponsavels = new ArrayList<>();
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("SELECT * FROM " + TABLE_NAME);
            var resultQuery = st.executeQuery();
            while(resultQuery.next()) {
                EmailResponsavel email = new EmailResponsavel(
                        resultQuery.getInt(TABLE_COLUMNS.get("ID")),
                        resultQuery.getString(TABLE_COLUMNS.get("EMAIL")),
                        resultQuery.getString(TABLE_COLUMNS.get("PRINCIPAL")).charAt(0),
                        resultQuery.getString(TABLE_COLUMNS.get("VALIDO")).charAt(0),
                        responsavelRepository.Read(resultQuery.getInt(TABLE_COLUMNS.get("ID_RESPONSAVEL")))
                );
                emailResponsavels.add(email);
            }

        }catch (SQLException e) {
            logError("Erro ao ler todos os emails do responsável: " + e.getMessage());
        }
        return emailResponsavels;
    }

    @Override
    public EmailResponsavel Read(int id) {
        EmailResponsavel emailResponsavel = new EmailResponsavel();
        try(var connection = oracleDatabase.getConnection()){
            var st = connection.prepareStatement("SELECT * FROM " + TABLE_NAME +
                    " WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            var resultQuery = st.executeQuery();
            while(resultQuery.next()) {
                emailResponsavel = new EmailResponsavel(
                        resultQuery.getInt(TABLE_COLUMNS.get("ID")),
                        resultQuery.getString(TABLE_COLUMNS.get("EMAIL")),
                        resultQuery.getString(TABLE_COLUMNS.get("PRINCIPAL")).charAt(0),
                        resultQuery.getString(TABLE_COLUMNS.get("VALIDO")).charAt(0),
                        responsavelRepository.Read(resultQuery.getInt(TABLE_COLUMNS.get("ID_RESPONSAVEL")))
                );
            }
        }catch (SQLException e) {
            logError("Erro ao ler email do responsável: " + e.getMessage());
        }
        return emailResponsavel;
    }

    @Override
    public void Update(EmailResponsavel entidade) {
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("UPDATE " + TABLE_NAME + " SET " +
                    TABLE_COLUMNS.get("EMAIL") + ", " + TABLE_COLUMNS.get("PRINCIPAL") + ", " +
                    TABLE_COLUMNS.get("VALIDO") + " WHERE " + TABLE_COLUMNS.get("ID_RESPONSAVEL") + " = ?"
            + " WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            st.setString(1, entidade.getEmail());
            st.setString(2, String.valueOf(entidade.getPrincipal()));
            st.setString(3, String.valueOf(entidade.getValido()));
            st.setInt(4, entidade.getResponsavel().getId());
            st.setInt(5, entidade.getId());
            var returnUpdate = st.executeUpdate();
            logInfo("Atualização de email do responsável feita com sucesso, linhas afetadas: " + returnUpdate);
        }catch (SQLException e) {
            logError("Erro ao atualizar email do responsável: " + e.getMessage());
        }
    }

    @Override
    public void Delete(int id) {
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("DELETE FROM " + TABLE_NAME +
                    " WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            st.setInt(1, id);
            var returnDelete = st.executeUpdate();
            logInfo("Deleção de email do responsável feita com sucesso, linhas afetadas: " + returnDelete);
        }catch (SQLException e) {
            logError("Erro ao deletar email do responsável: " + e.getMessage());
        }
    }
}
