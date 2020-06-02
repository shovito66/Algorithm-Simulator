package sample;

public class bubbleThread extends Thread {
    int[] array;
    SortController controller;
    Thread bubble;
    int bSortedPart=0;

    //for play pause
    boolean suspendFlag;

    bubbleThread(int [] arr, SortController c){
        //super();
        array=arr;
        controller=c;
        bubble =new Thread(this);
        bubble.start();
        //controller.assignRef();
        //for play pause
        suspendFlag=false;
    }
    @Override
    public void run() {

        System.out.println("run krse re mama");

        int temp=0;
        int n=array.length-1;
        System.out.println(n);
        boolean notSwapped;
        int swap=0;


        ///outer loop array length er cheye duibar kom chalaite hoy.
        while(n>=0) {
            notSwapped=true;
            System.out.println("sort loop eo dhuksi re mama");

            //inner loop 0-n porjonto chole but n protibar 1 kome.
            for (int i = 0; i < n; i++) {

                //System.out.println("ïnner loop e dhuksi");

                controller.changeColor(i,i+1);

                //for play pause
                ////cholar moto,ektu pore stop hoy. bt kaj kre.
                try {
                    synchronized (this){
                        while (suspendFlag){
                            wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                if (array[i + 1] < array[i]) {

                    System.out.println("swapping");
                    controller.swapmessage(array[i],array[i+1]);//illegalStateException khay
                    swap++;

                    temp = array[i + 1];
                    array[i + 1] = array[i];
                    array[i] = temp;

                    notSwapped=false;

                    /////////////////////////////////////////

                    ///this line does bubble run.
                    controller.swapRectangle(i,i+1);
                    try { bubble.sleep(1500); } catch (InterruptedException e) {
                        System.out.println("can'bubble sleep");
                    }
                    controller.swapRectangleRef(i,(i+1));

                }
                try { bubble.sleep(200); } catch (InterruptedException e) {
                    System.out.println("can'bubble sleep");
                }
                controller.setColorBack(i,i+1);
                try { bubble.sleep(200); } catch (InterruptedException e) {
                    System.out.println("can'bubble sleep");
                }

            }


            bSortedPart++;
            controller.bubbleSortedPartColor(bSortedPart);

            n--;
            if (notSwapped){
                controller.remainingPartColor();
                break;
            }

        }

        controller.completedMessage(swap);
        controller.remainingPartColor();


        controller.onBubbleEnded();

    }

    synchronized void mySuspend(){
        suspendFlag=true;
    }

    synchronized void myResume(){
        suspendFlag=false;
        notify();
    }
}
