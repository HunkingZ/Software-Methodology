package SceneB;

import Management.*;
import SceneA.SceneAController;

public class SceneBController {

    public SceneAController controllerA;
    Order database;
    public void getDatabase(Order database) {
        this.database = database;
    }

    public void setController(SceneAController controller) {
        controllerA = controller;
    }
}
