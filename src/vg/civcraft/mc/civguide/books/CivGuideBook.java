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
			pages.add(page);
			return true;
		}
	}
	
	public void addPages(List<String> pages){
		if (pages == null){return;}
		
		for (String page: pages){
			this.addPage(page);
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
}
