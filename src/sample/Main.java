package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        stage = primaryStage;
        showHomePage();
    }

    public void showHomePage()throws Exception{
        //loading xml
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("home.fxml"));
        Parent root=loader.load();

        //loading controller class
        sample.homeController controller=loader.getController();
        controller.setMain(this);

        //set the primary stage
        stage.setTitle("ALGORITHM SIMULATOR");
        stage.setScene(new Scene(root,1355,700));
        //stage.setScene(new Scene(root,1400,900));
        stage.show();
    }

    public void showSortPage()throws Exception{
        //loading xml
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("Sort.fxml"));
        Parent root=loader.load();

        //loading controller class
        SortController controller=loader.getController();
        controller.setMain(this);

        //ARRAY INITIALIZE KRBE, RECTANGLE ER REF SET KRBE.
        controller.setThings();

        //set the primary stage
        stage.setTitle("SORTING ALGORITHM SIMULATION");
        stage.setScene(new Scene(root,1355,700));
        //stage.setScene(new Scene(root,1400,900));
        stage.show();
    }

    public void showBfsPage() throws Exception{

        //loading xml
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml.fxml"));
        Parent root=loader.load();

        //loading controller class
        FxmlController controller=loader.getController();
        controller.setMain(this);

        //set the primary stage
        stage.setTitle("BREADTH FIRST SEARCH");
        stage.setScene(new Scene(root,1355,700));
        //stage.setScene(new Scene(root,1400,900));
        stage.show();

    }

    public void showBfsPage2() throws Exception{

        //loading xml
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("Bfs2.fxml"));
        Parent root=loader.load();

        //loading controller class
        bfs2Controller controller=loader.getController();
        controller.setMain(this);
        controller.autoStart();

        //set the primary stage
        stage.setTitle("BREADTH FIRST SEARCH");
        stage.setScene(new Scene(root,1355,700));
        //stage.setScene(new Scene(root,1400,900));
        stage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
