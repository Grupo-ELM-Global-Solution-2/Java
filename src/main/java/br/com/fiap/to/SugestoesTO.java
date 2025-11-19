package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class SugestoesTO {
    /** Identificador único de sugestoes. */
    private Long idSugestoes;

    /** Titulo das Sugestões. */
    @NotBlank
    private String titulo;

    /** Tipo das Sugestões. */
    @NotBlank
    private String tipo;

    /** Descrição de sugestões */
    @NotBlank
    private String descricao;

    /** Duração de sugestões */
    @NotBlank
    private String duracao;

    /** Dificuldade de sugestões */
    @NotBlank
    private String dificuldade;

    /** link de sugestões */
    @NotBlank
    private String link;

    /**
     * Construtor padrão da classe {@code SugestoesTO}.
     */
    public SugestoesTO() {}

    /**
     * Construtor completo para inicializar todos os atributos de uma sugestão.
     *
     * @param idSugestoes    identificador único da sugestão
     * @param titulo         nome da sugestão
     * @param tipo           tipo da sugestão
     * @param descricao    descricao da sugestão
     * @param duracao        duracao da sugestão
     * @param dificuldade    dificuldade da sugestão
     */
    public SugestoesTO(Long idSugestoes, String titulo, String tipo, String descricao, String duracao, String dificuldade, String link) {
        this.idSugestoes = idSugestoes;
        this.titulo = titulo;
        this.tipo = tipo;
        this.descricao = descricao;
        this.duracao = duracao;
        this.dificuldade = dificuldade;
        this.link = link;
    }

    /* Falta fazer o resto do JavaDOC */

    public Long getIdSugestoes() {
        return idSugestoes;
    }

    public void setIdSugestoes(Long idSugestoes) {
        this.idSugestoes = idSugestoes;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
