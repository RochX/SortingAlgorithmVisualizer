public class SelectionSort extends SortingAlgorithm {
    public SelectionSort(int[] arr, Draw draw, Window window) {
        super(arr, draw, window);
    }

    @Override
    public void run() {
        start();

        int bestIndex;
        for (int i = 0; i < arr.length; i++) {
            bestIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                if (stopped())
                    return;

                if (arr[j] < arr[bestIndex])
                    bestIndex = j;

                draw.changed.add(j);
                draw.changed.add(bestIndex);
                draw.changed.add(i);
                window.repaint();
                delayRepaint(TIME_BETWEEN_ACTION);
            }
            swap(arr, i, bestIndex);

            draw.changed.add(bestIndex);
            draw.changed.add(i);
            delayRepaint(TIME_BETWEEN_ACTION);
        }

        done();
    }
}
