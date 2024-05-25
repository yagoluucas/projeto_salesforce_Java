package salesforce.repository;
import salesforce.configuration.OracleDatabase;
import salesforce.configuration._Logger;
import salesforce.entities.TesteGratis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TesteGratisRepository implements _Logger<TesteGratis>, _BaseRepository<TesteGratis>{
    private static final PaisRepository paisRepository = new PaisRepository();
    private static final PorteEmpresaRepository porteEmpresaRepository = new PorteEmpresaRepository();
    private static final IdiomaRepository idiomaRepository = new IdiomaRepository();
    OracleDatabase oracleDatabase = new OracleDatabase();
    private static final Map<String, String> TABLE_COLUMNS = Map.of(
            "ID", "ID_TESTE_GRATIS",
            "NOME", "NOME",
            "SOBRENOME", "SOBRENOME",
            "CARGO", "CARGO",
            "EMAIL", "EMAIL",
            "TELEFONE", "TELEFONE",
            "ID_PAIS", "ID_PAIS",
            "ID_IDIOMA", "ID_IDIOMA",
            "ID_PORTE_EMPRESA", "ID_PORTE_EMPRESA"

    );
    private static final String TABLE_NAME = "TESTE_GRATIS";
    @Override
    public void Create(TesteGratis entidade) {
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("INSERT INTO " + TABLE_NAME +
                    " (" + TABLE_COLUMNS.get("NOME") + ", " + TABLE_COLUMNS.get("SOBRENOME") + ", " +
                    TABLE_COLUMNS.get("CARGO") + ", " + TABLE_COLUMNS.get("EMAIL") + ", " +
                    TABLE_COLUMNS.get("TELEFONE") + ", " + TABLE_COLUMNS.get("ID_IDIOMA") + ", " +
                    TABLE_COLUMNS.get("ID_PORTE_EMPRESA") + ", " + TABLE_COLUMNS.get("ID_PAIS") + ") " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, entidade.getNome());
            st.setString(2, entidade.getSobrenome());
            st.setString(3, entidade.getCargo());
            st.setString(4, entidade.getEmail());
            st.setString(5, entidade.getTelefone());
            st.setInt(6, entidade.getIdioma().getId());
            st.setInt(7, entidade.getPorteEmpresa().getId());
            st.setInt(8, entidade.getPais().getId());
            var returnInsert = st.executeUpdate();
            logInfo("Inserção de teste grátis feita com sucesso, linhas afetadas: " + returnInsert);
        }catch (SQLException e) {
            logError("Erro ao inserir teste grátis: " + e.getMessage());
        }
    }

    @Override
    public List<TesteGratis> ReadAll() {
        List<TesteGratis> testesGratis = new ArrayList<>();
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("SELECT * FROM " + TABLE_NAME);
            var resultQuery = st.executeQuery();
            while(resultQuery.next()) {
                TesteGratis testeGratis = new TesteGratis(
                        resultQuery.getInt(TABLE_COLUMNS.get("ID")),
                        resultQuery.getString(TABLE_COLUMNS.get("NOME")),
                        resultQuery.getString(TABLE_COLUMNS.get("SOBRENOME")),
                        resultQuery.getString(TABLE_COLUMNS.get("CARGO")),
                        resultQuery.getString(TABLE_COLUMNS.get("EMAIL")),
                        resultQuery.getString(TABLE_COLUMNS.get("TELEFONE")),
                        paisRepository.Read(resultQuery.getInt(TABLE_COLUMNS.get("ID_PAIS"))),
                        idiomaRepository.Read(resultQuery.getInt(TABLE_COLUMNS.get("ID_IDIOMA"))),
                        porteEmpresaRepository.Read(resultQuery.getInt(TABLE_COLUMNS.get("ID_PORTE_EMPRESA")))
                );
                testesGratis.add(testeGratis);
            }
            logInfo("Recuperação de testes grátis feita com sucesso, total de testes grátis: " + testesGratis.size());
        }catch (SQLException e) {
            logError("Erro ao recuperar testes grátis: " + e.getMessage());
        }
        return testesGratis;
    }

    @Override
    public TesteGratis Read(int id) {
        TesteGratis testeGratis = new TesteGratis();
        try(var connection = oracleDatabase.getConnection()){
            var st = connection.prepareStatement("SELECT * FROM " +
                    TABLE_NAME + " WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            st.setInt(1, id);
            var resultQuery = st.executeQuery();
            while(resultQuery.next()) {
                testeGratis = new TesteGratis(
                        resultQuery.getInt(TABLE_COLUMNS.get("ID")),
                        resultQuery.getString(TABLE_COLUMNS.get("NOME")),
                        resultQuery.getString(TABLE_COLUMNS.get("SOBRENOME")),
                        resultQuery.getString(TABLE_COLUMNS.get("CARGO")),
                        resultQuery.getString(TABLE_COLUMNS.get("EMAIL")),
                        resultQuery.getString(TABLE_COLUMNS.get("TELEFONE")),
                        paisRepository.Read(resultQuery.getInt(TABLE_COLUMNS.get("ID_PAIS"))),
                        idiomaRepository.Read(resultQuery.getInt(TABLE_COLUMNS.get("ID_IDIOMA"))),
                        porteEmpresaRepository.Read(resultQuery.getInt(TABLE_COLUMNS.get("ID_PORTE_EMPRESA")))
                );
            }
        }catch (SQLException e) {
            logError("Erro ao recuperar teste grátis: " + e.getMessage());
        }
        return testeGratis;
    }

    public boolean ReadByEmail(String email) {
        try(var connection = oracleDatabase.getConnection()){
            var st = connection.prepareStatement("SELECT * FROM " +
                    TABLE_NAME + " WHERE " + TABLE_COLUMNS.get("EMAIL") + " = ?");
            st.setString(1, email);
            if(st.executeQuery().next()) {
                logInfo("Email já cadastrado na tabela de teste grátis");
                return true;
            }
        }
        catch (SQLException e) {
            logError("Erro ao recuperar teste grátis por email: " + e.getMessage());
        }
        return false;
    }

    @Override
    public void Update(TesteGratis entidade) {
        try(var connection = oracleDatabase.getConnection()){
            var st = connection.prepareStatement("UPDATE " + TABLE_NAME + " SET " +
                    TABLE_COLUMNS.get("NOME") + " = ?, " + TABLE_COLUMNS.get("SOBRENOME") + " = ?, " +
                    TABLE_COLUMNS.get("CARGO") + " = ?, " + TABLE_COLUMNS.get("EMAIL") + " = ?, " +
                    TABLE_COLUMNS.get("TELEFONE") + " = ?, " + TABLE_COLUMNS.get("ID_IDIOMA") + " = ?, " +
                    TABLE_COLUMNS.get("ID_PORTE_EMPRESA") + " = ?, " + TABLE_COLUMNS.get("ID_PAIS") + " = ? " +
                    "WHERE " + TABLE_COLUMNS.get("ID") + " = ?");
            st.setString(1, entidade.getNome());
            st.setString(2, entidade.getSobrenome());
            st.setString(3, entidade.getCargo());
            st.setString(4, entidade.getEmail());
            st.setString(5, entidade.getTelefone());
            st.setInt(6, entidade.getIdioma().getId());
            st.setInt(7, entidade.getPorteEmpresa().getId());
            st.setInt(8, entidade.getPais().getId());
            st.setInt(9, entidade.getId());
            var returnUpdate = st.executeUpdate();
            logInfo("Atualização de teste grátis feita com sucesso, linhas afetadas: " + returnUpdate);
        }catch (SQLException e) {
            logError("Erro ao atualizar teste grátis: " + e.getMessage());
        }
    }

    @Override
    public void Delete(int id) {
        try(var connection = oracleDatabase.getConnection()) {
            var st = connection.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE " +
                    TABLE_COLUMNS.get("ID") + " = ?");
            st.setInt(1, id);
            var returnDelete = st.executeUpdate();
            logInfo("Deleção de teste grátis feita com sucesso, linhas afetadas: " + returnDelete);
        }catch (SQLException e) {
            logError("Erro ao deletar teste grátis: " + e.getMessage());
        }
    }
}
