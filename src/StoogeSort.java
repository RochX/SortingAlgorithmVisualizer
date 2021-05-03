public class StoogeSort extends SortingAlgorithm {
    public StoogeSort(int[] arr, Draw draw, Window window) {
        super(arr, draw, window);
    }

    @Override
    public void run() {
        start();

        startStoogeSort();

        done();
    }

    public void startStoogeSort() {
        stoogeSort(arr, 0, arr.length-1);
    }
    //not written by me, only modified in order to communicate which bars should be colored red
    public void stoogeSort(int[] arr, int s, int e) {
        if (stopped())
            return;

        if (arr[s] > arr[e]) {
            swap(arr, s, e);

            draw.changed.add(s);
            draw.changed.add(e);
            delayRepaint(TIME_BETWEEN_ACTION);
        }

        if ((e - s + 1) > 2) {
            int t = (e - s + 1) / 3;
            stoogeSort(arr, s, e-t);
            stoogeSort(arr, s+t, e);
            stoogeSort(arr, s, e-t);
        }
    }

}
