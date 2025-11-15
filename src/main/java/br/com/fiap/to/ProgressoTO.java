package br.com.fiap.to;


import jakarta.validation.constraints.*;

/**
 * Representa uma progresso médica cadastrada no sistema, associada a um {@code UsuarioTO} e a um {@code ModuloTO}.
 *
 * <p>Esta classe contém atributos como id, status, id do usuário e id do modulo.</p>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
public class ProgressoTO {
    /** Identificador único do progresso. */
    @NotNull
    private Long idProgresso;

    /** Status do progresso (Ex: 78%). */
    @NotNull
    @Min(0)
    @Max(100)
    private int status;

    /** Identificador do usuário que está progredindo. */
    @NotNull
    private Long idUser;

    /**
     * Identificador do modulo em que o progresso está ocorrendo.
     */
    @NotNull
    private Long idModulo;

    /**
     * Construtor padrão da classe {@code ProgressoTO}.
     */
    public ProgressoTO() {
    }

    /**
     * Construtor completo.
     *
     * @param idProgresso    identificador único do progresso
     * @param status         Status do progresso
     * @param idUser         Identificador do usuário que está progredindo
     * @param idModulo       Identificador do modulo em que o progresso está ocorrendo
     */
    public ProgressoTO(Long idProgresso, int status, Long idUser, Long idModulo) {
        this.idProgresso = idProgresso;
        this.status = status;
        this.idUser = idUser;
        this.idModulo = idModulo;
    }

    /** @return o identificador único do progresso */
    public Long getIdProgresso() {
        return idProgresso;
    }

    /**
     * Define o identificador do progresso.
     * @param idProgresso o ID do progresso
     */
    public void setIdProgresso(Long idProgresso) {
        this.idProgresso = idProgresso;
    }

    /** @return o status do progresso */
    public int getStatus() {
        return status;
    }

    /**
     * Define o status do progresso.
     * @param status o status atual do progresso.
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /** @return o identificador do usuário */
    public Long getIdUser() {
        return idUser;
    }

    /**
     * Define o identificador do usuário.
     * @param idUser o identificador do usuário.
     */
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    /** @return o identificador do modulo */
    public Long getIdModulo() {
        return idModulo;
    }

    /**
     * Define o identificador do modulo.
     * @param idModulo o identificador do modulo.
     */
    public void setIdModulo(Long idModulo) {
        this.idModulo = idModulo;
    }
}
