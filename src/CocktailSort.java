public class CocktailSort extends SortingAlgorithm {
    public CocktailSort(int[] arr, Draw draw, Window window) {
        super(arr, draw, window);
    }

    @Override
    public void run() {
        start();

        //cocktail sort
        //not written by me, only modified in order to communicate which bars should be colored red
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < arr.length-1; i++) {
                if (stopped())
                    return;

                if (arr[i] > arr[i+1]) {
                    swap(arr, i, i+1);
                    swapped = true;

                    draw.changed.add(i);
                    draw.changed.add(i+1);
                    delayRepaint(TIME_BETWEEN_ACTION);
                }
            }

            for (int i = arr.length-1; i > 0; i--) {
                if (stop) {
                    System.err.println("Cocktail Sort is Stopped");
                    return;
                }

                if (arr[i-1] > arr[i]) {
                    swap(arr, i-1, i);
                    swapped = true;

                    draw.changed.add(i-1);
                    draw.changed.add(i);
                    delayRepaint(TIME_BETWEEN_ACTION);
                }
            }

        } while (swapped);

        done();
    }
}
