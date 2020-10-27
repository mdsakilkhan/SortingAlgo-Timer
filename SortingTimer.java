/**
 * Sorting Algorithm Timer
 *
 * @author Md Sakil Khan
 * @version 1.0
 */

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;

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

	private void Timer(int arr[], int n) {
		String timeTaken = null;
		String insertionTime = "N/A";
		String mergeTime = "N/A";
		String heapTime = "N/A";
		String quickTime = "N/A";
		String quick2Time = "N/A";
		String radixTime = "N/A";
		
		for (int i = 1; i <= 6; ++i) {
			long nanoStart = System.nanoTime(); 
			long millisStart = System.currentTimeMillis(); 
			switch (i) {
				case 1: InsertionSort(arr, n);
						break;
				case 2: MergeSort(arr, 0, arr.length - 1);
						break;
				case 3: HeapSort(arr);
						break;
				case 4: QuickSort(arr, 0, arr.length - 1, false);
						break;
				case 5: QuickSort(arr, 0, arr.length - 1, true);
						break;
				case 6: RadixSort(arr, arr.length - 1);
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
	
	void InsertionSort(int arr[], int n) {
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
	
	void merge(int arr[], int l, int m, int r) {
		int n1 = m - l + 1;
		int n2 = r - m;
		int L[] = new int[n1];
		int R[] = new int[n2];
		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];
		
		int i = 0, j = 0;
		int k = l;
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
	
	void MergeSort(int arr[], int l, int r) {
		if (l < r) {
			int m = (l + r) / 2;
			MergeSort(arr, l, m);
            MergeSort(arr, m + 1, r);
			merge(arr, l, m, r);
		}
	}
	
	public void HeapSort(int arr[]) {
		int n = arr.length;
		for (int i = n / 2 - 1; i >= 0; i--)
			heapify(arr, n, i);
		for (int i=n-1; i>0; i--) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			heapify(arr, i, 0); 
		} 
	}
	
	void heapify(int arr[], int n, int i) {
		int largest = i;
		int l = 2*i + 1;
		int r = 2*i + 2;
		
		if (l < n && arr[l] > arr[largest])
			largest = l; 
		if (r < n && arr[r] > arr[largest])
			largest = r;
		if (largest != i) {
			int swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;
			heapify(arr, n, largest);
		}
	}
	
	int partition(int arr[], int low, int high, boolean type2) {
		int pivot;
		if (type2) {
			pivot = arr[randomBetween(low,high)];
		}
		else {
			pivot = arr[high]; 
		}
		int i = (low-1);
		for (int j=low; j<high; j++) {
			if (arr[j] < pivot) {
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		int temp = arr[i+1];
		arr[i+1] = arr[high];
		arr[high] = temp;
		return i+1;
	}
	
	void QuickSort(int arr[], int low, int high, boolean type) {
		if (low < high) { 
			int pi = partition(arr, low, high, type);
			QuickSort(arr, low, pi-1, type);
			QuickSort(arr, pi+1, high, type);
		}
	}
	
	static int getMax(int arr[], int n) {
		int mx = arr[0];
		for (int i = 1; i < n; i++)
			if (arr[i] > mx)
				mx = arr[i];
		return mx;
	}
	
	static void countSort(int arr[], int n, int exp) {
		int output[] = new int[n];
		int i;
		int count[] = new int[10];
		Arrays.fill(count, 0);
		
		for (i = 0; i < n; i++)
			count[(arr[i] / exp) % 10]++;
		for (i = 1; i < 10; i++)
			count[i] += count[i - 1];
		for (i = n - 1; i >= 0; i--) {
			output[count[(arr[i] / exp) % 10] - 1] = arr[i];
			count[(arr[i] / exp) % 10]--;
		}
		for (i = 0; i < n; i++)
			arr[i] = output[i];
	} 
	
	static void RadixSort(int arr[], int n) {
		int m = getMax(arr, n);
		for (int exp = 1; m / exp > 0; exp *= 10)
			countSort(arr, n, exp);
	}
}
