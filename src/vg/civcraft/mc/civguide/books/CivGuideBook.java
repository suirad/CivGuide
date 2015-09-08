package vg.civcraft.mc.civguide.books;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import vg.civcraft.mc.civguide.CivGuide;

public class CivGuideBook {
	private static CivGuide cg;
	private String fullname;
	private ArrayList<String> pages;
	
	public CivGuideBook(String fullname){
		this.fullname = fullname;
		pages = new ArrayList<String>();
		cg = CivGuide.getInstance();
	}
	
	public String getName(){
		return fullname;
	}
	
	public boolean addPage(String page){
		if (page == null){
			return false;
		} else if (page.isEmpty()){
			return false;
		} else{
			pages.add(parsePage(page));
			return true;
		}
	}
	
	public void addPages(List<String> pages){
		if (pages == null){return;}
		
		for (String page: pages){
			this.addPage(parsePage(page));
		}
	}
	
	
	public ArrayList<String> getPages(){
		return pages;
	}
	
	public void clearPages(){
		pages.clear();
	}
	
	public ItemStack makeBookItem(){
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta bookdat = (BookMeta)book.getItemMeta();
		
		bookdat.setDisplayName(ChatColor.AQUA+fullname);
		bookdat.setAuthor(cg.bookauthor);
		if (pages.isEmpty()){
			bookdat.addPage(ChatColor.DARK_RED+"     *Book is Empty*");
		} else { 
			for (String page : pages){
				bookdat.addPage(page);
			}
		}
		book.setItemMeta(bookdat);
		return book;
	}
	
	private String parsePage(String page){
		String ret = page;
		ret = ret.replaceAll("~n", "\n");
		ret = ret.replaceAll("~q", "\"");
		ret = ret.replaceAll("~s", ChatColor.STRIKETHROUGH.toString());
		ret = ret.replaceAll("~b", ChatColor.BOLD.toString());
		ret = ret.replaceAll("~i", ChatColor.ITALIC.toString());
		ret = ret.replaceAll("~u", ChatColor.UNDERLINE.toString());
		ret = ret.replaceAll("~r", ChatColor.RESET.toString());
		ret = ret.replaceAll("~c:a", ChatColor.AQUA.toString());
		ret = ret.replaceAll("~c:b", ChatColor.BLACK.toString());
		ret = ret.replaceAll("~c:bl", ChatColor.BLUE.toString());
		ret = ret.replaceAll("~c:m", ChatColor.MAGIC.toString());
		ret = ret.replaceAll("~c:da", ChatColor.DARK_AQUA.toString());
		ret = ret.replaceAll("~c:db", ChatColor.DARK_BLUE.toString());
		ret = ret.replaceAll("~c:dg", ChatColor.DARK_GRAY.toString());
		ret = ret.replaceAll("~c:dgr", ChatColor.DARK_GREEN.toString());
		ret = ret.replaceAll("~c:dp", ChatColor.DARK_PURPLE.toString());
		ret = ret.replaceAll("~c:dr", ChatColor.DARK_RED.toString());
		ret = ret.replaceAll("~c:go", ChatColor.GOLD.toString());
		ret = ret.replaceAll("~c:g", ChatColor.GRAY.toString());
		ret = ret.replaceAll("~c:gr", ChatColor.GREEN.toString());
		ret = ret.replaceAll("~c:lp", ChatColor.LIGHT_PURPLE.toString());
		ret = ret.replaceAll("~c:r", ChatColor.RED.toString());
		ret = ret.replaceAll("~c:w", ChatColor.WHITE.toString());
		ret = ret.replaceAll("~c:y", ChatColor.YELLOW.toString());
		return ret;
	}
	
}
