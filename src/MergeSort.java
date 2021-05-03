public class MergeSort extends SortingAlgorithm {
    public MergeSort(int[] arr, Draw draw, Window window) {
        super(arr, draw, window);
    }

    @Override
    public void run() {
        start();

        startMergeSort();

        done();
    }

    public void startMergeSort() {
        mergeSort(arr,0,arr.length-1);
    }

    //recursion part of the merge sort algorithm
    //this was not written by me
    private void mergeSort(int[] arr, int left, int right) {
        if (stopped())
            return;

        if (left < right) {
            int middle = (left + right) / 2;

            //the middle goes into the left sub array
            mergeSort(arr, left, middle);
            mergeSort(arr, middle+1, right);

            merge(arr, left, middle, right);
        }
    }

    //merges two arrays together
    //this was not written by me, only modified in order to communicate what bars should be colored red
    private void merge(int[] arr, int left, int middle, int right) {
        if (stopped())
            return;

        //find the sizes of the two arrays
        //the middle goes into the left sub array
        int[] leftArr = new int[middle - left + 1];
        int[] rightArr = new int[right - middle];

        //assign the values from the main array to the left and right sub arrays
        for (int i = 0; i < leftArr.length; i++) {
            leftArr[i] = arr[left + i];

            draw.changed.add(left + i);
            delayRepaint(TIME_BETWEEN_ACTION);
        }
        for (int j = 0; j < rightArr.length; j++) {
            rightArr[j] = arr[middle + 1 + j];

            draw.changed.add(middle + 1 + j);
            delayRepaint(TIME_BETWEEN_ACTION);
        }

        //indices of the left and right sub arrays
        int i = 0, j = 0;

        //position in the main array
        int k = left;

        //merge left and right sub arrays
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;

            draw.changed.add(k);
            delayRepaint(TIME_BETWEEN_ACTION);
        }

        //copy what is remaining
        while (i < leftArr.length) {
            arr[k] = leftArr[i];
            i++;
            k++;

            draw.changed.add(k);
            delayRepaint(TIME_BETWEEN_ACTION);
        }
        while (j < rightArr.length) {
            arr[k] = rightArr[j];
            j++;
            k++;

            draw.changed.add(k);
            delayRepaint(TIME_BETWEEN_ACTION);
        }
    }
}
