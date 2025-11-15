package br.com.fiap.bo;

import br.com.fiap.dao.ModuloDAO;
import br.com.fiap.to.ModuloTO;

import java.util.ArrayList;

/**
 * <p>Classe de negócios que gerencia operações relacionadas a modulos.</p>
 * <p>Utiliza a {@link ModuloDAO} para acessar o banco de dados.</p>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
public class ModuloBO {
    private ModuloDAO moduloDAO;

    /**
     * Retorna todos os modulos cadastrados no banco de dados.
     *
     * @return ArrayList de {@link ModuloTO} contendo todos os modulos.
     */
    public ArrayList<ModuloTO> findAll() {
        moduloDAO = new ModuloDAO();
        return moduloDAO.findAll();
    }

    /**
     * Busca uma modulo pelo seu código (ID).
     *
     * @param codigo Código (ID) do modulo.
     * @return {@link ModuloTO} correspondente ao código informado ou null se não encontrado.
     */
    public ModuloTO findByCodigo(Long codigo) {
        moduloDAO = new ModuloDAO();
        return moduloDAO.findByCodigo(codigo);
    }

    /**
     * Salva uma nova modulo no banco de dados.
     *
     * @param modulo Objeto {@link ModuloTO} contendo os dados do modulo a ser cadastrado.
     * @return {@link ModuloTO} salvo, ou null se não foi possível salvar.
     */
    public ModuloTO save(ModuloTO modulo) {
        moduloDAO = new ModuloDAO();
        return moduloDAO.save(modulo);
    }

    /**
     * Exclui uma modulo do banco de dados pelo seu código (ID).
     *
     * @param codigo Código (ID) do modulo a ser excluído.
     * @return true se a exclusão foi bem-sucedido, false caso contrário.
     */
    public boolean delete(Long codigo) {
        moduloDAO = new ModuloDAO();
        return moduloDAO.delete(codigo);
    }

    /**
     * Atualiza os dados de uma modulo existente no banco de dados.
     *
     * @param modulo Objeto {@link ModuloTO} contendo os dados atualizados do modulo.
     * @return {@link ModuloTO} atualizado, ou null se não foi possível atualizar.
     */
    public ModuloTO update(ModuloTO modulo) {
        moduloDAO = new ModuloDAO();
        return moduloDAO.update(modulo);
    }
}
