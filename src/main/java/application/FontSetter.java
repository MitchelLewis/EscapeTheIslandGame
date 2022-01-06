package main.java.application;

import javafx.scene.Node;

/**
 * A class which sets the font size of all nodes.
 * 
 * @author Lewis/Harry
 *
 */
public class FontSetter {
	/**
	 * Sets the same font for all elements by appending the font size styling attribute
	 * to the elements current styling. The font size is derived from Main.
	 * 
	 * @param elements A vararg of Node's to set the styling of.
	 */
	public static void setFontForElements(Node... elements) {
		for(Node element: elements) {
			String currentStyle = element.getStyle();
			String newStyle = currentStyle + " -fx-font-size: " + Main.textSize +"pt;";
			element.setStyle(newStyle);
		}
	}
}
