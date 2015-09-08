package vg.civcraft.mc.civguide.listener;

import net.md_5.bungee.api.ChatColor;

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
				cg.sendGuideBook(player, "default");
				return;
			case 1:
				switch (args[0].toLowerCase()){
					case "booklist":
					case "list":
						player.sendMessage("Booklist");
						return;
				}
				if (!cg.sendGuideBook(player, args[0].toLowerCase())){
					player.sendMessage(ChatColor.DARK_GREEN+"[CivGuide] "+ChatColor.WHITE+"GuideBook not found: "+args[0]);
				}
				return;
			default:
				String bookname = "";
				for (String arg: args){
					bookname+=arg+" ";
				}
				bookname = bookname.trim();
				if (!bookname.isEmpty()){
					if (!cg.sendGuideBook(player, bookname)){
						player.sendMessage(ChatColor.DARK_GREEN+"[CivGuide] "+ChatColor.WHITE+"GuideBook not found: "+bookname);
					}
				}
				return;
		}
	}

}
