package vg.civcraft.mc.civguide.listener;

import org.bukkit.entity.Player;

import vg.civcraft.mc.civguide.CivGuide;

public class CivGuideCommandListener {
	private static CivGuide cg;
	
	public CivGuideCommandListener(){
		cg = CivGuide.getInstance();
	}

	public void handleCommand(Player player, String[] args) {
		switch (args.length){
			case 0:
				cg.sendGuideBook(player, "main");
				return;
			case 1:
				if (args[0].equalsIgnoreCase("alt")){
					cg.sendGuideBook(player, "alt");
				}
				return;
		}
	}

}
