package VO;

public class Subject {
	public int stuNo;
	public String subName;
	public int score;
	public Subject(int stuNo, String subName, int score) {
		this.stuNo = stuNo;
		this.subName = subName;
		this.score = score;
	}
	@Override
	public String toString() {
		return subName + " " + score + "점 ";
	}
	public String getData() {
		String data = "";
		data = "%d/%s/%d\n".formatted(stuNo,subName,score);
		return data;
	}
}
