package main.java.application;

import javafx.scene.Parent;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;

/**
 * This controller will track the users achievements throughout the game via
 * static attributes that are set throughout the game.
 * 
 * @author Lewis/Harry
 *
 */
public class AchievementController {
	public static int amountOfRiddlesAnsweredInARow = 0;
	public static boolean chainedRiddles = false;
	public static boolean noRiddlesWrong = true;
	public static boolean noHintsUsed = true;
	public static boolean level1Complete = false;
	public static boolean level2Complete = false;
	public static boolean level3Complete = false;
	public static boolean level4Complete = false;
	
	/**
	 * Sets which level has been completed. Otherwise it throws an IllegalArgumentException.
	 * 
	 * @param level Level number (zero-indexed)
	 */
	public static void setLevelComplete(int level) {
		switch(level) {
			case 0: 
				level1Complete = true;
				break;
			case 1: 
				level2Complete = true;
				break;
			case 2: 
				level3Complete = true;
				break;
			case 3: 
				level4Complete = true;
				break;
			default: throw new IllegalArgumentException("level is not valid");
		}
	}
	
	/**
	 * This is used on the game win and game over screen and sets the achievements
	 * visibility accordingly, if an achievement has been unlocked then the image
	 * relating to that achievement is made fully visible and a tooltip is installed for context.
	 * 
	 * @param root The game window itself
	 */
	public static void setAchievementsUnlocked(Parent root) {
		ImageView level1Achievement = (ImageView) root.lookup("#level1Achievement");
        Tooltip t = new Tooltip("Level 1 complete");
        Tooltip.install(level1Achievement, t);
		ImageView level2Achievement = (ImageView) root.lookup("#level2Achievement");
        t = new Tooltip("Level 2 complete");
        Tooltip.install(level2Achievement, t);
		ImageView level3Achievement = (ImageView) root.lookup("#level3Achievement");
        t = new Tooltip("Level 3 complete");
        Tooltip.install(level3Achievement, t);
		ImageView level4Achievement = (ImageView) root.lookup("#level4Achievement");
		t = new Tooltip("Level 4 complete");
        Tooltip.install(level4Achievement, t);
		ImageView noRiddlesWrongAchievement = (ImageView) root.lookup("#noRiddlesWrongAchievement");
		t = new Tooltip("Perfect score - no riddles wrong!");
	    Tooltip.install(noRiddlesWrongAchievement, t);
		ImageView noHintsUsedAchievement = (ImageView) root.lookup("#noHintsUsedAchievement");
        t = new Tooltip("No hints used");
        Tooltip.install(noHintsUsedAchievement, t);
		ImageView chainedRiddlesAchievement = (ImageView) root.lookup("#chainedRiddlesAchievement");
        t = new Tooltip("Answer 3 riddles in a row");
        Tooltip.install(chainedRiddlesAchievement, t);
		ColorAdjust achievedEffect = new ColorAdjust();
		achievedEffect.setBrightness(0);
		if(AchievementController.level1Complete) {
			level1Achievement.setEffect(achievedEffect);			
		}
		if(AchievementController.level2Complete) {
			level2Achievement.setEffect(achievedEffect);
		}
		if(AchievementController.level3Complete) {
			level3Achievement.setEffect(achievedEffect);
		}
		if(AchievementController.level4Complete) {
			level4Achievement.setEffect(achievedEffect);
		}
		if(AchievementController.noRiddlesWrong) {
			noRiddlesWrongAchievement.setEffect(achievedEffect);
		}
		if(AchievementController.noHintsUsed) {
			noHintsUsedAchievement.setEffect(achievedEffect);
		}
		if(AchievementController.chainedRiddles) {
			chainedRiddlesAchievement.setEffect(achievedEffect);
		}
	}
	
	/**
	 * Resets the state of all achievements, primarily used when trying again.
	 * 
	 */
	public static void resetAchievements() {
		chainedRiddles = false;
		noRiddlesWrong = true;
		noHintsUsed = true;
		level1Complete = false;
		level2Complete = false;
		level3Complete = false;
		level4Complete = false;
		
	}
}
