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

public class Bandit extends GenericNPC {

	public static final ResourceLocation LOOT = new ResourceLocation(Techguns.MODID, "entities/Bandit");
	
	public Bandit(World world) {
		super(world);
		setTGArmorStats(5.0f, 0f);
	}

    @Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
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
			weapons.add(TGuns.boltaction);
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