package vg.civcraft.mc.civguide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;

import vg.civcraft.mc.civguide.books.CivGuideBook;
import vg.civcraft.mc.civguide.listener.CivGuideCommandListener;
import vg.civcraft.mc.civguide.listener.CivGuidePlayerListener;
import vg.civcraft.mc.namelayer.NameAPI;

public class CivGuide extends JavaPlugin {
	private static CivGuide instance;
	private static CivGuidePlayerListener playerListener;
	private static CivGuideCommandListener commandListener;
	private HashMap<String, CivGuideBook> guideBooks;
	private ArrayList<UUID> playersWithBooks;
	private String bookauthor = ChatColor.DARK_PURPLE+"CivCraft";
	

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (sender instanceof Player && (label.equals("guide") || label.equals("gui"))){
			commandListener.handleCommand((Player)sender, args);
			return true;
		}
		return false;
	}

	@Override
	public void onDisable() {
		
	}

	@Override
	public void onEnable() {
		instance = this;
		playerListener = new CivGuidePlayerListener();
		commandListener = new CivGuideCommandListener();
		playersWithBooks = new ArrayList<UUID>();
		guideBooks = new HashMap<String, CivGuideBook>();
		loadBooks(guideBooks);
		this.getServer().getPluginManager().registerEvents(playerListener, this);
	}
	
	private void loadBooks(HashMap<String,CivGuideBook> booklist) {
		//TODO: Test code remove
		booklist.put("main", new CivGuideBook());
		booklist.put("alt", new CivGuideBook());
		///
	}

	public static CivGuide getInstance(){
		return instance;
	}
	
	public HashMap<String, CivGuideBook> getGuideBooks(){
		return guideBooks;
	}
	
	public boolean isGuideBook(String book){
		return guideBooks.containsKey(book);
	}
	
	public boolean sendGuideBook(Player player, String bookname){
		if (!isGuideBook(bookname) || player == null){
			return false;
		}
		
		ItemStack inhand = player.getItemInHand();
		if (!(inhand.getType().equals(Material.AIR))){
			if (!(inhand.getType().equals(Material.WRITTEN_BOOK))){
				player.sendMessage(ChatColor.DARK_RED+"You're hand needs to be empty before you can summon a guidebook.");
				return false;
			}
			BookMeta handbook = (BookMeta)inhand.getItemMeta();
			if (!(handbook.getAuthor().equals(bookauthor))){
				player.sendMessage(ChatColor.DARK_RED+"You're hand needs to be empty before you can summon a guidebook.");
				return false;
			} else{
				player.setItemInHand(new ItemStack(Material.AIR));
			}
		}
		
		ItemStack book = makeBook(bookname);
		player.setItemInHand(book);
		playersWithBooks.add(NameAPI.getUUID(player.getName()));
		return true;
	}
	
	public boolean hasBook(Player player){
		if (playersWithBooks.contains(player.getUniqueId())){
			return true;
		}
		return false;
	}
	
	public void removeBook(Player player){
		removeBook(player, -1);
	}
	
	public void removeBook(Player player, int slot){
		playersWithBooks.remove(player.getUniqueId());
		ItemStack inhand;
		if (slot == -1){
			inhand = player.getItemInHand();
		} else{
			inhand = player.getInventory().getItem(slot);
		}
		if (!(inhand.getType().equals(Material.WRITTEN_BOOK))){
			return;
		}
		BookMeta bookdat = (BookMeta)inhand.getItemMeta();
		if (bookdat.getAuthor().equals(bookauthor)){
			player.setItemInHand(new ItemStack(Material.AIR));
		}

	}
	
	public void removeBook(List<ItemStack> items){
		for (ItemStack item : items){
			if (item.getType().equals(Material.WRITTEN_BOOK)){
				if (((BookMeta)item.getItemMeta()).getAuthor().equals(bookauthor)){
					items.remove(item);
				}
			}
		}
	}

	private ItemStack makeBook(String bookname) {
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta bookdat = (BookMeta)book.getItemMeta();
		
		bookdat.setDisplayName(ChatColor.AQUA+bookname);
		bookdat.setAuthor(bookauthor);
		bookdat.addPage("one","two","three");
		
		book.setItemMeta(bookdat);
		return book;
	}

}
