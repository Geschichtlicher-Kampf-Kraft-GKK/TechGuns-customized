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

import java.util.ArrayList;
import java.util.Random;

public class DictatorDave extends GenericNPC {

	public static final ResourceLocation LOOT = new ResourceLocation(Techguns.MODID, "entities/dictatordave");

	private static ArrayList<Item> weapons = new ArrayList<>();

	public DictatorDave(World world) {
		super(world);
		setTGArmorStats(15.0f, 0f);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.40D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(20);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0D);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(5D);
		this.experienceValue = 35;
	}

	public static void changeWeapon(ArrayList<Item> weapons){
		DictatorDave.weapons = weapons;
	}

	@Override
	protected void addRandomArmor(int difficulty) {

		// Armors
				this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(TGArmors.t2_beret));
				this.setItemStackToSlot(EntityEquipmentSlot.CHEST,new ItemStack(TGArmors.t1_combat_Chestplate));
				this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(TGArmors.t1_scout_Leggings));
			    this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(TGArmors.t1_combat_Boots));

				// Weapons
		Random r = new Random();
		if(weapons.isEmpty()){
			weapons.add(TGuns.ak47);
			weapons.add(TGuns.pistol);
			weapons.add(TGuns.combatshotgun);
			weapons.add(TGuns.boltaction);
			weapons.add(TGuns.revolver);
			weapons.add(TGuns.thompson);
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