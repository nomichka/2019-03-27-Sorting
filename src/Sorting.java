import java.util.Arrays;

public class Sorting {
	public static void main(String[] args) {
		int[] list1 = {600, 8, 8, 1, 6, 3, 5, 2, 76, 0, 62, 5};
		int[] copy1 = list1.clone();
		String[] list2 = {"i", "b", "c", "dv", "e", "fbgr", "g", "l", "a", "ha", "hal", "n"};
		String[] copy2 = list2.clone();
		double[] list3 = {600.6, 8.1, 8.1, 1.0, 6.0, 3.0, 5.0, 2.63, 76.6, 0.3, 62.3, 5.7};
		double[] copy3 = list3.clone();
		
		System.out.println("List 1 sorted in merge, quick, insetion, and selection:");
		mergeSort(list1); //Must be int[]
		toString(list1);
		quickSort(list1); //Must be int[]
		toString(list1);
		list1 = copy1.clone();
		insertionSort(list1); //Must be int[], double[], or String[]
		toString(list1);
		list1 = copy1.clone();
		selectionSort(list1); //Must be int[] or double[]
		toString(list1);		
		
		System.out.println("\nList 2 sorted in insertion and selection:");
		list2 = copy2.clone();
		insertionSort(list2);
		toString(list2);
		list2 = copy2.clone();
		selectionSort(list2);
		toString(list2);
		
		System.out.println("\nList 3 sorted in insetion and selection:");
		list3 = copy3.clone();
		insertionSort(list3);
		toString(list3);
		list3 = copy3.clone();
		selectionSort(list3);
		toString(list3);
	}
	
	//ToString for int[], double[], or String[]
	public static void toString(String[] arr) {
		for (String num : arr) {
			System.out.print(num + " ");
		}
		System.out.println("\n");
	}
	public static void toString(int[] arr) {
		for (int num : arr) {
			System.out.print(num + " ");
		}
		System.out.println("\n");
	}
	public static void toString(double[] arr) {
		for (double num : arr) {
			System.out.print(num + " ");
		}
		System.out.println("\n");
	}
	
	/*
	 * MergeSort: Fast
	 * Pre: and array of integers
	 * Post: the array sorted from least to greatest
	 */
	public static void mergeSort(int[] arr) {
		if (arr.length > 1) {
			int[] left = Arrays.copyOfRange(arr, 0, arr.length / 2);
			int[] right = Arrays.copyOfRange(arr, arr.length / 2, arr.length);
			mergeSort(left);
			mergeSort(right);
			int[] copy = new int[arr.length];
			for (int i = 0; i < arr.length / 2; i++) {
				copy[i] = left[i];
			}
			for (int i = arr.length / 2; i < arr.length; i++) {
				copy[i] = right[i - arr.length / 2];
			}
			int front1 = 0;
			int front2 = arr.length / 2;
			int i = 0;
			while (i < arr.length) {
				if (front1 == arr.length / 2) {
					for (int count = i; count < arr.length; count++) {
						arr[count] = copy[front2 + count - i];
					}
					i = arr.length;
				} else if (front2 == arr.length) {
					for (int count = i; count < arr.length; count++) {
						arr[count] = copy[front1 + count - i];
					}
					i = arr.length;
				} else if (copy[front1] <= copy[front2]) {
					arr[i] = copy[front1];
					front1++;
				} else {
					arr[i] = copy[front2];
					front2++;
				}
				i++;
			}
		}
	}
	
	
	
	
	/*QuickSort: Fast
	 *Pre: an array of integers
	 *Post: the array sorted from least to greatest
	 */
	public static void quickSort(int[] arr) {
		int pivot = arr[0];
		int[] temp = new int[arr.length];
		boolean[] filled = new boolean[arr.length];
		int sameAsP = 0;
		
		for (int j = 0; j < arr.length; j++) {
			if (arr[j] < pivot) {
				int i = 0;
				while (filled[i] == true) { i++; }
				filled[i] = true;
				temp[i] = arr[j];	
			} else if (arr[j] > pivot) {
				int i = arr.length - 1;
				while (filled[i] == true) { i--; }
				filled[i] = true;
				temp[i] = arr[j];
			} else {
				sameAsP += 1;
			}
		}
		int i = 0;
		int count;
		while (filled[i] == true) { i++; }
		for (count = i; count < i + sameAsP; count++) {
			temp[count] = pivot;
		}
		
		if (i > 1) { //Sorts before pivot
			int[] before = new int[i];
			for (int j = 0; j < i; j++) { before[j] = temp[j]; }
			quickSort(before);
			for (int j = 0; j < i; j++) { temp[j] = before[j]; }
		}
		
		if (arr.length - i - sameAsP > 1) { //Sorts after pivot
			int[] after = new int[arr.length - i - sameAsP];
			for (int j = i + sameAsP; j < arr.length; j++) { after[j - i - sameAsP] = temp[j]; }
			quickSort(after);
			for (int j = i + sameAsP; j < arr.length; j++) {temp[j] = after[j - i - sameAsP]; }
		}	

		for (int j = 0; j < arr.length; j++) {
			arr[j] = temp[j];
		}
	}
	
	/*InsertionSort: Medium
	 *Pre: an array of integers, doubles, or strings
	 *Post: the array sorted from least to greatest
	 *alphaFirst compares two Strings to compare which should come first;
	 *related to insertion with strings
	 */
	public static int alphaFirst(String one, String two) {
		int length = Math.min(one.length(), two.length());
		int i = 0;
		while (i < length) {
			int valueOne = one.charAt(i);
			int valueTwo = two.charAt(i);
			if (valueOne < valueTwo) {
				return -1;
			}
			if (valueTwo < valueOne) {
				return 1;
			}
			i++;
		}
		if (length == one.length()) {
			return -1;
		}
		return 0;
	}
	
	public static void insertionSort(String[] arr) {
		for (int i = 1; i < arr.length; i++) {
			String temp = arr[i];
			int left = 0;
			int right = i;
			int mid = (right + left) / 2;
			while (right != left) {
				int n = alphaFirst(temp, arr[mid]);
				if (n > 0) {
					left = mid + 1;
				} else if (n < 0) {
					right = mid;
				} else {
					left = mid;
					right = mid;
				}
				mid = (right + left) / 2;
			}
			for (int j = i; j > right; j--) {
				arr[j] = arr[j - 1];
			}
			arr[right] = temp;
		}
	}
	
	public static void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int temp = arr[i];
			int left = 0;
			int right = i;
			int mid = (right + left) / 2;
			while (right != left) {
				if (temp > arr[mid]) {
					left = mid + 1;
				} else if (temp < arr[mid]) {
					right = mid;
				} else {
					left = mid;
					right = mid;
				}
				mid = (right + left) / 2;
			}
			for (int j = i; j > right; j--) {
				arr[j] = arr[j - 1];
			}
			arr[right] = temp;
		}
	}
	
	public static void insertionSort(double[] arr) {
		for (int i = 1; i < arr.length; i++) {
			double temp = arr[i];
			int left = 0;
			int right = i;
			int mid = (right + left) / 2;
			while (right != left) {
				if (temp > arr[mid]) {
					left = mid + 1;
				} else if (temp < arr[mid]) {
					right = mid;
				} else {
					left = mid;
					right = mid;
				}
				mid = (right + left) / 2;
			}
			for (int j = i; j > right; j--) {
				arr[j] = arr[j - 1];
			}
			arr[right] = temp;
		}
	}
	
	/*SelectionSort: Slow
	 *Pre: an array of doubles or an array of ints
	 *Post: the array sorted from least to greatest
	 */
	public static void selectionSort(double[] arr) {
		double max;
		for (int length = arr.length; length > 0; length--) {
			int index = 0;
			max = arr[index];
			for (int i = 1; i < length; i++) {
				if (arr[i] > max) { 
					max = arr[i]; 
					index = i;
				}
			}
			arr[index] = arr[length - 1];
			arr[length - 1] = max;			
		}
	}
	public static void selectionSort(int[] arr) {
		int max;
		for (int length = arr.length; length > 0; length--) {
			int index = 0;
			max = arr[index];
			for (int i = 1; i < length; i++) {
				if (arr[i] > max) { 
					max = arr[i]; 
					index = i;
				}
			}
			arr[index] = arr[length - 1];
			arr[length - 1] = max;			
		}
	}
	public static void selectionSort(String[] arr) {
		String max;
		for (int length = arr.length; length > 0; length--) {
			int index = 0;
			max = arr[index];
			for (int i = 1; i < length; i++) {
				if (arr[i].compareTo(max) > 0) { 
					max = arr[i]; 
					index = i;
				}
			}
			arr[index] = arr[length - 1];
			arr[length - 1] = max;			
		}
	}
	
}
