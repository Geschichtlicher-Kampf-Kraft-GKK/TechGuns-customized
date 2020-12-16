package techguns.entities.npcs;

import java.util.AbstractList;
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

import javax.annotation.Nullable;

public class Bandit extends GenericNPC {

	public static final ResourceLocation LOOT = new ResourceLocation(Techguns.MODID, "entities/Bandit");
	
	public Bandit(World world) {
		super(world);
		setTGArmorStats(5.0f, 0f);
	}

    @Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(Bandit.speed);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(Bandit.healthy);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(Bandit.damage);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(Bandit.range);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(Bandit.armor);
		this.isImmuneToFire = Bandit.ifFireProof;
	}

	static int healthy = 20;
	static int damage = 1;
	static double range = 32.0D;
	static double speed = 0.3D;
	static double armor = 0.0D;
	static boolean ifFireProof = false;

	public static void changeData(int healthy, int damage, double range, double speed, double armor, boolean ifFireProof){
		if(healthy > 0)Bandit.healthy = healthy;
		if(damage > 0)Bandit.damage = damage;
		if(range > 0)Bandit.range = range;
		if(speed > 0)Bandit.speed = speed;
		if(armor > 0)Bandit.armor = armor;
		Bandit.ifFireProof = ifFireProof;
	}

	static ArrayList<Item> weapons = new ArrayList<>();

	@Override
	protected void addRandomArmor(int difficulty) {

		// Armors

		// Weapons
		Random r = new Random();
		if(weapons.isEmpty()){
			weapons.add(TGuns.ak47);
			weapons.add(TGuns.pistol);
			weapons.add(TGuns.combatshotgun);
			weapons.add(TGuns.revolver);
			weapons.add(TGuns.thompson);
		}
		this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(weapons.get(r.nextInt(weapons.size()))));
	}

	public static void changeWeapon(ArrayList<Item> weapons){
		Bandit.weapons = weapons;
	}
	
	@Override
	protected ResourceLocation getLootTable() {
		return LOOT;
	}
}