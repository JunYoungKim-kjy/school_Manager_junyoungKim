package DAO;

import java.util.ArrayList;
import java.util.Arrays;

import Utils.Util;
import VO.Student;

public class StudentDAO {
	private ArrayList<Student>stuList;
	private int maxNo;
	public StudentDAO(){
		maxNo = 1001;
		stuList = new ArrayList<Student>();
	}
	public void loadFromFile(String data) {
		String[] temp = data.split("\n");
		for(int i=0; i < temp.length; i+=1) {
			String[] info = temp[i].split("/");
			stuList.add(i,new Student(Integer.parseInt(info[0]),info[1],info[2]));
		}
	}
	public void setMaxNo() {
		if(stuList.size()==0)return;
		int max = 0;
		for(Student stu : stuList) {
			if(max < stu.stuNo)max = stu.stuNo;
		}
		this.maxNo = max;
	}
	public void insertStudent() {
		String id = Util.getString("아이디 입력");
		Student stu = getStudentById(id);
		if(stu != null) {
			System.out.println("이미 존재하는 아이디 입니다.");
			return;
		}
		String name = Util.getString("이름 입력");
		stu = new Student(stuList.size()==0?maxNo:++maxNo,name,id);
		stuList.add(stu);
		System.out.print(stu);
		System.out.println("학생 추가 완료");
	}
	public Student getStudentById(String id) {
		for(Student stu : stuList) {
			if(stu.stuId.equals(id))	return stu;
		}
		return null;
	}
	public void deleteStudent(SubjectDAO subDAO) {
		if(!hasData())return;
		String id = Util.getString("아이디 입력");
		Student stu = getStudentById(id);
		if(stu ==null) {
			System.out.println("존재하지 않는 아이디 입니다.");
			return;
		}
		subDAO.deleteAllSubjectOfDelStudent(stu);
		stuList.remove(stu);
		System.out.println(stu.stuName + " [삭제 완료]");
	}
	private boolean hasData() {
		if(stuList.size()==0) {
			System.out.println("데이터가 존재하지 않습니다.");
			return false;
		}
		return true;
	}
	public void insertSubjectOfStudent(SubjectDAO subDAO) {
		if(!hasData())return;
		int no = Util.getValue("학번 입력 :", 1001, maxNo);
		Student stu = getStudentByNo(no);
		if(stu == null) {
			System.out.println("존재하는 학번의 학생이 없습니다.");
			return;
		}
		subDAO.insertSubjectOneStudent(stu);
	}
	private Student getStudentByNo(int no) {
		for(Student stu : stuList) {
			if(stu.stuNo==no)return stu; 
		}
		return null;
	}
	public void deleteSubjectOfStudent(SubjectDAO subDAO) {
		if(!hasData())return;
		int no = Util.getValue("학번 입력", 1001, maxNo);
		Student stu = getStudentByNo(no);
		if(stu == null) {
			System.out.println("존재하는 학번의 학생이 없습니다.");
			return;
		}
		subDAO.deleteSubjectOfDelStudent(stu);
	}
	public void printAllStudent(SubjectDAO subDAO) {
		if(!hasData())return;
		ArrayList<Student> list = new ArrayList<Student>();
		ArrayList<Double> avgList = new ArrayList<Double>();
//		Student[] list = new Student[stuList.size()];
//		double[] avg = new double[stuList.size()];
//		int idx = 0;
		for(Student stu : stuList) {
			list.add(stu);
			avgList.add(subDAO.getAvgStudentScore(stu));
		}
		
		for(int i = 0; i < avgList.size(); i+=1) {
			double max = avgList.get(i);
			for(int k=i; k < avgList.size(); k+=1) {
				if(max < avgList.get(k)) {
					max = avgList.get(k);
					double temp = avgList.get(k);
					avgList.set(k, avgList.get(i));
					avgList.set(i, temp);
					
					Student sTemp = list.get(k);
					list.set(k, list.get(i));
					list.set(i, sTemp);
				}
			}
		}
		int idx =0;
		for(Student stu : list) {
			System.out.print(stu);
			subDAO.printSubjectsOfOneStudent(stu);
			if(avgList.get(idx) > 0) {
				System.out.println();
			System.out.println("평균 = " +avgList.get(idx++)+"점");
			}
			System.out.println("==========");
		}
	}
	public void printSubjectNameList(int[] list,SubjectDAO subDAO) {
		ArrayList<Student> tempList = new ArrayList<Student>();
		int idx= 0;
		for(Student stu : stuList) {
			if(idx == list.length)break;
			if(stu.stuNo == list[idx++]){
				tempList.add(stu);
			}
		}
		for(int i=0; i < list.length;i+=1) {
			for(int k=i; k< list.length;k+=1) {
				if(tempList.get(k).stuName.compareTo(tempList.get(i).stuName) < 0) {
					Student temp = tempList.get(k);
					tempList.set(k, tempList.get(i));
					tempList.set(i, temp);
				}
			}
		}
		for(Student stu : tempList) {
			System.out.print(stu);
			subDAO.printSubjectsOfOneStudent(stu);
			System.out.println();
			System.out.println("==========");
		}
		
	}
	public String getData() {
		String data = "";
		for(Student stu : stuList) {
			data += stu.getData();
		}
		return data;
	}
}
