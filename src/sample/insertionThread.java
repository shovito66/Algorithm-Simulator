package sample;

public class insertionThread extends Thread {
    int[] array;
    SortController controller;
    Thread insertion;
    //for play pause
    boolean suspendFlag;

    insertionThread(int [] arr, SortController c){
        //super();
        array=arr;
        controller=c;
        insertion =new Thread(this);
        insertion.start();
        //controller.assignRef();
    }


    @Override
    public void run() {
        int value=0,hole=0;
        Boolean notSwapped=true;
        int swap=0;

        ///outer loop ekbar iterate hobar por, array index 0-i porjonto sorted thake.
        for (int i = 1; i <array.length ; i++) {
            //controller.minValueRectangle(i);

            value=array[i];
            hole=i;
            System.out.println("value is now   "+value);

            ///inner loop diye hole ta last e koi ase seta ber kri r oitay value ta bosaya dei.
            while (hole>0 && array[hole-1]>value){





                try {
                    synchronized (this){
                        while (suspendFlag){
                            wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }









                notSwapped=false;
                //controller.minValueRectangle(i);

                controller.changeColor(hole,hole-1);

                controller.swapRectangle(hole,hole-1);
                controller.swapmessage(value,array[hole-1]);
                swap++;

                array[hole]=array[hole-1];
                System.out.println(array[hole-1]+" shifted right.");

                try { insertion.sleep(1500); } catch (InterruptedException e) {
                    System.out.println("can'bubble sleep");
                }

                controller.rectangleBackToNormal_2(hole,hole-1);
                controller.swapRectangleRef(hole,hole-1);

                //controller.insertionSortedPartColor(hole-1);
                try { insertion.sleep(200); } catch (InterruptedException e) {
                    System.out.println("can'bubble sleep");
                }

                hole--;

            }

            if (notSwapped){
                controller.insertedPartBackToNormal();
            }
            array[hole]=value;

            System.out.println();
        }

        controller.completedMessage(swap);
        controller.remainingPartColor();
        controller.onInsertionEnded();
    }

    synchronized void mySuspend(){
        suspendFlag=true;
    }

    synchronized void myResume(){
        suspendFlag=false;
        notify();
    }

}
