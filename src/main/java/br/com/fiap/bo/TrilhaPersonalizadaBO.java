package br.com.fiap.bo;

import br.com.fiap.dao.TrilhaPersonalizadaDAO;
import br.com.fiap.to.TrilhaPersonalizadaTO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * <p>Classe de negócios (Business Object) que gerencia operações
 * relacionadas a trilhas personalizadas.</p>
 * * <p>Esta classe faz a ponte entre a camada de Resource (API) e a camada DAO (Banco de Dados),
 * instanciando a {@link TrilhaPersonalizadaDAO} para executar as operações.</p>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
public class TrilhaPersonalizadaBO {

    private TrilhaPersonalizadaDAO trilhaPersonalizadaDAO;

    /**
     * Retorna todas as trilhas personalizadas cadastradas no banco de dados.
     *
     * @return ArrayList de {@link TrilhaPersonalizadaTO} contendo todas as trilhas.
     */
    public ArrayList<TrilhaPersonalizadaTO> findAll() {
        trilhaPersonalizadaDAO = new TrilhaPersonalizadaDAO();
        return trilhaPersonalizadaDAO.findAll();
    }

    /**
     * Busca uma trilha personalizada pelo seu código (ID).
     *
     * @param codigo Código (ID) da trilha personalizada (id_trilha_pers).
     * @return {@link TrilhaPersonalizadaTO} correspondente ao código informado ou null se não encontrado.
     */
    public TrilhaPersonalizadaTO findByCodigo(Long codigo) throws SQLException {
        trilhaPersonalizadaDAO = new TrilhaPersonalizadaDAO();
        return trilhaPersonalizadaDAO.findByCodigo(codigo);
    }

    /**
     * Busca todas as trilhas personalizadas de um usuário específico.
     *
     * @param idUser Código (ID) do usuário (id_user).
     * @return ArrayList de {@link TrilhaPersonalizadaTO} contendo as trilhas do usuário.
     */
    public ArrayList<TrilhaPersonalizadaTO> findByUserId(Long idUser) throws SQLException {
        trilhaPersonalizadaDAO = new TrilhaPersonalizadaDAO();
        return trilhaPersonalizadaDAO.findByUserId(idUser);
    }

    /**
     * Salva uma nova trilha personalizada no banco de dados.
     *
     * @param trilhaP Objeto {@link TrilhaPersonalizadaTO} contendo os dados da trilha a ser cadastrada.
     * @return {@link TrilhaPersonalizadaTO} salvo, ou null se não foi possível salvar.
     */
    public TrilhaPersonalizadaTO save(TrilhaPersonalizadaTO trilhaP) {
        trilhaPersonalizadaDAO = new TrilhaPersonalizadaDAO();
        return trilhaPersonalizadaDAO.save(trilhaP);
    }

    /**
     * Exclui uma trilha personalizada do banco de dados pelo seu código (ID).
     *
     * @param codigo Código (ID) da trilha a ser excluída.
     * @return true se a exclusão foi bem-sucedida, false caso contrário.
     */
    public boolean delete(Long codigo) {
        trilhaPersonalizadaDAO = new TrilhaPersonalizadaDAO();
        return trilhaPersonalizadaDAO.delete(codigo);
    }

    /**
     * Atualiza os dados de uma trilha personalizada existente no banco de dados.
     *
     * @param trilhaP Objeto {@link TrilhaPersonalizadaTO} contendo os dados atualizados da trilha.
     * @return {@link TrilhaPersonalizadaTO} atualizado, ou null se não foi possível atualizar.
     */
    public TrilhaPersonalizadaTO update(TrilhaPersonalizadaTO trilhaP) {
        trilhaPersonalizadaDAO = new TrilhaPersonalizadaDAO();
        return trilhaPersonalizadaDAO.update(trilhaP);
    }
}