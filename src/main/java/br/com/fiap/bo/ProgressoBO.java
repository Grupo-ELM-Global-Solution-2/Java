package br.com.fiap.bo;

import br.com.fiap.dao.ProgressoDAO;
import br.com.fiap.to.ProgressoTO;

import java.util.ArrayList;

/**
 * <p>Classe de negócios (Business Object) que gerencia operações relacionadas a progressos.</p>
 * <p>Utiliza a {@link br.com.fiap.dao.ProgressoDAO} para acessar o banco de dados.</p>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
public class ProgressoBO {
    private ProgressoDAO progressoDAO;

    /**
     * Retorna todos os progressos cadastrados no banco de dados.
     *
     * @return ArrayList de {@link ProgressoTO} contendo todos os progressos.
     */
    public ArrayList<ProgressoTO> findAll() {
        progressoDAO = new ProgressoDAO();
        return progressoDAO.findAll();
    }

    /**
     * Busca uma progresso pelo seu código (ID).
     *
     * @param codigo Código (ID) do progresso.
     * @return {@link ProgressoTO} correspondente ao código informado ou null se não encontrado.
     */
    public ProgressoTO findByCodigo(Long codigo) {
        progressoDAO = new ProgressoDAO();
        return progressoDAO.findByCodigo(codigo);
    }

    /**
     * Busca todos os progressos de um usuário.
     *
     * @param idUser Código (ID) do usuário.
     * @return ArrayList de {@link ProgressoTO}.
     */
    public ArrayList<ProgressoTO> findByUserId(Long idUser) {
        progressoDAO = new ProgressoDAO();
        return progressoDAO.findByUserId(idUser);
    }

    /**
     * Salva uma nova progresso no banco de dados.
     *
     * @param progresso Objeto {@link ProgressoTO} contendo os dados do progresso a ser cadastrado.
     * @return {@link ProgressoTO} salvo, ou null se não foi possível salvar.
     */
    public ProgressoTO save(ProgressoTO progresso) {
        progressoDAO = new ProgressoDAO();
        return progressoDAO.save(progresso);
    }

    /**
     * Exclui uma progresso do banco de dados pelo seu código (ID).
     *
     * @param codigo Código (ID) do progresso a ser excluído.
     * @return true se a exclusão foi bem-sucedida, false caso contrário.
     */
    public boolean delete(Long codigo) {
        progressoDAO = new ProgressoDAO();
        return progressoDAO.delete(codigo);
    }

    /**
     * Atualiza os dados de uma progresso existente no banco de dados.
     *
     * @param progresso Objeto {@link ProgressoTO} contendo os dados atualizados do progresso.
     * @return {@link ProgressoTO} atualizado, ou null se não foi possível atualizar.
     */
    public ProgressoTO update(ProgressoTO progresso) {
        progressoDAO = new ProgressoDAO();
        return progressoDAO.update(progresso);
    }
}
