package group15.a2xb3.stormware;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;


public class NewParser {

    // Run the first parser method. This is called from a separate program only once per file
    public static void main(String[] args) {
        firstParser("2017.csv");
        System.out.println("parsing complete");
    }

    // Parse the raw csv file with over 30 columns
    // and condense it down to 8 for faster parsing in the future.
    public static void firstParser(final String fileName) {

        try {

            // FileReader reads text files in the default encoding.
            BufferedReader br =
                    new BufferedReader(new FileReader("app/src/main/assets/" + fileName));

            BufferedWriter bufferedWriter =
                    new BufferedWriter(new FileWriter("app/src/main/assets/" + "c" + fileName));


            // Skip the first line (column headers)
            String line = br.readLine();

            // jump here if a row has insufficiant data (skip over it)
            outerloop:
            while ((line = br.readLine()) != null) {


                // Split the line by commas
                String[] tokens = line.split(",");

                // Input variables for DisasterEvent object
                String type = null;
                double lat1 = 0;
                double lng1 = 0;
                double lat2 = 0;
                double lng2 = 0;
                int year = 0;
                int month = 0;
                int day = 0;

                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < tokens.length; i++) {
                    if (tokens[i].length() > 0 && tokens[i].charAt(0) == '\"') {
                        tokens[i] = tokens[i].substring(1, tokens[i].length() - 1);
                    }

                    // Which column?
                    switch (i) {

                        // Parse the year and month
                        case 0:
                            String tkn = tokens[i];
                            year = Integer.parseInt(tkn.substring(0, 4));
                            month = Integer.parseInt(tkn.substring(4, 6));
                            sb.append(year + "," + month);
                            break;

                        // Parse the day
                        case 1:
                            day = Integer.parseInt(tokens[i]);
                            sb.append("," + day);
                            break;

                        // Parse the type of event
                        case 12:
                            type = tokens[i].trim().toUpperCase();
                            sb.append("," + type);
                            break;

                        // Parse the Starting latitude
                        case 44:
                            try {
                                lat1 = Double.parseDouble(tokens[i]);
                            } catch (NumberFormatException e) {
                                continue outerloop;
                            }
                            sb.append("," + lat1);
                            break;

                        // Parse the starting longitude
                        case 45:
                            try {
                                lng1 = Double.parseDouble(tokens[i]);
                            } catch (NumberFormatException e) {
                                continue outerloop;
                            }
                            sb.append("," + lng1);
                            break;

                        // Parse the ending latitude
                        case 46:
                            try {
                                lat2 = Double.parseDouble(tokens[i]);
                            } catch (NumberFormatException e) {
                                continue outerloop;
                            }
                            sb.append("," + lat2);
                            break;

                        // Parse the ending longitude
                        case 47:
                            try {
                                lng2 = Double.parseDouble(tokens[i]);
                            } catch (NumberFormatException e) {
                                continue outerloop;
                            }
                            sb.append("," + lng2);
                            break;
                    }
                }

                // Write the parsed row to the new file
                bufferedWriter.write(sb.toString());
                bufferedWriter.newLine();
            }

            br.close();
            bufferedWriter.close();


        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Used to parse the compacted file
    // 80% parsing speed improvement over the old parser module
    public static void secondParser(final String fileName, Context mContext)
    {
        try {
            // Open file
            InputStream f = mContext.getAssets().open(fileName);

            BufferedReader br =
                    new BufferedReader(new InputStreamReader(f));

            String line = br.readLine();
            while (line != null) {

                // Split each row by comma
                String[] details = line.split(",");

                // Each column is now relevant. No need to skip over any
                int year = Integer.parseInt(details[0]);
                int month = Integer.parseInt(details[1]);
                int day = Integer.parseInt(details[2]);
                String type = details[3].trim().toUpperCase();
                double lat1 = Double.parseDouble(details[4]);
                double lng1 = Double.parseDouble(details[5]);
                double lat2 = Double.parseDouble(details[6]);
                double lng2 = Double.parseDouble(details[7]);

                // Next row
                line = br.readLine();

                // Add a new event with data from the parsed row to be stored using the Data module.
                DisasterEvent de = new DisasterEvent(type, new LatLng(lat1, lng1), new LatLng(lat2, lng2), year, month, day);
                Data.add(de);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
