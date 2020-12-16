package techguns.entities.npcs;

import java.util.ArrayList;
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
import techguns.items.armors.GenericArmorMultiCamo;

public class StormTrooper extends GenericNPCGearSpecificStats {

	public static final ResourceLocation LOOT = new ResourceLocation(Techguns.MODID, "entities/stormtrooper");

	private static ArrayList<Item> weapons = new ArrayList<>();

	public StormTrooper(World world) {
		super(world);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(StormTrooper.speed);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(StormTrooper.healthy);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(StormTrooper.damage);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(StormTrooper.range);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(StormTrooper.armor);
		this.isImmuneToFire = StormTrooper.ifFireProof;
	}

	static int healthy = 40;
	static int damage = 1;
	static double range = 96.0D;
	static double speed = 0.4D;
	static double armor = 3.0D;
	static boolean ifFireProof = true;

	public static void changeData(int healthy, int damage, double range, double speed, double armor, boolean ifFireProof){
		if(healthy > 0)StormTrooper.healthy = healthy;
		if(damage > 0)StormTrooper.damage = damage;
		if(range > 0)StormTrooper.range = range;
		if(speed > 0)StormTrooper.speed = speed;
		if(armor > 0)StormTrooper.armor = armor;
		StormTrooper.ifFireProof = ifFireProof;
	}

	public static void changeWeapon(ArrayList<Item> weapons){
		StormTrooper.weapons = weapons;
	}

	@Override
	protected void addRandomArmor(int difficulty) {

		// Armors
		
		int camo = 1;
		
		this.setItemStackToSlot(EntityEquipmentSlot.HEAD, GenericArmorMultiCamo.getNewWithCamo(
			TGArmors.t3_miner_Helmet,camo));
		this.setItemStackToSlot(EntityEquipmentSlot.CHEST, GenericArmorMultiCamo.getNewWithCamo(
				TGArmors.t3_miner_Chestplate,camo));
		this.setItemStackToSlot(EntityEquipmentSlot.LEGS, GenericArmorMultiCamo.getNewWithCamo(
				TGArmors.t3_miner_Leggings,camo));
		this.setItemStackToSlot(EntityEquipmentSlot.FEET, GenericArmorMultiCamo.getNewWithCamo(
				TGArmors.t3_miner_Boots,camo));
		// Weapons

		Random r = new Random();
		if(weapons.isEmpty()){
			weapons.add(TGuns.scar);
			weapons.add(TGuns.m4_infiltrator);
			weapons.add(TGuns.rocketlauncher);
		}
		this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(weapons.get(r.nextInt(weapons.size()))));
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
		return SoundEvents.ENTITY_ZOMBIE_STEP;
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