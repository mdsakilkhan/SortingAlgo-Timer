import java.security.SecureRandom;
import java.util.Scanner;

/**
 * Sorting Algorithm Timer
 *
 * @author Md Sakil Khan
 * @version 1.0
 */

public class SortingTimer{
	
    private static final String line = String.format("%99s", " ").replaceAll(" ", "-");

	public SortingTimer() {
		System.out.printf("Loading... (please wait)%n%n");
		System.out.printf("%s%n| %-11s | %11s | %11s | %11s | %11s | %11s | %11s |%n", line, 
						" ", "Insertion", "Merge", "Heap", "Quick", "Quick Sort", "Radix");
		System.out.printf("| %-11s | %11s | %11s | %11s | %11s | %11s | %11s |%n%s%n", 
						"N", "Sort", "Sort", "Sort", "Sort", "Rand-Pivot", "Sort", line);
		Timer(createRandomArray(10), 10);
		Timer(createRandomArray(100), 100);
		Timer(createRandomArray(1000), 1000);
		Timer(createRandomArray(10000), 10000);
		Timer(createRandomArray(100000), 100000);
		Timer(createRandomArray(1000000), 1000000);
		System.out.printf("%s%n%n%s%n%s%n%s%n%n", line,
						"Press \"Enter\" : to continue",
						"  or enter the size of array then press \"Enter\"",
						"Type \"quit\" : to exit program");
	}
	
	public SortingTimer(int n) {
		System.out.printf("Loading... (please wait)%n%n");
		System.out.printf("%s%n| %-11s | %11s | %11s | %11s | %11s | %11s | %11s |%n", line, 
						" ", "Insertion", "Merge", "Heap", "Quick", "Quick Sort", "Radix");
		System.out.printf("| %-11s | %11s | %11s | %11s | %11s | %11s | %11s |%n%s%n", 
						"N", "Sort", "Sort", "Sort", "Sort", "Rand-Pivot", "Sort", line);
		Timer(createRandomArray(n), n);
		System.out.printf("%s%n%n%s%n%s%n%s%n%n", line,
						"Press \"Enter\" : to continue",
						"  or enter the size of array then press \"Enter\"",
						"Type \"quit\" : to exit program");
	}

	public static void main(String []args){
		System.out.printf("%n%s%n%s%n%s%n%n", "Press \"Enter\" : to continue",
						"  or enter the size of array then press \"Enter\"",
						"Type \"quit\" : to exit program");
		while (true) {
			Scanner input = new Scanner(System.in);
			String command = input.nextLine();
			if (command == null) {
				SortingTimer nondefaultRun = new SortingTimer();				
			}
			try {
				int arrSize = Integer.parseInt(command);
				SortingTimer defaultRun = new SortingTimer(arrSize);
			}
			catch (Exception exc) {
				if (command.equals("quit")) {
					input.close();
					break;
				}
				else {
					SortingTimer nondefaultRun = new SortingTimer();
				}				
			}
		}
		System.out.println("Bye!!!");
	}

	private void Timer(int[] array, int n) {
		String timeTaken = null;
		String insertionTime = "N/A";
		String mergeTime = "N/A";
		String heapTime = "N/A";
		String quickTime = "N/A";
		String quick2Time = "N/A";
		String radixTime = "N/A";
		int[] arr = new int[n];
		int max = 0;

		for (int i = 1; i <= 6; ++i) {
			System.arraycopy(array, 0, arr, 0, n);
			if (i == 6) {
				max = getMax(arr, n);
			}
			long nanoStart = System.nanoTime(); 
			long millisStart = System.currentTimeMillis(); 
			switch (i) {
				case 1: InsertionSort(arr, n);
						break;
				case 2: MergeSort(arr, 0, n-1);
						break;
				case 3: HeapSort(arr, n);
						break;
				case 4: QuickSort(arr, 0, n-1, false);
						break;
				case 5: QuickSort(arr, 0, n-1, true);
						break;
				case 6: RadixSort(arr, n, max);
						break;
			}
			long nanoEnd = System.nanoTime(); 
			long millisEnd = System.currentTimeMillis();
			
			if ((nanoEnd-nanoStart) < 10000) {
				timeTaken = String.format("%,d ns", (nanoEnd-nanoStart));
			}
			else if ((nanoEnd-nanoStart) < 10000000) {
				timeTaken = String.format("%,d us", (nanoEnd-nanoStart)/1000);
			}
			else if ((millisEnd-millisStart) < 10000) {
				timeTaken = String.format("%,d ms", (millisEnd-millisStart));
			}
			else {
				timeTaken = String.format("%,d s ", (millisEnd-millisStart)/1000);
			}
			switch (i) {
				case 1: insertionTime = timeTaken;
						break;
				case 2: mergeTime = timeTaken;
						break;
				case 3: heapTime = timeTaken;
						break;
				case 4: quickTime = timeTaken;
						break;
				case 5: quick2Time = timeTaken;
						break;
				case 6: radixTime = timeTaken;
						break;
			}
		}
		System.out.printf("| %-11d | %11s | %11s | %11s | %11s | %11s | %11s |%n", 
			n, insertionTime, mergeTime, heapTime, quickTime, quick2Time, radixTime);
	}
	
	private int[] createRandomArray(int n) {
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = randomBetween(1,10000);
		}
		return arr;
	}
	
	private int randomBetween(int a, int b) {
		SecureRandom randNum = new SecureRandom();
		int U = Math.max(a, b);		
		int L = Math.min(a, b);
		return randNum.nextInt(U-L+1)+L;
	}
	
	private boolean isSorted(int[] array) { // for testing if sorting algorithms are working 
		for (int i = 0; i < array.length - 1; i++) {
			if (array[i] > array[i + 1])
				return false;
		}
		return true;
	}

/*--------------------------- Insertion Sort ---------------------------*/

	private void InsertionSort(int[] arr, int n) {
		for (int i = 1; i < n; ++i) { 
			int key = arr[i]; 
			int j = i - 1; 
			while (j >= 0 && arr[j] > key) { 
				arr[j + 1] = arr[j]; 
				j = j - 1; 
			} 
			arr[j + 1] = key; 
		} 
	}

/*--------------------------- Merge Sort ---------------------------*/	

	private void merge(int[] arr, int p, int q, int r) {
		int n1 = q - p + 1;
		int n2 = r - q;
		int[] L = new int[n1];
		int[] R = new int[n2];
		for (int i = 0; i < n1; ++i)
			L[i] = arr[p + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[q + j + 1];
		
		int i = 0;
		int j = 0;
		int k = p;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			}
			else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}
		
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}

	private void MergeSort(int arr[], int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			MergeSort(arr, p, q);
            MergeSort(arr, q + 1, r);
			merge(arr, p, q, r);
		}
	}

/*--------------------------- Heap Sort ---------------------------*/	
	
	private void heapify(int arr[], int n, int i) {
		int max = i;
		int l = 2*i + 1;
		int r = 2*i + 2;
		
		if (l < n && arr[l] > arr[max])
			max = l; 
		if (r < n && arr[r] > arr[max])
			max = r;
		if (max != i) {
			int temp = arr[i];
			arr[i] = arr[max];
			arr[max] = temp;
			heapify(arr, n, max);
		}
	}
	
	private void HeapSort(int arr[], int n) {
		for (int i = (n/2)-1; i >= 0; i--)
			heapify(arr, n, i);
		for (int i = n-1; i > 0; i--) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			heapify(arr, i, 0);
		} 
	}

/*--------------------------- Quick Sort ---------------------------*/		

	private int partition(int arr[], int p, int r, boolean typeRandom) {
		int pivot;
		if (typeRandom) {	// for random pivot method
			pivot = randomBetween(p, r);
			int temp = arr[pivot];  
			arr[pivot]=arr[r]; 
			arr[r]=temp;
		}
		pivot = arr[r];
		
		int i = (p-1);
		for (int j=p; j<r; j++) {
			if (arr[j] < pivot) {
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		int temp = arr[i+1];
		arr[i+1] = arr[r];
		arr[r] = temp;
		return i+1;
	}

	private void QuickSort(int arr[], int p, int r, boolean typeRandom) {
		if (p < r) { 
			int pi = partition(arr, p, r, typeRandom);
			QuickSort(arr, p, pi-1, typeRandom);
			QuickSort(arr, pi+1, r, typeRandom);
		}
	}

/*--------------------------- Radix Sort ---------------------------*/	

	private int getMax(int array[], int n) {
		int max = array[0];
		for (int i = 1; i < n; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}
	
	private void countingSort(int[] arr, int n, int sig) {
		int[] output = new int[n + 1];
		int max = arr[0];
		for (int i = 1; i < n; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		int[] count = new int[max + 1];
		
		for (int i = 0; i < max; ++i)
			count[i] = 0;
		for (int i = 0; i < n; i++)
			count[(arr[i] / sig) % 10]++;
		for (int i = 1; i < 10; i++)
			count[i] += count[i - 1];
		
		for (int i = n - 1; i >= 0; i--) {
			output[count[(arr[i] / sig) % 10] - 1] = arr[i];
			count[(arr[i] / sig) % 10]--;
		}
		for (int i = 0; i < n; i++)
			arr[i] = output[i];
	}
	
	private void RadixSort(int[] arr, int n, int max) {
		for (int i = 1; max/i > 0; i *= 10) {
			countingSort(arr, n, i);
		}
	}
}
