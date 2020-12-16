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
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(64.0D);
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
	protected ResourceLocation getLootTable() {
		return LOOT;
	}
}