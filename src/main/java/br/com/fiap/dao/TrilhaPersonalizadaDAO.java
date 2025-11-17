package br.com.fiap.dao;

import br.com.fiap.to.TrilhaPersonalizadaTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date; // Importa java.sql.Date para conversão JDBC
import java.util.ArrayList;

/**
 * <p>Classe responsável pelo acesso e manipulação dos dados da entidade
 * <strong>DDD_TRILHAS_PERSONALIZADAS</strong> na base de dados SQL.</p>
 *
 * <p>Esta classe implementa as operações CRUD (Create, Read, Update, Delete)
 * para a tabela <b>DDD_TRILHAS_PERSONALIZADAS</b>.</p>
 *
 * <p>Utiliza a {@link ConnectionFactory} para gerenciar conexões com o banco de dados.</p>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
public class TrilhaPersonalizadaDAO {

    /**
     * Recupera todas as trilhas personalizadas cadastradas na tabela <b>DDD_TRILHAS_PERSONALIZADAS</b>.
     *
     * @return uma lista de {@link TrilhaPersonalizadaTO} com todas as trilhas encontradas,
     * ou {@code null} caso ocorra um erro.
     */
    public ArrayList<TrilhaPersonalizadaTO> findAll() {
        ArrayList<TrilhaPersonalizadaTO> trilhas = new ArrayList<>();
        String sql = "SELECT * FROM DDD_TRILHAS_PERSONALIZADAS ORDER BY id_trilha_pers";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    TrilhaPersonalizadaTO trilhaP = new TrilhaPersonalizadaTO();
                    trilhaP.setIdTrilhaPers(rs.getLong("id_trilha_pers"));
                    trilhaP.setIdUser(rs.getLong("id_user"));
                    trilhaP.setDataCriacao(rs.getDate("data_criacao").toLocalDate());
                    trilhaP.setJsonConteudo(rs.getString("json_conteudo"));
                    trilhas.add(trilhaP);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na busca: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return trilhas;
    }

    /**
     * Busca uma trilha personalizada pelo seu identificador único (ID).
     *
     * @param idTrilhaPers o código (ID) da trilha a ser buscada.
     * @return um objeto {@link TrilhaPersonalizadaTO} correspondente ao ID informado,
     * ou {@code null} se nenhum registro for encontrado.
     */
    public TrilhaPersonalizadaTO findByCodigo(Long idTrilhaPers) {
        TrilhaPersonalizadaTO trilhaP = new TrilhaPersonalizadaTO();
        String sql = "SELECT * FROM DDD_TRILHAS_PERSONALIZADAS WHERE id_trilha_pers = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, idTrilhaPers);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                trilhaP.setIdTrilhaPers(rs.getLong("id_trilha_pers"));
                trilhaP.setIdUser(rs.getLong("id_user"));
                trilhaP.setDataCriacao(rs.getDate("data_criacao").toLocalDate());
                trilhaP.setJsonConteudo(rs.getString("json_conteudo"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na busca: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return trilhaP;
    }

    /**
     * Busca todas as trilhas personalizadas de um usuário específico.
     *
     * @param idUser O ID do usuário.
     * @return uma lista de {@link TrilhaPersonalizadaTO} do usuário, ou null em caso de erro.
     */
    public ArrayList<TrilhaPersonalizadaTO> findByUserId(Long idUser) {
        ArrayList<TrilhaPersonalizadaTO> trilhas = new ArrayList<>();
        // Ordena pela data de criação, da mais nova para a mais antiga
        String sql = "SELECT * FROM DDD_TRILHAS_PERSONALIZADAS WHERE id_user = ? ORDER BY data_criacao DESC";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, idUser);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    TrilhaPersonalizadaTO trilhaP = new TrilhaPersonalizadaTO();
                    trilhaP.setIdTrilhaPers(rs.getLong("id_trilha_pers"));
                    trilhaP.setIdUser(rs.getLong("id_user"));
                    trilhaP.setDataCriacao(rs.getDate("data_criacao").toLocalDate());
                    trilhaP.setJsonConteudo(rs.getString("json_conteudo"));
                    trilhas.add(trilhaP);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na busca por ID de usuário: " + e.getMessage());
            return null;
        } finally {
            ConnectionFactory.closeConnection();
        }

        return trilhas;
    }


    /**
     * Insere um novo registro de trilha personalizada na tabela <b>DDD_TRILHAS_PERSONALIZADAS</b>.
     *
     * @param trilhaP o objeto {@link TrilhaPersonalizadaTO} contendo os dados a serem inseridos.
     * @return o próprio {@link TrilhaPersonalizadaTO} se o registro for inserido com sucesso,
     * ou {@code null} em caso de erro.
     */
    public TrilhaPersonalizadaTO save(TrilhaPersonalizadaTO trilhaP) {
        String sql = "INSERT INTO DDD_TRILHAS_PERSONALIZADAS(id_trilha_pers, id_user, data_criacao, json_conteudo) VALUES(?,?,?,?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, trilhaP.getIdTrilhaPers());
            ps.setLong(2, trilhaP.getIdUser());
            ps.setDate(3, Date.valueOf(trilhaP.getDataCriacao()));
            ps.setString(4, trilhaP.getJsonConteudo());

            if (ps.executeUpdate() > 0) {
                return trilhaP;
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
     * Exclui uma trilha personalizada pelo seu identificador único (ID).
     *
     * @param idTrilhaPers o identificador da trilha a ser excluída.
     * @return {@code true} se a exclusão for bem-sucedida, {@code false} caso contrário.
     */
    public boolean delete(Long idTrilhaPers) {
        String sql = "DELETE FROM DDD_TRILHAS_PERSONALIZADAS WHERE id_trilha_pers = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, idTrilhaPers);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    /**
     * Atualiza os dados de uma trilha personalizada existente na tabela <b>DDD_TRILHAS_PERSONALIZADAS</b>.
     *
     * @param trilhaP o objeto {@link TrilhaPersonalizadaTO} contendo os novos dados da trilha.
     * @return o {@link TrilhaPersonalizadaTO} atualizado, ou {@code null} se ocorrer algum erro.
     */
    public TrilhaPersonalizadaTO update(TrilhaPersonalizadaTO trilhaP) {
        String sql = "UPDATE DDD_TRILHAS_PERSONALIZADAS SET id_user=?, data_criacao=?, json_conteudo=? WHERE id_trilha_pers=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, trilhaP.getIdUser());
            ps.setDate(2, Date.valueOf(trilhaP.getDataCriacao()));
            ps.setString(3, trilhaP.getJsonConteudo());
            ps.setLong(4, trilhaP.getIdTrilhaPers());

            if (ps.executeUpdate() > 0) {
                return trilhaP;
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