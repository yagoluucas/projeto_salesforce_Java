package org.repository;
import org.configuration.OracleDatabase;
import org.entities.Responsavel;
import org.utils._Logger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResponsavelRepository implements _Logger<Responsavel>, _BaseRepository<Responsavel>{
    OracleDatabase oracleDatabase = new OracleDatabase();
    ClienteRepository clienteRepository = new ClienteRepository();
    private static final String TABLE_NAME = "RESPONSAVEL";
    private static final Map<String, String> TABLE_COLUMNS = Map.of(
        "ID", "ID_RESPONSAVEL",
        "NOME", "NOME",
        "SOBRENOME", "SOBRENOME",
        "CARGO", "CARGO",
        "ID_CLIENTE", "ID_CLIENTE"
    );
    @Override
    public void Create(Responsavel entidade) {
        try(var connection = oracleDatabase.getConnection()) {
            var statement = connection.prepareStatement(
                    "INSERT INTO " + TABLE_NAME + " (" + TABLE_COLUMNS.get("NOME") +
                            ", " + TABLE_COLUMNS.get("SOBRENOME") + ", " +
                            TABLE_COLUMNS.get("CARGO") + ", " + TABLE_COLUMNS.get("ID_CLIENTE") + ") " +
                            "VALUES (?, ?, ?, ?)");
            statement.setString(1, entidade.getNome());
            statement.setString(2, entidade.getSobrenome());
            statement.setString(3, entidade.getCargo());
            statement.setInt(4, entidade.getCliente().getId());
            var returnInsert = statement.executeUpdate();
            logInfo("Inserção de responsável feita com sucesso, linhas afetadas: " + returnInsert);
        }catch (SQLException e){
            logError("Erro ao inserir responsável: " + e.getMessage());
        }
    }

    @Override
    public List<Responsavel> ReadAll() {
        List<Responsavel> responsaveis = new ArrayList<>();
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("SELECT * FROM " + TABLE_NAME);
            var resultQuery = st.executeQuery();
            while(resultQuery.next()) {
                Responsavel responsavel = new Responsavel(
                        resultQuery.getInt(TABLE_COLUMNS.get("ID")),
                        resultQuery.getString(TABLE_COLUMNS.get("NOME")),
                        resultQuery.getString(TABLE_COLUMNS.get("SOBRENOME")),
                        resultQuery.getString(TABLE_COLUMNS.get("CARGO")),
                        clienteRepository.Read(resultQuery.getInt(TABLE_COLUMNS.get("ID_CLIENTE")))
                );
                responsaveis.add(responsavel);
            }
            logInfo("Recuperação de responsáveis feita com sucesso");
        }catch (SQLException e) {
            logError("Erro ao recuperar dados: " + e.getMessage());
        }
        return responsaveis;
    }

    @Override
    public Responsavel Read(int id) {
        Responsavel responsavel = new Responsavel();
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("SELECT * FROM " +
                    TABLE_NAME + " WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            st.setInt(1, id);
            var resultQuery = st.executeQuery();
            if(!resultQuery.next()) {
                logInfo("Responsável não encontrado");
                return null;
            }
            responsavel = new Responsavel(
                resultQuery.getInt(TABLE_COLUMNS.get("ID")),
                resultQuery.getString(TABLE_COLUMNS.get("NOME")),
                resultQuery.getString(TABLE_COLUMNS.get("SOBRENOME")),
                resultQuery.getString(TABLE_COLUMNS.get("CARGO")),
                clienteRepository.Read(resultQuery.getInt(TABLE_COLUMNS.get("ID_CLIENTE")))
            );
        logInfo("Recuperação de responsável feita com sucesso");
        }catch (SQLException e) {
            logError("Erro ao recuperar dados: " + e.getMessage());
        }

        return responsavel;
    }

    @Override
    public void Update(Responsavel entidade) {
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("UPDATE " + TABLE_NAME + " SET " +
                    TABLE_COLUMNS.get("NOME") + " = ?, " + TABLE_COLUMNS.get("SOBRENOME") + " = ?, " +
                    TABLE_COLUMNS.get("CARGO") + " = ?, " + TABLE_COLUMNS.get("ID_CLIENTE") + " = ? " +
                    "WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            st.setString(1, entidade.getNome());
            st.setString(2, entidade.getSobrenome());
            st.setString(3, entidade.getCargo());
            st.setInt(4, entidade.getCliente().getId());
            st.setInt(5, entidade.getId());
            var resultUpdate = st.executeUpdate();
            logInfo("Atualização de responsável feita com sucesso, linhas afetadas: " + resultUpdate);
        }catch (SQLException e) {
            logError("Erro ao atualizar o usuário : " + e.getMessage());
        }
    }

    @Override
    public void Delete(int id) {
        try(var connection = oracleDatabase.getConnection()){
            var st = connection.prepareStatement("DELETE FROM " +
                    TABLE_NAME + " WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            st.setInt(1, id);
            var resultDelete = st.executeUpdate();
            logInfo("Sucesso ao exlcluir usário, linhas afetadas : " + resultDelete);
        }catch (SQLException e) {
            logError("Erro ao deletar usuário" + e.getMessage());
        }
    }
}
