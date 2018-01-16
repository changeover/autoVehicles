package present;

import javafx.scene.control.Button;

/**
 * Created by matth on 16.01.2018.
 */
class GUIElements {
    static Button createButton(String text){
        Button button = new Button(text);
        return button;
    }
}
