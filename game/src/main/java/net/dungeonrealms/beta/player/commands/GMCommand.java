package net.dungeonrealms.beta.player.commands;

import net.dungeonrealms.beta.player.menus.GMToggleGUI;
import net.dungeonrealms.common.game.commands.BasicCommand;
import net.dungeonrealms.common.game.database.player.rank.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Matthew on 8/13/2016.
 */

public class GMCommand extends BasicCommand {

    public GMCommand(String command, String usage, String description, List<String> aliases) {
        super(command, usage, description, aliases);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Rank.isGM(player)) {
                openGMToggles(player);
            } else {
                player.sendMessage(ChatColor.RED + "You " + ChatColor.BOLD + " CANNOT" + ChatColor.RED + "run this command.");
                return true;
            }
        }
        return true;
    }

    public void openGMToggles(Player player) {
        if (!Rank.isGM(player)) return;
        player.openInventory(new GMToggleGUI(player).openInventory());
    }
}
