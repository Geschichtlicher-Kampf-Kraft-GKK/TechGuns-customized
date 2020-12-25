package techguns.plugins.crafttweaker;

import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.Item;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import techguns.entities.npcs.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

@ZenClass("mods.techguns.mobs")
public class MobTweaker {

    @ZenMethod
    public static void setWeapon(String entity, IItemStack... list){
        ArrayList<Item> weapons = Arrays.stream(list).map(tmp -> CraftTweakerMC.getItemStack(tmp).getItem()).distinct().collect(Collectors.toCollection(ArrayList::new));
        switch (entity){
            case "bandit":
                Bandit.changeWeapon(weapons);
            case "armysoldier":
                ArmySoldier.changeWeapon(weapons);
            case "cyberdemon":
                CyberDemon.changeWeapon(weapons);
            case "dictatordave":
                DictatorDave.changeWeapon(weapons);
            case "outcast":
                Outcast.changeWeapon(weapons);
            case "psychosteve":
                PsychoSteve.changeWeapon(weapons);
            case "stormtrooper":
                StormTrooper.changeWeapon(weapons);
            case "supermutantbasic":
                SuperMutantBasic.changeWeapon(weapons);
            case "supermutantheavy":
                SuperMutantHeavy.changeWeapon(weapons);
            case "supermutantelite":
                SuperMutantElite.changeWeapon(weapons);
            default:
        }
    }

    @ZenMethod
    public static void setData(String entity, int healthy, int damage, double range, double speed, double armor, boolean isFireProof){
        switch (entity){
            case "bandit":
                Bandit.changeData(healthy, damage, range, speed, armor, isFireProof);
                break;
            case "armysoldier":
                ArmySoldier.changeData(healthy, damage, range, speed, armor, isFireProof);
                break;
            case "cyberdemon":
                CyberDemon.changeData(healthy, damage, range, speed, armor, isFireProof);
                break;
            case "dictatordave":
                DictatorDave.changeData(healthy, damage, range, speed, armor, isFireProof);
                break;
            case "outcast":
                Outcast.changeData(healthy, damage, range, speed, armor, isFireProof);
                break;
            case "psychosteve":
                PsychoSteve.changeData(healthy, damage, range, speed, armor, isFireProof);
                break;
            case "stormtrooper":
                StormTrooper.changeData(healthy, damage, range, speed, armor, isFireProof);
                break;
            case "supermutantbasic":
                SuperMutantBasic.changeData(healthy, damage, range, speed, armor, isFireProof);
                break;
            case "attackhelicopter":
                AttackHelicopter.changeData(healthy, range, 8.0D, armor, isFireProof);
            case "supermutantheavy":
                SuperMutantHeavy.changeData(healthy, damage, range, speed, armor, isFireProof);
                break;
            case "supermutantelite":
                SuperMutantElite.changeData(healthy, damage, range, speed, armor, isFireProof);
                break;
            default:
        }
    }

}
