package sample;

public class selectionThread extends Thread {
    int[] array;
    sample.SortController controller;
    Thread selection;
    int sSortedPart=0;

    //for play pause
    boolean suspendFlag;

    selectionThread(int [] arr, SortController c){
        //super();
        array=arr;
        controller=c;
        selection =new Thread(this);
        selection.start();
        //controller.assignRef();
    }

    @Override
    public void run() {
        System.out.println("SELECTION SORTING.......");

        int minIndex=0;//etay proti iteration e unsorted part er minimum value er index rakhbo.
        int temp;      //eta swap er jonno use krbo.
        int swap=0;

        //outer loop swap er kaj krbe.each iteration er por array er 0-i porjonto sorted thakbe.
        for (int i = 0; i <(array.length-1) ; i++) {

            minIndex=i; ///unsorted part er first element kei minimum dhore nisi.
            //controller.minValueRectangle(i);

            ///inner loop minimum value er index khuje dibe.start krbe i er porer index theke.
            for (int j =i+1 ; j <array.length ; j++) {
                controller.minValueRectangle(j);




                try {
                    synchronized (this){
                        while (suspendFlag){
                            wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }









                try { selection.sleep(100); } catch (InterruptedException e) { System.out.println("can'bubble sleep"); }

                if(array[j]<array[minIndex]){
                    minIndex=j;
/*
                    controller.minValueRectangle(minIndex);
                    try { selection.sleep(100); } catch (InterruptedException e) { System.out.println("can'bubble sleep"); }

                    controller.rectangleBackToNormal(j);
                    try { selection.sleep(200); } catch (InterruptedException e) { System.out.println("can'bubble sleep"); }
*/

                }

                controller.rectangleBackToNormal(j);
                try { selection.sleep(200); } catch (InterruptedException e) { System.out.println("can'bubble sleep"); }

            }
            controller.minValueRectangle(minIndex);
            try { selection.sleep(300); } catch (InterruptedException e) { System.out.println("can'bubble sleep"); }
            ////increasing decreasing o ekhan thekei kra jabe. ami arekta param pathailam jeta 1 hoile ei loop chalabe r 2 hoile arekta chalabe jetay < check krbo.



            //ekhane swap hbe
            if(array[minIndex]!=array[i]) {
                controller.swapmessage(array[i],array[minIndex]);
                swap++;

                //controller.changeColor(i,minIndex);
                controller.minValueRectangle(i);
                System.out.println("swapping "+array[i]+"\tand\t"+array[minIndex]);

                temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;

                controller.swapRectangle(i,minIndex);
                try { selection.sleep(1500); } catch (InterruptedException e) {
                    System.out.println("can'bubble sleep");
                }
                controller.swapRectangleRef(i,minIndex);
            }


            //try { selection.sleep(200); } catch (InterruptedException e) { System.out.println("can'bubble sleep"); }

            controller.setColorBack(i,minIndex);
            try { selection.sleep(200); } catch (InterruptedException e) {
                System.out.println("can'bubble sleep");
            }

            /*SortController.selectionSortedPart++;
            controller.sortedPartColor();*/
            sSortedPart++;
            controller.sortedPartColor(sSortedPart);

        }

        controller.completedMessage(swap);
        controller.onSelelctionEnded();
        controller.remainingPartColor();

    }

    synchronized void mySuspend(){
        suspendFlag=true;
    }

    synchronized void myResume(){
        suspendFlag=false;
        notify();
    }

}
