package br.com.fiap.to;

import jakarta.validation.constraints.*;

/**
 * Classe que representa um usuário do sistema.
 *
 * <p>Esta classe contém atributos como id, nome, email e senha.</p>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
public class UsuarioTO {
    /** Identificador único do usuário */
    private Long idUser;

    /** Nome do usuário */
    @NotBlank
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ]+(?:[\\s'-][A-Za-zÀ-ÖØ-öø-ÿ]+)*$")
    private String nome;

    /** Email do usuário */
    @NotBlank
    @Email
    private String email;

    /** Senha do usuário */
    @NotBlank
    private String senha;

    /**
     * Construtor padrão da classe {@code UsuarioTO}.
     */
    public UsuarioTO() {}

    /**
     * Construtor completo.
     *
     * @param idUser        identificador único do usuário
     * @param nome          nome completo do usuário
     * @param email         e-mail do usuário
     * @param senha         senha de acesso
     */
    public UsuarioTO(Long idUser, String nome, String email, String senha) {
        this.idUser = idUser;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    /** @return o identificador único do usuário */
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

    /** @return o nome do usuário */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do usuário.
     * @param nome o nome a ser atribuído
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /** @return o e-mail do usuário */
    public String getEmail() {
        return email;
    }

    /**
     * Define o e-mail do usuário.
     * @param email o e-mail a ser atribuído
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /** @return a senha de acesso do usuário */
    public String getSenha() {
        return senha;
    }

    /**
     * Define a senha de acesso do usuário.
     * @param senha a senha a ser atribuída
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
