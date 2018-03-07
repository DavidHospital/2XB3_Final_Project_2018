package group15.a2xb3.stormware;

/**
 * Created by David on 2018-03-05.
 */

public class Parser {
  public static void parseCSV(final String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			
			String line = br.readLine();	// Ignore first line (table header)
			while((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, ",", false);
				String type = null;
				double lat1 = 0;
				double lng1 = 0;
				double lat2 = 0;
				double lng2 = 0;
				int year;
				int month;
				int day;
				double magnitude = 0;
				
				int counter = 0;
				while (st.hasMoreTokens()) {
					switch (counter) {
					case 0:
						String tkn = st.nextToken();
						year = Integer.parseInt(tkn.substring(0, 4));
						month = Integer.parseInt(tkn.substring(4, 6));
						break;
					case 1:
						day = Integer.parseInt(st.nextToken());
						break;
					case 12:
						type = st.nextToken();
						break;
					case 27:
						magnitude = Double.parseDouble(st.nextToken());
						break;
					case 44:
						lat1 = Double.parseDouble(st.nextToken());
						break;
					case 45:
						lng1 = Double.parseDouble(st.nextToken());
						break;
					case 46:
						lat2 = Double.parseDouble(st.nextToken());
						break;
					case 47:
						lng2 = Double.parseDouble(st.nextToken());
						break;
					}
					counter ++;
				}
				
				Date d = new Date();
				DisasterEvent de = new DisasterEvent(type, new LatLng((lat1 + lat2) / 2, (lng1 + lng2) / 2), d, magnitude);
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
