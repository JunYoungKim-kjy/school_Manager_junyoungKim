package VO;

public class Student {
	public int stuNo;
	public String stuName;
	public String stuId;
	public Student(int stuNo, String stuName, String stuId) {
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.stuId = stuId;
	}
	@Override
	public String toString() {
		return stuNo + " " + stuName + " " + stuId + "\n";
	}
	public String getData() {
		String data = "";
		data = "%d/%s/%s\n".formatted(stuNo,stuName,stuId);
		return data;
	}
	
}
