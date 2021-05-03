public class BubbleSort extends SortingAlgorithm {
    public BubbleSort(int[] arr, Draw draw, Window window) {
        super(arr, draw, window);
    }

    @Override
    public void run() {
        start();

        //standard bubble sort
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j+1 < arr.length-i; j++) {
                if (stopped())
                    return;

                if (arr[j] > arr[j+1])
                    swap(arr, j, j+1);

                draw.changed.add(j);
                draw.changed.add(j+1);
                delayRepaint(TIME_BETWEEN_ACTION);
            }
        }

        done();
    }
}
