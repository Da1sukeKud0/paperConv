package paperConv;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PaperConv {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("usage: java paperConv/PaperConv input.txt output.txt");
			System.exit(1);
		}

		// Load input file into FileClass
		File file = new File(args[0]);

		if (file.exists()) {

			// Create FileReaderClass object
			FileReader filereader = new FileReader(file);

			// Read file one character at a time with filereader.read()
			int data;
			String moji;
			ArrayList<String> strArrList = new ArrayList<String>();
			while ((data = filereader.read()) != -1) {
				if ((moji = String.valueOf((char) data)) != null)
					strArrList.add(moji);
			}

			removeEnter(strArrList);
			eosToEnter(strArrList);

			// Create output file
			File newfile = new File(args[1]);
			FileWriter filewriter = new FileWriter(newfile);
			for (int i = 0; i < strArrList.size(); i++) {
				filewriter.write(strArrList.get(i));
			}
			filewriter.close();
			newfile.createNewFile();

			// Close file
			filereader.close();
			System.out.println("process is end");
		}
	}

	private static ArrayList<String> removeEnter(ArrayList<String> strArrList) {
		// unix: "\n" to " "
		String ostype = System.getProperty("os.name");
		System.out.println(ostype);
		switch (ostype) {
		case "Windows":
			// windowsの改行コードは\r\n
			for (int i = 0; i < strArrList.size(); i++) {
				if (strArrList.get(i).equals("\r")) {
					if (strArrList.get(i + 1).equals("\n")) {
						strArrList.set(i, " ");
						strArrList.remove(i + 1);
					}
				}
			}
			break;
		default:
			// Unix系の改行コードは\n
			for (int i = 0; i < strArrList.size(); i++) {
				if (strArrList.get(i).equals("\n")) {
					strArrList.set(i, " ");
				}
			}
		}
		return strArrList;
	}

	private static ArrayList<String> eosToEnter(ArrayList<String> strArrList) {
		// ". "を検知し、改行
		for (int i = 0; i < strArrList.size() - 1; i++) {
			if (strArrList.get(i).equals(".")) { // 参照型を==で比較するとアドレスを比べられちゃうのでequals()
				System.out.println("find dot");
				if (strArrList.get(i + 1).equals(" ")) {
					strArrList.set(i + 1, "\n");
					System.out.println("kaigyo");
					i++;
				}
			}
		}
		return strArrList;
	}
}
