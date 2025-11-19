package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Representa um módulo cadastrado no sistema, associada a uma {@code TrilhaTO}.
 *
 * <p>Esta classe contém atributos como id, nome, duracao e id da trilha.</p>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
public class ModuloTO {

    /** Identificador único do modulo. */
    private Long idModulo;

    /** Nome do modulo. */
    @NotBlank
    private String nome;

    /** Duração do modulo. */
    @NotBlank
    private String duracao;

    /** Link Vídeo do modulo. */
    @NotBlank
    private String link;

    /** Identificador da trilha a qual o módulo pertence. */
    @NotNull
    private Long idTrilha;

    /**
     * Construtor padrão da classe {@code ModuloTO}.
     */
    public ModuloTO() {
    }

    /**
     * Construtor completo para inicializar todos os atributos de uma modulo.
     *
     * @param idModulo   identificador único da modulo
     * @param nome       nome do modulo
     * @param duracao    duracao do modulo
     * @param link      link vídeo do modulo
     * @param idTrilha   identificador da trilha a qual pertence
     */
    public ModuloTO(Long idModulo, String nome, String duracao, String link, Long idTrilha) {
        this.idModulo = idModulo;
        this.nome = nome;
        this.duracao = duracao;
        this.link = link;
        this.idTrilha = idTrilha;
    }

    /** @return o identificador único da modulo */
    public Long getIdModulo() {
        return idModulo;
    }

    /**
     * Define o identificador da modulo.
     * @param idModulo o ID da modulo
     */
    public void setIdModulo(Long idModulo) {
        this.idModulo = idModulo;
    }

    /** @return o nome do módulo */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do módulo.
     * @param nome o nome a ser atribuído
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /** @return a duração do módulo */
    public String getDuracao() {
        return duracao;
    }

    /**
     * Define a duração do módulo.
     * @param duracao a duração a ser atribuída
     */
    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    /** @return a link vídeo do módulo */
    public String getLink() {
        return link;
    }

    /**
     * Define a link vídeo do módulo.
     * @param link link vídeo a ser atribuído
     */
    public void setLink(String link) {
        this.link = link;
    }

    /** @return o identificador da trilha */
    public Long getIdTrilha() {
        return idTrilha;
    }

    /**
     * Define o identificador da trilha.
     * @param idTrilha o ID da trilha
     */
    public void setIdTrilha(Long idTrilha) {
        this.idTrilha = idTrilha;
    }
}