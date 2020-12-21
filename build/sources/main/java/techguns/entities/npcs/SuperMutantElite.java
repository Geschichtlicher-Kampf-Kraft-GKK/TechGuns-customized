package techguns.entities.npcs;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import techguns.TGuns;
import techguns.damagesystem.TGDamageSource;

import java.util.ArrayList;
import java.util.Random;

public class SuperMutantElite extends SuperMutantBasic {

	private static ArrayList<Item> weapons = new ArrayList<>();

	public SuperMutantElite(World world) {
		super(world);
		setTGArmorStats(15.0f, 1f);
	}

	public static void changeWeapon(ArrayList<Item> weapons){
		SuperMutantElite.weapons = weapons;
	}
	
	public int gettype() {
		return 2;
	};

	public double getModelHeightOffset(){
		return 0.95d;
	}
	
	public float getModelScale() {
		return 1.65f;
	}
	
	@Override
	protected float getMutantWidth() {
		return 1.4f;
	}

	@Override
	public float getWeaponPosX() {
		return 0.23f;
	}

	@Override
	public float getWeaponPosZ() {
		return -0.39f;
	}

	
	@Override
	public float getTotalArmorAgainstType(TGDamageSource dmgsrc) {
		switch(dmgsrc.damageType){
		case EXPLOSION:
		case LIGHTNING:
		case ENERGY:
		case FIRE:
		case ICE:
			return 14.0f;
		case PHYSICAL:
		case PROJECTILE:
			return 10.0f;
		case POISON:
		case RADIATION:
			return 16.0f;
		case UNRESISTABLE:
	default:
		return 0.0f;
		}
	}

	@Override
	public int getTotalArmorValue() {
		return 10;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(SuperMutantElite.speed);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(SuperMutantElite.healthy);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(SuperMutantElite.damage);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(SuperMutantElite.range);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(SuperMutantElite.armor);
		this.isImmuneToFire = SuperMutantElite.ifFireProof;
	}

	static int healthy = 1024;
	static int damage = 0;
	static double range = 32.0D;
	static double speed = 0.005D;
	static double armor = 20.0D;
	static boolean ifFireProof = true;

	public static void changeData(int healthy, int damage, double range, double speed, double armor, boolean ifFireProof){
		if(healthy > 0)SuperMutantElite.healthy = healthy;
		if(damage > 0)SuperMutantElite.damage = damage;
		if(range > 0)SuperMutantElite.range = range;
		if(speed > 0)SuperMutantElite.speed = speed;
		if(armor > 0)SuperMutantElite.armor = armor;
		SuperMutantElite.ifFireProof = ifFireProof;
	}

	@Override
	protected void addRandomArmor(int difficulty) {

		// Weapons
		Random r = new Random();
		if(weapons.isEmpty()){
			weapons.add(TGuns.minigun);
			weapons.add(TGuns.grimreaper);
		}
		this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(weapons.get(r.nextInt(weapons.size()))));
	}
}
