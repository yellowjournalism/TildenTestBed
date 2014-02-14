package com.tildenprep.tildentestbed;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by kenny on 2/14/14.
 */
public class TildenTestBed extends JavaPlugin implements Listener {

    public void onEnable(){
        getLogger().info("onEnable has been invoked!");
        getServer().getPluginManager().registerEvents(this, this);
    }

    public void onDisable(){
        getLogger().info("onDisable has been invoked!");
    }

    @EventHandler
    public void onPlayerInteractBlock(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand().getType() == Material.SAND) {
            // Creates a bolt of lightning at a given location. In this case, that location is where the player is looking.
            // Can only create lightning up to 200 blocks away.
            player.getWorld().strikeLightning(player.getTargetBlock(null, 200).getLocation());
            getLogger().info("LIGHTNING BOLT");
        }
        getLogger().info("onPlayerInteractBlock invoked!");
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Uses equalsIgnoreCase() over equals() to accept "ignite" and "IgNiTe."
        if (cmd.getName().equalsIgnoreCase("ignite")) {
            // Make sure that the player specified exactly one argument (the name of the player to ignite).
            if (args.length != 1) {
                // When onCommand() returns false, the help message associated with that command is displayed.
                return false;
            }

            // Make sure the sender is a player.
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can set other players on fire.");
                sender.sendMessage("This is an arbitrary requirement for demonstration purposes only.");
                return true;
            }

            // Get the player who should be set on fire. Remember that indecies start with 0, not 1.
            Player target = Bukkit.getServer().getPlayer(args[0]);

            // Make sure the player is online.
            if (target == null) {
                sender.sendMessage(args[0] + " is not currently online.");
                return true;
            }

            // Sets the player on fire for 1,000 ticks (there are ~20 ticks in second, so 50 seconds total).
            target.setFireTicks(1000);
            return true;
        }
        else if (cmd.getName().equalsIgnoreCase("blah")) {
            if(sender instanceof Player)
            {
                ((Player) sender).sendMessage("blah yourself?");
            }
        }
        return false;
    }
}
