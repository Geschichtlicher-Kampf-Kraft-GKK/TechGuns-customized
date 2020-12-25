package techguns.entities.npcs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import techguns.TGArmors;
import techguns.TGuns;
import techguns.Techguns;

public class ArmySoldier extends GenericNPC {

	public static final ResourceLocation LOOT = new ResourceLocation(Techguns.MODID, "entities/armysoldier");
	
	public ArmySoldier(World world) {
		super(world);
		setTGArmorStats(8.0f, 0f);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(ArmySoldier.speed);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ArmySoldier.healthy);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(ArmySoldier.damage);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(ArmySoldier.range);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(ArmySoldier.armor);
		this.isImmuneToFire = ArmySoldier.ifFireProof;
	}

	static int healthy = 10;
	static int damage = 0;
	static double range = 32.0D;
	static double speed = 0.2D;
	static double armor = 1.0D;
	static boolean ifFireProof = false;

	public static void changeData(int healthy, int damage, double range, double speed, double armor, boolean ifFireProof){
		if(healthy > 0)ArmySoldier.healthy = healthy;
		if(damage > 0)ArmySoldier.damage = damage;
		if(range > 0)ArmySoldier.range = range;
		if(speed > 0)ArmySoldier.speed = speed;
		if(armor > 0)ArmySoldier.armor = armor;
		ArmySoldier.ifFireProof = ifFireProof;
	}

	private static ArrayList<Item> weapons = new ArrayList<>();

	@Override
	protected void addRandomArmor(int difficulty) {

		// Armors
		this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(TGArmors.t2_combat_Helmet));
		this.setItemStackToSlot(EntityEquipmentSlot.CHEST,new ItemStack(TGArmors.t2_combat_Chestplate));
		this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(TGArmors.t2_combat_Leggings));
		this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(TGArmors.t2_combat_Boots));

		// Weapons
		Random r = new Random();
		if(weapons.isEmpty()){
			weapons.add(TGuns.m4);
			weapons.add(TGuns.boltaction);
		}
		this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(weapons.get(r.nextInt(weapons.size()))));
	}

	public static void changeWeapon(ArrayList<Item> weapons){
		ArmySoldier.weapons = weapons;
	}
	
	@Override
	public SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_VILLAGER_AMBIENT;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.ENTITY_VILLAGER_HURT;
	}

	@Override
	public SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_VILLAGER_DEATH;
	}

	public SoundEvent getStepSound() {
		return SoundEvents.ENTITY_ZOMBIE_VILLAGER_STEP;
	}
	
	@Override
    protected void playStepSound(BlockPos pos, Block blockIn)
    {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }
	
	@Override
	protected ResourceLocation getLootTable() {
		return LOOT;
	}
}