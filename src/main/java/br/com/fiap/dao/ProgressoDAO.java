package br.com.fiap.dao;

import br.com.fiap.to.ProgressoTO;

import java.sql.*;
import java.util.ArrayList;

/**
 * Classe responsável pelo acesso e manipulação dos dados da entidade <strong>ddd_progresso</strong>
 * na base de dados SQL.
 * <p>
 * Esta classe implementa as operações CRUD (Create, Read, Update, Delete)
 * para a tabela <b>ddd_progresso</b>, permitindo o gerenciamento dos progressos
 * dos modulos do sistema.
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
public class ProgressoDAO {

    /**
     * Recupera todos os progressos cadastrados na tabela <b>ddd_progresso</b>.
     *
     * @return uma lista de {@link ProgressoTO} com todos os progressos encontrados,
     * ou {@code null} caso ocorra um erro na progresso.
     */
    public ArrayList<ProgressoTO> findAll() {
        ArrayList<ProgressoTO> progressos = new ArrayList<ProgressoTO>();
        String sql = "SELECT * FROM ddd_progresso ORDER BY id_prog";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    ProgressoTO progresso = new ProgressoTO();
                    progresso.setIdProgresso(rs.getLong("id_prog"));
                    progresso.setStatus(rs.getInt("status"));
                    progresso.setIdUser(rs.getLong("id_user"));
                    progresso.setIdModulo(rs.getLong("id_modulo"));
                    progressos.add(progresso);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na progresso: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return progressos;
    }

    /**
     * Busca uma progresso pelo seu identificador único (ID).
     *
     * @param idProgresso o código (ID) do progresso a ser buscada.
     * @return um objeto {@link ProgressoTO} correspondente ao ID informado,
     * ou {@code null} se nenhum registro for encontrado.
     */
    public ProgressoTO findByCodigo(Long idProgresso) {
        ProgressoTO progresso = new ProgressoTO();
        String sql = "SELECT * FROM ddd_progresso WHERE id_prog = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, idProgresso);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                progresso.setIdProgresso(rs.getLong("id_prog"));
                progresso.setStatus(rs.getInt("status"));
                progresso.setIdUser(rs.getLong("id_user"));
                progresso.setIdModulo(rs.getLong("id_modulo"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na progresso: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return progresso;
    }

    /**
     * Busca todos os registros de progresso de um usuário específico.
     *
     * @param idUser O ID do usuário.
     * @return uma lista de {@link ProgressoTO} do usuário, ou null em caso de erro.
     */
    public ArrayList<ProgressoTO> findByUserId(Long idUser) {
        ArrayList<ProgressoTO> progressos = new ArrayList<>();
        String sql = "SELECT * FROM ddd_progresso WHERE id_user = ?";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, idUser);
            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    ProgressoTO progresso = new ProgressoTO();
                    progresso.setIdProgresso(rs.getLong("id_prog"));
                    progresso.setStatus(rs.getInt("status"));
                    progresso.setIdUser(rs.getLong("id_user"));
                    progresso.setIdModulo(rs.getLong("id_modulo"));
                    progressos.add(progresso);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na busca de progresso por ID de usuário: " + e.getMessage());
            return null;
        } finally {
            ConnectionFactory.closeConnection();
        }

        return progressos;
    }

    /**
     * Insere um novo registro de progresso na tabela <b>ddd_progresso</b>.
     *
     * @param progresso o objeto {@link ProgressoTO} contendo os dados a serem inseridos.
     * @return o próprio {@link ProgressoTO} se o registro for inserido com sucesso,
     * ou {@code null} em caso de erro.
     */
    public ProgressoTO save(ProgressoTO progresso) {
        String sql = "INSERT INTO ddd_progresso(id_prog, status, id_user, id_modulo) VALUES(?,?,?,?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, progresso.getIdProgresso());
            ps.setInt(2, progresso.getStatus());
            ps.setLong(3, progresso.getIdUser());
            ps.setLong(4, progresso.getIdModulo());
            if (ps.executeUpdate() > 0) {
                return progresso;
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
     * Exclui uma progresso pelo seu identificador único (ID).
     *
     * @param idProgresso o identificador do progresso a ser excluído.
     * @return {@code true} se a exclusão for bem-sucedida, {@code false} caso contrário.
     */
    public boolean delete(Long idProgresso) {
        String sql = "DELETE FROM ddd_progresso WHERE id_prog = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, idProgresso);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    /**
     * Atualiza os dados de uma progresso existente na tabela <b>ddd_progresso</b>.
     *
     * @param progresso o objeto {@link ProgressoTO} contendo os novos dados do progresso.
     * @return o {@link ProgressoTO} atualizado, ou {@code null} se ocorrer algum erro.
     */
    public ProgressoTO update(ProgressoTO progresso) {
        String sql = "UPDATE ddd_progresso SET status=?, id_user=?, id_modulo=? WHERE id_prog=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setInt(1, progresso.getStatus());
            ps.setLong(2, progresso.getIdUser());
            ps.setLong(3, progresso.getIdModulo());
            ps.setLong(4, progresso.getIdProgresso());

            if (ps.executeUpdate() > 0) {
                return progresso;
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
