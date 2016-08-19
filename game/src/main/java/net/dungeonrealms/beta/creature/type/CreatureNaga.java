package net.dungeonrealms.beta.creature.type;

import net.dungeonrealms.beta.base64.Base64Skull;
import net.dungeonrealms.beta.creature.CreatureTier;
import net.dungeonrealms.beta.creature.CreatureType;
import net.dungeonrealms.beta.creature.type.data.IAggressive;
import net.dungeonrealms.beta.creature.type.element.CreatureElement;
import net.dungeonrealms.beta.item.data.ItemType;
import net.dungeonrealms.beta.utilities.math.IntegerUtil;
import net.dungeonrealms.beta.world.spawner.SpawnerObject;
import net.minecraft.server.v1_9_R2.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_9_R2.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

/**
 * Created by Giovanni on 16-8-2016.
 */
public class CreatureNaga extends EntityZombie implements IAggressive
{
    private CreatureTier creatureTier;

    private boolean elemental = false;
    private CreatureElement creatureElement;

    private List<ItemStack> skulls;
    private List<String> names;
    private String name;

    private int level;
    private int expDrop;

    private SpawnerObject spawnerObject;

    public CreatureNaga(World world, CreatureTier creatureTier, SpawnerObject spawnerObject)
    {
        super(world);

        this.spawnerObject = spawnerObject;

        this.skulls = Arrays.asList(
                new Base64Skull().getSkullByUrl("http://textures.minecraft.net/texture/df7ede9c88f1c33c954bbc81c8da871cabfcc23d55f8b493a589f8239d6c39"),
                new Base64Skull().getSkullByUrl("http://textures.minecraft.net/texture/977babd9f6d919f42fb40585ffd31618715152093fb686eff9552f926e1b54")
        );

        this.creatureElement = CreatureElement.random();
        this.creatureTier = creatureTier;

        this.level = IntegerUtil.randomInteger(creatureTier.getMinLevel(), creatureTier.getMaxLevel());
        this.expDrop = level * 4 / 2 + level + 15 - 10;

        if (level >= 35)
        {
            this.getAttributeInstance(GenericAttributes.maxHealth)
                    .setValue(IntegerUtil.randomInteger(creatureTier.getMinHealth(), creatureTier.getMaxHealth()) +
                            IntegerUtil.randomInteger(creatureTier.getMinHealth(), creatureTier.getMaxDmg()));
            this.setHealth(getMaxHealth());
            this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.3D);
        } else
        {
            this.getAttributeInstance(GenericAttributes.maxHealth)
                    .setValue(IntegerUtil.randomInteger(creatureTier.getMinHealth(), creatureTier.getMaxHealth()));
            this.setHealth(getMaxHealth());
            this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE)
                    .setValue(IntegerUtil.randomInteger(creatureTier.getMinDmg(), creatureTier.getMaxDmg()) * 2);
            this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.3D);
        }

        if (creatureElement == CreatureElement.EMPTY)
        {
            this.names = Arrays.asList("Gross", "Slimey", "Great");
            this.elemental = false;
        } else
        {
            this.names = Arrays.asList(creatureElement.getChatColor() + creatureElement.getPrefix());
            this.elemental = true;
            if(creatureElement == CreatureElement.HOLY)
            {
                this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE)
                        .setValue(IntegerUtil.randomInteger(creatureTier.getMinDmg(), creatureTier.getMaxDmg()) * 4);
            } else
            {
                this.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE)
                        .setValue(IntegerUtil.randomInteger(creatureTier.getMinDmg(), creatureTier.getMaxDmg()) * 2);
            }
        }
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
    public boolean isEquippable()
    {
        return true;
    }

    @Override
    public CreatureType getCreatureType()
    {
        return CreatureType.NAGA;
    }

    @Override
    public CreatureTier getCreatureTier()
    {
        return creatureTier;
    }

    @Override
    public CreatureElement getCreatureElement()
    {
        return creatureElement;
    }

    @Override
    public boolean isElemental()
    {
        return elemental;
    }

    @Override
    public int getExpDrop()
    {
        return expDrop;
    }

    @Override
    public String getName()
    {
        return name;
    }

    public int getLevel()
    {
        return level;
    }

    @Override
    public SpawnerObject getSpawnerObject()
    {
        return spawnerObject;
    }

    @Override
    public void spawn(Location location)
    {
        World craftWorld = ((CraftWorld) location.getWorld()).getHandle();
        this.setLocation(location.getX(), location.getY(), location.getZ(), location.getPitch(), location.getYaw());
        craftWorld.addEntity(this);
        aBE();
    }

    private void aBE()
    {
        this.setCustomName(ChatColor.AQUA + "[" + level + "]" + ChatColor.DARK_RED + names.get(new Random().nextInt(names.size())) + " Bandit");
        this.setCustomNameVisible(true);
        this.name = this.getCustomName();

        ((LivingEntity) this.getBukkitEntity()).getEquipment().setHelmet(skulls.get(new Random().nextInt(skulls.size())));
        ((LivingEntity) this.getBukkitEntity()).getEquipment().setChestplate(new ItemStack(creatureTier.getItemTier()
                .getMaterialByType(ItemType.CHESTPLATE)));
        ((LivingEntity) this.getBukkitEntity()).getEquipment().setLeggings(new ItemStack(creatureTier.getItemTier()
                .getMaterialByType(ItemType.LEGGINGS)));
        ((LivingEntity) this.getBukkitEntity()).getEquipment().setBoots(new ItemStack(creatureTier.getItemTier()
                .getMaterialByType(ItemType.BOOTS)));
    }
}
