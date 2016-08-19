package net.dungeonrealms.beta.creature.vendors.betavendor;

import net.dungeonrealms.GameAPI;
import net.dungeonrealms.beta.BetaHandler;
import net.dungeonrealms.beta.constant.MenuConstants;
import net.dungeonrealms.beta.creature.vendors.betavendor.menus.*;
import net.dungeonrealms.beta.creature.vendors.betavendor.menus.spawngear.SpawnT1Menu;
import net.dungeonrealms.beta.creature.vendors.betavendor.menus.spawngear.SpawnT2Menu;
import net.dungeonrealms.beta.creature.vendors.betavendor.menus.spawngear.SpawnT3Menu;
import net.dungeonrealms.beta.creature.vendors.betavendor.menus.spawngear.SpawnT4Menu;
import net.dungeonrealms.beta.handler.Handler;
import net.dungeonrealms.beta.item.data.ItemRarity;
import net.dungeonrealms.beta.utilities.string.StringUtils;
import net.dungeonrealms.common.game.database.DatabaseAPI;
import net.dungeonrealms.common.game.database.data.EnumData;
import net.dungeonrealms.common.game.database.data.EnumOperators;
import net.dungeonrealms.game.mastery.Utils;
import net.dungeonrealms.game.mechanic.ItemManager;
import net.dungeonrealms.game.player.banks.BankMechanics;
import net.dungeonrealms.game.player.chat.Chat;
import net.dungeonrealms.game.world.item.Item;
import net.dungeonrealms.game.world.item.itemgenerator.ItemGenerator;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.text.NumberFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Matthew on 8/14/2016.
 */

public class BetaVendorClickHandler implements Handler.ListeningHandler {

    public static HashMap<UUID, String> beta_vendor = new HashMap<UUID, String>();
    private static final Logger logger = Logger.getLogger(BetaVendorClickHandler.class.getName());

    @EventHandler
    public void onNPCRightClick(PlayerInteractAtEntityEvent e) {
        Player player = e.getPlayer();
        if (e.getRightClicked() instanceof HumanEntity) {
            HumanEntity npc = (HumanEntity) e.getRightClicked();
            if (npc.hasMetadata("NPC")) {
                switch (npc.getName()) {
                    case "Beta Vendor":
                        if (BetaHandler.getDungeonRealms().isBetaShard) {
                            beta_vendor.put(getUUID(player), "main");
                            player.openInventory(new MainBetaVendorMenu().openInventory());
                            break;
                        } else {
                            player.sendMessage(StringUtils.colorCodes("&7Beta Vendor: &fWhere did you find me?"));
                            break;
                        }
                    default:
                        break;
                }
            }
        }
    }

    @EventHandler
    public void onBetaVendorClickEvent(InventoryClickEvent e) {
        if (e.getSlotType() == InventoryType.SlotType.OUTSIDE) {
            return;
        }
        Player player = (Player) e.getWhoClicked();
        if (e.getClickedInventory().getTitle().equals(MenuConstants.BETA_VENDOR_RARITY_NAME)) {
            e.setCancelled(true);
            String type = "helmT1";
            if (beta_vendor.containsKey(getUUID(player))) {
                type = beta_vendor.get(getUUID(player));
                beta_vendor.remove(getUUID(player));
            } else {
                return;
            }
            int slot = e.getSlot();
            switch (slot) {
                case 0:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "spawnGear");
                    player.openInventory(new SpawnGearMenu().openInventory());
                    giveGear(player, ItemRarity.COMMON, type);
                    break;
                case 1:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "spawnGear");
                    player.openInventory(new SpawnGearMenu().openInventory());
                    giveGear(player, ItemRarity.UNCOMMON, type);
                    break;
                case 2:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "spawnGear");
                    player.openInventory(new SpawnGearMenu().openInventory());
                    giveGear(player, ItemRarity.RARE, type);
                    break;
                case 3:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "spawnGear");
                    player.openInventory(new SpawnGearMenu().openInventory());
                    giveGear(player, ItemRarity.UNIQUE, type);
                    break;
                case 8:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "spawnGear");
                    player.openInventory(new SpawnGearMenu().openInventory());
                    break;
                default:
                    break;
            }
        }
        if (e.getClickedInventory().getTitle().contains("Spawn T")) {
            e.setCancelled(true);
            int tier = 1;
            if ((beta_vendor.containsKey(getUUID(player))) && (beta_vendor.get(getUUID(player)).contains("spawnT"))) {
                String raw = beta_vendor.get(getUUID(player)).replace("spawnT", "").trim();
                if (Utils.isInt(raw)) {
                    tier = Integer.parseInt(raw);
                }
                beta_vendor.remove(getUUID(player));
            } else {
                return;
            }

            int slot = e.getSlot();
            switch (slot) {
                case 0:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "helmT" + tier);
                    player.openInventory(new PickRarityMenu().openInventory());
                    break;
                case 1:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "chestT" + tier);
                    player.openInventory(new PickRarityMenu().openInventory());
                    break;
                case 2:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "legT" + tier);
                    player.openInventory(new PickRarityMenu().openInventory());
                    break;
                case 3:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "bootT" + tier);
                    player.openInventory(new PickRarityMenu().openInventory());
                    break;
                case 4:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "swordT" + tier);
                    player.openInventory(new PickRarityMenu().openInventory());
                    break;
                case 5:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "axeT" + tier);
                    player.openInventory(new PickRarityMenu().openInventory());
                    break;
                case 6:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "bowT" + tier);
                    player.openInventory(new PickRarityMenu().openInventory());
                    break;
                case 7:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "staffT" + tier);
                    player.openInventory(new PickRarityMenu().openInventory());
                    break;
                case 8:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "polearmT" + tier);
                    player.openInventory(new PickRarityMenu().openInventory());
                    break;
                default:
                    break;
            }
        }
        if (e.getClickedInventory().getTitle().equals(MenuConstants.BETA_VENDOR_SPAWN_SCRAP_NAME)) {
            e.setCancelled(true);
            if ((beta_vendor.containsKey(getUUID(player))) && (beta_vendor.get(getUUID(player)).equals("spawnScrap"))) {
                beta_vendor.remove(getUUID(player));
            } else {
                return;
            }
            int slot = e.getSlot();
            switch (slot) {
                case 0:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "main");
                    player.openInventory(new MainBetaVendorMenu().openInventory());
                    giveScrap(player, 1);
                    break;
                case 1:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "main");
                    player.openInventory(new MainBetaVendorMenu().openInventory());
                    giveScrap(player, 2);
                    break;
                case 2:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "main");
                    player.openInventory(new MainBetaVendorMenu().openInventory());
                    giveScrap(player, 3);
                    break;
                case 3:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "main");
                    player.openInventory(new MainBetaVendorMenu().openInventory());
                    giveScrap(player, 4);
                    break;
                case 4:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "main");
                    player.openInventory(new MainBetaVendorMenu().openInventory());
                    giveScrap(player, 5);
                    break;
                case 8:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "main");
                    player.openInventory(new MainBetaVendorMenu().openInventory());
                    break;
                default:
                    break;
            }
        }
        if (e.getClickedInventory().getTitle().equals(MenuConstants.BETA_VENDOR_SPAWN_SCROLLS_NAME)) {
            e.setCancelled(true);
            if ((beta_vendor.containsKey(getUUID(player))) && (beta_vendor.get(getUUID(player)).equals("spawnScrolls"))) {
                beta_vendor.remove(getUUID(player));
            } else {
                return;
            }
            int slot = e.getSlot();
            switch (slot) {
                case 0:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "main");
                    player.openInventory(new MainBetaVendorMenu().openInventory());
                    giveScroll(player, "1");
                    break;
                case 1:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "main");
                    player.openInventory(new MainBetaVendorMenu().openInventory());
                    giveScroll(player, "2");
                    break;
                case 2:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "main");
                    player.openInventory(new MainBetaVendorMenu().openInventory());
                    giveScroll(player, "3");
                    break;
                case 3:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "main");
                    player.openInventory(new MainBetaVendorMenu().openInventory());
                    giveScroll(player, "4");
                    break;
                case 4:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "main");
                    player.openInventory(new MainBetaVendorMenu().openInventory());
                    giveScroll(player, "5");
                    break;
                case 5:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "main");
                    player.openInventory(new MainBetaVendorMenu().openInventory());
                    giveScroll(player, "orb");
                    break;
                case 8:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "main");
                    player.openInventory(new MainBetaVendorMenu().openInventory());
                    break;
                default:
                    break;
            }
        }
        if (e.getClickedInventory().getTitle().equals(MenuConstants.BETA_VENDOR_SPAWN_TELEPORT_NAME)) {
            e.setCancelled(true);
            if ((beta_vendor.containsKey(getUUID(player))) && (beta_vendor.get(getUUID(player)).equals("spawnTps"))) {
                beta_vendor.remove(getUUID(player));
            } else {
                return;
            }
            int slot = e.getSlot();
            switch (slot) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "main");
                    player.openInventory(new MainBetaVendorMenu().openInventory());
                    addTP(player, slot);
                    break;
                default:
                    break;
            }
        }
        if (e.getClickedInventory().getTitle().equals(MenuConstants.BETA_VENDOR_GEAR_NAME)) {
            e.setCancelled(true);
            if ((beta_vendor.containsKey(getUUID(player))) && (beta_vendor.get(getUUID(player)).equals("spawnGear"))) {
                beta_vendor.remove(getUUID(player));
            } else {
                return;
            }
            int slot = e.getSlot();
            switch (slot) {
                case 0:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "spawnT1");
                    player.openInventory(new SpawnT1Menu().openInventory());
                    break;
                case 1:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "spawnT2");
                    player.openInventory(new SpawnT2Menu().openInventory());
                    break;
                case 2:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "spawnT3");
                    player.openInventory(new SpawnT3Menu().openInventory());
                    break;
                case 3:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "spawnT4");
                    player.openInventory(new SpawnT4Menu().openInventory());
                    break;
                case 4:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "spawnT5");
                    player.openInventory(new SpawnT4Menu().openInventory());
                    break;
                case 8:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "main");
                    player.openInventory(new MainBetaVendorMenu().openInventory());
                    break;
                default:
                    break;
            }
        }
        if (e.getClickedInventory().getTitle().equals(MenuConstants.BETA_VENDOR_MAIN_NAME)) {
            e.setCancelled(true);
            if ((beta_vendor.containsKey(getUUID(player))) && (beta_vendor.get(getUUID(player)).equals("main"))) {
                beta_vendor.remove(getUUID(player));
            } else {
                return;
            }
            int slot = e.getSlot();
            switch (slot) {
                case 0:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "spawnGear");
                    player.openInventory(new SpawnGearMenu().openInventory());
                    break;
                case 1:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "spawnScrolls");
                    player.openInventory(new SpawnScrollsMenu().openInventory());
                    break;
                case 2:
                    player.closeInventory();
                    player.getInventory().addItem(ItemManager.createHealingFood(1, Item.ItemRarity.COMMON));
                    player.getInventory().addItem(ItemManager.createHealingFood(1, Item.ItemRarity.RARE));
                    player.getInventory().addItem(ItemManager.createHealingFood(1, Item.ItemRarity.UNIQUE));
                    player.getInventory().addItem(ItemManager.createHealingFood(2, Item.ItemRarity.COMMON));
                    player.getInventory().addItem(ItemManager.createHealingFood(2, Item.ItemRarity.RARE));
                    player.getInventory().addItem(ItemManager.createHealingFood(2, Item.ItemRarity.UNIQUE));
                    player.getInventory().addItem(ItemManager.createHealingFood(3, Item.ItemRarity.COMMON));
                    player.getInventory().addItem(ItemManager.createHealingFood(3, Item.ItemRarity.RARE));
                    player.getInventory().addItem(ItemManager.createHealingFood(3, Item.ItemRarity.UNIQUE));
                    player.getInventory().addItem(ItemManager.createHealingFood(4, Item.ItemRarity.COMMON));
                    player.getInventory().addItem(ItemManager.createHealingFood(4, Item.ItemRarity.RARE));
                    player.getInventory().addItem(ItemManager.createHealingFood(4, Item.ItemRarity.UNIQUE));
                    player.getInventory().addItem(ItemManager.createHealingFood(5, Item.ItemRarity.COMMON));
                    player.getInventory().addItem(ItemManager.createHealingFood(5, Item.ItemRarity.RARE));
                    player.getInventory().addItem(ItemManager.createHealingFood(5, Item.ItemRarity.UNIQUE));
                    break;
                case 3:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "spawnScrap");
                    player.openInventory(new SpawnScrapMenu().openInventory());
                    break;
                case 4:
                    player.closeInventory();
                    player.getInventory().addItem(ItemManager.createHealthPotion(1, false, false));
                    player.getInventory().addItem(ItemManager.createHealthPotion(2, false, false));
                    player.getInventory().addItem(ItemManager.createHealthPotion(3, false, false));
                    player.getInventory().addItem(ItemManager.createHealthPotion(4, false, false));
                    player.getInventory().addItem(ItemManager.createHealthPotion(5, false, false));
                    player.getInventory().addItem(ItemManager.createHealthPotion(1, false, true));
                    player.getInventory().addItem(ItemManager.createHealthPotion(2, false, true));
                    player.getInventory().addItem(ItemManager.createHealthPotion(3, false, true));
                    player.getInventory().addItem(ItemManager.createHealthPotion(4, false, true));
                    player.getInventory().addItem(ItemManager.createHealthPotion(5, false, true));
                    break;
                case 5:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "setLevel");
                    player.sendMessage(StringUtils.colorCodes("&eType what you want your level set to &a<1-100>"));
                    Chat.listenForMessage(player, event -> {
                        if (!((beta_vendor.containsKey(getUUID(player))) && (beta_vendor.get(getUUID(player)).equals("setLevel")))) {
                            return;
                        }
                        if (GameAPI.getGamePlayer(player) == null) {
                            player.sendMessage(ChatColor.RED + "You cannot set your level right now.");
                            beta_vendor.remove(getUUID(player));
                            return;
                        }
                        String message = event.getMessage().trim();
                        if (Utils.isInt(message)) {
                            int lvl = Integer.parseInt(message);
                            if ((lvl > 100) || (lvl < 1)) {
                                player.sendMessage(ChatColor.RED + "Bad Syntax: <1-100>");
                                beta_vendor.remove(getUUID(player));
                                return;
                            } else {
                                GameAPI.getGamePlayer(player).updateLevel(lvl, false, true);
                                DatabaseAPI.getInstance().update(player.getUniqueId(), EnumOperators.$SET, EnumData.LEVEL, lvl, true);
                                Utils.sendCenteredMessage(player, ChatColor.YELLOW + "Level of " + ChatColor.GREEN + player.getName() + ChatColor.YELLOW + " set to: " + ChatColor.LIGHT_PURPLE + lvl);
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1f, 63f);
                                beta_vendor.remove(getUUID(player));
                                return;
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "Bad Syntax: <1-100>");
                            beta_vendor.remove(getUUID(player));
                            return;
                        }

                    }, null);
                    break;
                case 6:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "spawnGems");
                    player.sendMessage(StringUtils.colorCodes("&eType how many gems you want"));
                    Chat.listenForMessage(player, event -> {
                        if (!((beta_vendor.containsKey(getUUID(player))) && (beta_vendor.get(getUUID(player)).equals("spawnGems")))) {
                            return;
                        }
                        String message = event.getMessage().trim();
                        if (Utils.isInt(message)) {
                            int amt = Integer.parseInt(message);
                            if ((amt > 200000) || (amt < 1)) {
                                player.sendMessage(ChatColor.RED + "To high or to low!");
                                beta_vendor.remove(getUUID(player));
                                return;
                            } else {
                                player.getInventory().addItem(BankMechanics.createBankNote(amt));
                                player.sendMessage(ChatColor.GREEN + "Successfully created a bank note worth " + NumberFormat.getIntegerInstance().format(amt) + " gems.");
                                beta_vendor.remove(getUUID(player));
                                return;
                            }
                        } else {
                            player.sendMessage(ChatColor.RED + "Not a number!");
                            beta_vendor.remove(getUUID(player));
                            return;
                        }

                    }, null);
                    break;
                case 7:
                    player.closeInventory();
                    //beta_vendor.put(getUUID(player), "spawnProfession");
                    break;
                case 8:
                    player.closeInventory();
                    beta_vendor.put(getUUID(player), "spawnTps");
                    player.openInventory(new SpawnTeleportMenu().openInventory());
                    break;
                case 17:
                    player.closeInventory();
                    break;
                default:
                    break;
            }
        }
    }

    private void addTP(Player player, int id) {
        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage(StringUtils.colorCodes("&c&lWarning: &cYour inventory is full!"));
            return;
        }
        List<String> teleports = new ArrayList<String>(Arrays.asList("Cyrennica", "Harrison_Field", "Dark_Oak", "Trollsbane", "Tripoli", "Gloomy_Hollows", "Crestguard", "Deadpeaks"));
        ItemStack book = ItemManager.createTeleportBook(teleports.get(id).replaceAll("_", ""));
        player.getInventory().setItem(player.getInventory().firstEmpty(), book);
    }

    private void giveScrap(Player player, int tier) {
        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage(StringUtils.colorCodes("&c&lWarning: &cYour inventory is full!"));
            return;
        }
        ItemStack scrap = ItemManager.createArmorScrap(tier);
        scrap.setAmount(64);
        player.getInventory().addItem(scrap);
    }

    private void giveScroll(Player player, String type) {
        List<ItemStack> items = new ArrayList<ItemStack>();
        switch (type) {
            case "orb":
                ItemStack orb = ItemManager.createOrbofAlteration();
                orb.setAmount(64);
                items.add(orb);
                break;
            default:
                int tier = 1;
                if (Utils.isInt(type)) {
                    tier = Integer.parseInt(type);
                }
                ItemStack arm = ItemManager.createArmorEnchant(tier);
                arm.setAmount(64);
                ItemStack wep = ItemManager.createWeaponEnchant(tier);
                wep.setAmount(64);
                ItemStack prot = ItemManager.createProtectScroll(tier);
                prot.setAmount(64);
                items.add(arm);
                items.add(wep);
                items.add(prot);
                break;
        }
        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage(StringUtils.colorCodes("&c&lWarning: &cYour inventory is full!"));
            return;
        } else {
            for (ItemStack item : items) {
                player.getInventory().addItem(item);
            }
            return;
        }
    }

    private void giveGear(Player player, ItemRarity itemRarity, String type) {
        ItemGenerator generator = new ItemGenerator();
        String rawTier = type.substring(type.length() - 1).trim();
        Item.ItemType itemType = Item.ItemType.HELMET;
        int tier = 1;

        if (Utils.isInt(rawTier)) {
            tier = Integer.parseInt(rawTier);
        }
        String rawType = type.split("T" + rawTier)[0].trim();
        switch (rawType) {
            case "helm":
                itemType = Item.ItemType.HELMET;
                break;
            case "chest":
                itemType = Item.ItemType.CHESTPLATE;
                break;
            case "leg":
                itemType = Item.ItemType.LEGGINGS;
                break;
            case "boot":
                itemType = Item.ItemType.BOOTS;
                break;
            case "sword":
                itemType = Item.ItemType.SWORD;
                break;
            case "axe":
                itemType = Item.ItemType.AXE;
                break;
            case "bow":
                itemType = Item.ItemType.BOW;
                break;
            case "staff":
                itemType = Item.ItemType.STAFF;
                break;
            case "polearm":
                itemType = Item.ItemType.POLEARM;
                break;
            default:
                itemType = Item.ItemType.HELMET;
                break;
        }
        ItemStack item = generator.setTier(Item.ItemTier.getByTier(tier)).setRarity(Item.ItemRarity.getByName(itemRarity.name())).setType(itemType).generateItem().getItem();
        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage(StringUtils.colorCodes("&c&lWarning: &cYour inventory is full!"));
            return;
        } else {
            player.getInventory().setItem(player.getInventory().firstEmpty(), item);
        }
    }

    public UUID getUUID(Player player) {
        return GameAPI.getUUIDFromName(player.getName());
    }

    @Override
    public boolean supered() {
        return false;
    }

    @Override
    public boolean onEnable() {
        getLogger().log(Level.INFO, "Enabling Beta Vendor...");
        return true;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }
}