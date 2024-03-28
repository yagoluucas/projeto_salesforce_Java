package org.repository;

import org.entities.TelefoneResponsavel;
import org.utils._Logger;
import org.configuration.OracleDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TelefoneResponsavelRepository implements _Logger<TelefoneResponsavel>, _BaseRepository<TelefoneResponsavel>{
    OracleDatabase oracleDatabase = new OracleDatabase();
    private static final String TABLE_NAME = "TELEFONE_RESPONSAVEL";
    private static final ResponsavelRepository responsavelRepository = new ResponsavelRepository();
    private static final Map<String, String> TABLE_COLUMNS = Map.of(
            "ID", "ID_TELEFONE",
            "NUMERO", "NUMERO_TELEFONE",
            "VALIDO", "VALIDO",
            "PRINCIPAL", "PRINCIPAL",
            "ID_RESPONSAVEL", "ID_RESPONSAVEL"
    );
    @Override
    public void Create(TelefoneResponsavel entidade) {
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("INSERT INTO " +
                    TABLE_NAME + " (" + TABLE_COLUMNS.get("NUMERO") + ", " +
                    TABLE_COLUMNS.get("VALIDO") + ", " + TABLE_COLUMNS.get("PRINCIPAL") + ", " +
                    TABLE_COLUMNS.get("ID_RESPONSAVEL") + ") VALUES (?, ?, ?, ?)");
            st.setString(1, entidade.getNumeroTelefone());
            st.setString(2, String.valueOf(entidade.getValido()));
            st.setString(3, String.valueOf(entidade.getPrincipal()));
            st.setInt(4, entidade.getResponsavel().getId());
            var returnInsert = st.executeUpdate();
            logInfo("Inserção de telefone do responsável feita com sucesso, linhas afetadas: " + returnInsert);
        }catch (SQLException e) {
            logError("Erro ao inserir telefone do responsável: " + e.getMessage());
        }
    }

    @Override
    public List<TelefoneResponsavel> ReadAll() {
        List<TelefoneResponsavel> listaTelefones = new ArrayList<>();
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("SELECT * FROM " + TABLE_NAME);
            var resultQuery = st.executeQuery();
            while(resultQuery.next()) {
                TelefoneResponsavel telefoneResponsavel = new TelefoneResponsavel(
                        resultQuery.getInt(TABLE_COLUMNS.get("ID")),
                        resultQuery.getString(TABLE_COLUMNS.get("NUMERO")),
                        resultQuery.getString(TABLE_COLUMNS.get("VALIDO")).charAt(0),
                        resultQuery.getString(TABLE_COLUMNS.get("PRINCIPAL")).charAt(0),
                        responsavelRepository.Read(resultQuery.getInt(TABLE_COLUMNS.get("ID_RESPONSAVEL")))
                );
                listaTelefones.add(telefoneResponsavel);
            }
            logInfo("Recuperação de telefones feita com sucesso, total de telefones: " + listaTelefones.size());
        }catch (SQLException e) {
            logError("Erro ao recuperar lista de telefones: " + e.getMessage());
        }
        return listaTelefones;
    }

    @Override
    public TelefoneResponsavel Read(int id) {
        TelefoneResponsavel telefoneResponsavel = new TelefoneResponsavel();
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("SELECT * FROM " +
                    TABLE_NAME + " WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            st.setInt(1, id);
            var resultQuery = st.executeQuery();
            telefoneResponsavel.setId(resultQuery.getInt(TABLE_COLUMNS.get("ID")));
            telefoneResponsavel.setNumeroTelefone(resultQuery.getString(TABLE_COLUMNS.get("NUMERO")));
            telefoneResponsavel.setValido(resultQuery.getString(TABLE_COLUMNS.get("VALIDO")).charAt(0));
            telefoneResponsavel.setPrincipal(resultQuery.getString(TABLE_COLUMNS.get("PRINCIPAL")).charAt(0));
            telefoneResponsavel.setResponsavel(responsavelRepository.Read(resultQuery.getInt(TABLE_COLUMNS.get("ID_RESPONSAVEL"))));
            logInfo("Recuperação de telefone do responsável feita com sucesso");
        }catch (SQLException e) {
            logError("Erro ao recuperar telefone do responsável: " + e.getMessage());
        }
        return telefoneResponsavel;
    }

    @Override
    public void Update(TelefoneResponsavel entidade) {
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("UPDATE " + TABLE_NAME + " SET " +
                    TABLE_COLUMNS.get("NUMERO") + " = ?, " + TABLE_COLUMNS.get("VALIDO") + " = ?, " +
                    TABLE_COLUMNS.get("PRINCIPAL") + " = ?, " + TABLE_COLUMNS.get("ID_RESPONSAVEL") + " = ? WHERE " +
                    TABLE_COLUMNS.get("ID") + " = ?");
            st.setString(1, entidade.getNumeroTelefone());
            st.setString(2, String.valueOf(entidade.getValido()));
            st.setString(3, String.valueOf(entidade.getPrincipal()));
            st.setInt(4, entidade.getResponsavel().getId());
            st.setInt(5, entidade.getId());
            var returnUpdate = st.executeUpdate();
            logInfo("Atualização de telefone do responsável feita com sucesso, linhas afetadas: " + returnUpdate);
        }catch (SQLException e) {
            logError("Erro ao atualizar telefone do responsável: " + e.getMessage());
        }
    }

    @Override
    public void Delete(int id) {
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("DELETE FROM " +
                    TABLE_NAME + " WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            st.setInt(1, id);
            var returnDelete = st.executeUpdate();
            logInfo("Deleção de telefone do responsável feita com sucesso, linhas afetadas: " + returnDelete);
        }catch (SQLException e) {
            logError("Erro ao deletar telefone do responsável: " + e.getMessage());
        }
    }
}
