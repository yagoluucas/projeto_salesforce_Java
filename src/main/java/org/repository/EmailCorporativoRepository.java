package org.repository;

import org.configuration.OracleDatabase;
import org.entities.EmailCorporativo;
import org.utils._Logger;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class EmailCorporativoRepository implements _BaseRepository<EmailCorporativo>, _Logger<EmailCorporativo>{

    OracleDatabase oracleDatabase = new OracleDatabase();

    private static final String TABLE_NAME = "EMAIL_CORPORATIVO";

    private static final Map<String, String> TABLE_COLUMNS = Map.of(
            "ID", "ID_EMAIL_CORPORATIVO",
            "EMAIL", "EMAIL",
            "PRINCIPAL", "PRINCIPAL",
            "VALIDO", "VALIDO",
            "ID_CLIENTE", "ID_CLIENTE",
            "ID_RESPONSAVEL", "ID_RESPONSAVEL"
    );

    @Override
    public void Create(EmailCorporativo entidade) {
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("INSERT INTO " +
                    TABLE_NAME + " (" + TABLE_COLUMNS.get("EMAIL") + ", " +
                    TABLE_COLUMNS.get("PRINCIPAL") + ", " + TABLE_COLUMNS.get("VALIDO") + ", " +
                    TABLE_COLUMNS.get("ID_CLIENTE") + ", " + TABLE_COLUMNS.get("ID_RESPONSAVEL") + ") VALUES (?, ?, ?, ?, ?)");
            st.setString(1, entidade.getEmail());
            st.setString(2, String.valueOf(entidade.getPrincipal()));
            st.setString(3, String.valueOf(entidade.getValido()));
            st.setInt(4, entidade.getCliente().getId());
            st.setInt(5, entidade.getResponsavel().getId());
            var returnInsert = st.executeUpdate();
            logInfo("Inserção de email corporativo feita com sucesso, linhas afetadas: " + returnInsert);
        }catch (SQLException e) {
            logError("Erro ao inserir email corporativo: " + e.getMessage());
        }
    }

    @Override
    public List<EmailCorporativo> ReadAll() {
        return null;
    }

    @Override
    public EmailCorporativo Read(int id) {
        return null;
    }

    @Override
    public void Update(EmailCorporativo entidade) {

    }

    @Override
    public void Delete(int id) {

    }
}
