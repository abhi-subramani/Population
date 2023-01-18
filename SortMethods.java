import java.util.List;
import java.util.ArrayList;

/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Abhinav Subramani
 *	@since	December 5, 2022
 */
public class SortMethods {
	
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of City objects to sort
	 */
	public void bubbleSort(List<City> list) {
		for (int outer = list.size() - 1; outer > 0; outer--) {
			for (int inner = 0; inner < outer; inner++) {
				if (list.get(inner).compareTo(list.get(inner+1)) > 0)
					swap(list, inner, inner + 1);
			}
		}
	}
	
	/**
	 *	Swaps two City objects in List list
	 *	@param arr		array of City objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(List<City> list, int x, int y) {
		City temp = list.get(x);
		list.set(x, list.get(y));
		list.set(y, temp);
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of City objects to sort
	 */
	public void selectionSort(List<City> list) {
		int greatestIndex;
		for (int outer = list.size() - 1; outer >= 0; outer--) {
			greatestIndex = 0;
			for (int inner = 0; inner <= outer; inner++) {
				if (list.get(greatestIndex).compareTo(list.get(inner)) < 0)
					greatestIndex = inner;
			}
			swap(list, greatestIndex, outer);
		}
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of City objects to sort
	 */
	public void insertionSort(List<City> list) {
		for (int outer = 1; outer < list.size(); outer++) {
			City temp = list.get(outer);
			int inner;
			for (inner = outer; inner > 0 && temp.compareTo(list.get(inner - 1)) < 0; inner--)
				list.set(inner, list.get(inner - 1));
			
			list.set(inner, temp);
		}
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of City objects to sort
	 */
	public void mergeSort(List<City> list) {
		split(list);
	}
	
	public void merge(List<City> list, List<City> listL, List<City> listR) {
		int l = 0;
		int r = 0;
		int a = 0;
		while (l < listL.size() && r < listR.size()) {
			if (listL.get(l).compareTo(listR.get(r)) < 0) {
				list.set(a, listL.get(l));
				a++;
				l++;
			}
			else {
				list.set(a, listR.get(r));
				a++;
				r++;
			}
		}
		while (l < listL.size()) {
			list.set(a, listL.get(l));
			a++;
			l++;
		}
		while (r < listR.size()) {
			list.set(a, listR.get(r));
			a++;
			r++;
		}
	}
	
	public void split(List<City> list) {
		ArrayList<City> listLeft;
		ArrayList<City> listRight;
		if (list.size() > 2) {
			int split = (list.size() - 1)/2;
			listLeft = new ArrayList<City>(split);
			listRight = new ArrayList<City>(list.size() - split);
			for (int a = 0; a < split; a++) {
				listLeft.add(a, list.get(a));
			}
			for (int b = split; b < list.size(); b++) {
				listRight.add(b - split, list.get(b));
			}
			split(listLeft);
			split(listRight);
			
			merge(list, listLeft, listRight);
		}
		else {
			if (list.size() == 2) {
				if (list.get(0).compareTo(list.get(1)) > 0)
					swap(list, 0, 1);
			}
			return;
		}
	}
	
	/*****************************************************************/
	/************************* For Testing ***************************/
	/*****************************************************************/
	
	//~ /**
	 //~ *	Print an array of Integers to the screen
	 //~ *	@param arr		the array of Integers
	 //~ */
	//~ public void printArray(Integer[] arr) {
		//~ if (arr.length == 0) System.out.print("(");
		//~ else System.out.printf("( %4d", arr[0]);
		//~ for (int a = 1; a < arr.length; a++) {
			//~ if (a % 10 == 0) System.out.printf(",\n  %4d", arr[a]);
			//~ else System.out.printf(", %4d", arr[a]);
		//~ }
		//~ System.out.println(" )");
	//~ }

	//~ public static void main(String[] args) {
		//~ SortMethods se = new SortMethods();
		//~ se.run();
	//~ }
	
	//~ public void run() {
		//~ Integer[] arr = new Integer[10];
		//~ // Fill arr with random numbers
		//~ for (int a = 0; a < 10; a++)
			//~ arr[a] = (int)(Math.random() * 100) + 1;
		//~ System.out.println("\nBubble Sort");
		//~ System.out.println("Array before sort:");
		//~ printArray(arr);
		//~ System.out.println();
		//~ bubbleSort(arr);
		//~ System.out.println("Array after sort:");
		//~ printArray(arr);
		//~ System.out.println();
		
		//~ for (int a = 0; a < 10; a++)
			//~ arr[a] = (int)(Math.random() * 100) + 1;
		//~ System.out.println("\nSelection Sort");
		//~ System.out.println("Array before sort:");
		//~ printArray(arr);
		//~ System.out.println();
		//~ selectionSort(arr);
		//~ System.out.println("Array after sort:");
		//~ printArray(arr);
		//~ System.out.println();
	
		//~ for (int a = 0; a < 10; a++)
			//~ arr[a] = (int)(Math.random() * 100) + 1;
		//~ System.out.println("\nInsertion Sort");
		//~ System.out.println("Array before sort:");
		//~ printArray(arr);
		//~ System.out.println();
		//~ insertionSort(arr);
		//~ System.out.println("Array after sort:");
		//~ printArray(arr);
		//~ System.out.println();
	
		//~ for (int a = 0; a < 10; a++)
			//~ arr[a] = (int)(Math.random() * 100) + 1;
		//~ System.out.println("\nMerge Sort");
		//~ System.out.println("Array before sort:");
		//~ printArray(arr);
		//~ System.out.println();
		//~ mergeSort(arr);
		//~ System.out.println("Array after sort:");
		//~ printArray(arr);
		//~ System.out.println();

	//~ }
}
