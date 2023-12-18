package School_김준영Ver3;

import java.util.ArrayList;
import java.util.Arrays;

public class SubjectDAO {
	private ArrayList<Subject>subList;
	public SubjectDAO(){
		subList = new ArrayList<Subject>();
	}
	public void loadFromFile(String data) {
		String[] temp = data.split("\n");
		for(int i=0; i < temp.length; i+=1) {
			String[] info = temp[i].split("/");
			subList.add(i,new Subject(Integer.parseInt(info[0]),info[1],Integer.parseInt(info[2])));
		}
	}
	public void deleteAllSubjectOfDelStudent(Student stu) {
		for(int i=0; i < subList.size();i+=1) {
			if(subList.get(i).stuNo==stu.stuNo) {
				subList.remove(i);
				i-=1;
			}
		}
	}
	public void insertSubjectOneStudent(Student stu) {
		String name = Util.getString("과목이름 입력");
		Subject sub = getSubjectByNameAndStudentNo(stu, name);
		if(sub != null) {
			System.out.println("해당 학번에 이미 존재하는 과목입니다.");
			return;
		}
		int score = Util.rd.nextInt(51)+50;
		sub = new Subject(stu.stuNo,name,score);
		subList.add(sub);
		System.out.print(sub);
		System.out.println("추가 완료");
	}
	public Subject getSubjectByNameAndStudentNo(Student stu,String name) {
		for(Subject sub : subList) {
			if(sub.subName.equals(name) && sub.stuNo==stu.stuNo) {
				subList.remove(sub);
				return sub;
			}
		}
		return null;
	}
	public void deleteSubjectOfDelStudent(Student stu) {
		if(!hasData(stu))return;
		String name = Util.getString("과목이름 입력");
		Subject sub = getSubjectByNameAndStudentNo(stu, name);
		if(sub ==null) {
			System.out.println("학생에게 존재하지 않는 과목입니다.");
			return;
		}
		subList.remove(sub);
		System.out.println(name + "과목 삭제 완료");
	}
	public double getAvgStudentScore(Student stu) {
		int sum=0;
		int cnt=0;
		for(Subject sub :subList) {
			if(sub.stuNo==stu.stuNo) {
				sum += sub.score;
				cnt+=1;
			}
		}
		return sum * 1.0 / cnt;
	}
	public void printSubjectsOfOneStudent(Student stu) {
		for(Subject sub : subList) {
			if(sub.stuNo==stu.stuNo) {
				System.out.print(sub);
			}
		}
	}
	public void printOneSubjectName(StudentDAO stuDAO,SubjectDAO subDAO) {
		String name = Util.getString("과목이름 입력");
		int cnt = 0;
		for(Subject sub :subList) {
			if(sub.subName.equals(name)) {
				cnt +=1;
			}
		}
		if(cnt ==0) {
			System.out.println("과목이 존재하지 않습니다.");
			return;
		}
		int[] list = new int[cnt];
		int idx = 0;
		for(Subject sub :subList) {
			if(sub.subName.equals(name)) {
				list[idx++] = sub.stuNo;
			}
		}
		stuDAO.printSubjectNameList(list,subDAO);
		
	}
	public String getData() {
		String data = "";
		for(Subject sub : subList) {
			data += sub.getData();
		}
		return data;
	}
	private boolean hasData(Student stu) {
		for(Subject sub : subList) {
			if(stu.stuNo==sub.stuNo) {
				return true;
			}
		}
		System.out.println("과목이 존재하지않습니다.");
		return false;
	}
}
