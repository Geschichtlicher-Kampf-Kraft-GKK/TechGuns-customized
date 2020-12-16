package techguns.entities.npcs;

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

import java.util.ArrayList;
import java.util.Random;

public class PsychoSteve extends GenericNPC {

	public static final ResourceLocation LOOT = new ResourceLocation(Techguns.MODID, "entities/psychosteve");

	private static ArrayList<Item> weapons = new ArrayList<>();

	public PsychoSteve(World world) {
		super(world);
		setTGArmorStats(5.0f, 0f);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(PsychoSteve.speed);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(PsychoSteve.healthy);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(PsychoSteve.damage);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(PsychoSteve.range);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(PsychoSteve.armor);
		this.isImmuneToFire = PsychoSteve.ifFireProof;
	}

	static int healthy = 10;
	static int damage = 1;
	static double range = 32.0D;
	static double speed = 0.6D;
	static double armor = 0.0D;
	static boolean ifFireProof = true;

	public static void changeData(int healthy, int damage, double range, double speed, double armor, boolean ifFireProof){
		if(healthy > 0)PsychoSteve.healthy = healthy;
		if(damage > 0)PsychoSteve.damage = damage;
		if(range > 0)PsychoSteve.range = range;
		if(speed > 0)PsychoSteve.speed = speed;
		if(armor > 0)PsychoSteve.armor = armor;
		PsychoSteve.ifFireProof = ifFireProof;
	}

	@Override
	protected void addRandomArmor(int difficulty) {

		// Armors
		Random r = new Random();
		if(weapons.isEmpty()){
			weapons.add(TGuns.chainsaw);
		}
		this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(weapons.get(r.nextInt(weapons.size()))));

	}

	public static void changeWeapon(ArrayList<Item> weapons){
		PsychoSteve.weapons = weapons;
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
