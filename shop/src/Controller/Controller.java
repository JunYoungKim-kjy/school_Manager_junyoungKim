package Controller;

import DAO.StudentDAO;
import DAO.SubjectDAO;
import Utils.Util;

public class Controller {
	private StudentDAO stuDAO;
	private SubjectDAO subDAO;
	public Controller(){
		stuDAO = new StudentDAO();
		subDAO = new SubjectDAO();
		Util.loadFile(stuDAO, subDAO);
	}
	private void mainMenu() {
	System.out.println("[1]학생추가"); //학번(1001) 자동증가 : 학생id중복 불가
	System.out.println("[2]학생삭제"); //학생 id 입력 후 삭제 과목도 같이 삭제
	System.out.println("[3]학생의과목추가"); //학번 입력 후 점수 랜덤 50 - 100 : 과목이름 중복 저장 불가능
	System.out.println("[4]학생의과목삭제"); //학번 입력 후 과목 이름 받아서 해당 과목에서 학생 1명 삭제
	System.out.println("[5]전체학생목록");  //과목이름과 과목점수로 출력
	System.out.println("[6]과목별학생목록"); //과목이름 입력받아서 해당 과목 학생이름과 과목점수 출력(이름오름차순)
	System.out.println("[7]파일 저장");
	System.out.println("[8]파일 로드");
	System.out.println("[0]종료");
	}
	public void run() {
		while(true) {
			mainMenu();
			int sel = Util.getValue("[메뉴선택] 메뉴입력", 0, 8);
			if(sel == 0) {
				System.out.println("[종료]");
				return;
			}else if(sel ==1) {
				System.out.println("======[학생 추가]=======");
				stuDAO.insertStudent();
			}else if(sel ==2) {
				System.out.println("======[학생 삭제]=======");
				stuDAO.deleteStudent(subDAO);
			}else if(sel ==3) {
				System.out.println("======[과목 추가]=======");
				stuDAO.insertSubjectOfStudent(subDAO);
			}else if(sel ==4) {
				System.out.println("======[과목 삭제]=======");
				stuDAO.deleteSubjectOfStudent(subDAO);
			}else if(sel ==5) {
				System.out.println("======[전체 출력]=======");
				stuDAO.printAllStudent(subDAO);
			}else if(sel ==6) {
				System.out.println("======[과목 출력]=======");
				subDAO.printOneSubjectName(stuDAO,subDAO);
			}else if(sel ==7) {
				System.out.println("======[파일 저장]=======");
				Util.saveFromDataToFile(stuDAO,subDAO);
			}else if(sel ==8) {
				System.out.println("======[파일 로드]=======");
				Util.loadFile(stuDAO, subDAO);
			}
		}
	}
}
