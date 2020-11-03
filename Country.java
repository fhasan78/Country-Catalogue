/**
 * Class that represents a country with instance variables name, population,
 * area, and continent
 * 
 * @author Fatima Hasan 
 */
public class Country {
	private String name;
	private int population;
	private double area;
	private String continent;

	/**
	 * Constructor takes four parameters
	 * 
	 * @param name
	 * @param population
	 * @param area
	 * @param continent
	 */
	public Country(String name, int population, double area, String continent) {
		this.name = name;
		this.population = population;
		this.area = area;
		this.continent = continent;
	}

	/**
	 * Getter method getName returns the name of the name of the country
	 * 
	 * @return name of the country
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Getter method getPopulation returns the country's population
	 * 
	 * @return population of the country
	 */
	public int getPopulation() {
		return this.population;
	}

	/**
	 * Getter method getArea returns the country's area
	 * 
	 * @return area of the country
	 */
	public double getArea() {
		return this.area;
	}

	/**
	 * Getter method getContinent returns the country's continent
	 * 
	 * @return continent of the country
	 */
	public String getContinent() {
		return this.continent;
	}

	/**
	 * Getter method getPopDensity returns the country's population density
	 * 
	 * @return country's population density
	 */
	public double getPopDensity() {
		return (this.population / this.area);
	}

	/**
	 * Setter method setPopulation sets the country's population overrides
	 * previous population
	 * 
	 * @param pop
	 */
	public void setPopulation(int pop) {
		this.population = pop;
	}

	/**
	 * Setter method setContinent sets the country's continent overrides
	 * previous continent
	 * 
	 * @param cont
	 */
	public void setContinent(String cont) {
		this.continent = cont;
	}

	/**
	 * writeToFile method writes the name,continent, population, population
	 * density to the ThingToWriteFile object
	 * 
	 * @param out
	 */
	public void writeToFile(ThingToWriteFile file1) {

		file1.writeLine(this.getName() + "," + this.getContinent() + "," + this.getPopulation() + ","
				+ this.getPopDensity() + "\n");

	}

	/**
	 * printCountryDetails method prints details about the country to the screen
	 */
	public void printCountryDetails() {
		System.out.println(this.getName() + " is located in " + this.getContinent() + " has a population of "
				+ this.getPopulation() + ", an area of " + this.getArea() + ", and has a population density of "
				+ this.getPopDensity());
	}

	/**
	 * @return a string saying the name of the country in the name of the
	 *         continent it's in
	 */
	public String toString() {
		String s = (this.getName() + " in " + this.getContinent() + "\n");
		return s;
	}

}
