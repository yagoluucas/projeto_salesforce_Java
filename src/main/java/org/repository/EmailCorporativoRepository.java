package org.repository;

import org.configuration.OracleDatabase;
import org.entities.EmailCorporativo;
import org.utils._Logger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmailCorporativoRepository implements _BaseRepository<EmailCorporativo>, _Logger<EmailCorporativo>{

    OracleDatabase oracleDatabase = new OracleDatabase();
    private static final ClienteRepository clienteRepository = new ClienteRepository();
    private static final ResponsavelRepository responsavelRepository = new ResponsavelRepository();
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
        List<EmailCorporativo> emailCorporativos = new ArrayList<>();
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("SELECT * FROM " + TABLE_NAME);
            var resultQuery = st.executeQuery();
            while(resultQuery.next()) {
                EmailCorporativo emailCorporativo = new EmailCorporativo(
                        resultQuery.getInt(TABLE_COLUMNS.get("ID")),
                        resultQuery.getString(TABLE_COLUMNS.get("EMAIL")),
                        resultQuery.getString(TABLE_COLUMNS.get("PRINCIPAL")).charAt(0),
                        resultQuery.getString(TABLE_COLUMNS.get("VALIDO")).charAt(0),
                        clienteRepository.Read(resultQuery.getInt(TABLE_COLUMNS.get("ID_CLIENTE"))),
                        responsavelRepository.Read(resultQuery.getInt(TABLE_COLUMNS.get("ID_RESPONSAVEL")))
                );
                emailCorporativos.add(emailCorporativo);
            }
            logInfo("Leitura de todos os emails corporativos feita com sucesso" + emailCorporativos.size() + " registros");
        }catch (SQLException e) {
            logError("Erro ao ler todos os emails corporativos: " + e.getMessage());
        }
        return emailCorporativos;
    }

    @Override
    public EmailCorporativo Read(int id) {
        EmailCorporativo emailCorporativo = new EmailCorporativo();
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("SELECT * FROM " + TABLE_NAME +
                    " WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            st.setInt(1, id);
            var resultQuery = st.executeQuery();
            while(resultQuery.next()) {
                emailCorporativo = new EmailCorporativo(
                        resultQuery.getInt(TABLE_COLUMNS.get("ID")),
                        resultQuery.getString(TABLE_COLUMNS.get("EMAIL")),
                        resultQuery.getString(TABLE_COLUMNS.get("PRINCIPAL")).charAt(0),
                        resultQuery.getString(TABLE_COLUMNS.get("VALIDO")).charAt(0),
                        clienteRepository.Read(resultQuery.getInt(TABLE_COLUMNS.get("ID_CLIENTE"))),
                        responsavelRepository.Read(resultQuery.getInt(TABLE_COLUMNS.get("ID_RESPONSAVEL")))
                );
            }
            logInfo("Leitura de email corporativo feita com sucesso");
        }catch (SQLException e) {
            logError("Erro ao ler email corporativo: " + e.getMessage());
        }
        return emailCorporativo;
    }

    @Override
    public void Update(EmailCorporativo entidade) {
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("UPDATE " + TABLE_NAME + " SET " +
                    TABLE_COLUMNS.get("EMAIL") + " = ?, " + TABLE_COLUMNS.get("PRINCIPAL") + " = ?, " +
                    TABLE_COLUMNS.get("VALIDO") + " = ?, " + TABLE_COLUMNS.get("ID_CLIENTE") + " = ?, " +
                    TABLE_COLUMNS.get("ID_RESPONSAVEL") + " = ? WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            st.setString(1, entidade.getEmail());
            st.setString(2, String.valueOf(entidade.getPrincipal()));
            st.setString(3, String.valueOf(entidade.getValido()));
            st.setInt(4, entidade.getCliente().getId());
            st.setInt(5, entidade.getResponsavel().getId());
            st.setInt(6, entidade.getId());
            var returnUpdate = st.executeUpdate();
            logInfo("Atualização de email corporativo feita com sucesso, linhas afetadas: " + returnUpdate);
        }catch (SQLException e) {
            logError("Erro ao atualizar email corporativo: " + e.getMessage());
        }
    }

    @Override
    public void Delete(int id) {
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("DELETE FROM " + TABLE_NAME +
                    " WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            st.setInt(1, id);
            var returnDelete = st.executeUpdate();
            logInfo("Deleção de email corporativo feita com sucesso, linhas afetadas: " + returnDelete);
        }catch (SQLException e) {
            logError("Erro ao deletar email corporativo: " + e.getMessage());
        }
    }
}
