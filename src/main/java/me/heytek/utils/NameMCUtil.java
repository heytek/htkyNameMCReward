package me.heytek.utils;

import me.heytek.htkyNameMCReward;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

public class NameMCUtil {

    static htkyNameMCReward plugin = (htkyNameMCReward) htkyNameMCReward.getPlugin(htkyNameMCReward.class);

    public static boolean verifyReward(UUID uuid) {
        try {
            URL url = new URL("https://api.namemc.com/server/" + plugin.getConfig().getString("ip") + "/likes?profile=" + uuid);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            boolean ret = false;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.toLowerCase();
                if (line.contains("true")) {
                    ret = true;
                    break;
                }
            }
            bufferedReader.close();
            return ret;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
