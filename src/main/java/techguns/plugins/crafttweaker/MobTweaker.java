package techguns.plugins.crafttweaker;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import techguns.entities.npcs.*;

import java.util.ArrayList;
import java.util.Arrays;

@ZenClass("mods.techguns.mob")
public class MobTweaker {

    @ZenMethod
    public static void setWeapon(String entity, Item... list){
        ArrayList<Item> weapons = new ArrayList<>(Arrays.asList(list));
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
    public static void setData(String entity, int healthy, int damage, double range, double speed, double armor, boolean ifFireProof){
        switch (entity){
            case "bandit":
                Bandit.changeData(healthy, damage, range, speed, armor, ifFireProof);
                break;
            case "armysoldier":
                ArmySoldier.changeData(healthy, damage, range, speed, armor, ifFireProof);
                break;
            case "cyberdemon":
                CyberDemon.changeData(healthy, damage, range, speed, armor, ifFireProof);
                break;
            case "dictatordave":
                DictatorDave.changeData(healthy, damage, range, speed, armor, ifFireProof);
                break;
            case "outcast":
                Outcast.changeData(healthy, damage, range, speed, armor, ifFireProof);
                break;
            case "psychosteve":
                PsychoSteve.changeData(healthy, damage, range, speed, armor, ifFireProof);
                break;
            case "stormtrooper":
                StormTrooper.changeData(healthy, damage, range, speed, armor, ifFireProof);
                break;
            case "supermutantbasic":
                SuperMutantBasic.changeData(healthy, damage, range, speed, armor, ifFireProof);
                break;
            case "attackhelicopter":
                AttackHelicopter.changeData(healthy, range, 8.0D, armor, ifFireProof);
            case "supermutantheavy":
                SuperMutantHeavy.changeData(healthy, damage, range, speed, armor, ifFireProof);
                break;
            case "supermutantelite":
                SuperMutantElite.changeData(healthy, damage, range, speed, armor, ifFireProof);
                break;
            default:
        }
    }

}
