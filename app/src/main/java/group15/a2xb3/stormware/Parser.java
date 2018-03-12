package group15.a2xb3.stormware;//package group15.a2xb3.stormware;

/**
 * Created by David on 2018-03-05.
 */
import android.provider.ContactsContract;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.io.IOException;

import group15.a2xb3.stormware.DisasterEvent;

public class Parser {
  public static void parseCSV(final String fileName) {
      try {
          BufferedReader br = new BufferedReader(new FileReader(fileName));

          int c = 0;
          String line = br.readLine();    // Ignore first line (table header)
          while ((line = br.readLine()) != null) {

              String[] tokens = line.split(",");
              //StringTokenizer st = new StringTokenizer(line, ",", false);
              String type = null;
              double lat1 = 0;
              double lng1 = 0;
              double lat2 = 0;
              double lng2 = 0;
              int year = 0;
              int month = 0;
              int day = 0;
              double magnitude = 0;

              for (int i = 0; i < tokens.length; i++) {
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
                      case 27:
                          magnitude = Double.parseDouble(tokens[i]);
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


                  DisasterEvent de = new DisasterEvent(type, new LatLng((lat1 + lat2) / 2, (lng1 + lng2) / 2), year, month, day, magnitude);
              }

              br.close();
          }
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
