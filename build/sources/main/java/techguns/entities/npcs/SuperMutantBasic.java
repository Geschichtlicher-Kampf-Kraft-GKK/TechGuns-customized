package techguns.entities.npcs;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import techguns.TGuns;
import techguns.Techguns;
import techguns.damagesystem.TGDamageSource;

public class SuperMutantBasic extends GenericNPC {
	
	public static final ResourceLocation LOOT = new ResourceLocation(Techguns.MODID, "entities/supermutantbasic");

	private static ArrayList<Item> weapons = new ArrayList<>();

	public SuperMutantBasic(World world) {
		super(world);
		this.setSize(getMutantWidth(), 2F*this.getModelScale());
		setTGArmorStats(5.0f, 0f);
	}

	public static void changeWeapon(ArrayList<Item> weapons){
		SuperMutantBasic.weapons = weapons;
	}
	
	public int gettype() {
		return 0;
	};
	
	protected float getMutantWidth() {
		return 1.0f;
	}
	
	public double getModelHeightOffset(){
		return 0.55d;
	}
	
	public float getModelScale() {
		return 1.35f;
	}

	@Override
	public float getWeaponPosY() {
		return 0f;
	}
	
	@Override
	public float getWeaponPosX() {
		return 0.13f;
	}

	@Override
	public float getWeaponPosZ() {
		return -0.18f;
	}

	
	@Override
	public float getTotalArmorAgainstType(TGDamageSource dmgsrc) {
		switch(dmgsrc.damageType){
			case EXPLOSION:
			case LIGHTNING:
			case ENERGY:
			case FIRE:
			case ICE:
				return 10.0f;
			case PHYSICAL:
			case PROJECTILE:
				return 7.0f;
			case POISON:
			case RADIATION:
				return 15.0f;
			case UNRESISTABLE:
		default:
			return 0.0f;
		}
	}

	@Override
	public int getTotalArmorValue() {
		return 7;
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(SuperMutantBasic.speed);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(SuperMutantBasic.healthy);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(SuperMutantBasic.damage);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(SuperMutantBasic.range);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(SuperMutantBasic.armor);
		this.isImmuneToFire = SuperMutantBasic.ifFireProof;
	}

	static int healthy = 64;
	static int damage = 0;
	static double range = 32.0D;
	static double speed = 0.01D;
	static double armor = 5.0D;
	static boolean ifFireProof = true;

	public static void changeData(int healthy, int damage, double range, double speed, double armor, boolean ifFireProof){
		if(healthy > 0)SuperMutantBasic.healthy = healthy;
		if(damage > 0)SuperMutantBasic.damage = damage;
		if(range > 0)SuperMutantBasic.range = range;
		if(speed > 0)SuperMutantBasic.speed = speed;
		if(armor > 0)SuperMutantBasic.armor = armor;
		SuperMutantBasic.ifFireProof = ifFireProof;
	}

	

	@Override
	protected void addRandomArmor(int difficulty) {

			// Weapons
		Random r = new Random();
		if(weapons.isEmpty()){
			weapons.add(TGuns.lmg);
			weapons.add(TGuns.rocketlauncher);
		}
		this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(weapons.get(r.nextInt(weapons.size()))));
	}

	@Override
	public SoundEvent getAmbientSound() {
		return techguns.TGSounds.CYBERDEMON_IDLE;
	}

	@Override
	public SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return techguns.TGSounds.CYBERDEMON_HURT;
	}

	@Override
	public SoundEvent getDeathSound() {
		return techguns.TGSounds.CYBERDEMON_DEATH;
	}

	public SoundEvent getStepSound() {
		return techguns.TGSounds.CYBERDEMON_STEP;
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
