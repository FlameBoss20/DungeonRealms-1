package net.dungeonrealms.game.world.entity.type.monster.base;

import lombok.Getter;
import net.dungeonrealms.DungeonRealms;
import net.dungeonrealms.GameAPI;
import net.dungeonrealms.game.anticheat.AntiCheat;
import net.dungeonrealms.game.miscellaneous.SkullTextures;
import net.dungeonrealms.game.world.entity.EnumEntityType;
import net.dungeonrealms.game.world.entity.type.monster.DRMonster;
import net.dungeonrealms.game.world.entity.type.monster.EnumMonster;
import net.minecraft.server.v1_9_R2.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_9_R2.inventory.CraftItemStack;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Created by Chase on Oct 3, 2015
 */
public abstract class DRWitherSkeleton extends EntitySkeleton implements DRMonster
{

    public EnumMonster enumMonster;
    @Getter
    protected Map<String, Integer[]> attributes = new HashMap<>();

    public DRWitherSkeleton(World world)
    {
        super(world);
    }

    public DRWitherSkeleton(World world, EnumMonster mon, int tier, EnumEntityType entityType)
    {
        super(world);
        enumMonster = mon;
        this.getAttributeInstance(GenericAttributes.FOLLOW_RANGE).setValue(20d);
        this.getAttributeInstance(GenericAttributes.c).setValue(1.00d);
        setSkeletonType(1);
        LivingEntity livingEntity = (LivingEntity) this.getBukkitEntity();
        if (livingEntity instanceof Skeleton)
        {
            ((Skeleton) livingEntity).setSkeletonType(Skeleton.SkeletonType.WITHER);
        }
        setArmor(tier);
        setStats();
        String customName = enumMonster.getPrefix() + " " + enumMonster.name + " " + enumMonster.getSuffix() + " ";
        this.setCustomName(customName);
        this.getBukkitEntity().setMetadata("customname", new FixedMetadataValue(DungeonRealms.getInstance(), customName));
        this.setSize(0.7F, 2.4F);
        this.fireProof = true;
        this.setEquipment(EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(enumMonster.getSkullItem(enumMonster)));
        livingEntity.getEquipment().setHelmet(enumMonster.getSkullItem(enumMonster));
        this.noDamageTicks = 0;
        this.maxNoDamageTicks = 0;
    }

    /**
     * Implemented by Giovanni on 13/8/2016
     */
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
        this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, 1.0D));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 5.0F));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, false, true));
    }


    protected abstract void setStats();

    @Override
    protected Item getLoot()
    {
        return null;
    }

    public void setArmor(int tier)
    {
        ItemStack[] armor = GameAPI.getTierArmor(tier);
        // weapon, boots, legs, chest, helmet/head
        LivingEntity livingEntity = (LivingEntity) this.getBukkitEntity();
        boolean armorMissing = false;
        int chance = 6 + tier;
        if (tier >= 3 || random.nextInt(10) <= chance)
        {
            ItemStack armor0 = AntiCheat.getInstance().applyAntiDupe(armor[0]);
            livingEntity.getEquipment().setBoots(armor0);
            this.setEquipment(EnumItemSlot.FEET, CraftItemStack.asNMSCopy(armor0));
        } else
        {
            armorMissing = true;
        }
        if (tier >= 3 || random.nextInt(10) <= chance || armorMissing)
        {
            ItemStack armor1 = AntiCheat.getInstance().applyAntiDupe(armor[1]);
            livingEntity.getEquipment().setLeggings(armor1);
            this.setEquipment(EnumItemSlot.LEGS, CraftItemStack.asNMSCopy(armor1));
            armorMissing = false;
        } else
        {
            armorMissing = true;
        }
        if (tier >= 3 || random.nextInt(10) <= chance || armorMissing)
        {
            ItemStack armor2 = AntiCheat.getInstance().applyAntiDupe(armor[2]);
            livingEntity.getEquipment().setChestplate(armor2);
            this.setEquipment(EnumItemSlot.CHEST, CraftItemStack.asNMSCopy(armor2));
        }
        if (enumMonster == EnumMonster.FrozenSkeleton)
        {
            livingEntity.getEquipment().setHelmet(SkullTextures.FROZEN_SKELETON.getSkull());
        } else
        {
            livingEntity.getEquipment().setHelmet(SkullTextures.SKELETON.getSkull());
        }
    }

    public void setWeapon(int tier)
    {

    }

    @Override
    public boolean B(Entity entity)
    {
        return super.B(entity);
        //Should prevent wither effect being added on.
    }

    protected String getCustomEntityName()
    {
        return this.enumMonster.name;
    }

    @Override
    public void collide(Entity e)
    {
    }

    @Override
    public void onMonsterAttack(Player p)
    {
        // TODO Auto-generated type stub

    }

    @Override
    public void enderTeleportTo(double d0, double d1, double d2)
    {
        //Test for EnderPearl TP Cancel.
    }

    @Override
    public void onMonsterDeath(Player killer)
    {
        Bukkit.getScheduler().scheduleSyncDelayedTask(DungeonRealms.getInstance(), () -> {
            this.checkItemDrop(this.getBukkitEntity().getMetadata("tier").get(0).asInt(), enumMonster, this.getBukkitEntity(), killer);
        });
    }

    @Override
    public EnumMonster getEnum()
    {
        return this.enumMonster;
    }
}
