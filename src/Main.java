import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;


public class Main extends JavaPlugin implements Listener{
	
//TODO: Add sounds
//TODO: Put all on cooldown.
	
	public void onEnable() {
		//TODO: check if player has ability equiped
		
		//runnable
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

			@Override
			public void run() {
				Random chance2 = new Random();
				int chance3 = chance2.nextInt(4);
				if (chance3 == 0) {
					//fix player
					for (Entity entities : p.getNearbyEntities(5.0, 5.0, 5.0)) {
						if ((entities instanceof LivingEntity)) {
						LivingEntity entity = (LivingEntity)entities;
						
						PotionEffect slow = new PotionEffect(PotionEffectType.SLOW, 3 * 20, 1, true);
						
						entity.addPotionEffect(slow);
						
						
						}
					}
				}
			}
			
		}, 7 * 20, 7 * 20);
		
		
		
		/*
		 * Frost Aura (Purple):
			-Every 7 seconds, each player nearby you (5 block radius) have a 25% chance to get slowness 1 for 3 seconds
		 */
		
		
		
		
	}
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			
			//TODO: if player has ability
			Random chance = new Random();
			int chance1 = chance.nextInt(10);
			
			if (chance1 == 0) {
				e.setDamage(e.getDamage() / 2);
			}
		}
		
		
		
		
	}
	private ArrayList<Object> inBash = new ArrayList();
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = (Player) e.getPlayer();
		
		//check if bash ability equipped
		//TODO: check if off of cooldown / not disabled
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			
			inBash.add(p);
			
			
			Vector dash = p.getLocation().getDirection().multiply(1.5D).setY(0.4D);
			             
			p.setVelocity(dash);
			
			
			
		
			
			
			
		}
		//TODO:check if flash ability is equipped
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			p.teleport(p.getLineOfSight((HashSet<Byte>)null, 4).get(p.getLineOfSight((HashSet<Byte>)null, 4).size()-1).getLocation());
		}
		//TODO: check if Sonic Sound is equipped / not cancelled
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			for (Entity entities : p.getNearbyEntities(5.0, 5.0, 5.0)) {
				if ((entities instanceof LivingEntity)) {
				LivingEntity entity = (LivingEntity)entities;
				
				
				PotionEffect blind = new PotionEffect(PotionEffectType.BLINDNESS,4 * 20, 1, true);
				
				entity.addPotionEffect(blind);
				
				p.playSound(entity.getLocation(), Sound.ENTITY_FIREWORK_BLAST, 0, 2);
				
				//put on cooldown
				}
			}
			
			
		}
		//TODO check if Sky Slam is requipped / not cancelled
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			Vector up = p.getLocation().getDirection().multiply(0.5D).setY(2.0D);
            
			p.setVelocity(up);
			p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_LAUNCH, 0, 2);
			
			Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(this, new BukkitRunnable() {

				@Override
				public void run() {
					Vector down = p.getLocation().getDirection().multiply(0.1D).setY(-2.0D);
		            
					p.setVelocity(down);
					p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_SHOOT, 0, 2);
					
					
					Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

						@Override
						public void run() {
							if (p.isOnGround()) {
								for (Entity entities : p.getNearbyEntities(4.0, 4.0, 4.0)) {
									if ((entities instanceof LivingEntity)) {
									LivingEntity entity = (LivingEntity)entities;
									//seismic slam effect, fix...
									Vector kb = entity.getLocation().getDirection().multiply(2.0D).setY(0.7D);
						            
									entity.setVelocity(kb);
									
									Bukkit.getServer().getScheduler().cancelTask(1);  // "1" ?!? what is that...
									 
									
									}
								}
							}
								
							
							
						}
						
					}, 2 * 5, 2 * 5);
				}
				
			}, 2 * 20);
		}
		
		//TODO: if player has Quick Slash equipped / not cancelled
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (!(p.getNearbyEntities(3.0, 3.0, 3.0) == null)) {
				//get entities in players sight
				for (Entity entities : p.getNearbyEntities(4.0, 4.0, 4.0)) {
					if ((entities instanceof LivingEntity)) {
					LivingEntity entity = (LivingEntity)entities;
					entity.damage(6.0);
					PotionEffect slow = new PotionEffect(PotionEffectType.SLOW, 2 * 20, 2, true);
					
					entity.addPotionEffect(slow);
					//put on cooldown
					}
				}
			}
		}
		
		
	}
	
	
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = (Player) e.getPlayer();
		
		//TODO: CHeck if player has ability equiped
		if (inBash.contains(p)) {
			for (Entity entities : p.getNearbyEntities(1.0, 1.0, 1.0)) {
				if ((entities instanceof LivingEntity)) {
				LivingEntity entity = (LivingEntity)entities;
				
				entity.damage(4.0);
				
				Vector kb = p.getLocation().getDirection().multiply(-0.2D).setY(0.7D);
	             
				p.setVelocity(kb);

				Vector ekb = p.getLocation().getDirection().multiply(1.0D).setY(0.3D);
	             
				entity.setVelocity(ekb);
				
				PotionEffect slow = new PotionEffect(PotionEffectType.SLOW, 2 * 20, 2, true);
				
				entity.addPotionEffect(slow);
				
				
				inBash.remove(p);
				}
			}
			
			
			
			
			
			
		}
		
	}
	
	
	
	
	
	
	
	
}
