/**
 *	City data - the city name, state name, location, designation,
 *				and population est. 2017
 *
 *	@author	Abhinav Subramani
 *	@since	January 10 2023
 */
public class City implements Comparable<City> {
	
	// fields
	private String cityName;
	private String stateName;
	private String designation;
	private int population;
	
	// constructor
	public City(String state, String city, String type, int pop) {
		stateName = state;
		cityName = city;
		designation = type;
		population = pop;
	}
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.stateName - other.stateName)
	 *		else returns (this.cityName - other.cityName)
	 */
	 
	public int compareTo(City other) {
		if (this.population != other.getPopulation())
			return this.population - other.getPopulation();
		else if (!this.stateName.equals(other.getStateName()))
			return this.stateName.compareTo(other.getStateName());
		else
			return this.cityName.compareTo(other.getCityName());
	}
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	 
	public boolean equals(City other) {
		 if (this.cityName.equals(other.getCityName()) && 
			this.stateName.equals(other.getStateName()))
			return true;
		
		return false;
	}
	
	
	/**	Accessor methods */
	
	public int getPopulation() { return population; }
	
	public String getCityName() { return cityName; }
	
	public String getStateName() { return stateName; }
	
	public String getDesignation() { return designation; }
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", stateName, cityName, designation,
						population);
	}
}
