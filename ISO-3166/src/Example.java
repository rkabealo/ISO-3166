import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Objects;

public class Example {
	
	public static <T, E> String getKeyByValue(HashMap<String, String> map, String value) {
	    for (Entry<String,String> entry : map.entrySet()) {
	        if (Objects.equals(value, entry.getValue())) {
	            return entry.getKey().toString();
	        }
	    }
	    return null;
	}
	
	public static void main(String[] args) throws IOException {

		//  Read in a file containing all countries and their corresponding codes 
		HashMap<String, String> country_codes = new HashMap<>();
		country_codes.put("", "");

		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("data/country_codes.csv"), "UTF-8"));
		String line = null;
		
		// Avoids UTF-8 encoding character at the beginning of the csv 
		br.mark(1);
		if (br.read() != 0xFEFF)
		  br.reset();

		while ((line = br.readLine()) != null) {
			String str[] = line.split(",");
			country_codes.put(str[0], str[1]);
		}
		
		country_codes.remove(""); 
		
		// Allow the user to access the data 
		BufferedReader read_user = new BufferedReader(new InputStreamReader(System.in));
		boolean run_again = true; 
		
		// Initial prompt 
		System.out.println("Enter a country's 3-letter code to find out the country's full name. Alternatively, enter a country's full name to find out it's 3-letter code: \n");
		
		while(run_again){
			
			System.out.print("\nEnter a country code or full name (or type \"quit\" to quit): "); 
			String s = read_user.readLine(); 
			
			if(country_codes.containsValue(s)){
				System.out.println(s + "'s country name is: " + getKeyByValue(country_codes, s));
			} else if (country_codes.containsKey(s)){
				System.out.println(s + "'s country code is: " + country_codes.get(s));
				
			}else if(s.equals("quit")){
				run_again = false; 
			}else{
				System.out.println("\nInvalid input! Please try again.\n");
			}
		
		}
		
		br.close();

	}

}
