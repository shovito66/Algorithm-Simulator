package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class homeController {
    private Main main;

    void setMain(Main main) {
        this.main = main;
    }

    @FXML
    void bubblesimulationclicked() {
        try {
            main.showSortPage();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    private Button BfsButton;

    @FXML
    void BFSsimulationclicked() {
        try {
            main.showBfsPage();
            //System.out.println("Buble clicked");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert BfsButton != null : "fx:id=\"BfsButton\" was not injected: check your FXML file 'home.fxml'.";

    }
}
