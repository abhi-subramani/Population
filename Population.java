import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *	Population - This program collects data on the state, name, population, 
 *  and designation from a text file with 31,675 city entries. It creates 
 * 	a database of the cities, grabbing a certain amount of cities and sorting
 * 	them based on user input.
 * 
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Abhinav Subramani
 *	@since	January 12, 2023
 */
public class Population {
	
	// List of cities
	private List<City> cities;
	// SortMethods instance
	private SortMethods sorts;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";
	// constant for top 50 cities in a list
	private final int AMT_IN_LIST = 50;
	
	public Population() {
		cities = new ArrayList<City>();
		sorts = new SortMethods();
	}
	
	/** insantiates a Population object, uses it to call the run method */
	public static void main(String[] args) {
		Population pop = new Population();
		pop.run();
	}
	
	/** calls all method necessary, processes user input in order to perform
	 * 	the corresponding sort */
	public void run() {
		printIntroduction();
		readCities();
		int selection;
		do {
			selection = getSelection();
			if (selection == 1)
				choice1();
			else if (selection == 2)
				choice2();
			else if (selection == 3)
				choice3();
			else if (selection == 4)
				choice4();
			else if (selection == 5)
				choice5();
			else if (selection == 6)
				choice6();
			else if (selection == 9) {
				System.out.println("\nThanks for using Population!\n");
				System.exit(1);
			} 
		} while (selection != 9);
	}
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
	/** reads in each city's data from the usPopData2017 text file */
	public void readCities() {
		Scanner fileIn = FileUtils.openToRead(DATA_FILE).useDelimiter("[\t\n]");
		while (fileIn.hasNext()) {
			cities.add(new City(fileIn.next(), fileIn.next(), fileIn.next(), fileIn.nextInt()));
		}
		
		System.out.println(cities.size() + " cities in database\n");
	}
	
	/** gets the user selection of what is to be sorted 
	 * 	@return select		an integer corresponding to a query in the selection menu */
	public int getSelection() {
		printMenu();
		int select = Prompt.getInt("Enter selection");
		while (select < 1 || select > 6 && select < 9 || select > 9)
			select = Prompt.getInt("Enter selection");
		return select;
	}
	
	/** Sorts the cities in ascending order by population using a selection sort. Prints
	 * 	the last 50 cities in the sorted data set */
	public void choice1() {
		long startMillisec = System.currentTimeMillis();
		sorts.selectionSort(cities);
		long endMillisec = System.currentTimeMillis();
		System.out.println("\nFifty least populous cities");
		System.out.printf("     %-22s %-22s %-12s %12s", "State", "City", "Type", "Population");
		for (int a = 0; a < AMT_IN_LIST; a++) {
			System.out.printf("\n%5s%s", (a+1) + ": ", cities.get(a).toString());
		}
		
		System.out.println("\n\nElapsed Time " + (endMillisec - startMillisec) + " milliseconds\n");
	}
	
	/** Sorts the cities in ascending order by population using a merge sort. Prints
	 * 	the first 50 cities in the sorted data set */
	public void choice2() {
		long startMillisec = System.currentTimeMillis();
		sorts.mergeSort(cities);
		long endMillisec = System.currentTimeMillis();
		System.out.println("\nFifty most populous cities");
		System.out.printf("     %-22s %-22s %-12s %12s", "State", "City", "Type", "Population");
		int count = 1;
		for (int a = cities.size() - 1; a >= cities.size() - AMT_IN_LIST; a--) {
			System.out.printf("\n%5s%s", (count) + ": ", cities.get(a).toString());
			count++;
		}
		
		System.out.println("\n\nElapsed Time " + (endMillisec - startMillisec) + " milliseconds\n");
	}
	
	/** Sorts the cities in ascending order by name using an insertion sort. Prints
	 * 	the first 50 cities in the sorted data set */
	public void choice3() {
		long startMillisec = System.currentTimeMillis();
		for (int outer = 1; outer < cities.size(); outer++) {
			City temp = cities.get(outer);
			int inner;
			for (inner = outer; inner > 0 && 
			temp.getCityName().compareTo(cities.get(inner - 1).getCityName()) < 0; inner--)
				cities.set(inner, cities.get(inner - 1));
			
			cities.set(inner, temp);
		}
		long endMillisec = System.currentTimeMillis();
		
		System.out.println("\nFifty cities sorted by name");
		System.out.printf("     %-22s %-22s %-12s %12s", "State", "City", "Type", "Population");
		for (int a = 0; a < AMT_IN_LIST; a++) {
			System.out.printf("\n%5s%s", (a + 1) + ": ", cities.get(a).toString());
		}
		
		System.out.println("\n\nElapsed Time " + (endMillisec - startMillisec) + " milliseconds\n");
	}
	
	/** Sorts the cities in ascending order by name using a merge sort. Prints
	 * 	the last 50 cities in the sorted data set */
	public void choice4() {
		long startMillisec = System.currentTimeMillis();
		split(cities);
		long endMillisec = System.currentTimeMillis();
		
		System.out.println("\nFifty cities sorted by name descending");
		System.out.printf("     %-22s %-22s %-12s %12s", "State", "City", "Type", "Population");
		int count = 1;
		for (int a = cities.size() - 1; a >= cities.size() - AMT_IN_LIST; a--) {
			System.out.printf("\n%5s%s", count + ": ", cities.get(a).toString());
			count++;
		}
		
		System.out.println("\n\nElapsed Time " + (endMillisec - startMillisec) + " milliseconds\n");
	}
	
	/** merges two separate arrays into one. the resulting array is sorted
	 * 	in ascending order by name
	 * 	@param list			the result array of the two combined lists
	 * 	@param listL		the array merged from the left
	 * 	@param listR 		the array merged from the right*/
	public void merge(List<City> list, List<City> listL, List<City> listR) {
		int l = 0;
		int r = 0;
		int a = 0;
		while (l < listL.size() && r < listR.size()) {
			if (listL.get(l).getCityName().compareTo(listR.get(r).getCityName()) < 0) {
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
	
	/** splits a given array into two separate arrays by the midpoint
	 * 	@param list		the array to be separated */
	public void split(List<City> list) {
		ArrayList<City> listLeft;
		ArrayList<City> listRight;
		City temp = null;
		if (list.size() > 2) {
			int split = (list.size() - 1)/2;
			listLeft = new ArrayList<City>();
			listRight = new ArrayList<City>();
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
				if (list.get(0).getCityName().compareTo(list.get(1).getCityName()) > 0) {
					temp = list.get(0);
					list.set(0, list.get(1));
					list.set(1, temp);
				}
			}
			return;
		}
	}
	
	/** forms a new list of cities within a state given by the user. Then
	 * 	sorts the new list in ascending order by population. The last 50 cities
	 * 	are printed from the sorted data set */
	public void choice5() {
		ArrayList<City> citiesInState = new ArrayList<City>();
		String chosenState = Prompt.getString("Enter state name (ie. Alabama)");
		for (int c = 0; c < cities.size(); c++) {
			if (chosenState.equals(cities.get(c).getStateName()))
				citiesInState.add(cities.get(c));
		}
		
		if (citiesInState.size() == 0) {
			System.out.println("ERROR: " + chosenState + " is not valid");
			choice5();
		}
		else {
			sorts.mergeSort(citiesInState);
			System.out.println("\nFifty most populous cities in " + chosenState);
			System.out.printf("     %-22s %-22s %-12s %12s", "State", "City", "Type", "Population");
			int count = 1;
			for (int a = citiesInState.size() - 1; a >= citiesInState.size() - AMT_IN_LIST; a--) {
				System.out.printf("\n%5s%s", (count) + ": ", citiesInState.get(a).toString());
				count++;
			}
			System.out.println("\n");
		}
	}
	
	/** forms a new list of cities all with the same name given by the user. Then
	 * 	sorts the new list in ascending order by population. All cities are
	 * 	printed from the end of the array to the beginning */
	public void choice6() {
		ArrayList<City> chosenCities = new ArrayList<City>();
		String chosenCity = Prompt.getString("Enter city name");
		for (int c = 0; c < cities.size(); c++) {
			if (chosenCity.equals(cities.get(c).getCityName()))
				chosenCities.add(cities.get(c));
		}
		
		if (chosenCities.size() == 0) {
			System.out.println("ERROR: " + chosenCity + " is not valid");
			choice6();
		}
		else {
			sorts.mergeSort(chosenCities);
			System.out.println("\nCity" + chosenCity + " by population");
			System.out.printf("     %-22s %-22s %-12s %12s", "State", "City", "Type", "Population");
			int count = 1;
			for (int a = chosenCities.size() - 1; a >= 0; a--) {
				System.out.printf("\n%5s%s", (count) + ": ", chosenCities.get(a).toString());
				count++;
			}
			System.out.println("\n");
		}
	}
}
