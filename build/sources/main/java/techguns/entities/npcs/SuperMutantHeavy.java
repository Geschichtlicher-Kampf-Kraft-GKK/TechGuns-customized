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

public class SuperMutantHeavy extends SuperMutantBasic {

	private static ArrayList<Item> weapons = new ArrayList<>();

	public SuperMutantHeavy(World world) {
		super(world);
		setTGArmorStats(10.0f, 0);
	}

	public static void changeWeapon(ArrayList<Item> weapons){
		SuperMutantHeavy.weapons = weapons;
	}
	
	public int gettype() {
		return 1;
	};

	public double getModelHeightOffset(){
		return 0.75d;
	}
	
	public float getModelScale() {
		return 1.5f;
	}
	
	@Override
	protected float getMutantWidth() {
		return 1.2f;
	}
	
	@Override
	public float getWeaponPosX() {
		return 0.17f;
	}

	@Override
	public float getWeaponPosZ() {
		return -0.3f;
	}

	
	@Override
	public float getTotalArmorAgainstType(TGDamageSource dmgsrc) {
		switch(dmgsrc.damageType){
		case EXPLOSION:
		case LIGHTNING:
		case ENERGY:
		case FIRE:
		case ICE:
			return 16.0f;
		case PHYSICAL:
		case PROJECTILE:
			return 12.0f;
		case POISON:
		case RADIATION:
			return 18.0f;
		case UNRESISTABLE:
	default:
		return 0.0f;
		}
	}

	@Override
	public int getTotalArmorValue() {
		return 12;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(SuperMutantHeavy.speed);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(SuperMutantHeavy.healthy);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(SuperMutantHeavy.damage);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(SuperMutantHeavy.range);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(SuperMutantHeavy.armor);
		this.isImmuneToFire = SuperMutantHeavy.ifFireProof;
	}

	static int healthy = 1024;
	static int damage = 1;
	static double range = 64.0D;
	static double speed = 0.1D;
	static double armor = 8.0D;
	static boolean ifFireProof = true;

	public static void changeData(int healthy, int damage, double range, double speed, double armor, boolean ifFireProof){
		if(healthy > 0)SuperMutantHeavy.healthy = healthy;
		if(damage > 0)SuperMutantHeavy.damage = damage;
		if(range > 0)SuperMutantHeavy.range = range;
		if(speed > 0)SuperMutantHeavy.speed = speed;
		if(armor > 0)SuperMutantHeavy.armor = armor;
		SuperMutantHeavy.ifFireProof = ifFireProof;
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
