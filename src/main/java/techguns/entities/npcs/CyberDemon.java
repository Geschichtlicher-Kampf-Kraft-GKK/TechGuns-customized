package techguns.entities.npcs;

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

import java.util.ArrayList;
import java.util.Random;

public class CyberDemon extends GenericNPCUndead {

	public static final ResourceLocation LOOT = new ResourceLocation(Techguns.MODID, "entities/cyberdemon");

	private static ArrayList<Item> weapons = new ArrayList<>();

	public CyberDemon(World world) {
		super(world);
		setTGArmorStats(10.0f, 0f);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(CyberDemon.speed);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(CyberDemon.healthy);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(CyberDemon.damage);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(CyberDemon.range);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(CyberDemon.armor);
		this.isImmuneToFire = CyberDemon.ifFireProof;
	}

	static int healthy = 50;
	static int damage = 0;
	static double range = 32.0D;
	static double speed = 0.1D;
	static double armor = 3.0D;
	static boolean ifFireProof = true;

	public static void changeData(int healthy, int damage, double range, double speed, double armor, boolean ifFireProof){
		if(healthy > 0)CyberDemon.healthy = healthy;
		if(damage > 0)CyberDemon.damage = damage;
		if(range > 0)CyberDemon.range = range;
		if(speed > 0)CyberDemon.speed = speed;
		if(armor > 0)CyberDemon.armor = armor;
		CyberDemon.ifFireProof = ifFireProof;
	}

	public static void changeWeapon(ArrayList<Item> weapons){
		CyberDemon.weapons = weapons;
	}

	@Override
	protected void addRandomArmor(int difficulty) {

		// No Armor
		// Weapons
		Random r = new Random();
		if(weapons.isEmpty()){
			weapons.add(TGuns.netherblaster);
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
	public boolean hasWeaponArmPose() {
		return false;
	}

	
	
	@Override
	public float getWeaponPosX() {
		return 0.16f;
	}

	@Override
	public float getWeaponPosY() {
		return 0.72f;
	}

	@Override
	public float getWeaponPosZ() {
		return 0.1f;
	}

	@Override
	public float getGunScale() {
		return 1.5f;
	}

	
	
	@Override
	public float getBulletOffsetSide() {
		return 0.3f;
	}

	@Override
	public float getBulletOffsetHeight() {
		return -0.59f;
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

	@Override
	protected boolean shouldBurnInDay() {
		return false;
	}
	
	
}