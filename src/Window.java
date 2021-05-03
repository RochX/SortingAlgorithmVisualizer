import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Window extends JFrame {
    //default parameters for number elements
    private final int DEFAULT_SIZE = 100;
    private final String DEFAULT_TEXT = "Input size of array here. Default is "+ DEFAULT_SIZE + ".";

    private JComboBox sortList;
    private Draw draw;
    private int[] arr;
    private JTextField sizeInput;
    private BubbleSort bubbleSort;
    private SelectionSort selectionSort;
    private InsertionSort insertionSort;
    private CocktailSort cocktailSort;
    private QuickSort quickSort;
    private StoogeSort stoogeSort;
    private MergeSort mergeSort;

    //constructor
    public Window(String title) {
        setTitle(title);

        //initialize array
        arr = new int[DEFAULT_SIZE];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i+1;
        }

        //set layout and add all visual elements
        setLayout(new GridBagLayout());
        addContentsToPane(getContentPane());

        //create the sorting algorithm objects
        bubbleSort = new BubbleSort(arr, draw, this);
        selectionSort = new SelectionSort(arr, draw, this);
        insertionSort = new InsertionSort(arr, draw, this);
        cocktailSort = new CocktailSort(arr, draw, this);
        quickSort = new QuickSort(arr, draw, this);
        stoogeSort = new StoogeSort(arr, draw, this);
        mergeSort = new MergeSort(arr, draw, this);
    }

    //add all visual elements
    public void addContentsToPane(Container pane) {
        sortList = new JComboBox<>(SortsList.values());
        draw = new Draw(arr);
        JButton sortButton = new JButton("Sort!");
        JButton stopButton = new JButton("Stop");
        sizeInput = new JTextField(DEFAULT_TEXT);


        //add action listener that runs an algorithm when the sort button is pressed
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortingAlgo((SortsList) Objects.requireNonNull(sortList.getSelectedItem()));
            }
        });

        //add action listener that stops the currently running algorithm
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        //format the GUI
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.FIRST_LINE_START;

        //add the list of sorting algorithms
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.7;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        pane.add(sortList, constraints);

        //add the size input
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.10;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        pane.add(sizeInput, constraints);

        //add the sort button
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.weightx = 0.15;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        pane.add(sortButton, constraints);

        //add the stop button
        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.weightx = 0.15;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        pane.add(stopButton, constraints);

        //add the display
        constraints.gridx = 0;
        constraints.ipady = 5;
        constraints.gridy = 1;
        constraints.weightx = 0;
        constraints.weighty = 1;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.BOTH;
        pane.add(draw, constraints);
    }

    //start a sorting algorithm
    private void sortingAlgo(SortsList sort) {
        //set new size of array and show user an error if its invalid
        try {
            int newArrSize;
            if (sizeInput.getText().equals(DEFAULT_TEXT))
                newArrSize = 100;
            else
                newArrSize = Integer.parseInt(sizeInput.getText());

            if (newArrSize <= 0) {
                throw new IndexOutOfBoundsException();
            }

            arr = new int[newArrSize];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = i+1;
            }

            draw.setArr(arr);
            repaint();
        }
        catch (Exception e) {
            draw.setText("Invalid Size.");
            repaint();
            return;
        }

        reset();

        //select which algorithm to run
        switch (sort) {
            case BUB:
                bubbleSort.startAlgorithm();
                break;
            case SEL:
                selectionSort.startAlgorithm();
                break;
            case INS:
                insertionSort.startAlgorithm();
                break;
            case COC:
                cocktailSort.startAlgorithm();
                break;
            case QUI:
                quickSort.startAlgorithm();
                break;
            case STO:
                stoogeSort.startAlgorithm();
                break;
            case MER:
                mergeSort.startAlgorithm();
                break;
            default:
                System.err.println("Algorithm Not Implemented");
        }
    }

    //reset and stop all the algorithms
    private void reset() {
        bubbleSort.stop();
        bubbleSort = new BubbleSort(arr, draw, this);
        selectionSort.stop();
        selectionSort = new SelectionSort(arr, draw, this);
        insertionSort.stop();
        insertionSort = new InsertionSort(arr, draw, this);
        cocktailSort.stop();
        cocktailSort = new CocktailSort(arr, draw, this);
        quickSort.stop();
        quickSort = new QuickSort(arr, draw, this);
        stoogeSort.stop();
        stoogeSort = new StoogeSort(arr, draw, this);
        mergeSort.stop();
        mergeSort = new MergeSort(arr, draw, this);

        delayRepaint(50);
    }

    private void delayRepaint(int milli) {
        try {
            Thread.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        draw.changed.clear();
        repaint();
    }

    public String getCurrentSortingAlgo() {
        return Objects.requireNonNull(sortList.getSelectedItem()).toString();
    }
}
