package org.repository;
import org.configuration.OracleDatabase;
import org.configuration._Logger;
import org.entities.PorteEmpresa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PorteEmpresaRepository implements _Logger<PorteEmpresaRepository>, _BaseRepository<PorteEmpresa> {

    OracleDatabase oracleDatabase = new OracleDatabase();

    private static final Map<String, String> TABLE_COLUMNS = Map.of(
            "ID","ID_PORTE_EMPRESA",
            "DESCRICAO", "DESCRICAO"
    );
    private static final String TABLE_NAME = "PORTE_EMPRESA";
    @Override
    public void Create(PorteEmpresa entidade) {
        try (var connection = oracleDatabase.getConnection()) {
            var statement = connection.prepareStatement("INSERT INTO " + TABLE_NAME + " ("
                    + TABLE_COLUMNS.get("DESCRICAO") + ") VALUES (?)");
            statement.setString(1, entidade.getDescricao());
            var returnInsert = statement.executeUpdate();
            logInfo("Inserção feita com sucesso, linhas afetadas: " + returnInsert);
        }catch (SQLException e) {
            logError("Erro ao inserir: " + e.getMessage());
        }
    }

    @Override
    public List<PorteEmpresa> ReadAll() {
        List<PorteEmpresa> porteEmpresas = new ArrayList<>();
        try (var connection = oracleDatabase.getConnection()) {
            var statement = connection.prepareStatement("SELECT * FROM " + TABLE_NAME);
            var resultQuery = statement.executeQuery();
            while(resultQuery.next()) {
                PorteEmpresa porteEmpresa = new PorteEmpresa(
                        resultQuery.getInt(TABLE_COLUMNS.get("ID")),
                        resultQuery.getString(TABLE_COLUMNS.get("DESCRICAO"))
                );
                porteEmpresas.add(porteEmpresa);
            }
            statement.close();
            resultQuery.close();
            logInfo("Recuperação de portes de empresa feita com sucesso, total de linhas: " + porteEmpresas.size());
        } catch (SQLException e) {
            logError("Erro ao recuperar portes de empresa: " + e.getMessage());
        }
        return porteEmpresas;
    }

    @Override
    public PorteEmpresa Read(int id) {
        PorteEmpresa porteEmpresa = new PorteEmpresa();
        try (var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("SELECT * FROM " + TABLE_NAME +
                    " WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            st.setInt(1, id);
            var resultQuery = st.executeQuery();
            if(!resultQuery.next()) {
                logInfo("Nenhum Porte de empresa com esse id");
                return null;
            }
            porteEmpresa.setId(resultQuery.getInt(TABLE_COLUMNS.get("ID")));
            porteEmpresa.setDescricao(resultQuery.getString(TABLE_COLUMNS.get("DESCRICAO")));
            logInfo("Recuperação de porte de empresa feita com sucesso");
        }catch (SQLException e) {
            logError("Erro ao recuperar porte de empresa: " + e.getMessage());
        }
        return porteEmpresa;
    }

    @Override
    public void Update(PorteEmpresa entidade) {
        try (var connection = oracleDatabase.getConnection()) {
            var statement = connection.prepareStatement("UPDATE " + TABLE_NAME +
                    " SET " + TABLE_COLUMNS.get("DESCRICAO") + " = ? WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            statement.setString(1, entidade.getDescricao());
            statement.setInt(2, entidade.getId());
            var returnUpdate = statement.executeUpdate();
            logInfo("Atualização de Porte de Empresa feita com sucesso, linhas afetadas: " + returnUpdate);
        } catch (SQLException e) {
            logError("Erro ao atualizar porte de empresa: " + e.getMessage());
        }
    }

    @Override
    public void Delete(int id) {
        try(var connection = oracleDatabase.getConnection()) {
            var statement = connection.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE " +
                    TABLE_COLUMNS.get("ID") + " = " + id);
            var resultQuery = statement.executeUpdate();
            logInfo("Sucesso ao deletar porte de empresa, linhas afetadas : " + resultQuery);
        }catch (SQLException e) {
            logError("Não foi possivel deleter o porte de empresa informado: " + e.getMessage());
        }
    }
}
