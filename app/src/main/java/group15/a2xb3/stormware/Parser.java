//package group15.a2xb3.stormware;

/**
 * Created by David on 2018-03-05.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Parser {
  public static void parseCSV(final String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			
			int c = 0;
			String line = br.readLine();	// Ignore first line (table header)
			while((line = br.readLine()) != null) {
				System.out.println(line);
				System.out.println(c++);
				StringTokenizer st = new StringTokenizer(line, ",", false);
				String type = null;
				double lat1 = 0;
				double lng1 = 0;
				double lat2 = 0;
				double lng2 = 0;
				int year = 0;
				int month = 0;
				int day = 0;
				double magnitude = 0;
				
				int counter = 0;
				while (st.hasMoreTokens()) {
					switch (counter) {
					case 0:
						String tkn = next(st, counter);
						year = Integer.parseInt(tkn.substring(0, 4));
						month = Integer.parseInt(tkn.substring(4, 6));
						break;
					case 1:
						day = Integer.parseInt(next(st, counter));
						break;
					case 12:
						type = next(st, counter);
						break;
					case 27:
						magnitude = Double.parseDouble(next(st, counter));
						break;
					case 44:
						lat1 = Double.parseDouble(next(st, counter));
						break;
					case 45:
						lng1 = Double.parseDouble(next(st, counter));
						break;
					case 46:
						lat2 = Double.parseDouble(next(st, counter));
						break;
					case 47:
						lng2 = Double.parseDouble(next(st, counter));
						break;
					default:
						next(st, counter);
						break;
					}
					counter ++;
				}
				
				DisasterEvent de = new DisasterEvent(type, new LatLng((lat1 + lat2) / 2, (lng1 + lng2) / 2), year, month, day, magnitude);
				Data.add(de);
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static String next(StringTokenizer st, int c) {
		System.out.println(c);
		return st.nextToken();
	}
}
