package me.heytek.database;

import me.heytek.htkyNameMCReward;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    htkyNameMCReward plugin = (htkyNameMCReward) htkyNameMCReward.getPlugin(htkyNameMCReward.class);

    private String host = plugin.getConfig().getString("host");
    private String port = plugin.getConfig().getString("port");
    private String database = plugin.getConfig().getString("database");
    private String username = plugin.getConfig().getString("username");
    private String password = plugin.getConfig().getString("password");

    private static Connection connection;

    public static boolean isConnected() {
    return (connection == null ? false : true);
    }

    public void connect() throws ClassNotFoundException, SQLException {
    if (!isConnected()) {
        connection = DriverManager.getConnection("jdbc:mysql://" +
                        host + ":" + port + "/" + database + "?useSSL=" + plugin.getConfig().getString("useSSL"),
                username, password);
        }
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
