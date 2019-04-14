package ex12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class EJDic {
    private HashMap<String,String> map;
   
    public EJDic() {
	map = new HashMap<String,String>();
    }

    public void open(String filename) { // ファイルからの読み込み
	try {
	    map.clear();
	    File file = new File(filename);
            BufferedReader reader = new BufferedReader(new FileReader(file));
	    String line;
            while((line = reader.readLine()) != null) {
                String[] field = line.split(",");
		put(field[0], field[1]);
            }
	    reader.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void save(String filename) { // ファイルへの書き出し
	try {
	    File file = new File(filename);
	    PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
	    for (Map.Entry<String,String> entry : map.entrySet()) {
		writer.println(entry.getKey() + "," + entry.getValue());
	    } 
	    writer.close();
	}catch(Exception e) {
	    e.printStackTrace();
	}
    }

    public String get(String eng) { // 英語を指定して日本語を得る
	return map.get(eng);
    }

    public void put(String eng, String jap) { // 英語と日本語の組を登録する
	map.put(eng, jap);
    }

    public void remove(String eng) { // 英語に対応する組を削除する
	map.remove(eng);
    }

    public void showWords() { // 一覧をプリント文で出力する
	for (Map.Entry<String,String> entry : map.entrySet()) {
	    System.out.println(entry.getKey() + "," + entry.getValue());
	}
    }

    public Set<String> keySet() { // 英単語の一覧を得る
	return map.keySet();
    }

    public static void main(String[] args) { // 使ってみる
	EJDic dic = new EJDic();
	dic.open("dic.txt");
	dic.showWords();
	dic.put("banana", "バナナ");
	dic.showWords();
	dic.remove("banana");
	dic.showWords();
	dic.save("dic.txt");
    }
}