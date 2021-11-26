package application;

import javafx.scene.Node;

public class FontSetter {
	public static void setFontForElements(Node... elements) {
		for(Node element: elements) {
			String currentStyle = element.getStyle();
			String newStyle = currentStyle + " -fx-font-size: " + Main.textSize +"pt;";
			element.setStyle(newStyle);
		}
	}
}
