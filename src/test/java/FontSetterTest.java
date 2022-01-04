//Created by Lewis/Harry
package test.java;

import static org.junit.Assert.*;

import main.java.application.FontSetter;
import org.junit.*;
import javafx.scene.control.*;

public class FontSetterTest {
	
	@Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
	
	@Test
	public void setFontForElementsTest() {
		Label lbl1 = new Label();
		Label lbl2 = new Label();
		Label lbl3 = new Label();

		FontSetter.setFontForElements(lbl1, lbl2, lbl3);
		assertTrue(lbl1.getStyle().equals(" -fx-font-size: 16pt;"));
		assertTrue(lbl2.getStyle().equals(" -fx-font-size: 16pt;"));
		assertTrue(lbl3.getStyle().equals(" -fx-font-size: 16pt;"));
	}

}
