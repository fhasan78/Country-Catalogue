import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author FatimaH 
 *
 */
public class CountryCatalogue {
	private final int DEFAULT_SIZE = 5; // Constant used when the Catalogue
										// array is instantiated
	private final int NOT_FOUND = -1; // This will be used as a flag for some
										// methods
	private Country[] catalogue;
	private int numCountries;

	/**
	 * Constructor take two string parameters, reads the files provided and
	 * creates a country object from the country file and an country and
	 * continent from the continent file
	 * 
	 * @param countryFile
	 * @param continentFile
	 * 
	 */
	public CountryCatalogue(String countryFile, String continentFile) {
		catalogue = new Country[DEFAULT_SIZE];
		numCountries = 0;

		ThingToReadFile countFile = new ThingToReadFile(countryFile);
		String c = countFile.readLine();
		while (countFile.endOfFile() == false) {
			c = countFile.readLine();
			String[] lineCountry = c.split(",");
			Country countrycat = new Country(lineCountry[0], Integer.parseInt(lineCountry[1]),
					Double.parseDouble(lineCountry[2]), "");
			addCountry(countrycat);

			ThingToReadFile catalFile = new ThingToReadFile(continentFile);
			String catal = catalFile.readLine();
			while (catalFile.endOfFile() == false) {
				catal = catalFile.readLine();
				String[] lineContinent = catal.split(",");
				String countryname = lineContinent[0];
				String continentname = lineContinent[1];

				for (int i = 0; i < numCountries; i++) {
					if (catalogue[i].getName().equals(countryname)) {
						countrycat.setContinent(continentname);

					}

				}
			}
		}

	}

	/**
	 * second constructor takes no parameters
	 */
	public CountryCatalogue() {
		catalogue = new Country[DEFAULT_SIZE];
		numCountries = 0;

	}

	/**
	 * this method adds the provided country to the catalogue array
	 * 
	 * @param cntry
	 */
	public void addCountry(Country cntry) {
		if (numCountries >= catalogue.length) {
			expandCapacity();
		}

		catalogue[numCountries] = cntry;
		numCountries++;

	}

	/**
	 * this method will double the size of the catalogue array, and is called by
	 * the addCountry() method
	 */
	private void expandCapacity() {
		Country[] largerList = new Country[catalogue.length * 2];
		for (int i = 0; i < this.numCountries; i++)
			largerList[i] = catalogue[i];
		catalogue = largerList;
	}

	/**
	 * 
	 * @param index
	 * @return a country object from the index provided, returns null if the
	 *         index is inadmissible
	 */
	public Country getCountry(int index) {
		if (index < 0 || index >= catalogue.length) {
			return null;
		} else {
			return catalogue[index];
		}
	}

	/**
	 * The method printCountryCatalogue() simply calls the toString() method for
	 * each country currently in the catalogue instance variable and prints
	 * these strings
	 */
	public void printCountryCatalogue() {
		for (int i = 0; i < numCountries; i++) {
			System.out.println(catalogue[i].toString());
		}
	}

	/**
	 * The filterCountriesByContinent method will print out all countries from a
	 * specified continent
	 * 
	 * @param continent
	 */
	public void filterCountriesByContinent(String continent) {
		for (int i = 0; i < numCountries; i++) {
			if (catalogue[i].getContinent().equals(continent)) {
				System.out.println(catalogue[i]);
			}
		}
	}

	/**
	 * The method searchCatalogue recieves parameter country and returns an int
	 * representation the index of the country
	 * 
	 * @param countryName
	 * @return int integer representing the index of the country in the
	 *         catalogue, if the item isn't found it returns NOT_FOUND, and
	 *         notifies the user
	 */
	public int searchCatalogue(String countryName) {
		int search = NOT_FOUND;

		// search the list for the specified country
		for (int i = 0; i < numCountries && search == NOT_FOUND; i++) {
			if (catalogue[i].getName().equals(countryName)) {
				search = i;
				return search;
			}
		}
		System.out.println(countryName + " cannot be found.");
		return NOT_FOUND;

	}

	/**
	 * removeCountry method receives as parameter the name of a country and
	 * removes it from the catalogue
	 * 
	 * @param countryName
	 * @return boolean depending the searchCatalogue method
	 */
	public boolean removeCountry(String countryName) {
		if (searchCatalogue(countryName) != NOT_FOUND) {
			catalogue[(searchCatalogue(countryName))] = catalogue[numCountries - 1];
			catalogue[numCountries - 1] = null;
			numCountries--;
			System.out.println(countryName + " has been removed successfully");
			return true;
		} else {
			return false;
		}
	}

	/**
	 * setPopulationOfACountry method receives parameters the name of country
	 * and an integer value and changes the population of that country to the
	 * specified value
	 * 
	 * @param countryName
	 * @param pop
	 */
	public void setPopulationOfACountry(String countryName, int pop) {
		if (searchCatalogue(countryName) == NOT_FOUND) {
			System.out.println(countryName + " is not in the catalogue,and therefore has not been altered");
		} else {
			catalogue[searchCatalogue(countryName)].setPopulation(pop);
			System.out.println("Population has been successfully altered!");
		}
	}

	/**
	 * saveCountryCatalogue method will write the catalogue's content to a
	 * file(passed through the parameter). This method calls the writeToFile()
	 * method
	 * 
	 * @param filename
	 */
	public void saveCountryCatalogue(String filename) {
		ThingToWriteFile out = new ThingToWriteFile(filename);
		for (int i = 0; i < numCountries; ++i) {
			catalogue[i].writeToFile(out);
		}
		out.close();
	}

	/**
	 * findCountryWithLargestPop() method returns the index location with the
	 * largest population currently in the catalogue
	 * 
	 * @return index of the country with the largest population.
	 */
	public int findCountryWithLargestPop() {
		int largestPop = 0;
		int countryIndex = 0;
		for (int i = 0; i < numCountries; i++) {
			if ((catalogue[i].getPopulation()) > largestPop) {
				largestPop = catalogue[i].getPopulation();
				countryIndex = i;
			}
		}
		return countryIndex;
	}

	/**
	 * findCountryWithSmallestArea() will return the index location of the
	 * country with smallest area currently in the catalogue
	 * 
	 * @return index of country with smallest area
	 */
	public int findCountryWithSmallestArea() {
		double smallestArea = 999999999999999999999999999999999999999999999999999999.9999;
		int countryIndex = 0;
		for (int i = 0; i < numCountries; i++) {
			if (catalogue[i].getArea() < smallestArea) {
				smallestArea = catalogue[i].getArea();
				countryIndex = i;
			}
		}
		return countryIndex;
	}

	/**
	 * printCountriesFilterDensity() method prints out all the details about the
	 * countries that lie within the given population density range.
	 * 
	 * @param low
	 * @param high
	 */
	public void printCountriesFilterDensity(int low, int high) {
		for (int i = 0; i < numCountries; i++) {
			if (catalogue[i].getPopDensity() >= low && catalogue[i].getPopDensity() <= high) {
				System.out.println(catalogue[i]);
			}
		}

	}

	/**
	 * This method find the most populous continent
	 */
	public void findMostPopulousContinent() {

		Set<String> cDict = new HashSet<String>();
		int currentpop = 0;
		int maxPop = 0;
		String cont = "";
		String populousContinent = "";
		for (int i = 0; i < numCountries; i++) {
			cDict.add(catalogue[i].getContinent());
		}
		for (String continent : cDict) {
			cont = continent;
			currentpop = 0;
			for (int i = 0; i < numCountries; i++) {
				if (catalogue[i].getContinent().equals(continent)) {
					currentpop += catalogue[i].getPopulation();
				}
			}
			if (maxPop < currentpop) {
				maxPop = currentpop;
				populousContinent = cont;
			}
		}

		System.out
				.println("Continent with the largest population: " + populousContinent + ", with population " + maxPop);
	}

}
