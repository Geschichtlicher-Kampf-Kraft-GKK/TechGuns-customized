package techguns.entities.npcs;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import techguns.TGArmors;
import techguns.TGuns;
import techguns.Techguns;
import techguns.items.armors.GenericArmorMultiCamo;

public class Outcast extends GenericNPCGearSpecificStats {

	public static final ResourceLocation LOOT = new ResourceLocation(Techguns.MODID, "entities/outcast");

	private static ArrayList<Item> weapons = new ArrayList<>();

	public Outcast(World world) {
		super(world);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(Outcast.speed);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(Outcast.healthy);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(Outcast.damage);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(Outcast.range);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(Outcast.armor);
		this.isImmuneToFire = Outcast.ifFireProof;
	}

	static int healthy = 30;
	static int damage = 0;
	static double range = 32.0D;
	static double speed = 0.05D;
	static double armor = 5.0D;
	static boolean ifFireProof = true;

	public static void changeData(int healthy, int damage, double range, double speed, double armor, boolean ifFireProof){
		if(healthy > 0)Outcast.healthy = healthy;
		if(damage > 0)Outcast.damage = damage;
		if(range > 0)Outcast.range = range;
		if(speed > 0)Outcast.speed = speed;
		if(armor > 0)Outcast.armor = armor;
		Outcast.ifFireProof = ifFireProof;
	}

	public static void changeWeapon(ArrayList<Item> weapons){
		Outcast.weapons = weapons;
	}

	@Override
	protected void addRandomArmor(int difficulty) {

		Random r = new Random();
		// Armors

		int camo=r.nextInt(2);
		this.setItemStackToSlot(EntityEquipmentSlot.HEAD, GenericArmorMultiCamo.getNewWithCamo(TGArmors.t3_power_Helmet,camo+1));
		camo=r.nextInt(2);
		this.setItemStackToSlot(EntityEquipmentSlot.CHEST, GenericArmorMultiCamo.getNewWithCamo(TGArmors.t3_power_Chestplate,camo+1));
		camo=r.nextInt(2);
		this.setItemStackToSlot(EntityEquipmentSlot.LEGS, GenericArmorMultiCamo.getNewWithCamo(TGArmors.t3_power_Leggings,camo+1));
		camo=r.nextInt(2);
		this.setItemStackToSlot(EntityEquipmentSlot.FEET, GenericArmorMultiCamo.getNewWithCamo(TGArmors.t3_power_Boots,camo+1));
		

		// Weapons

		if(weapons.isEmpty()){
			weapons.add(TGuns.lasergun);
		}
		this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(weapons.get(r.nextInt(weapons.size()))));
	}
	
	
	@Override
	protected ResourceLocation getLootTable() {
		return LOOT;
	}
}