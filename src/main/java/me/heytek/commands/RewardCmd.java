package me.heytek.commands;

import me.heytek.database.RewardSQL;
import me.heytek.htkyNameMCReward;
import me.heytek.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import static me.heytek.utils.NameMCUtil.verifyReward;

public class RewardCmd implements CommandExecutor {

    htkyNameMCReward plugin = (htkyNameMCReward) htkyNameMCReward.getPlugin(htkyNameMCReward.class);
    public RewardSQL rewardSQL;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        this.rewardSQL = new RewardSQL(plugin);
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Player p = (Player) commandSender;

        if (!(commandSender instanceof Player)) {
            return true;
        }
        if (args.length == 0) {
            if (!p.hasPermission(plugin.getConfig().getString("permission-reward"))) {
                p.sendMessage(ChatUtil.fixColor(plugin.getConfig().getString("no-permissions-reward-message")));
                return true;
            }
            if (rewardSQL.exists(p.getUniqueId())) {
                p.sendMessage(ChatUtil.fixColor(plugin.getConfig().getString("reward-already-received-message")));
            }
            if (!rewardSQL.exists(p.getUniqueId())) {
                if (verifyReward(p.getUniqueId())) {
                    rewardSQL.createPlayer(p);
                    p.sendMessage(ChatUtil.fixColor(plugin.getConfig().getString("message-after-recived-reward")));
                    String msg1 = plugin.getConfig().getString("command-after-recived-reward1");
                    assert msg1 != null;
                    msg1 = msg1.replaceAll("%player%", p.getName());
                    Bukkit.dispatchCommand(console, msg1);
                    String msg2 = plugin.getConfig().getString("command-after-recived-reward2");
                    assert msg2 != null;
                    msg2 = msg2.replaceAll("%player%", p.getName());
                    Bukkit.dispatchCommand(console, msg2);
                    String msg3 = plugin.getConfig().getString("command-after-recived-reward3");
                    assert msg3 != null;
                    msg3 = msg3.replaceAll("%player%", p.getName());
                    Bukkit.dispatchCommand(console, msg3);
                }
            }
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (!p.hasPermission(plugin.getConfig().getString("permission-reload"))) {
                    p.sendMessage(ChatUtil.fixColor(plugin.getConfig().getString("no-permissions-reload-message")));
                    return true;
                } else {
                    p.sendMessage(ChatUtil.fixColor(plugin.getConfig().getString("reload-message")));
                    plugin.reloadConfig();
                }
            }
        }
        return false;
    }
}





