package br.com.fiap.bo;

import br.com.fiap.dao.SugestoesDAO;
import br.com.fiap.to.SugestoesTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * <p>Classe de negócios (Business Object) que gerencia operações relacionadas a sugestões.</p>
 * <p>Utiliza a {@link SugestoesDAO} para acessar o banco de dados.</p>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
public class SugestoesBO {
    private SugestoesDAO sugestoesDAO;

    /**
     * Retorna todas as sugestões cadastradas no banco de dados.
     *
     * @return ArrayList de {@link SugestoesTO} contendo todas as sugestões.
     */
    public ArrayList<SugestoesTO> findAll() {
        sugestoesDAO = new SugestoesDAO();
        return sugestoesDAO.findAll();
    }

    /**
     * Busca uma sugestão pelo seu código (ID).
     *
     * @param codigo Código (ID) da sugestão.
     * @return {@link SugestoesTO} correspondente ao código informado ou null se não encontrada.
     */
    public SugestoesTO findByCodigo(Long codigo) throws SQLException {
        sugestoesDAO = new SugestoesDAO();
        return sugestoesDAO.findByCodigo(codigo);
    }

    /**
     * Salva uma nova sugestão no banco de dados.
     *
     * @param sugestao Objeto {@link SugestoesTO} contendo os dados da sugestão a ser cadastrada.
     * @return {@link SugestoesTO} salvo, ou null se não foi possível salvar.
     */
    public SugestoesTO save(SugestoesTO sugestao) {
        sugestoesDAO = new SugestoesDAO();
        return sugestoesDAO.save(sugestao);
    }

    /**
     * Exclui uma sugestão do banco de dados pelo seu código (ID).
     *
     * @param codigo Código (ID) da sugestão a ser excluída.
     * @return true se a exclusão foi bem-sucedida, false caso contrário.
     */
    public boolean delete(Long codigo) {
        sugestoesDAO = new SugestoesDAO();
        return sugestoesDAO.delete(codigo);
    }

    /**
     * Atualiza os dados de uma sugestão existente no banco de dados.
     *
     * @param sugestao Objeto {@link SugestoesTO} contendo os dados atualizados da sugestão.
     * @return {@link SugestoesTO} atualizado, ou null se não foi possível atualizar.
     */
    public SugestoesTO update(SugestoesTO sugestao) {
        sugestoesDAO = new SugestoesDAO();
        return sugestoesDAO.update(sugestao);
    }
}