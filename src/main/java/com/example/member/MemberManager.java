package com.example.member;

import java.util.Scanner;

public class MemberManager {
    MemberDAO dao = new MemberDAO();
    Scanner sc = new Scanner(System.in);

    public void readMenu() {
        while (true) {
            System.out.println("목록을 원하시면 1번을 입력하세요.");
            System.out.println("등록을 원하시면 2번을 입력하세요.");
            System.out.println("수정을 원하시면 3번을 입력하세요.");
            System.out.println("삭제를 원하시면 4번을 입력하세요..");
            System.out.println("종료를 원하시면 5번을 입력하세요..");

            int number = sc.nextInt();

            if (number == 1) {
                dao.getMemberList();
            }
            else if (number == 2) {
                dao.insertMember();
            }
            else if (number == 3) {
                dao.updateMember();
            }
            else if (number == 4) {
                dao.deleteMember();
            }
            else {
                sc.close();
                break;
            }
        }
    }
}