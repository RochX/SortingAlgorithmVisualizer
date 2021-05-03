//steps for creating a new sorting algorithm class:
//1. create the class
//2. create its constructor, only thing in it is the call to super()
//3. add it to the SortsList enum (if needed)
//4. add it to the window class and in its constructor
//5. add it to the window class's switch and reset methods
//6. write the sorting algorithm
//      a. include shuffle()
//      b. include the stop exit
//      c. include done()

import java.util.Random;
public abstract class SortingAlgorithm implements Runnable {
    static int TIME_BETWEEN_ACTION = 5; //<-- this is in milliseconds
    boolean stop = false, outputted = false;

    int[] arr;
    Draw draw;
    Window window;
    public SortingAlgorithm(int[] arr, Draw draw, Window window) {
        this.arr = arr;
        this.draw = draw;
        this.window = window;
    }

    public void startAlgorithm() {
        Thread t = new Thread(this);
        t.start();
    }

    public void shuffle() {
        Random rand = new Random();

        for (int i = 0; i < arr.length; i++) {
            if (stop) {
                System.err.println("shuffle is Stopped");
                return;
            }

            int randInt = rand.nextInt(arr.length);
            swap(arr, i, randInt);

            draw.changed.clear();
            draw.changed.add(i);
            draw.changed.add(randInt);

            delayRepaint(TIME_BETWEEN_ACTION);
        }
        draw.changed.clear();
        delayRepaint(TIME_BETWEEN_ACTION);
    }

    void delayRepaint(int milli) {
        delayRepaint(milli, true);
    }

    void delayRepaint(int milli, boolean clear) {
        if (stop) {
            System.err.println("delayRepaint is Stopped");
            return;
        }

        window.repaint();
        try {
            Thread.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (clear)
            draw.changed.clear();
    }

    //starts algorithm
    void start() {
        draw.setText("Shuffling...");
        shuffle();

        delayRepaint(500);
        System.out.println("Running " + window.getCurrentSortingAlgo() + "...");
        draw.setText("Running " + window.getCurrentSortingAlgo() + "...");
    }

    //method to use when an algorithm ends
    void done() {
        if (!stop) {
            draw.setText("Done.");
            System.out.println("Done.");
        }
        window.repaint();
    }

    //when a function is stopped do everything in this function
    boolean stopped() {
        if (stop && !outputted) {
            draw.setText(window.getCurrentSortingAlgo() + " is Stopped");
            System.err.println(window.getCurrentSortingAlgo() + " is Stopped");
            outputted = true;
        }
        return stop;
    }

    void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    void stop() {
        stop = true;
    }
}
