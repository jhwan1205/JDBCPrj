package com.newlecture.app.console;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.newlecture.app.entity.Notice;
import com.newlecture.app.service.NoticeService;

public class NoticeConsole {

	private NoticeService service;
	private int page;
	private String searchField;
	private String searchWord;
	private int searchId;
	
	public NoticeConsole() {
		service=new NoticeService();
		page=1;
		searchField="title";
		searchWord="%";
	}
	
	public void detailData() {
		Scanner scan =new Scanner(System.in);
		
		System.out.print("상세조회할 id를 입력하세요 >");
		String id_= scan.nextLine();
		int id=Integer.parseInt(id_);
		
		
		
	}

	public void printNoticeList() throws ClassNotFoundException, SQLException {
		List<Notice> list=service.getList(page,searchField,searchWord);
		int count=service.getCount();
		int lastPage=count/10; 
		lastPage=count%10>0?lastPage+1:lastPage;
		
		
		System.out.println("────────────────────────────────────");
		System.out.printf("<공지사항> 총%d 게시글\n",count);
		System.out.println("────────────────────────────────────");
		
		for(Notice n:list) {
			System.out.printf("%d. %s / %s / %s\n",
					n.getId(),n.getTitle(),n.getWriterId(),n.getRegDate());
			
		}
		
		System.out.println("────────────────────────────────────");
		System.out.printf("               %d/%d pages\n",page,lastPage);
	}

	public int inputNoticeMenu() {
		Scanner scan =new Scanner(System.in);
		
		System.out.print("1.상세조회/ 2.이전/ 3.다음/ 4.글쓰기/ 5.검색/ 6.종료 >");
		String menu_ = scan.nextLine();
		int menu=Integer.parseInt(menu_);	//menu_를 int형식으로 변환
		
		return menu;
	}

	public void movePrevList() {
		if(page==1) {
			System.out.println("================");
			System.out.println("이전페이지가 없습니다.");
			System.out.println("================");
			return;
		}
		page--;
		
	}

	public void movNextList() throws ClassNotFoundException, SQLException {
		int count=service.getCount();
		int lastPage=count/10; 
		lastPage=count%10>0?lastPage+1:lastPage;
		if(page==lastPage) {
			System.out.println("================");
			System.out.println("이후페이지가 없습니다.");
			System.out.println("================");
			return;
		}
		page++;
	}
	
	public void insertData() throws ClassNotFoundException, SQLException {
		Scanner scan =new Scanner(System.in);
		System.out.println("TITLE을 입력하세요 > ");
		System.out.println(">");
		String title1=scan.nextLine();
		System.out.println("검색어 > ");
		System.out.println("writerId을 입력하세요 > ");
		System.out.println(">");
		String writerId1=scan.nextLine();
		System.out.println("검색어 > ");
		System.out.println("content을 입력하세요 > ");
		System.out.println(">");
		String content1=scan.nextLine();
		System.out.println("검색어 > ");
		System.out.println("files을 입력하세요 > ");
		System.out.println(">");
		String files1=scan.nextLine();
		System.out.println("검색어 > ");
		
		Notice notice =new Notice(title1,writerId1,content1,files1);
		service.insert(notice);
	}

	public void inputSerchWord() {
		Scanner scan =new Scanner(System.in);
		System.out.println("검색 범주(title/content/writerId)중 하나를 입력하세요");
		System.out.println(">");
		searchField=scan.nextLine();
		System.out.println("검색어 > ");
		searchWord=scan.nextLine();
		
	}


	
}
