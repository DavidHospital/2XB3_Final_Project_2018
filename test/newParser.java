package group15.a2xb3.stormware;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;


public class Parser {

public static void firstParser(final String fileName, Context mContext) {

  // FileReader reads text files in the default encoding.
            //FileReader fileReader =
            try {

            new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            InputStream f = mContext.getAssets().open(fileName);

            BufferedReader br =
                new BufferedReader(new InputStreamReader(f));

            FileWriter fileWriter =
                new FileWriter(writeFile);

            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);


            String line = br.readLine();

            outerloop:
            while (line = br.readLine() != null) {

            for (int i = 0; i < tokens.length; i++) {
              if (tokens[i].length() > 0 && tokens[i].charAt(0) == '\"') {
                tokens[i] = tokens[i].substring(1, tokens[i].length() - 1);
              }
                switch (i) {
                    case 0:
                        String tkn = tokens[i];
                        year = Integer.parseInt(tkn.substring(0, 4));
                        month = Integer.parseInt(tkn.substring(4, 6));
                        bufferedWriter.write(year, "," , month);
                        break;
                    case 1:
                        day = Integer.parseInt(tokens[i]);
                        bufferedWriter.write(",", day);
                        break;
                    case 12:
                        type = tokens[i].trim().toUpperCase();
                        bufferedWriter.write(",", type);
                        break;
                    case 44:
                        try {
                        lat1 = Double.parseDouble(tokens[i]);
                        } catch (NumberFormatException e) {
                          continue outerloop;
                        }
                        bufferedWriter.write(",", lat1);
                        break;
                    case 45:
                        try {
                            lng1 = Double.parseDouble(tokens[i]);
                            } catch (NumberFormatException e) {
                              continue outerloop;
                            }
                            bufferedWriter.write(",", lng1);
                        break;
                    case 46:
                        try {
                            lat2 = Double.parseDouble(tokens[i]);
                            } catch (NumberFormatException e) {
                              continue outerloop;
                            }
                            bufferedWriter.write(",", lat2);
                        break;
                    case 47:
                        try {
                            lng2 = Double.parseDouble(tokens[i]);
                            } catch (NumberFormatException e) {
                              continue outerloop;
                            }
                            bufferedWriter.write(",", lng2);
                        break;

                    bufferedWriter.newLine();

                  }

                  br.close();
                  bufferedWriter.close();
                }
              }


            }catch(FileNotFoundException ex) {
        System.out.println(
            "Unable to open file '" +
            fileName + "'");
          }
}


public static void secondParser(final String readFile) //Context mContext)
{
  FileReader fileReader = new FileReader(readFile);

  BufferedReader br = new BufferedReader(fileReader);

  line = br.readLine;

  while (line != null) {

    String[] details = line.split(",");

    //tokens[i] = tokens[i].substring(1, tokens[i].length() - 1);

    year = Integer.parseInt(details[0]);
    month = Integer.parseInt(details[1]);
    day = Integer.parseInt(details[2]);
    type = details[3].trim().toUpperCase();
    lat1 = Double.parseDouble(details[4]);
    lat2 = Double.parseDouble(details[5]);
    lng1 = Double.parseDouble(details[6]);
    lng2 = Double.parseDouble(details[7]);

    line = br.readLine();

    DisasterEvent de = new DisasterEvent(type, new LatLng(lat1, lng1), new LatLng(lat2, lng2), year, month, day);
    Data.add(de);


    }

  }


/*
  public static void parseCSV(final String fileName, Context mContext) {
      try {
          System.out.println(fileName);
          InputStream f = mContext.getAssets().open(fileName);
          BufferedReader br = new BufferedReader(new InputStreamReader(f));
          System.out.println(fileName);

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
                          type = tokens[i].trim().toUpperCase();
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

              }


              DisasterEvent de = new DisasterEvent(type, new LatLng(lat1, lng1), new LatLng(lat2, lng2), year, month, day);
              Data.add(de);
          }

          br.close();
      } catch (FileNotFoundException e) {
          e.-();
      } catch (IOException e) {
          e.printStackTrace();
      }


      System.out.println("Parsing Complete");
  }

  */


    static String next(StringTokenizer st, int c) {
		System.out.println(c);
		return st.nextToken();
	}

}
