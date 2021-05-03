public class QuickSort extends SortingAlgorithm {
    public QuickSort(int[] arr, Draw draw, Window window) {
        super(arr, draw, window);
    }

    @Override
    public void run() {
        start();

        startQuickSort();

        done();
    }

    public void startQuickSort() {
        quickSort(arr, 0, arr.length-1);
    }

    //these two functions are part of the standard quick sort algorithm and I did not come up with them myself
    private void quickSort(int[] arr, int low, int high) {
        if (stopped())
            return;

        if (low < high) {
            int par = partition(arr, low, high);
            quickSort(arr, low, par - 1);
            quickSort(arr, par + 1, high);
        }
    }
    //the partition function was modified in order to communicate which bars to make red
    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low;
        for (int j = low; j < high; j++) {
            if (stopped())
                return -1;

            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
                draw.changed.add(i);
            }
            draw.changed.add(j);
            delayRepaint(TIME_BETWEEN_ACTION);
        }

        swap(arr, i, high);
        draw.changed.add(high);
        draw.changed.add(i);
        delayRepaint(TIME_BETWEEN_ACTION);
        return i;
    }
}
