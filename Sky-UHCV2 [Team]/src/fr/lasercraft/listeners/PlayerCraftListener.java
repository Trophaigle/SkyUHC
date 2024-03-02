package fr.lasercraft.listeners;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerCraftListener implements Listener {

    @EventHandler
    public void changeCraft(PrepareItemCraftEvent e){
       
        //LE CRAFT SE PASSE BIEN DANS UNE TABLE DE CRAFT
        if(e.getInventory() instanceof CraftingInventory){
           
            //ON RECUPERE L INVENTAIRE SOUS FORME DUNE VARIABLE
            CraftingInventory inv = (CraftingInventory)e.getInventory();
           
            switch(inv.getResult().getType()){
           
            //FAIRE UN SYSTEM PLUS OPTI Avec des méthodes
           
            case WOOD_PICKAXE:
                    //ON CREER NOTRE PIOCHE EN PIERRE
                    ItemStack customResult = new ItemStack(Material.STONE_PICKAXE, 1);
                    ItemMeta customM = customResult.getItemMeta();
                    customM.addEnchant(Enchantment.DIG_SPEED, 2, true);
                    customM.addEnchant(Enchantment.DURABILITY, 3, true);
                    customResult.setItemMeta(customM);
                   
                    inv.setResult(customResult);
               
            break;
       
            case WOOD_AXE:
                //ON CREER NOTRE PIOCHE EN PIERRE
                ItemStack customResults = new ItemStack(Material.STONE_AXE, 1);
                ItemMeta customMs = customResults.getItemMeta();
                customMs.addEnchant(Enchantment.DIG_SPEED, 2, true);
                customMs.addEnchant(Enchantment.DURABILITY, 3, true);
                customResults.setItemMeta(customMs);
               
                inv.setResult(customResults);
           
            break;
            
            case IRON_AXE:
                //ON CREER NOTRE PIOCHE EN PIERRE
                ItemStack customResultss = new ItemStack(Material.IRON_AXE, 1);
                ItemMeta customMss = customResultss.getItemMeta();
                customMss.addEnchant(Enchantment.DIG_SPEED, 2, true);
                customMss.addEnchant(Enchantment.DURABILITY, 3, true);
                customResultss.setItemMeta(customMss);
               
                inv.setResult(customResultss);
           
            break;
            
            case IRON_PICKAXE:
                //ON CREER NOTRE PIOCHE EN PIERRE
                ItemStack customResultsss = new ItemStack(Material.IRON_PICKAXE, 1);
                ItemMeta customMsss = customResultsss.getItemMeta();
                customMsss.addEnchant(Enchantment.DIG_SPEED, 2, true);
                customMsss.addEnchant(Enchantment.DURABILITY, 3, true);
                customResultsss.setItemMeta(customMsss);
               
                inv.setResult(customResultsss);
           
            break;
            
            case DIAMOND_AXE:
                //ON CREER NOTRE PIOCHE EN PIERRE
                ItemStack customResultsssss = new ItemStack(Material.DIAMOND_AXE, 1);
                ItemMeta customMssss = customResultsssss.getItemMeta();
                customMssss.addEnchant(Enchantment.DIG_SPEED, 2, true);
                customMssss.addEnchant(Enchantment.DURABILITY, 3, true);
                customResultsssss.setItemMeta(customMssss);
               
                inv.setResult(customResultsssss);
           
            break;
            
            case DIAMOND_PICKAXE:
                //ON CREER NOTRE PIOCHE EN PIERRE
                ItemStack customResultssss = new ItemStack(Material.DIAMOND_PICKAXE, 1);
                ItemMeta customMsssss = customResultssss.getItemMeta();
                customMsssss.addEnchant(Enchantment.DIG_SPEED, 2, true);
                customMsssss.addEnchant(Enchantment.DURABILITY, 3, true);
                customResultssss.setItemMeta(customMsssss);
               
                inv.setResult(customResultssss);
           
            break;
            
            default:
            break;
           
            }
           
        }
       
       
    }
}
