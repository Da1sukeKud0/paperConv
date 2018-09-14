package paperConv;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PaperConv {

	public static void main(String[] args) throws IOException {
		//Load input file into FileClass
		File file = new File(args[0]);

		if(file.exists()) {

			//Create FileReaderClass object
			FileReader filereader = new FileReader(file);

			//Read file one character at a time with filereader.read()
			int data;
			String moji;
			ArrayList<String> hoge = new ArrayList<String>();
			while((data = filereader.read()) != -1) {
				if((moji = String.valueOf((char)data)) != null)hoge.add(moji);
			}

			removeEnter(hoge);
			eosToEnter(hoge);

			//Create output file
			File newfile = new File(args[1]);
			FileWriter filewriter = new FileWriter(newfile);
			for(int i=0;i<hoge.size();i++){
				filewriter.write(hoge.get(i));
			}
			filewriter.close();
			newfile.createNewFile();

			//Close file
			filereader.close();
			System.out.println("process is end");
		}
	}

	private static ArrayList<String> removeEnter(ArrayList<String> hoge){
		//unix: "\n" to " "
		for(int i=0;i<hoge.size();i++){
<<<<<<< HEAD
			if(hoge.get(i).equals("\n")) hoge.set(i," ");
=======
			if(hoge.get(i).equals("\n")) {
				hoge.set(i," ");
			}
>>>>>>> 841fef9b26cbe3a713b343be770287d69c337abd
		}
		//win: "\r\n" to " "
		//			for(int i=0;i<hoge.size();i++){
		//				if(hoge.get(i).equals("\r")) {
		//					if(hoge.get(i+1).equals("\n")) {
		//						hoge.set(i," ");
		//						hoge.remove(i+1);
		//					}
		//				}
		//			}
		return hoge;
	}
	private static ArrayList<String> eosToEnter(ArrayList<String> hoge){
		//". "を検知し、改行
		for(int i=0;i<hoge.size()-1;i++){
			if(hoge.get(i).equals(".")){ //参照型を==で比較するとアドレスを比べられちゃうのでequals()
				System.out.println("find dot");
				if (hoge.get(i+1).equals(" ")){
					hoge.set(i+1,"\n");System.out.println("kaigyo");i++;
				}
			}
		}
		return hoge;
	}
}
