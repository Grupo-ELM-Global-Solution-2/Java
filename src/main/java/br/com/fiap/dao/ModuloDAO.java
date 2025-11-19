package br.com.fiap.dao;

import br.com.fiap.to.ModuloTO;

import java.sql.*;
import java.util.ArrayList;

/**
 * Classe responsável pelo acesso e manipulação dos dados da entidade <strong>ddd_modulo</strong>
 * na base de dados SQL.
 * <p>
 * Esta classe implementa as operações CRUD (Create, Read, Update, Delete)
 * para a tabela <b>ddd_modulo</b>, permitindo o gerenciamento das modulos
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
public class ModuloDAO {

    /**
     * Recupera todos os modulos cadastrados na tabela <b>ddd_modulo</b>.
     *
     * @return uma lista de {@link ModuloTO} com todos os modulos encontrados,
     * ou {@code null} caso ocorra um erro na modulo.
     */
    public ArrayList<ModuloTO> findAll() {
        ArrayList<ModuloTO> modulos = new ArrayList<ModuloTO>();
        String sql = "SELECT * FROM ddd_modulo ORDER BY id_mod";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    ModuloTO modulo = new ModuloTO();
                    modulo.setIdModulo(rs.getLong("id_mod"));
                    modulo.setNome(rs.getString("nome"));
                    modulo.setDuracao(rs.getString("duracao"));
                    modulo.setIdTrilha(rs.getLong("id_tri"));
                    modulos.add(modulo);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na modulo: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return modulos;
    }

    /**
     * Busca uma modulo pelo seu identificador único (ID).
     *
     * @param idModulo o código (ID) da modulo a ser buscada.
     * @return um objeto {@link ModuloTO} correspondente ao ID informado,
     * ou {@code null} se nenhum registro for encontrado.
     */
    public ModuloTO findByCodigo(Long idModulo) {
        ModuloTO modulo = new ModuloTO();
        String sql = "SELECT * FROM ddd_modulo WHERE id_mod = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, idModulo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                modulo.setIdModulo(rs.getLong("id_mod"));
                modulo.setNome(rs.getString("nome"));
                modulo.setDuracao(rs.getString("duracao"));
                modulo.setIdTrilha(rs.getLong("id_tri"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na modulo: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return modulo;
    }

    /**
     * Insere um novo registro de modulo na tabela <b>ddd_modulo</b>.
     *
     * @param modulo o objeto {@link ModuloTO} contendo os dados a serem inseridos.
     * @return o próprio {@link ModuloTO} se o registro for inserido com sucesso,
     * ou {@code null} em caso de erro.
     */
    public ModuloTO save(ModuloTO modulo) {
        String sql = "INSERT INTO ddd_modulo(id_mod, nome, duracao, id_tri) VALUES(?,?,?,?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, modulo.getIdModulo());
            ps.setString(2, modulo.getNome());
            ps.setString(3, modulo.getDuracao());
            ps.setLong(4, modulo.getIdTrilha());
            if (ps.executeUpdate() > 0) {
                return modulo;
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
     * Exclui um modulo pelo seu identificador único (ID).
     *
     * @param idModulo o identificador da modulo a ser excluída.
     * @return {@code true} se a exclusão for bem-sucedida, {@code false} caso contrário.
     */
    public boolean delete(Long idModulo) {
        String sql = "DELETE FROM ddd_modulo WHERE id_mod = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, idModulo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    /**
     * Atualiza os dados de uma modulo existente na tabela <b>ddd_modulo</b>.
     *
     * @param modulo o objeto {@link ModuloTO} contendo os novos dados da modulo.
     * @return o {@link ModuloTO} atualizado, ou {@code null} se ocorrer algum erro.
     */
    public ModuloTO update(ModuloTO modulo) {
        String sql = "UPDATE ddd_modulo SET nome=?, duracao=?, id_tri=? WHERE id_mod=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, modulo.getNome());
            ps.setString(2, modulo.getDuracao());
            ps.setLong(3, modulo.getIdTrilha());
            ps.setLong(4, modulo.getIdModulo());

            if (ps.executeUpdate() > 0) {
                return modulo;
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
