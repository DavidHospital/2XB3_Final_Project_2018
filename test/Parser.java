import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.io.IOException;


public class Parser {
  public static void parseCSV(final String fileName, Graph graph) {
      try {
          BufferedReader br = new BufferedReader(new FileReader(fileName));

          int c = 0;
          String line = br.readLine();    // Ignore first line (table header)
          while ((line = br.readLine()) != null) {

              String[] tokens = line.split(",");
              String type = null;
              double lat1 = 0;
              double lng1 = 0;
              double lat2 = 0;
              double lng2 = 0;
              int year = 0;
              int month = 0;
              int day = 0;

              for (int i = 0; i < tokens.length; i++) {
            	  if (tokens[i].charAt(0) == '\"') {
            		  tokens[i] = tokens[i].substring(1, tokens[i].length());
            	  }
                  switch (i) {
                      case 0:
                          String tkn = tokens[i];
                          year = Integer.parseInt(tkn.substring(0, 4));
                          month = Integer.parseInt(tkn.substring(4, 6));
                          break;
                      case 1:
                          day = Integer.parseInt(tokens[i]);
                          break;
                      case 12:
                          type = tokens[i];
                          break;
                      case 44:
                          lat1 = Double.parseDouble(tokens[i]);
                          break;
                      case 45:
                          lng1 = Double.parseDouble(tokens[i]);
                          break;
                      case 46:
                          lat2 = Double.parseDouble(tokens[i]);
                          break;
                      case 47:
                          lng2 = Double.parseDouble(tokens[i]);
                          break;
                  }


                  System.out.println(line);
                  DisasterEvent de = new DisasterEvent(type, new LatLng(lat1, lng1), new LatLng(lat2, lng2), year, month, day);
                  graph.addEvent(de);
              }
          }

          br.close();
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
  }

    static String next(StringTokenizer st, int c) {
		System.out.println(c);
		return st.nextToken();
	}
}