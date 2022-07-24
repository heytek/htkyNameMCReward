package me.heytek;

import me.heytek.commands.RewardCmd;
import me.heytek.database.MySQL;
import me.heytek.database.RewardSQL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.SQLException;

public final class htkyNameMCReward extends JavaPlugin {

    public MySQL SQL;
    public RewardSQL rewardSQL;

    @Override
    public void onEnable() {
        loadConfig();
        hookCommands();
        hookDatabase();
    }

    @Override
    public void onDisable() {
        SQL.disconnect();
    }

    public void hookCommands() {
        this.getCommand("reward").setExecutor(new RewardCmd());
    }

    public void hookDatabase() {
        this.SQL = new MySQL();
        this.rewardSQL = new RewardSQL(this);
        try {
            SQL.connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            Bukkit.getLogger().info("✘ An error occurred while trying to connect to the database! Check your config.yml");
            loadConfig();
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        if (SQL.isConnected()) {
            Bukkit.getLogger().info("✔ Successfully connected to the database!");
            rewardSQL.createTable();
        }
    }

    private void loadConfig() {
        if (!this.getDataFolder().exists())
            this.getDataFolder().mkdir();
        if (!(new File(this.getDataFolder(), "config.yml")).exists())
            this.saveDefaultConfig();
    }
}


