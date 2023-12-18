package School_김준영Ver3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Util {
	public static Scanner sc;
	public static Random rd;
	private File file;
	private static final String CUR_PATH = System.getProperty("user.dir") + "\\src\\"
			+Util.class.getPackageName()+"\\";;
	private static Util instance = new Util();
	private Util(){
		sc = new Scanner(System.in);
		rd = new Random();
		init();
	}
	private void init() {
		fileInit("student.txt");
		fileInit("subject.txt");
	}
	public static int getValue(String msg,int start,int end) {
		while (true) {
			System.out.printf("> %s (%d~%d)", msg, start, end);
			try {
				int input = sc.nextInt();
				if (input < start || input > end) {
					System.out.println("입력 범위 오류");
					continue;
				}
				return input;
			} catch (Exception e) {
				sc.nextLine();
				System.out.println("숫자값을 입력하세요.");
			}
		}
	}
	public static String getString(String msg) {
		System.out.println("> "+msg);
		return sc.next();
	}
	private void fileInit(String fileName) {
		file = new File(CUR_PATH + fileName);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private static String getDataFromFile(String fileName) {
		String data = "";
		try(FileReader fr = new FileReader(CUR_PATH + fileName);
			BufferedReader br = new BufferedReader(fr);){
			while(true) {
				String str = br.readLine();
				if(str == null) {
					break;
				}
				data += str + "\n";
			}
			System.out.println(fileName + "로드 성공");
		}catch (IOException e) {
			System.out.println(fileName + "로드 실패");
		}
		if(data.isEmpty()) {
			return null;
		}
		data = data.substring(0,data.length()-1);
		
		return data;
	}
	public static void saveFromDataToFile(StudentDAO stuDAO,SubjectDAO subDAO) {
		String stuData = stuDAO.getData();
		String subData = subDAO.getData();
		
		saveFile("student.txt", stuData);
		saveFile("subject.txt", subData);
	}
	private static void saveFile(String fileName,String data) {
		try(FileWriter fw = new FileWriter(CUR_PATH + fileName);){
			fw.write(data);
			System.out.println(fileName + "파일 저장 성공");
		} catch (IOException e) {
			System.out.println(fileName + "파일 저장 실패");
		}
	}
	public static void loadFile(StudentDAO stuDAO,SubjectDAO subDAO) {
		String stuData = getDataFromFile("student.txt");
		String subData = getDataFromFile("subject.txt");
		if(stuData !=null)stuDAO.loadFromFile(stuData);
		if(subData !=null)subDAO.loadFromFile(subData);
		stuDAO.setMaxNo();
	}



}
