package br.com.fiap.to;


import jakarta.validation.constraints.*;

/**
 * Representa uma trilha cadastrada no sistema.
 *
 * <p>Esta classe contém atributos como id, nome, dificuldade e descricao.</p>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
public class TrilhaTO {
    /** Identificador único da trilha. */
    @NotNull
    private Long idTrilha;

    /** Nome da trilha. */
    @NotBlank
    private String nome;

    /** dificuldade da trilha (fácil, médio e difícil). */
    @NotNull
    @Pattern(regexp = "^(?i)(facil|medio|dificil)$")
    private String dificuldade;

    /**
     * Descrição da trilha
     */
    @NotBlank
    private String descricao;

    /**
     * Construtor padrão da classe {@code TrilhaTO}.
     */
    public TrilhaTO() {
    }

    /**
     * Construtor completo.
     *
     * @param idTrilha    identificador único da trilha
     * @param nome        nome do medicamento
     * @param dificuldade dificuldade da trilha
     * @param descricao   descrição da trilha
     */
    public TrilhaTO(Long idTrilha, String nome, String dificuldade, String descricao) {
        this.idTrilha = idTrilha;
        this.nome = nome;
        this.dificuldade = dificuldade;
        this.descricao = descricao;
    }

    /** @return o identificador único da trilha */
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

    /** @return o nome da trilha */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da trilha.
     * @param nome o nome da trilha.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /** @return o a dificuldade da trilha */
    public String getDificuldade() {
        return dificuldade;
    }

    /**
     * Define a dificuldade da trilha.
     * @param dificuldade a dificuldade da trilha.
     */
    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    /** @return o a descrição da trilha */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição da trilha.
     * @param descricao a descrição da trilha.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
