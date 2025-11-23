package br.com.fiap.dao;

import br.com.fiap.to.UsuarioTO;

import java.sql.*;
import java.util.ArrayList;

/**
 * Classe responsável pelo acesso e manipulação dos dados da entidade <strong>ddd_user</strong>
 * na base de dados SQL.
 * <p>
 * Esta classe implementa as operações CRUD (Create, Read, Update, Delete)
 * para a tabela <b>ddd_user</b>, permitindo o gerenciamento das users
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
public class UsuarioDAO {

    /**
     * Recupera todos os usuarios cadastrados na tabela <b>ddd_user</b>.
     *
     * @return uma lista de {@link UsuarioTO} com todas as users encontradas,
     * ou {@code null} caso ocorra um erro na user.
     */
    public ArrayList<UsuarioTO> findAll() {
        ArrayList<UsuarioTO> users = new ArrayList<UsuarioTO>();
        String sql = "SELECT * FROM ddd_user ORDER BY id_user";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UsuarioTO user = new UsuarioTO();
                    user.setIdUser(rs.getLong("id_user"));
                    user.setNome(rs.getString("nome"));
                    user.setEmail(rs.getString("email"));
                    user.setSenha(rs.getString("senha"));
                    users.add(user);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na user: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return users;
    }

    /**
     * Busca um usuario pelo seu identificador único (ID).
     *
     * @param idUser o código (ID) do usuario a ser buscado.
     * @return um objeto {@link UsuarioTO} correspondente ao ID informado,
     * ou {@code null} se nenhum registro for encontrado.
     */
    public UsuarioTO findByCodigo(Long idUser) throws SQLException {
        UsuarioTO user = new UsuarioTO();
        String sql = "SELECT * FROM ddd_user WHERE id_user = ?";
        ResultSet rs = null;
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, idUser);
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setIdUser(rs.getLong("id_user"));
                user.setNome(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setSenha(rs.getString("senha"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na user: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
            if (rs != null) {
                rs.close();
            }
        }
        return user;
    }

    /**
     * Busca um usuário pelo email.
     *
     * @param email O email do usuário.
     * @return O objeto {@link UsuarioTO} encontrado, ou {@code null} se não existir.
     */
    public UsuarioTO findByEmail(String email) throws SQLException {
        UsuarioTO user = null;
        String sql = "SELECT * FROM ddd_user WHERE email = ?";
        ResultSet rs = null;
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                user = new UsuarioTO();
                user.setIdUser(rs.getLong("id_user"));
                user.setNome(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setSenha(rs.getString("senha"));
            }
        } catch (SQLException e) {
            System.out.println("Erro na busca de usuário por email: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
            if (rs != null) {
                rs.close();
            }
        }
        return user;
    }

    /**
     * Insere um novo registro de usuario na tabela <b>ddd_user</b>.
     *
     * @param user o objeto {@link UsuarioTO} contendo os dados a serem inseridos.
     * @return o próprio {@link UsuarioTO} se o registro for inserido com sucesso,
     * ou {@code null} em caso de erro.
     */
    public UsuarioTO save(UsuarioTO user) {
        String sql = "INSERT INTO ddd_user(nome, email, senha) VALUES(?,?,?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, user.getNome());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getSenha());
            if (ps.executeUpdate() > 0) {
                return user;
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
     * Exclui um usuario pelo seu identificador único (ID).
     *
     * @param idUser o identificador da user a ser excluída.
     * @return {@code true} se a exclusão for bem-sucedida, {@code false} caso contrário.
     */
    public boolean delete(Long idUser) {
        String sql = "DELETE FROM ddd_user WHERE id_user = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, idUser);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    /**
     * Atualiza os dados de um usuario existente na tabela <b>ddd_user</b>.
     *
     * @param user o objeto {@link UsuarioTO} contendo os novos dados da user.
     * @return o {@link UsuarioTO} atualizado, ou {@code null} se ocorrer algum erro.
     */
    public UsuarioTO update(UsuarioTO user) {
        String sql = "UPDATE ddd_user SET nome=?, email=?, senha=? WHERE id_user=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, user.getNome());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getSenha());
            ps.setLong(4, user.getIdUser());

            if (ps.executeUpdate() > 0) {
                return user;
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
