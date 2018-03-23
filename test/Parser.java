package test;

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
          
          outerloop:
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
            	  if (tokens[i].length() > 0 && tokens[i].charAt(0) == '\"') {
            		  tokens[i] = tokens[i].substring(1, tokens[i].length() - 1);
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
                    	  	try {
                          lat1 = Double.parseDouble(tokens[i]);
                    	  	} catch (NumberFormatException e) {
                    	  		continue outerloop;
                    	  	}
                    	  	break;
                      case 45:
                          try {
                              lng1 = Double.parseDouble(tokens[i]);
                        	  	} catch (NumberFormatException e) {
                        	  		continue outerloop;
                        	  	}
                          break;
                      case 46:
                          try {
                              lat2 = Double.parseDouble(tokens[i]);
                        	  	} catch (NumberFormatException e) {
                        	  		continue outerloop;
                        	  	}
                          break;
                      case 47:
                          try {
                              lng2 = Double.parseDouble(tokens[i]);
                        	  	} catch (NumberFormatException e) {
                        	  		continue outerloop;
                        	  	}
                          break;
                  }


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
      

      System.out.println("Parsing Complete");
  }

    static String next(StringTokenizer st, int c) {
		System.out.println(c);
		return st.nextToken();
	}
}