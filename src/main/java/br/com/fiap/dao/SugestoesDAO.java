package br.com.fiap.dao;

import br.com.fiap.to.SugestoesTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe responsável pelo acesso e manipulação dos dados da entidade <strong>ddd_sugestoes</strong>
 * na base de dados SQL.
 * <p>
 * Esta classe implementa as operações CRUD (Create, Read, Update, Delete)
 * para a tabela <b>ddd_sugestoes</b>.
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
public class SugestoesDAO {

    /**
     * Recupera todas as sugestões cadastradas na tabela <b>ddd_sugestoes</b>.
     *
     * @return uma lista de {@link SugestoesTO} com todas as sugestões encontradas,
     * ou {@code null} caso ocorra um erro.
     */
    public ArrayList<SugestoesTO> findAll() {
        ArrayList<SugestoesTO> sugestoes = new ArrayList<SugestoesTO>();
        String sql = "SELECT * FROM ddd_sugestoes ORDER BY id_sugs";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    SugestoesTO sugestao = new SugestoesTO();
                    sugestao.setIdSugestoes(rs.getLong("id_sugs"));
                    sugestao.setTitulo(rs.getString("titulo"));
                    sugestao.setTipo(rs.getString("tipo"));
                    sugestao.setDescricao(rs.getString("descricao"));
                    sugestao.setDuracao(rs.getString("duracao"));
                    sugestao.setDificuldade(rs.getString("dificuldade"));
                    sugestao.setLink(rs.getString("link"));
                    sugestoes.add(sugestao);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na busca: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return sugestoes;
    }

    /**
     * Busca uma sugestão pelo seu identificador único (ID).
     *
     * @param idSugestoes o código (ID) da sugestão a ser buscada.
     * @return um objeto {@link SugestoesTO} correspondente ao ID informado,
     * ou {@code null} se nenhum registro for encontrado.
     */
    public SugestoesTO findByCodigo(Long idSugestoes) {
        SugestoesTO sugestao = new SugestoesTO();
        String sql = "SELECT * FROM ddd_sugestoes WHERE id_sugs = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, idSugestoes);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sugestao.setIdSugestoes(rs.getLong("id_sugs"));
                sugestao.setTitulo(rs.getString("titulo"));
                sugestao.setTipo(rs.getString("tipo"));
                sugestao.setDescricao(rs.getString("descricao"));
                sugestao.setDuracao(rs.getString("duracao"));
                sugestao.setDificuldade(rs.getString("dificuldade"));
                sugestao.setLink(rs.getString("link"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na busca: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return sugestao;
    }

    /**
     * Insere um novo registro de sugestão na tabela <b>ddd_sugestoes</b>.
     *
     * @param sugestao o objeto {@link SugestoesTO} contendo os dados a serem inseridos.
     * @return o próprio {@link SugestoesTO} se o registro for inserido com sucesso,
     * ou {@code null} em caso de erro.
     */
    public SugestoesTO save(SugestoesTO sugestao) {
        String sql = "INSERT INTO ddd_sugestoes(titulo, tipo, descricao, duracao, dificuldade, link) VALUES(?,?,?,?,?,?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, sugestao.getTitulo());
            ps.setString(2, sugestao.getTipo());
            ps.setString(3, sugestao.getDescricao());
            ps.setString(4, sugestao.getDuracao());
            ps.setString(5, sugestao.getDificuldade());
            ps.setString(6, sugestao.getLink());

            if (ps.executeUpdate() > 0) {
                return sugestao;
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
     * Exclui uma sugestão pelo seu identificador único (ID).
     *
     * @param idSugestoes o identificador da sugestão a ser excluída.
     * @return {@code true} se a exclusão for bem-sucedida, {@code false} caso contrário.
     */
    public boolean delete(Long idSugestoes) {
        String sql = "DELETE FROM ddd_sugestoes WHERE id_sugs = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, idSugestoes);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    /**
     * Atualiza os dados de uma sugestão existente na tabela <b>ddd_sugestoes</b>.
     *
     * @param sugestao o objeto {@link SugestoesTO} contendo os novos dados da sugestão.
     * @return o {@link SugestoesTO} atualizado, ou {@code null} se ocorrer algum erro.
     */
    public SugestoesTO update(SugestoesTO sugestao) {
        String sql = "UPDATE ddd_sugestoes SET titulo=?, tipo=?, descricao=?, duracao=?, dificuldade=?, link=? WHERE id_sugs=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, sugestao.getTitulo());
            ps.setString(2, sugestao.getTipo());
            ps.setString(3, sugestao.getDescricao());
            ps.setString(4, sugestao.getDuracao());
            ps.setString(5, sugestao.getDificuldade());
            ps.setString(6, sugestao.getLink());
            ps.setLong(7, sugestao.getIdSugestoes());

            if (ps.executeUpdate() > 0) {
                return sugestao;
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