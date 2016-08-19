package net.dungeonrealms.beta.rift.creature.entity;

import com.google.common.collect.Lists;
import net.dungeonrealms.beta.base64.Base64Skull;
import net.dungeonrealms.beta.utilities.math.IntegerUtil;
import net.dungeonrealms.beta.item.data.ItemType;
import net.dungeonrealms.beta.rift.Rift;
import net.dungeonrealms.beta.rift.creature.RiftCreature;
import net.dungeonrealms.beta.rift.creature.RiftCreatureElement;
import net.minecraft.server.v1_9_R2.*;
import net.minecraft.server.v1_9_R2.World;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_9_R2.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

/**
 * Created by Giovanni on 12-8-2016.
 */
public class RiftEntity extends EntitySkeleton implements RiftCreature
{
    private Rift rift;
    private int expDrop;

    private boolean elemantal = false;
    private RiftCreatureElement riftCreatureElement;
    private int level;

    private List<ItemStack> skulls;
    private List<org.bukkit.Material> weaponry = Arrays.asList(Material.STONE_HOE, Material.IRON_HOE, Material.STONE_AXE,
            Material.WOOD_SWORD, Material.GOLD_SPADE, Material.WOOD_HOE);

    public RiftEntity(World world, Rift rift)
    {
        super(world);

        this.rift = rift;

        this.skulls = Lists.newArrayList();
        this.riftCreatureElement = RiftCreatureElement.random();
        this.level = IntegerUtil.randomInteger(rift.getRiftTier().getMinLevel(), rift.getRiftTier().getMaxLevel());
        this.skulls.add(new Base64Skull().getSkullByUrl("http://textures.minecraft.net/texture/45556083c9cde19f54bf78a421cb9731f60f1d3de3cf584f54b2d43677df2a7"));
        this.skulls.add(new Base64Skull().getSkullByUrl("http://textures.minecraft.net/texture/f142a35ac0b055ed50a5cbf870b6ef1cc1f94e2642b9ba650c9e0385e6cbe36"));
        this.expDrop = level * 4 / 2 + level + 15 - 10;

        this.getAttributeInstance(GenericAttributes.maxHealth).setValue(IntegerUtil.randomInteger(rift.getRiftTier().getRiftCreatureTier().getMinHealth(),
                rift.getRiftTier().getRiftCreatureTier().getMaxHealth()));
        this.setHealth(getMaxHealth());
        this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE).setValue(IntegerUtil.randomInteger(400, 1000));
        this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.27D);

        if (riftCreatureElement != RiftCreatureElement.EMPTY)
        {
            elemantal = true;

            if (riftCreatureElement == RiftCreatureElement.UNIDENTIFIED)
            {
                int healthMin = IntegerUtil.randomInteger(rift.getRiftTier().getRiftCreatureTier().getMinHealth(),
                        rift.getRiftTier().getRiftCreatureTier().getMaxHealth());
                int healthAdd = IntegerUtil.randomInteger(rift.getRiftTier().getRiftCreatureTier().getMinHealth(),
                        rift.getRiftTier().getRiftCreatureTier().getMaxHealth()) - 500;
                this.getAttributeInstance(GenericAttributes.maxHealth).setValue(healthMin + healthAdd);
                this.setHealth(getMaxHealth());
            }
        } else
        {
            elemantal = false;
        }
    }

    @Override
    public SoundEffect G()
    {
        return SoundEffects.D;
    }

    @Override
    public SoundEffect bS()
    {
        return SoundEffects.aW;
    }

    @Override
    public SoundEffect bT()
    {
        return SoundEffects.aF;
    }

    @Override
    public void r()
    {
        try
        {
            Field bField = PathfinderGoalSelector.class.getDeclaredField("b");
            bField.setAccessible(true);
            Field cField = PathfinderGoalSelector.class.getDeclaredField("c");
            cField.setAccessible(true);

            bField.set(goalSelector, new LinkedHashSet<PathfinderGoalSelector>());
            bField.set(targetSelector, new LinkedHashSet<PathfinderGoalSelector>());
            cField.set(goalSelector, new LinkedHashSet<PathfinderGoalSelector>());
            cField.set(targetSelector, new LinkedHashSet<PathfinderGoalSelector>());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, 1.0D, false));
        this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, 1.0D));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 5.0F));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, false, true));
    }

    @Override
    public Rift getParentRift()
    {
        return rift;
    }

    @Override
    public int getExpDrop()
    {
        return expDrop;
    }

    @Override
    public boolean isElemental()
    {
        return elemantal;
    }

    public RiftCreatureElement getElement()
    {
        return riftCreatureElement;
    }

    public int getLevel()
    {
        return level;
    }

    public void spawn(Location location)
    {
        World craftWorld = ((CraftWorld) location.getWorld()).getHandle();
        this.setLocation(location.getX(), location.getY(), location.getZ(), location.getPitch(), location.getYaw());
        craftWorld.addEntity(this);
        aBE();
    }

    private void aBE()
    {
        this.setCustomName(ChatColor.AQUA + "[" + level + "] " + riftCreatureElement.getChatColor()
                + riftCreatureElement.getPrefix() + " Corrupted Creature");
        this.setCustomNameVisible(true);
        if (riftCreatureElement != RiftCreatureElement.UNIDENTIFIED)
        {
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setHelmet(skulls.get(new Random().nextInt(skulls.size())));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setChestplate(new ItemStack(rift.getRiftTier().getItemTier().getMaterialByType(ItemType.CHESTPLATE)));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setLeggings(new ItemStack(rift.getRiftTier().getItemTier().getMaterialByType(ItemType.LEGGINGS)));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setBoots(new ItemStack(rift.getRiftTier().getItemTier().getMaterialByType(ItemType.BOOTS)));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(weaponry.get(new Random().nextInt(weaponry.size()))));
        } else
        {
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setHelmet(skulls.get(new Random().nextInt(skulls.size())));
            ((LivingEntity) this.getBukkitEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, Integer.MAX_VALUE));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(weaponry.get(new Random().nextInt(weaponry.size()))));
            ((LivingEntity) this.getBukkitEntity()).getEquipment().setItemInOffHand(new ItemStack(weaponry.get(new Random().nextInt(weaponry.size()))));
        }
    }
}
