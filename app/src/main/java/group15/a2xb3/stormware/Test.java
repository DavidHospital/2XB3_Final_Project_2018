import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		Parser.parseCSV("StormEvents_details-ftp_v1.0_d2004_c20170717.csv");
		
		ArrayList<DisasterEvent> data = Data.getList("Tornado");
		for (int i = 0; i < data.size(); i ++) {
			System.out.println(data.get(i));
		}
	}
}