package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável por gerenciar a criação e o fechamento de conexões com o banco de dados Oracle.
 *<strong>DB_URL</strong> — endereço JDBC do banco de dados
 *<strong>DB_USER</strong> — nome do usuário do banco
 *<strong>DB_PASSWORD</strong> — senha do banco
 *
 * <p>O método {@link #getConnection()} verifica se já existe uma conexão ativa e reutiliza-a sempre que possível.
 * Caso contrário, ele cria uma nova conexão utilizando o driver JDBC da Oracle.</p>
 *
 * <p>O método {@link #closeConnection()} deve ser chamado para encerrar a conexão aberta, evitando vazamentos
 * de recursos.</p>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
public class ConnectionFactory {

    /** Instância única da conexão ativa com o banco de dados. */
    private static Connection connection;

    /**
     * Fecha a conexão atual com o banco de dados, caso esteja aberta.
     *
     * <p>Este método deve ser chamado ao final do uso da conexão para liberar
     * os recursos do banco de dados e evitar vazamentos de memória.</p>
     */
    public static void closeConnection() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    /**
     * Obtém uma conexão ativa com o banco de dados Oracle.
     *
     * <p>Se já existir uma conexão aberta, ela será reutilizada. Caso contrário,
     * uma nova conexão será criada utilizando as variáveis de ambiente configuradas.</p>
     *
     * @return uma instância de {@link Connection} ativa com o banco de dados, ou {@code null} se ocorrer erro
     */
    public static Connection getConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String url = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Erro nome da classe: " + e.getMessage());
        }
        return connection;
    }
}
