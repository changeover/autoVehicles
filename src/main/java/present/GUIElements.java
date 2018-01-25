package present;

import javafx.scene.control.Button;

/**
 * @author Matthias Meychtry
 */
class GUIElements {
    static Button createButton(String text){
        Button button = new Button(text);
        return button;
    }
}
