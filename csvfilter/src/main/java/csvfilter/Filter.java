package csvfilter;

import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Paths;
import java.nio.file.Files;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;

public class Filter {
	public static void main(String[] args) {
		try {
            Reader reader = Files.newBufferedReader(Paths.get("Food_Inspections.csv"));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
            		.withFirstRecordAsHeader());
            Writer writer = Files.newBufferedWriter(Paths.get("new.csv"));
            CSVPrinter out = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Inspection ID", "Name", "License", "Facility Type", "Address", "Zip", "Inspection Date", "Results", "Violations", "Latitude", "Longitude"));
            for (CSVRecord csvRecord : csvParser) {
                // Accessing Values by Column Index
            	String id = csvRecord.get(0);
            	String name = csvRecord.get(1);
            	String license = csvRecord.get(3);
                String type = csvRecord.get(4);
                String address = csvRecord.get(6);
                String zip = csvRecord.get(9);
                String date = csvRecord.get(10);
                String result = csvRecord.get(12);
                String violation = csvRecord.get(13);
                String latitude = csvRecord.get(14);
                String longitude = csvRecord.get(15);
                

                if (type.equals("Restaurant")) {
                	out.printRecord(id, name, license, type, address, zip, date, result,violation, latitude, longitude);
                }
                	
                	

            }
				
		} catch(Exception e) {
			 System.out.println("Error - " + e);
            
        }
	}
}
