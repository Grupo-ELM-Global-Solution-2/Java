package br.com.fiap.dao;

import br.com.fiap.to.TrilhaTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe responsável pelo acesso e manipulação dos dados da entidade <strong>ddd_trilha</strong>
 * na base de dados SQL.
 * <p>
 * Esta classe implementa as operações CRUD (Create, Read, Update, Delete)
 * para a tabela <b>ddd_trilha</b>, permitindo o gerenciamento das trilhas
 * médicas associadas aos usuários do sistema.
 * </p>
 *
 * <p>Utiliza a {@link ConnectionFactory} para gerenciar conexões com o banco de dados.</p>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
public class TrilhaDAO {

    /**
     * Recupera todas as trilhas cadastradas na tabela <b>ddd_trilha</b>.
     *
     * @return uma lista de {@link TrilhaTO} com todas as trilhas encontradas,
     * ou {@code null} caso ocorra um erro na trilha.
     */
    public ArrayList<TrilhaTO> findAll() {
        ArrayList<TrilhaTO> trilhas = new ArrayList<TrilhaTO>();
        String sql = "SELECT * FROM ddd_trilha ORDER BY id_tri";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    TrilhaTO trilha = new TrilhaTO();
                    trilha.setIdTrilha(rs.getLong("id_tri"));
                    trilha.setNome(rs.getString("nome"));
                    trilha.setDificuldade(rs.getString("dificuldade"));
                    trilha.setDescricao(rs.getString("descricao"));
                    trilhas.add(trilha);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na trilha: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return trilhas;
    }

    /**
     * Busca uma trilha pelo seu identificador único (ID).
     *
     * @param idTrilha o código (ID) da trilha a ser buscada.
     * @return um objeto {@link TrilhaTO} correspondente ao ID informado,
     * ou {@code null} se nenhum registro for encontrado.
     */
    public TrilhaTO findByCodigo(Long idTrilha) throws SQLException {
        TrilhaTO trilha = new TrilhaTO();
        String sql = "SELECT * FROM ddd_trilha WHERE id_tri = ?";
        ResultSet rs = null;
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, idTrilha);
            rs = ps.executeQuery();
            if (rs.next()) {
                trilha.setIdTrilha(rs.getLong("id_tri"));
                trilha.setNome(rs.getString("nome"));
                trilha.setDificuldade(rs.getString("dificuldade"));
                trilha.setDescricao(rs.getString("descricao"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na trilha: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
            if (rs != null) {
                rs.close();
            }
        }
        return trilha;
    }

    /**
     * Insere um novo registro de trilha na tabela <b>ddd_trilha</b>.
     *
     * @param trilha o objeto {@link TrilhaTO} contendo os dados a serem inseridos.
     * @return o próprio {@link TrilhaTO} se o registro for inserido com sucesso,
     * ou {@code null} em caso de erro.
     */
    public TrilhaTO save(TrilhaTO trilha) {
        String sql = "INSERT INTO ddd_trilha(nome, dificuldade, descricao) VALUES(?,?,?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, trilha.getNome());
            ps.setString(2, trilha.getDificuldade());
            ps.setString(3, trilha.getDescricao());
            if (ps.executeUpdate() > 0) {
                return trilha;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    /**
     * Exclui uma trilha pelo seu identificador único (ID).
     *
     * @param idTrilha o identificador da trilha a ser excluída.
     * @return {@code true} se a exclusão for bem-sucedida, {@code false} caso contrário.
     */
    public boolean delete(Long idTrilha) {
        String sql = "DELETE FROM ddd_trilha WHERE id_tri = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, idTrilha);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    /**
     * Atualiza os dados de uma trilha existente na tabela <b>ddd_trilha</b>.
     *
     * @param trilha o objeto {@link TrilhaTO} contendo os novos dados da trilha.
     * @return o {@link TrilhaTO} atualizado, ou {@code null} se ocorrer algum erro.
     */
    public TrilhaTO update(TrilhaTO trilha) {
        String sql = "UPDATE ddd_trilha SET nome=?, dificuldade=?, descricao=? WHERE id_tri=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, trilha.getNome());
            ps.setString(2, trilha.getDificuldade());
            ps.setString(3, trilha.getDescricao());
            ps.setLong(4, trilha.getIdTrilha());

            if (ps.executeUpdate() > 0) {
                return trilha;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}
