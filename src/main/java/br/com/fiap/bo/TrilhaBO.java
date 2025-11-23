package br.com.fiap.bo;

import br.com.fiap.dao.TrilhaDAO;
import br.com.fiap.to.TrilhaTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * <p>Classe de negócios (Business Object) que gerencia operações relacionadas a trilhas.</p>
 * <p>Utiliza a {@link TrilhaDAO} para acessar o banco de dados.</p>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
public class TrilhaBO {
    private TrilhaDAO trilhaDAO;

    /**
     * Retorna todas as trilhas cadastradas no banco de dados.
     *
     * @return ArrayList de {@link TrilhaTO} contendo todas as trilhas.
     */
    public ArrayList<TrilhaTO> findAll() {
        trilhaDAO = new TrilhaDAO();
        return trilhaDAO.findAll();
    }

    /**
     * Busca uma trilha pelo seu código (ID).
     *
     * @param codigo Código (ID) da trilha.
     * @return {@link TrilhaTO} correspondente ao código informado ou null se não encontrada.
     */
    public TrilhaTO findByCodigo(Long codigo) throws SQLException {
        trilhaDAO = new TrilhaDAO();
        return trilhaDAO.findByCodigo(codigo);
    }

    /**
     * Salva uma nova trilha no banco de dados.
     *
     * @param trilha Objeto {@link TrilhaTO} contendo os dados da trilha a ser cadastrada.
     * @return {@link TrilhaTO} salvo, ou null se não foi possível salvar.
     */
    public TrilhaTO save(TrilhaTO trilha) {
        trilhaDAO = new TrilhaDAO();
        return trilhaDAO.save(trilha);
    }

    /**
     * Exclui uma trilha do banco de dados pelo seu código (ID).
     *
     * @param codigo Código (ID) da trilha a ser excluída.
     * @return true se a exclusão foi bem-sucedida, false caso contrário.
     */
    public boolean delete(Long codigo) {
        trilhaDAO = new TrilhaDAO();
        return trilhaDAO.delete(codigo);
    }

    /**
     * Atualiza os dados de uma trilha existente no banco de dados.
     *
     * @param trilha Objeto {@link TrilhaTO} contendo os dados atualizados da trilha.
     * @return {@link TrilhaTO} atualizado, ou null se não foi possível atualizar.
     */
    public TrilhaTO update(TrilhaTO trilha) {
        trilhaDAO = new TrilhaDAO();
        return trilhaDAO.update(trilha);
    }
}
