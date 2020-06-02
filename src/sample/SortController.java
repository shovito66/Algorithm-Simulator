package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class SortController {
    //ei main variable r method scene set krte lage.
    private Main main;

    void setMain(Main main) {
        this.main = main;
    }

    //input niye first string e rakhbo. then oitare split kre main array te rakhbo.
    public int[] array;




    //public static int selectionSortedPart=0,bubbleSortedPart=0,insertionSortedPart=0;///koyta sorted hoye gese tar hishab rakhbe.

    @FXML
    private Label messageLabel;

    @FXML
    private TextField  arrayInput;//array input nibe.

    @FXML
    private Rectangle rectangle1,rectangle2,rectangle3,rectangle4,rectangle5,rectangle6,rectangle7,rectangle8;

    @FXML
    private Button arrayEnterButton,bubbleSortButton,SelectionSortButton,InsertionSortButton,refreshButton,Home,playButton,pauseButton,playAgainButton;


    private Rectangle rectangle[]=new Rectangle[8];

    ////////FOR NEW CODE
    int inputtedArrayLength;
    int[] inputtedArray={0,0,0,0,0,0,0,0};
    double scalingRatio;
    int sortRequest;//1 for bubble,2 for selction,3 for insertion.

    /////thread class objects.
    bubbleThread bubbleThreadObject;
    selectionThread selectionThreadObject;
    insertionThread insertionThreadObject;



    ////rectangle array te ref assign kre.
    void assignRef(){
        rectangle[0]=rectangle1;
        rectangle[1]=rectangle2;
        rectangle[2]=rectangle3;
        rectangle[3]=rectangle4;
        rectangle[4]=rectangle5;
        rectangle[5]=rectangle6;
        rectangle[6]=rectangle7;
        rectangle[7]=rectangle8;

    }

    ///sort button disable,enable kre.
    void setButtonState(Boolean value){
        bubbleSortButton.setDisable(value);
        SelectionSortButton.setDisable(value);
        InsertionSortButton.setDisable(value);
    }

    //ARRAY INITIALIZE KRBE, RECTANGLE ER REF SET KRBE.

    ///sortpage set kre.
    void setThings(){
        ///////old part.
        /*array=new int[8];
        assignRef();
        messageLabel.setTextFill(Color.GRAY);
        //rectangle1.setFill(Color.RED);

        //button disable kre rakhsi as akhono array input dey nai.
        setButtonState(true);
        bubbleSortedPart=0;
        selectionSortedPart=0;
        insertionSortedPart=0;*/

///////new part
        assignRef();///rectangle array te reference assign kre.
        setButtonState(true);//button disable kre rakhsi as akhono array input dey nai.

        ///height 0 krle invisible hoye jacce.

        for (int i = 0; i <8 ; i++) {
            rectangle[i].setHeight(0.0);
        }

        bubbleSortButton.setTextFill(Color.LIME);
        SelectionSortButton.setTextFill(Color.LIME);
        InsertionSortButton.setTextFill(Color.LIME);

    }


    ////array enter krle strings array te load kre.
    @FXML
    void onArrayEnterClicked() {

        String string=arrayInput.getText();

        ///ekhane wild input handle krte hbe.

        try {


            String[] strings= string.split(",");

            ///inputtedArray te niye rakhtesi jetar lenngth age theke bole disi 8.
            ///play again er kaj krar jonno eta rakhsi.
            inputtedArrayLength =strings.length;
            if (inputtedArrayLength>8){messageLabel.setText("MAXIMUM 8 ELEMENT ARRAY");}
            else {
                for (int i = 0; i < inputtedArrayLength; i++) {
                    inputtedArray[i] = Integer.parseInt(strings[i]);
                }

                ///jei array tare sort krum setay inputted tare copy krsi.
                array = new int[inputtedArrayLength];
                for (int i = 0; i < array.length; i++) {
                    array[i] = inputtedArray[i];
                }
                success();//Entered Successfully print krbe.

                int max = arrayMAXelement(array);
                //int min=arrayMINelement(array);

                scalingRatio = 570.0 / max;//eta use kre relative height create kri.

                for (int i = 0; i < array.length; i++) {
                    setRectangleThings(i);///rectangle form kre.
                }

                setButtonState(false);///sort button enable kre.

            }


        } catch (NumberFormatException e) {
            errrorMessage();
        }


    }

    private void success(){
        messageLabel.setText("Entered Successfully");
        messageLabel.setTextFill(Color.DARKBLUE);
    }

    private void errrorMessage(){
        messageLabel.setText("Please Enter in 10,20,30  FORMAT");
        messageLabel.setTextFill(Color.RED);
    }

    private void setRectangleThings(int i){
        rectangle[i].setFill(Color.rgb(185, 247, 0));
        rectangle[i].setLayoutY(600-array[i]*scalingRatio);
        rectangle[i].setHeight(array[i]*scalingRatio);
    }

    /////setRectangleThings puran ta.
    /*private void setRectangleThings(int i){
        rectangle[i].setFill(Color.rgb(185, 247, 0));
        rectangle[i].setLayoutY(600-array[i]*10);
        rectangle[i].setHeight(array[i]*10);
    }*/

    private int arrayMAXelement(int[] arr){
        int max=arr[0];
        for (int i = 1; i <arr.length ; i++) {
            if (arr[i]>max)max=arr[i];
        }
        return max;
    }

    ///////swap krar message dey.
    void  swapmessage(int x,int y){
        Platform.runLater(() -> {
                    messageLabel.setText("Swapping "+x+"  and  "+y);
                    messageLabel.setTextFill(Color.ORANGE);
                }
        );
    }

    ///sort complete krar message dey.
    void completedMessage(int x){
        Platform.runLater(() -> {
                    messageLabel.setText("SORTED IN  "+x+"  swaps");
                    messageLabel.setTextFill(Color.ORANGE);
                }
        );
    }



    void swapRectangleRef(int i, int j){
        Rectangle temp=rectangle[i];
        rectangle[i]=rectangle[j];
        rectangle[j]=temp;
    }

    void changeColor(int i,int j){
        rectangle[i].setFill(Color.RED);
        rectangle[j].setFill(Color.RED);
    }

    void setColorBack(int i,int j){
        rectangle[i].setFill(Color.rgb(185, 247, 0));
        rectangle[j].setFill(Color.rgb(185, 247, 0));

    }

    ////rectangle swap kre timeline use kre.
    void swapRectangle(int i,int j){
        //System.out.println(rectangle[i]+"\t"+rectangle[j]);


        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setAutoReverse(false);
        KeyValue kv = new KeyValue(rectangle[i].layoutXProperty(),rectangle[j].getLayoutX());
        KeyFrame kf = new KeyFrame(Duration.millis(700), kv);
        timeline.getKeyFrames().add(kf);



        Timeline timeline2 = new Timeline();
        timeline2.setCycleCount(1);
        timeline2.setAutoReverse(false);
        KeyValue kv2 = new KeyValue(rectangle[j].layoutXProperty(), rectangle[i].getLayoutX());
        KeyFrame kf2 = new KeyFrame(Duration.millis(700), kv2);
        timeline2.getKeyFrames().add(kf2);
        timeline.play();
        timeline2.play();

    }

    //last e kontay kon reference thake oita print kre.


    ///sort krar loop theke ber hobar por, run method sesh hbar age eta ekbar call kre sob blue kre dibo.
    void remainingPartColor(){
        for (Rectangle temp:rectangle) {
            if(temp.getFill()!=Color.BLUE)temp.setFill(Color.BLUE);
        }
    }


    @FXML
    void onBubbleClicked(){
        sortRequest=1;//bujhbe j bubble sort start hoie. play pause e lagbe.
        bubbleSortButton.setTextFill(Color.RED);
        SelectionSortButton.setDisable(true);
        InsertionSortButton.setDisable(true);

        //Thread obj=new bubbleThread(array,this);

        bubbleThreadObject=new bubbleThread(array,this);
        //bubbleThreadObject.bubble.wait();
    }

    void bubbleSortedPartColor(int part){
        int start=450+(inputtedArrayLength-1)*100;


        double xPos=start-((part-1)*100);
        for (Rectangle temp:rectangle) {
            if (temp.getLayoutX()==xPos)temp.setFill(Color.BLUE);
        }
        System.out.println(part);


    }




    ////selection sort dekhano.
    @FXML
    void onSelectionClicked(){
        sortRequest=2;//bujhbe j bubble sort start hoie. play pause e lagbe.
        SelectionSortButton.setTextFill(Color.RED);
        bubbleSortButton.setDisable(true);
        InsertionSortButton.setDisable(true);

        selectionThreadObject=new selectionThread(array,this);
    }

    void minValueRectangle(int i){
        rectangle[i].setFill(Color.RED);
    }


    void rectangleBackToNormal(int i){
        rectangle[i].setFill(Color.rgb(185, 247, 0));
    }


    void sortedPartColor(int part){

        double xPos=450+((part-1)*100);
        for (Rectangle temp:rectangle) {
            if (temp.getLayoutX()==xPos)temp.setFill(Color.BLUE);
        }

    }

    @FXML
    void onInsertionClicked(){
        sortRequest=3;//bujhbe j bubble sort start hoie. play pause e lagbe.
        InsertionSortButton.setTextFill(Color.RED);
        bubbleSortButton.setDisable(true);
        SelectionSortButton.setDisable(true);

        //Thread obj3=new insertionThread(array,this);
        //obj.start();
        insertionThreadObject=new insertionThread(array,this);
    }

    void insertedPartBackToNormal(){

        for (Rectangle temp:rectangle) {
            if(temp.getFill()!=Color.RED)temp.setFill(Color.rgb(185, 247, 0));
        }

    }

    void rectangleBackToNormal_2(int i,int j) {
        rectangle[i].setFill(Color.rgb(185, 247, 0));
        rectangle[j].setFill(Color.rgb(185, 247, 0));
    }


    @FXML
    void onRefreshClicked(){
        try {
            main.showSortPage();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void HomeClicked(){
        try {
            main.showHomePage();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /////SORT SESH HOLE BUTTON COLOUR ABAR AGER JAGAY NIYE JAY.
    void onBubbleEnded(){ bubbleSortButton.setTextFill(Color.LIME); }

    void onInsertionEnded(){ InsertionSortButton.setTextFill(Color.LIME); }

    void onSelelctionEnded(){
        SelectionSortButton.setTextFill(Color.LIME);
    }


    @FXML
    void onPlayAgainClicked(ActionEvent event) {
        onArrayEnterClicked();
    }



    //////////KAJ HOY NAI.

    @FXML
    void onPlayClicked(ActionEvent event) {
        System.out.println("clicked play");
        //bubbleThreadObject.bubble.suspend();
        if (sortRequest==1) bubbleThreadObject.myResume();
        else if (sortRequest==2)selectionThreadObject.myResume();
        else insertionThreadObject.myResume();
    }

    @FXML
    void onPauseClicked(ActionEvent event) {
        messageLabel.setText("PAUSED");
        messageLabel.setTextFill(Color.DARKBLUE);
        System.out.println("clicked pause");
        //bubbleThreadObject.bubble.stop();
        if (sortRequest==1) bubbleThreadObject.mySuspend();
        else if (sortRequest==2)selectionThreadObject.mySuspend();
        else insertionThreadObject.mySuspend();
    }

}
