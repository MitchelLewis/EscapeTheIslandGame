package application;

public class AchievementController {
	public static boolean chainedRiddles = false;
	public static boolean noRiddlesWrong = true;
	public static boolean noHintsUsed = true;
	public static boolean level1Complete = false;
	public static boolean level2Complete = false;
	public static boolean level3Complete = false;
	public static boolean level4Complete = false;
	
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
}
