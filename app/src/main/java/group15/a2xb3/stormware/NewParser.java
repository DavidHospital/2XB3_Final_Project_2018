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

    public static void main(String[] args) {
        firstParser("2017.csv");
        System.out.println("parsing complete");
    }

    public static void firstParser(final String fileName) {

        // FileReader reads text files in the default encoding.
        //FileReader fileReader =
        try {

            BufferedReader br =
                    new BufferedReader(new FileReader("app/src/main/assets/" + fileName));

            BufferedWriter bufferedWriter =
                    new BufferedWriter(new FileWriter("app/src/main/assets/" + "c" + fileName));


            String line = br.readLine();

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

                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < tokens.length; i++) {
                    if (tokens[i].length() > 0 && tokens[i].charAt(0) == '\"') {
                        tokens[i] = tokens[i].substring(1, tokens[i].length() - 1);
                    }
                    switch (i) {
                        case 0:
                            String tkn = tokens[i];
                            year = Integer.parseInt(tkn.substring(0, 4));
                            month = Integer.parseInt(tkn.substring(4, 6));
                            sb.append(year + "," + month);
                            break;
                        case 1:
                            day = Integer.parseInt(tokens[i]);
                            sb.append("," + day);
                            break;
                        case 12:
                            type = tokens[i].trim().toUpperCase();
                            sb.append("," + type);
                            break;
                        case 44:
                            try {
                                lat1 = Double.parseDouble(tokens[i]);
                            } catch (NumberFormatException e) {
                                continue outerloop;
                            }
                            sb.append("," + lat1);
                            break;
                        case 45:
                            try {
                                lng1 = Double.parseDouble(tokens[i]);
                            } catch (NumberFormatException e) {
                                continue outerloop;
                            }
                            sb.append("," + lng1);
                            break;
                        case 46:
                            try {
                                lat2 = Double.parseDouble(tokens[i]);
                            } catch (NumberFormatException e) {
                                continue outerloop;
                            }
                            sb.append("," + lat2);
                            break;
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


    public static void secondParser(final String fileName, Context mContext)
    {
        try {
            InputStream f = mContext.getAssets().open(fileName);

            BufferedReader br =
                    new BufferedReader(new InputStreamReader(f));

            String line = br.readLine();

            while (line != null) {

                String[] details = line.split(",");

                int year = Integer.parseInt(details[0]);
                int month = Integer.parseInt(details[1]);
                int day = Integer.parseInt(details[2]);
                String type = details[3].trim().toUpperCase();
                double lat1 = Double.parseDouble(details[4]);
                double lng1 = Double.parseDouble(details[5]);
                double lat2 = Double.parseDouble(details[6]);
                double lng2 = Double.parseDouble(details[7]);

                line = br.readLine();

                DisasterEvent de = new DisasterEvent(type, new LatLng(lat1, lng1), new LatLng(lat2, lng2), year, month, day);
                Data.add(de);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
