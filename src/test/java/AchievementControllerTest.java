//Created by Lewis/Harry
package test.java;

import static org.junit.Assert.*;

import main.java.application.AchievementController;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javafx.scene.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.layout.*;

public class AchievementControllerTest {
	
	@Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();

	@Test
	public void setLevelCompleteTest() {
		AchievementController.setLevelComplete(0);
		assertTrue(AchievementController.level1Complete);
		AchievementController.setLevelComplete(1);
		assertTrue(AchievementController.level2Complete);
		AchievementController.setLevelComplete(2);
		assertTrue(AchievementController.level3Complete);
		AchievementController.setLevelComplete(3);
		assertTrue(AchievementController.level4Complete);
	}
	
	@Test
	public void resetAchievementsTest() {
		AchievementController.setLevelComplete(0);
		AchievementController.setLevelComplete(1);
		AchievementController.setLevelComplete(2);
		AchievementController.setLevelComplete(3);
		AchievementController.resetAchievements();
		assertFalse(AchievementController.level1Complete);
		assertFalse(AchievementController.level2Complete);
		assertFalse(AchievementController.level3Complete);
		assertFalse(AchievementController.level4Complete);
		assertTrue(AchievementController.noHintsUsed);
		assertTrue(AchievementController.noRiddlesWrong);
		assertFalse(AchievementController.chainedRiddles);
	}

	@Test
	public void setAchievementsUnlockedTest() {
		AnchorPane root = new AnchorPane();
		javafx.scene.image.ImageView level1Achievement = new javafx.scene.image.ImageView();
		level1Achievement.setId("level1Achievement");
		javafx.scene.image.ImageView level2Achievement = new javafx.scene.image.ImageView();
		level2Achievement.setId("level2Achievement");
		javafx.scene.image.ImageView level3Achievement = new javafx.scene.image.ImageView();
		level3Achievement.setId("level3Achievement");
		javafx.scene.image.ImageView level4Achievement = new javafx.scene.image.ImageView();
		level4Achievement.setId("level4Achievement");
		javafx.scene.image.ImageView noRiddlesWrongAchievement = new javafx.scene.image.ImageView();
		noRiddlesWrongAchievement.setId("noRiddlesWrongAchievement");
		javafx.scene.image.ImageView noHintsUsedAchievement = new javafx.scene.image.ImageView();
		noHintsUsedAchievement.setId("noHintsUsedAchievement");
		javafx.scene.image.ImageView chainedRiddlesAchievement = new javafx.scene.image.ImageView();
		chainedRiddlesAchievement.setId("chainedRiddlesAchievement");
		ColorAdjust colorAdjustForChainedRiddles = new ColorAdjust();
		colorAdjustForChainedRiddles.setBrightness(-0.75);
		chainedRiddlesAchievement.setEffect(colorAdjustForChainedRiddles);
		
		root.getChildren().add(level1Achievement);
		root.getChildren().add(level2Achievement);
		root.getChildren().add(level3Achievement);
		root.getChildren().add(level4Achievement);
		root.getChildren().add(noRiddlesWrongAchievement);
		root.getChildren().add(noHintsUsedAchievement);
		root.getChildren().add(chainedRiddlesAchievement);
		AchievementController.setLevelComplete(0);
		AchievementController.setLevelComplete(1);
		AchievementController.setLevelComplete(2);
		AchievementController.setLevelComplete(3);
		AchievementController.setAchievementsUnlocked(root);

		ColorAdjust level1Effect = (ColorAdjust) root.lookup("#level1Achievement").getEffect();
		ColorAdjust level2Effect = (ColorAdjust) root.lookup("#level2Achievement").getEffect();
		ColorAdjust level3Effect = (ColorAdjust) root.lookup("#level3Achievement").getEffect();
		ColorAdjust level4Effect = (ColorAdjust) root.lookup("#level4Achievement").getEffect();
		ColorAdjust noRiddlesWrongAchievementEffect = (ColorAdjust) root.lookup("#noRiddlesWrongAchievement").getEffect();
		ColorAdjust noHintsUsedAchievementEffect = (ColorAdjust) root.lookup("#noHintsUsedAchievement").getEffect();
		ColorAdjust chainedRiddlesAchievementEffect = (ColorAdjust) root.lookup("#chainedRiddlesAchievement").getEffect();
		assertEquals(0, level1Effect.getBrightness(), 0);
		assertEquals(0, level2Effect.getBrightness(), 0);
		assertEquals(0, level3Effect.getBrightness(), 0);
		assertEquals(0, level4Effect.getBrightness(), 0);
		assertEquals(0, noRiddlesWrongAchievementEffect.getBrightness(), 0);
		assertEquals(0, noHintsUsedAchievementEffect.getBrightness(), 0);
		assertEquals(-0.75, chainedRiddlesAchievementEffect.getBrightness(), 0);
	}
}
