public class InsertionSort extends SortingAlgorithm {
    public InsertionSort(int[] arr, Draw draw, Window window) {
        super(arr, draw, window);
    }

    @Override
    public void run() {
        start();

        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (stopped())
                    return;

                if (arr[j-1] > arr[j])
                    swap(arr, j, j-1);
                else
                    break;

                draw.changed.add(j);
                draw.changed.add(j+1);
                delayRepaint(TIME_BETWEEN_ACTION);
            }
        }

        done();
    }
}
