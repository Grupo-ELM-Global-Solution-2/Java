package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Representa uma trilha personalizada criada por um usuário.
 *
 * <p>Esta classe contém o ID da trilha, o ID do usuário, a data de criação
 * e o conteúdo (em formato JSON) da trilha.</p>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
public class TrilhaPersonalizadaTO {

    /** Identificador único da trilha personalizada. */
    private Long idTrilhaPers;

    /** Identificador do usuário que criou a trilha. */
    @NotNull
    private Long idUser;

    /** Data de criação da trilha. */
    private LocalDate dataCriacao;

    /** Conteúdo da trilha, armazenado como um texto JSON (CLOB). */
    @NotBlank
    private String jsonConteudo;

    /**
     * Construtor padrão da classe {@code TrilhaPersonalizadaTO}.
     */
    public TrilhaPersonalizadaTO() {
    }

    /**
     * Construtor completo.
     *
     * @param idTrilhaPers  identificador único
     * @param idUser        identificador do usuário
     * @param dataCriacao   data da criação
     * @param jsonConteudo  conteúdo JSON da trilha
     */
    public TrilhaPersonalizadaTO(Long idTrilhaPers, Long idUser, LocalDate dataCriacao, String jsonConteudo) {
        this.idTrilhaPers = idTrilhaPers;
        this.idUser = idUser;
        this.dataCriacao = dataCriacao;
        this.jsonConteudo = jsonConteudo;
    }

    /** @return o identificador único da trilha personalizada */
    public Long getIdTrilhaPers() {
        return idTrilhaPers;
    }

    /**
     * Define o identificador da trilha personalizada.
     * @param idTrilhaPers o ID
     */
    public void setIdTrilhaPers(Long idTrilhaPers) {
        this.idTrilhaPers = idTrilhaPers;
    }

    /** @return o identificador do usuário */
    public Long getIdUser() {
        return idUser;
    }

    /**
     * Define o identificador do usuário.
     * @param idUser o ID do usuário
     */
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    /** @return a data de criação */
    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    /**
     * Define a data de criação.
     * @param dataCriacao a data
     */
    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    /** @return o conteúdo JSON da trilha */
    public String getJsonConteudo() {
        return jsonConteudo;
    }

    /**
     * Define o conteúdo JSON da trilha.
     * @param jsonConteudo o texto JSON
     */
    public void setJsonConteudo(String jsonConteudo) {
        this.jsonConteudo = jsonConteudo;
    }
}