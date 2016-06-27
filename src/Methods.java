import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Methods {
	
	Random random = new Random();
	private int totemChance = random.nextInt(3);
	Block totem;
	Location totemBaseLocation;
	
	public void summonTotem(Player p) {
		if (totemChance == 0) {
			//Regen
			totemBaseLocation = totem.getLocation();
			totem = p.getEyeLocation().setY(totemBaseLocation.getY() + 1);;
			totemBaseLocation.getBlock().setType(Material.CHORUS_PLANT);
			
			
		}
		if (totemChance == 1) {
			//Strength
		}
		if (totemChance == 2) {
			//Resist
		}
		
	}

}
