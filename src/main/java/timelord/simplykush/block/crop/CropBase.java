package timelord.simplykush.block.crop;

import net.minecraft.block.CropsBlock;
import net.minecraft.item.Item;

public class CropBase extends CropsBlock {
	
	public static int seedsGrassRate;
	public static int seedsCropAmount;
	public static int hempAmount;
	public static int budAmount;
	public static Item crop;
	public static String cropName;
	
	public CropBase (Properties properties) {
		super(properties);
	}
	
	public static int getSeedsCropAmount () {
		return seedsCropAmount;
	}
	
	public static int getSeedsGrassRate () {
		return seedsGrassRate;
	}
	
	public static int getHempAmount () {
		return hempAmount;
	}
	
	public static int getBudAmount () {
		return budAmount;
	}
	
	public static Item getCrop () {
		return crop;
	}
	
	public static String getCropName () {
		return cropName;
	}
	
	public static void setSeedsCropAmount (int seedsCropAmount) {
		CropBase.seedsCropAmount = seedsCropAmount;
	}
	
	public static void setSeedsGrassRate (int seedsGrassRate) {
		CropBase.seedsGrassRate = seedsGrassRate;
	}
	
	public static void setHempAmount (int hempAmount) {
		CropBase.hempAmount = hempAmount;
	}
	
	public static void setBudAmount (int budAmount) {
		CropBase.budAmount = budAmount;
	}
	
	public static void setCrop (Item crop) {
		CropBase.crop = crop;
	}
	
	public static void setCropName (String cropName) {
		CropBase.cropName = cropName;
	}
}
