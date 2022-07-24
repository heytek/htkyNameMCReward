/*    */ package me.heytek.utils;
/*    */ 
/*    */

import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.Packet;
import net.minecraft.server.v1_16_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

/*    */
/*    */ public class ChatUtil {
/*    */   public static String fixColor(String text) {
/* 23 */     return ChatColor.translateAlternateColorCodes('&', text.replace(">>", "»").replace("<<", "«").replace("{o}", "●").replace("%x%", "✘").replaceAll("%v%", "✔"));
/*    */   }
/*    */   
/*    */   public static void sendActionBar(Player player, String text) {
/* 42 */     PacketPlayOutTitle actionbar = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.ACTIONBAR, (IChatBaseComponent)IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + text + "\"}".replace("&", "§")), 0, 40, 0);
/* 44 */     (((CraftPlayer)player).getHandle()).playerConnection.sendPacket((Packet)actionbar);
/*    */   }
/*    */   
/*    */   public static int getRandomNumberUsingNextInt(int min, int max) {
/* 48 */     Random random = new Random();
/* 49 */     return random.nextInt(max - min) + min;
/*    */   }
/*    */   
/*    */   public static boolean sendMessage(CommandSender p, String text) {
/* 53 */     p.sendMessage(fixColor(text));
/* 54 */     return true;
/*    */   }
/*    */   
/*    */   public static boolean sendMessage(Player p, String text) {
/* 58 */     p.sendMessage(fixColor(text));
/* 59 */     return true;
/*    */   }
/*    */   
/*    */   public static void giveItems(Player p, ItemStack... items) {
/* 63 */     PlayerInventory playerInventory = p.getInventory();
/* 64 */     HashMap<Integer, ItemStack> notStored = playerInventory.addItem(items);
/* 65 */     for (Map.Entry<Integer, ItemStack> e : notStored.entrySet())
/* 66 */       p.getWorld().dropItemNaturally(p.getLocation(), e.getValue()); 
/*    */   }
/*    */   
/*    */   public static boolean isInteger(String string) {
/* 70 */     return Pattern.matches("-?[0-9]+", string.subSequence(0, string.length()));
/*    */   }
/*    */   
/*    */   public static Location locFromString(String str) {
/* 74 */     String[] str2loc = str.split(":");
/* 75 */     Location loc = new Location(Bukkit.getWorlds().get(0), 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
/* 76 */     loc.setX(Double.parseDouble(str2loc[0]));
/* 77 */     loc.setY(Double.parseDouble(str2loc[1]));
/* 78 */     loc.setZ(Double.parseDouble(str2loc[2]));
/* 79 */     loc.setYaw(Float.parseFloat(str2loc[3]));
/* 80 */     loc.setPitch(Float.parseFloat(str2loc[4]));
/* 81 */     return loc;
/*    */   }
/*    */   
/*    */   public static String locToString(double x, double y, double z) {
/* 85 */     return String.valueOf(x) + ":" + y + ":" + z + ":" + 0.0F + ":" + 0.0F;
/*    */   }
/*    */   
/*    */   public static double round(double value, int decimals) {
/* 89 */     double p = Math.pow(10.0D, decimals);
/* 90 */     return Math.round(value * p) / p;
/*    */   }
/*    */ }
