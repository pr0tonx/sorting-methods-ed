import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    String fileName = "decrescente_10000";

    int arraySize = Integer.parseInt(fileName.split("_")[1]);
    Integer[] array1 = new Integer[arraySize];
    Integer[] array2 = new Integer[arraySize];
    Integer[] array3 = new Integer[arraySize];



    /* Bubble Sort */
    String filePath = "src/conjuntosDeDados/" + fileName + ".csv";
    Integer[] data = readCsv(filePath, array1);

    double startTime1 = System.nanoTime();
    Integer[] bubbleSorted = bubbleSort(data);
    double endTime1 = System.nanoTime();
    double timeElapsed1 = (endTime1 - startTime1) / 1000000000;

    System.out.print("BUBBLE SORT: " + timeElapsed1 + " seconds.\n");
    System.out.println(Arrays.toString(bubbleSorted) + "\n");



    /* Insertion Sort */
    String filePath2 = "src/conjuntosDeDados/" + fileName + ".csv";
    Integer[] data2 = readCsv(filePath2, array2);

    double startTime2 = System.nanoTime();
    Integer[] insertionSorted = insertionSort(data2);
    double endTime2 = System.nanoTime();
    double timeElapsed2 = (endTime2 - startTime2) / 1000000000;

    System.out.println("INSERTION SORT: " + timeElapsed2 + " seconds.");
    System.out.println(Arrays.toString(insertionSorted) + "\n");



    /* Quick Sort */
    String filePath3 = "src/conjuntosDeDados/" + fileName + ".csv";
    Integer[] data3 = readCsv(filePath3, array3);

    double startTime3 = System.nanoTime();
    Integer[] quickSorted = quickSort(data3, 0, data3.length - 1);
    double endTime3 = System.nanoTime();
    double timeElapsed3 = (endTime3 - startTime3) / 1000000000;

    System.out.println("QUICK SORT: " + timeElapsed3 + " seconds.");
    System.out.println(Arrays.toString(quickSorted) + "\n");
  }

  private static Integer[] readCsv(String filePath, Integer[] arr) {
    try {
      List<String> lines = Files.readAllLines(Paths.get(filePath));
      if (!lines.isEmpty()) lines.removeFirst();

      int i = 0;
      for (String line : lines) {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(",");

        while (scanner.hasNext()) {
          if (scanner.hasNextInt()) {
            arr[i] = scanner.nextInt();
            i++;
          } else {
            scanner.next();
          }
        }
        scanner.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return arr;
  }

  private static Integer[] bubbleSort(Integer[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
      for (int j = 0; j < arr.length - i - 1; j++) {
        int temp;

        if (arr[j] > arr[j + 1]) {
          temp = arr[j + 1];
          arr[j + 1] = arr[j];
          arr[j] = temp;
        }
      }
    }

    return arr;
  }

  private static Integer[] insertionSort(Integer[] arr) {
    for (int i = 1; i < arr.length; i++) {
      int temp = arr[i];

      if (temp > arr[i]) {
        arr[i] = arr[i - 1];
        arr[i - 1] = temp;
      }

      for (int j = i; j > 0; j--) {
        if (temp > arr[j - 1]) break;
        arr[j] = arr[j - 1];
        arr[j - 1] = temp;
      }
    }

    return arr;
  }

  public static Integer[] quickSort(Integer[] arr, int startIndex, int endIndex) {
    if (startIndex >= endIndex) return arr;

    int pivot = arr[startIndex];
    int i = startIndex + 1;
    int temp;

    for (int j = startIndex + 1; j <= endIndex; j++) {
      if (pivot > arr[j]) {
        temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;

        i++;
      }
    }

    arr[startIndex] = arr[i - 1];
    arr[i - 1] = pivot;

    quickSort(arr, startIndex, i - 2);
    quickSort(arr, i, endIndex);

    return arr;
  }
}
