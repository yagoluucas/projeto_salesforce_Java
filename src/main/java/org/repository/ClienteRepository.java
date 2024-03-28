package org.repository;

import org.configuration.OracleDatabase;
import org.entities.Cliente;
import org.utils._Logger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteRepository implements _Logger<Cliente>, _BaseRepository<Cliente>{
    OracleDatabase oracleDatabase = new OracleDatabase();
    private static final PaisRepository paisRepository = new PaisRepository();
    private static final PorteEmpresaRepository porteEmpresaRepository = new PorteEmpresaRepository();
    private static final String TABLE_NAME = "CLIENTE";
    private static final Map<String, String> TABLE_COLLUMS = Map.of(
        "ID", "ID_CLIENTE",
        "LOGIN", "LOGIN",
        "SENHA", "SENHA",
        "RAZAO_SOCIAL", "RAZAO_SOCIAL",
            "ID_PORTE_EMPRESA", "ID_PORTE_EMPRESA",
        "ID_PAIS", "ID_PAIS"

    );

    @Override
    public void Create(Cliente entidade) {
        try(var connection = oracleDatabase.getConnection()){
            var st = connection.prepareStatement("INSERT INTO " + TABLE_NAME
                    + " (" + TABLE_COLLUMS.get("RAZAO_SOCIAL") + ", " +
                    TABLE_COLLUMS.get("LOGIN") + ", " +
                    TABLE_COLLUMS.get("SENHA") + ", " +
                    TABLE_COLLUMS.get("ID_PORTE_EMPRESA") + ", " +
                    TABLE_COLLUMS.get("ID_PAIS") + ") VALUES (?, ?, ?, ?, ?)");
            st.setString(1, entidade.getRazaoSocial());
            st.setString(2, entidade.getLogin());
            st.setString(3, entidade.getSenha());
            st.setInt(4, entidade.getPorteEmpresa().getId());
            var resultInsert = st.executeUpdate();
            logInfo("Inserção de cliente feita com sucesso, linhas afetadas: " + resultInsert);
        }catch (SQLException e) {
            logError("Erro ao inserir cliente: " + e.getMessage());
        }
    }

    @Override
    public List<Cliente> ReadAll() {
        List<Cliente> clientes = new ArrayList<>();
        try(var connection = oracleDatabase.getConnection()){
            var st = connection.prepareStatement("SELECT * FROM " + TABLE_NAME);
            var resultQuery = st.executeQuery();
            while(resultQuery.next()) {
                Cliente cliente = new Cliente(
                        resultQuery.getInt(TABLE_COLLUMS.get("ID")),
                        resultQuery.getString(TABLE_COLLUMS.get("RAZAO_SOCIAL")),
                        resultQuery.getString(TABLE_COLLUMS.get("LOGIN")),
                        resultQuery.getString(TABLE_COLLUMS.get("SENHA")),
                        porteEmpresaRepository.Read(resultQuery.getInt(TABLE_COLLUMS.get("ID_PORTE_EMPRESA"))),
                        paisRepository.Read(resultQuery.getInt(TABLE_COLLUMS.get("ID_PAIS")))
                );
                clientes.add(cliente);
            }
            logInfo("Recuperação de clientes feita com sucesso, total de clientes: " + clientes.size());
        }catch (SQLException e) {
            logError("Erro ao recuperar dados: " + e.getMessage());
        }
        return clientes;
    }

    @Override
    public Cliente Read(int id) {
        Cliente cliente = new Cliente();
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("SELECT * FROM CLIENTE WHERE ID_CLIENTE = ?");
            st.setInt(1, id);
            var resultQuery = st.executeQuery();
            if(!resultQuery.next()) {
                logInfo("Cliente não encontrado");
                return null;
            }
            cliente = new Cliente(
                    resultQuery.getInt(TABLE_COLLUMS.get("ID")),
                    resultQuery.getString(TABLE_COLLUMS.get("RAZAO_SOCIAL")),
                    resultQuery.getString(TABLE_COLLUMS.get("LOGIN")),
                    resultQuery.getString(TABLE_COLLUMS.get("SENHA")),
                    porteEmpresaRepository.Read(resultQuery.getInt(TABLE_COLLUMS.get("ID_PORTE_EMPRESA"))),
                    paisRepository.Read(resultQuery.getInt(TABLE_COLLUMS.get("ID_PAIS")))
            );
        }catch (SQLException e) {
            logError("Erro ao recuperar dados: " + e.getMessage());
        }
        return cliente;
    }

    @Override
    public void Update(Cliente entidade) {
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("UPDATE "+ TABLE_NAME + " SET " +
                    TABLE_COLLUMS.get("RAZAO_SOCIAL") + " = ?, " +
                    TABLE_COLLUMS.get("LOGIN") + " = ?, " +
                    TABLE_COLLUMS.get("SENHA") + " = ?, " +
                    TABLE_COLLUMS.get("ID_PORTE_EMPRESA") + " = ?, " +
                    TABLE_COLLUMS.get("ID_PAIS") + " = ? WHERE " +
                    TABLE_COLLUMS.get("ID") + " = ?");
            st.setString(1, entidade.getRazaoSocial());
            st.setString(2, entidade.getLogin());
            st.setString(3, entidade.getSenha());
            st.setInt(4, entidade.getPorteEmpresa().getId());
            st.setInt(5, entidade.getPais().getId());
            st.setInt(6, entidade.getId());
            var resultUpdate = st.executeUpdate();
            logInfo("Atualização de cliente feita com sucesso, linhas afetadas: " + resultUpdate);
        }catch (SQLException e) {
            logError("Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    @Override
    public void Delete(int id) {
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE " +
                    TABLE_COLLUMS.get("ID") + " = ?");
            st.setInt(1, id);
            var resultDelete = st.executeUpdate();
            logInfo("Deleção de cliente feita com sucesso, linhas afetadas: " + resultDelete);
        }catch (SQLException e) {
            logError("Erro ao deletar cliente: " + e.getMessage());
        }
    }
}
