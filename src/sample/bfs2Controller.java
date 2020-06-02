package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.LinkedList;

public class bfs2Controller {

    private Main main;

    void setMain(Main main) {
        this.main = main;
        Enter.setMouseTransparent(false);
    }

    public Circle CicrleTemp[];
    public Circle arrow[];
    public static Circle temp;
    LinkedList<Integer> list;
    public int sourceNode;

    bfsThread thread;

    @FXML
    private Circle circle0,circle1,circle2,circle3,circle4,circle5;

    @FXML // fx:id="enterButton"
    private Button Enter;

    @FXML
    private TextField EnterSourceText;

    @FXML
    void onEnterButton(ActionEvent event) {
        System.out.println("entered");

        sourceNode= Integer.parseInt(EnterSourceText.getText());

        thread=new bfsThread(6,sourceNode,this);
        //obj.start();

        addEdge();

        assignRef();

        assignEdgeRef();

    }



    void autoStart(){
        thread=new bfsThread(6,3,this);
        //obj.start();

        addEdge();

        assignRef();

        assignEdgeRef();
    }




    void setEDge(){
        arrow[0].setOpacity(0.0);
        arrow[1].setOpacity(0.0);
        arrow[2].setOpacity(0.0);
    }

    public void assignRef(){
        CicrleTemp = new Circle[6];
        CicrleTemp[0]=circle0;
        CicrleTemp[1]=circle1;
        CicrleTemp[2]=circle2;
        CicrleTemp[3]=circle3;
        CicrleTemp[4]=circle4;
        CicrleTemp[5]=circle5;
    }

    @FXML
    Circle arrow1,arrow2,arrow3,arrow4,arrow5;

    void assignEdgeRef(){
        arrow=new Circle[6];
        //arrow[0]=arrow1;
        arrow[1]=arrow1;
        arrow[2]=arrow2;
        arrow[3]=arrow3;
        arrow[4]=arrow4;
        arrow[5]=arrow5;

    }

    void addEdge(){
        thread.addEdge(1, 2);
        thread.addEdge(2, 1);
        thread.addEdge(1, 4);
        thread.addEdge(2, 5);
        thread.addEdge(5, 4);
        thread.addEdge(4, 2);
        thread.addEdge(3, 5);
        thread.addEdge(3, 0);
        thread.addEdge(0, 0);
    }

    /*@FXML
    void BfsRefreshClicked(){
        try {
            main.showBfsPage();
        }catch (Exception e){
            e.printStackTrace();
        }
    }*//////////////////////baki ase


    void circleRed(int i){
        temp=CicrleTemp[i];

        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);

        KeyValue kv1 = new KeyValue(temp.fillProperty(), Color.RED);
        KeyFrame kf1 = new KeyFrame(Duration.millis(700), kv1);
        timeline.getKeyFrames().add(kf1);
        timeline.play();
    }

    void createEdge(int i,int j){
        //Polyline temp=arrow[i];

        arrow[i].setOpacity(1);
        System.out.println("edge  "+i+j);


        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);

        KeyValue kv1 = new KeyValue(arrow[i].layoutXProperty(),CicrleTemp[j].getLayoutX());
        KeyValue kv2 = new KeyValue(arrow[i].layoutYProperty(),CicrleTemp[j].getLayoutY());

        KeyFrame kf1 = new KeyFrame(Duration.millis(900), kv1,kv2);
        timeline.getKeyFrames().add(kf1);
        timeline.play();

        timeline.setOnFinished(event -> {
            arrow[i].setLayoutX(CicrleTemp[i].getLayoutX());
            arrow[i].setLayoutY(CicrleTemp[i].getLayoutY());
            arrow[i].setOpacity(0.0);
        });


    }

}
