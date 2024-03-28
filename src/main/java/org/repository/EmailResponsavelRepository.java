package org.repository;

import org.entities.EmailResponsavel;
import org.utils._Logger;
import org.configuration.OracleDatabase;
import java.sql.SQLException;
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
        return null;
    }

    @Override
    public EmailResponsavel Read(int id) {
        return null;
    }

    @Override
    public void Update(EmailResponsavel entidade) {

    }

    @Override
    public void Delete(int id) {

    }
}
