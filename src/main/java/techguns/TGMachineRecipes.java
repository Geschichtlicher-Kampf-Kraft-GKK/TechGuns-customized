package techguns;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import techguns.api.radiation.TGRadiation;
import techguns.blocks.EnumOreType;
import techguns.items.armors.ICamoChangeable;
import techguns.radiation.RadRegenerationPotion;
import techguns.tileentities.operation.AmmoPressBuildPlans;
import techguns.tileentities.operation.UpgradeBenchRecipes;
import techguns.tileentities.operation.UpgradeBenchRecipes.UpgradeBenchRecipe;
import techguns.tileentities.operation.BlastFurnaceRecipes;
import techguns.tileentities.operation.BlastFurnaceRecipes.BlastFurnaceRecipe;
import techguns.tileentities.operation.CamoBenchRecipes;
import techguns.tileentities.operation.CamoBenchRecipes.CamoBenchRecipe;
import techguns.tileentities.operation.ChargingStationRecipe;
import techguns.tileentities.operation.ChemLabRecipes;
import techguns.tileentities.operation.FabricatorRecipe;
import techguns.tileentities.operation.GrinderRecipes;
import techguns.tileentities.operation.MetalPressRecipes;
import techguns.tileentities.operation.ReactionBeamFocus;
import techguns.tileentities.operation.ReactionChamberRecipe;
import techguns.tileentities.operation.ReactionChamberRecipe.RiskType;
import techguns.util.ItemStackOreDict;
import techguns.util.ItemUtil;

/**
* Class that contains recipes for machines
*
*/
public class TGMachineRecipes {

	public static void addRecipes(){
		
		ItemStack GOLD_OR_ELECTRUM=ItemStack.EMPTY;
		if(OreDictionary.doesOreNameExist("ingotElectrum")) {
			NonNullList<ItemStack> list = OreDictionary.getOres("ingotElectrum");
			if(!list.isEmpty()) {
				GOLD_OR_ELECTRUM = list.get(0);
			} else {
				GOLD_OR_ELECTRUM = new ItemStack(Items.GOLD_INGOT,1);
			}
		} else {
			GOLD_OR_ELECTRUM = new ItemStack(Items.GOLD_INGOT,1);
		}
		
		//AMMO PRESS
		ArrayList<String> metal2 = new ArrayList<String>();
		ArrayList<String> metal1 = new ArrayList<String>();
		ArrayList<String> powders = new ArrayList<String>();
		metal2.add("ingotCopper");
		metal2.add("ingotTin");
		metal2.add("ingotIron");
		metal2.add("ingotBronze");
		metal1.add("ingotSteel");
		metal1.add("ingotLead");
		powders.add("gunpowder");
		AmmoPressBuildPlans.init(metal1, metal2, powders);
		
		//METAL PRESS
		MetalPressRecipes.addRecipe("ingotTin","ingotTin",TGItems.newStack(TGItems.PLATE_TIN,2), true);
		MetalPressRecipes.addRecipe("ingotCopper","ingotCopper",TGItems.newStack(TGItems.PLATE_COPPER,2), true);
		MetalPressRecipes.addRecipe("ingotBronze","ingotBronze",TGItems.newStack(TGItems.PLATE_BRONZE,2), true);
		MetalPressRecipes.addRecipe("ingotIron","ingotIron",TGItems.newStack(TGItems.PLATE_IRON,2), true);
		MetalPressRecipes.addRecipe("ingotSteel","ingotSteel",TGItems.newStack(TGItems.PLATE_STEEL,2), true);
		MetalPressRecipes.addRecipe("ingotLead","ingotLead",TGItems.newStack(TGItems.PLATE_LEAD,2), true);
		MetalPressRecipes.addRecipe("plateIron", new ItemStack(Items.FLINT,1), TGItems.newStack(TGItems.MECHANICAL_PARTS_IRON, 1), true);
		MetalPressRecipes.addRecipe("plateCopper", "plateCopper", TGItems.newStack(TGItems.WIRE_COPPER, 8), true);
		MetalPressRecipes.addRecipe("ingotGold", "ingotGold", TGItems.newStack(TGItems.WIRE_GOLD, 2), true);
		/**
		 * Blast Furnace
		 */
		BlastFurnaceRecipes.addRecipe(new ItemStack(Items.IRON_INGOT,4), new ItemStack(Items.COAL,4), TGItems.newStack(TGItems.INGOT_STEEL, 4), 10, 800);
		BlastFurnaceRecipes.addRecipe(new ItemStack(Items.IRON_INGOT,4), new ItemStack(Items.COAL,4,1), TGItems.newStack(TGItems.INGOT_STEEL, 4), 10, 800);
		BlastFurnaceRecipes.addRecipe("ingotCopper", 3,"ingotTin",1,TGItems.newStack(TGItems.INGOT_BRONZE, 4), 10, 100);
		/**
		 * CHARGING STATION
		 */
		ChargingStationRecipe.addRecipe(new ItemStackOreDict(TGItems.REDSTONE_BATTERY_EMPTY), TGItems.REDSTONE_BATTERY, 20000);

		//brewing
		ItemStack awkwardPotion = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.AWKWARD);
		ItemStack radPotion = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), TGRadiationSystem.RAD_POTION);
		
		BrewingRecipeRegistry.addRecipe(awkwardPotion, "dustUranium", radPotion);
		BrewingRecipeRegistry.addRecipe(awkwardPotion, TGItems.newStack(TGItems.ENRICHED_URANIUM, 1), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), TGRadiationSystem.RAD_POTION_SEVERE));
		
		BrewingRecipeRegistry.addRecipe(awkwardPotion, "ingotLead", PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), TGRadiationSystem.RAD_RESISTANCE_POTION));
		
		BrewingRecipeRegistry.addRecipe(radPotion, new ItemStack(Items.GOLDEN_CARROT), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), TGRadiationSystem.RAD_REGENERATION_POTION));
		BrewingRecipeRegistry.addRecipe(radPotion, new ItemStack(Items.SPECKLED_MELON), PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), TGRadiationSystem.RAD_REGENERATION_POTION));
	
		/**
		 * Grinder recipes
		 */
		//Ammo crush
		GrinderRecipes.addRecipesWithChance(TGItems.LMG_MAGAZINE_EMPTY,
				drop_plastic(4, 1.0, 1, 1.0),
				drop_steel(0, 0, 0, 0, 4, 1.0, 0, 0),
				drop_iron(0, 0, 0, 0, 0, 0, 2, 1.0, 0, 0));
		GrinderRecipes.addRecipesWithChance(TGItems.MINIGUN_DRUM_EMPTY,
				drop_steel(0, 0, 0, 0, 8, 1.0, 0, 0),
				drop_iron(0, 0, 0, 0, 0, 0, 4, 1.0, 0, 0));
		GrinderRecipes.addRecipesWithChance(TGItems.AS50_MAGAZINE_EMPTY,
				drop_plastic(2, 1.0, 0, 0),
				drop_iron(0, 0, 0, 0, 2, 1.0, 1, 1.0, 0, 0));
		GrinderRecipes.addRecipesWithChance(TGItems.FUEL_TANK_EMPTY,
				drop_steel(0, 0, 0, 0, 8, 1.0, 0, 0),
				drop_iron(0, 0, 0, 0, 0, 0, 2, 1.0, 0, 0));
		GrinderRecipes.addRecipesWithChance(TGItems.ENERGY_CELL_EMPTY,
				drop_lead(0, 0, 0, 0, 16, 0.8),
				drop_steel(0, 0, 0, 0, 8, 1.0, 0, 0),
				drop_copper(1, 1.0, 0, 0, 0, 0),
				drop_tin(1, 1.0, 0, 0),
				drop_electro(4, 0.8, 1, 0.5, 0, 0));
		GrinderRecipes.addRecipesWithChance(TGItems.NUCLEAR_POWERCELL_EMPTY,
				drop_lead(0, 0, 12, 1.0, 0, 0),
				drop_steel(0, 0, 0, 0, 24, 1.0, 0, 0),
				drop_plastic(8, 1.0, 12, 1.0),
				drop_electro(8, 1.0, 4, 1.0, 0, 0),
				drop_copper(2, 1.0, 0, 0, 0, 0),
				drop_tin(2, 1.0, 0, 0));
		GrinderRecipes.addRecipesWithChance(TGItems.COMPRESSED_AIR_TANK_EMPTY,
				drop_steel(0, 0, 0, 0, 8, 1.0, 0, 0),
				drop_iron(0, 0, 0, 0, 0, 0, 2, 1.0, 0, 0));
		GrinderRecipes.addRecipesWithChance(TGItems.ASSAULTRIFLE_MAGAZINE_EMPTY,
				drop_plastic(1, 1.0, 0, 0),
				drop_iron(0, 0, 0, 0, 0, 0, 1, 1.0, 0, 0));
		GrinderRecipes.addRecipesWithChance(TGItems.PISTOL_MAGAZINE_EMPTY,
				drop_iron(0, 0, 0, 0,1, 1.0, 1, 0.5, 0, 0));
		GrinderRecipes.addRecipesWithChance(TGItems.SMG_MAGAZINE_EMPTY,
				drop_iron(0, 0, 0, 0, 1, 1.0, 1, 1.0, 0, 0));
		GrinderRecipes.addRecipesWithChance(TGuns.revolver,
				drop_steel(2, 0.8, 1, 0.5, 1, 1.0, 0, 0),
				drop_iron(1, 0.5, 0, 0, 0, 0, 4, 0.8, 0, 0),
				drop_wood(1, 0.5));
		GrinderRecipes.addRecipesWithChance(TGuns.thompson,
				drop_steel(2, 1.0, 1, 1.0, 4, 1.0, 0, 0),
				drop_iron(2, 1.0, 0, 0, 0, 0,8, 0.8, 0, 0),
				drop_wood(4, 0.8),
				drop_lead(0, 0, 0, 0, 8, 0.5),
				drop_bronze(1, 0.8, 0, 0),
				drop_magazin("smg"));
		GrinderRecipes.addRecipesWithChance(TGuns.ak47,
				drop_steel(6, 1.0, 1, 1.0, 6, 1.0, 0, 0),
				drop_iron(2, 1.0, 0, 0, 0, 0, 4, 1.0, 0, 0),
				drop_wood(2, 1.0),
				drop_magazin("ar"));
		GrinderRecipes.addRecipesWithChance(TGuns.boltaction,
				drop_steel(2, 0.5, 1, 0.5, 6, 0.5, 0, 0),
				drop_iron(0, 0, 0, 0, 0, 0,12, 0.5, 0, 0),
				drop_wood(8, 0.8));
		GrinderRecipes.addRecipesWithChance(TGuns.m4,
				drop_steel(2, 0.8, 2, 0.8, 4, 0.8, 0, 0),
				drop_iron(0, 0, 0, 0, 0, 0, 8, 0.8, 0, 0),
				drop_plastic(4, 0.5, 2, 0.5),
				drop_magazin("ar"));
		GrinderRecipes.addRecipesWithChance(TGuns.m4_infiltrator,
				drop_steel(2, 0.8, 2, 0.8, 5, 0.8, 0, 0),
				drop_iron(0, 0, 1, 0.5, 0, 0, 10, 0.8, 0, 0),
				drop_plastic(6, 0.5, 3, 0.5),
				drop_electro(2, 0.5, 0, 0, 0, 0),
				drop_lead(1, 0.5, 0, 0, 0, 0),
				drop_copper(1, 0.5, 0, 0, 0, 0),
				drop_magazin("ar"));
		GrinderRecipes.addRecipesWithChance(TGuns.pistol,
				drop_iron(1, 1.0, 1, 1.0, 1, 1.0, 1, 1.0, 0, 0),
				drop_magazin("pistol"));
		GrinderRecipes.addRecipesWithChance(TGuns.combatshotgun,
				drop_steel(1, 1.0, 1, 1.0, 2, 1.0, 16, 0.2),
				drop_iron(0, 0, 0, 0, 0, 0, 2, 1.0, 0, 0),
				drop_lead(0, 0, 0, 0, 16, 0.8),
				drop_plastic(4, 1.0, 1, 1.0));
		GrinderRecipes.addRecipesWithChance(TGuns.flamethrower,
				drop_steel(0, 0, 4, 0.5, 24, 1.0, 0, 0),
				drop_iron(0, 0, 0, 0, 0, 0, 2, 0.8, 0, 0),
				drop_plastic(2, 1.0, 1, 1.0),
				drop_electro(0, 0, 0, 0,4, 1.0),
				drop_magazin("fuel"));
		GrinderRecipes.addRecipesWithChance(TGuns.rocketlauncher,
				drop_steel(0, 0, 0, 0, 4, 1.0, 0, 0),
				drop_plastic(2, 1.0, 1, 1.0));
		GrinderRecipes.addRecipesWithChance(TGuns.grimreaper,
				drop_steel(0, 0, 1, 1.0, 16, 1.0, 0, 0),
				drop_plastic(2, 1.0, 1, 1.0));
		GrinderRecipes.addRecipesWithChance(TGuns.grenadelauncher,
				drop_steel(2, 0.8, 1, 1.0, 4, 1.0, 0, 0),
				drop_iron(0, 0, 0, 0, 0, 0,2, 1.0, 0, 0),
				drop_plastic(2, 1.0, 1, 1.0));
		GrinderRecipes.addRecipesWithChance(TGuns.aug,
				drop_steel(1, 1.0, 2, 1.0, 4, 1.0, 0, 0),
				drop_iron(0, 0, 0, 0, 0, 0,8, 1.0, 0, 0),
				drop_plastic(6, 1.0, 2, 1.0),
				drop_magazin("ar"));
		GrinderRecipes.addRecipesWithChance(TGuns.scar,
				drop_steel(1, 1.0, 2, 1.0, 5, 1.0, 0, 0),
				drop_iron(0, 0, 0, 0, 0, 0, 8, 1.0, 0, 0),
				drop_plastic(6, 1.0, 2, 1.0),
				drop_electro(2, 1.0, 0, 0, 0, 0),
				drop_magazin("ar"));
		GrinderRecipes.addRecipesWithChance(TGuns.vector,
				drop_steel(1, 1.0, 1, 1.0, 3, 1.0, 0, 0),
				drop_iron(0, 0, 0, 0, 0, 0, 4, 1.0, 0, 0),
				drop_plastic(4, 1.0, 2, 1.0),
				drop_magazin("smg"));
		GrinderRecipes.addRecipesWithChance(TGuns.lmg,
				drop_steel(2, 0.8, 1, 0.8, 8, 1.0, 0, 0),
				drop_iron(0, 0, 0, 0, 0, 0, 12, 0.5, 0, 0),
				drop_plastic(8, 0.5, 2, 1.0),
				drop_copper(4, 1.0, 0, 0, 0, 0),
				drop_bronze(4, 1.0, 0, 0),
				drop_magazin("lmg"));
		GrinderRecipes.addRecipesWithChance(TGuns.minigun,
				drop_steel(8, 1.0, 4, 1.0, 32, 1.0, 0, 0),
				drop_iron(0, 0, 0, 0, 0, 0, 24, 1.0, 0, 0),
				drop_plastic(2, 1.0, 1, 1.0),
				drop_copper(12, 1.0, 0, 0, 0, 0),
				drop_bronze(12, 1.0, 0, 0),
				drop_electro(4, 1.0, 1, 1.0, 6, 1.0),
				drop_magazin("minigun"));
		GrinderRecipes.addRecipesWithChance(TGuns.as50,
				drop_steel(2, 1.0, 1, 1.0, 16, 1.0, 0, 0),
				drop_iron(0, 0, 0, 0, 0, 0, 2, 1.0, 0, 0),
				drop_plastic(4, 1.0, 2, 1.0),
				drop_tin(0, 0, 6, 1.0),
				drop_magazin("sniper"));
		GrinderRecipes.addRecipesWithChance(TGuns.chainsaw,
				drop_steel(0, 0, 0, 0, 0, 0, 32, 1.0),
				drop_iron(2, 1.0, 2, 1.0, 0, 0, 4, 1.0, 32, 1.0),
				drop_plastic(4, 1.0, 1, 1.0),
				drop_electro(1, 1.0, 0, 0, 4, 1.0),
				drop_magazin("fuel"));
	}

	private static ItemStack setCount(ItemStack itemStack, int value){
		ItemStack tmp = itemStack.copy();
		tmp.setCount(value);
		return tmp;
	}

	private static HashMap<ItemStack, Double> drop_wood(int plank, double plank_chance){
		HashMap<ItemStack, Double> map = new HashMap<>();
		if(plank>0 && plank_chance>0) { map.put(new ItemStack(Blocks.PLANKS, plank), plank_chance);}
		return map;
	}

	private static HashMap<ItemStack, Double> drop_iron(int ingot, double ingot_chance,
												   int receiver, double receiver_chance,
												   int plate, double plate_chance,
												   int mech_part, double mech_part_chance,
												   int nugget, double nugget_chance){
		HashMap<ItemStack, Double> map = new HashMap<>();
		if(ingot>0 && ingot_chance>0) { map.put(new ItemStack(Items.IRON_INGOT, ingot), ingot_chance);}
		if(receiver>0 && receiver_chance>0) { map.put(setCount(TGItems.RECEIVER_IRON, receiver), receiver_chance);}
		if(plate>0) { map.put(setCount(TGItems.PLATE_IRON, plate), plate_chance);}
		if(mech_part>0) { map.put(setCount(TGItems.MECHANICAL_PARTS_IRON, mech_part), mech_part_chance);}
		if(nugget>0) { map.put(new ItemStack(Items.IRON_NUGGET, nugget), nugget_chance);}
		return map;
	}

	private static HashMap<ItemStack, Double> drop_steel(int ingot, double ingot_chance,
													int receiver, double receiver_chance,
													int plate, double plate_chance,
													int nugget, double nugget_chance){
		HashMap<ItemStack, Double> map = new HashMap<>();
		if(ingot>0 && ingot_chance>0) { map.put(setCount(TGItems.INGOT_STEEL, ingot), ingot_chance);}
		if(receiver>0 && receiver_chance>0) { map.put(setCount(TGItems.RECEIVER_STEEL, receiver), receiver_chance);}
		if(plate>0 && plate_chance>0) { map.put(setCount(TGItems.PLATE_STEEL, plate), plate_chance);}
		if(nugget>0 && nugget_chance>0) { map.put(setCount(TGItems.NUGGET_STEEL, nugget), nugget_chance);}
		return map;
	}

	private static HashMap<ItemStack, Double> drop_copper(int ingot, double ingot_chance,
													 int plate, double plate_chance,
													 int nugget, double nugget_chance){
		HashMap<ItemStack, Double> map = new HashMap<>();
		if(ingot>0 && ingot_chance>0) { map.put(setCount(TGItems.INGOT_COPPER, ingot), ingot_chance);}
		if(plate>0 && plate_chance>0) { map.put(setCount(TGItems.PLATE_COPPER, plate), plate_chance);}
		if(nugget>0 && nugget_chance>0) { map.put(setCount(TGItems.NUGGET_COPPER, nugget), nugget_chance);}
		return map;
	}

	private static HashMap<ItemStack, Double> drop_tin(int ingot, double ingot_chance,
												  int plate, double plate_chance){
		HashMap<ItemStack, Double> map = new HashMap<>();
		if(ingot>0 && ingot_chance>0) { map.put(setCount(TGItems.INGOT_TIN, ingot), ingot_chance);}
		if(plate>0 && plate_chance>0) { map.put(setCount(TGItems.PLATE_TIN, plate), plate_chance);}
		return map;
	}

	private static HashMap<ItemStack, Double> drop_lead(int ingot, double ingot_chance,
												   int plate, double plate_chance,
												   int nugget, double nugget_chance){
		HashMap<ItemStack, Double> map = new HashMap<>();
		if(ingot>0 && ingot_chance>0) { map.put(setCount(TGItems.INGOT_LEAD, ingot), ingot_chance);}
		if(plate>0 && plate_chance>0) { map.put(setCount(TGItems.PLATE_LEAD, plate), plate_chance);}
		if(nugget>0 && nugget_chance>0) { map.put(setCount(TGItems.NUGGET_LEAD, nugget), nugget_chance);}
		return map;
	}

	private static HashMap<ItemStack, Double> drop_bronze(int ingot, double ingot_chance,
													 int plate, double plate_chance){
		HashMap<ItemStack, Double> map = new HashMap<>();
		if(ingot>0 && ingot_chance>0) { map.put(setCount(TGItems.INGOT_BRONZE, ingot), ingot_chance);}
		if(plate>0 && plate_chance>0) { map.put(setCount(TGItems.PLATE_BRONZE, plate), plate_chance);}
		return map;
	}

	private static HashMap<ItemStack, Double> drop_plastic(int plastic, double plastic_chance,
													  int rubber, double rubber_chance){
		HashMap<ItemStack, Double> map = new HashMap<>();
		if(plastic>0 && plastic_chance>0) { map.put(setCount(TGItems.PLASTIC_SHEET, plastic), plastic_chance);}
		if(rubber>0 && rubber_chance>0) { map.put(setCount(TGItems.RUBBER_BAR, rubber), rubber_chance);}
		return map;
	}

	private static HashMap<ItemStack, Double> drop_electro(int board, double board_chance,
													  int board_advanced, double board_advanced_chance,
													  int motor, double motor_chance){
		HashMap<ItemStack, Double> map = new HashMap<>();
		if(board>0 && board_chance>0) { map.put(setCount(TGItems.INGOT_STEEL, board), board_chance);}
		if(board_advanced>0 && board_advanced_chance>0) { map.put(setCount(TGItems.RECEIVER_STEEL, board_advanced), board_advanced_chance);}
		if(motor>0 && motor_chance>0) { map.put(setCount(TGItems.PLATE_STEEL, motor), motor_chance);}
		return map;
	}

	private static HashMap<ItemStack, Double> drop_magazin(String type){
		HashMap<ItemStack, Double> map = new HashMap<>();
		switch(type){
			case "sniper":
				map.put(TGItems.AS50_MAGAZINE_EMPTY, 1.0);
				break;
			case "pistol":
				map.put(TGItems.PISTOL_MAGAZINE_EMPTY, 1.0);
				break;
			case "smg":
				map.put(TGItems.SMG_MAGAZINE_EMPTY, 1.0);
				break;
			case "ar":
				map.put(TGItems.ASSAULTRIFLE_MAGAZINE_EMPTY, 1.0);
				break;
			case "lmg":
				map.put(TGItems.LMG_MAGAZINE_EMPTY, 1.0);
				break;
			case "minigun":
				map.put(TGItems.MINIGUN_DRUM_EMPTY, 1.0);
				break;
			case "fuel":
				map.put(TGItems.FUEL_TANK_EMPTY, 1.0);
				break;
			case "default":
				map.put(TGItems.PISTOL_MAGAZINE_EMPTY, 1.0);
				break;
		}
		return map;
	}
}