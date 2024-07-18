package com.example.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MemberDAO {
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    private final String MEMBER_LIST = "select * from member";
    private final String MEMBER_INSERT = "insert into member values(?, ?, ?)";
    private final String MEMBER_UPDATE = "update member set phone_number = ? where member_id = ?";
    private String MEMBER_DELETE = "delete member where member_id = ?";

    Scanner scan = new Scanner(System.in);

    // 회원 삭제
    public void deleteMember() {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(MEMBER_DELETE);

            System.out.print("삭제할 아이디를 입력하세요 (형식 M-00001): ");
            String member_id = scan.next();

            stmt.setString(1, member_id);
            stmt.executeUpdate();

            System.out.println("---> " + member_id + "회원 삭제에 성공하셨습니다.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    // 회원 수정
    public void updateMember() {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(MEMBER_UPDATE);

            System.out.print("수정할 아이디를 입력하세요 (형식 M-00001): ");
            String member_id = scan.next();

            System.out.print("수정할 전화번호를 입력하세요 : ");
            String phone_number = scan.next();

            stmt.setString(1, phone_number);
            stmt.setString(2, member_id);
            stmt.executeUpdate();

            System.out.println("---> 회원 수정에 성공하셨습니다.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    // 회원 삽입
    public void insertMember() {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(MEMBER_INSERT);

            System.out.print("아이디를 입력하세요 (형식 M-00001): ");
            String member_id = scan.next();

            System.out.print("이름을 입력하세요 : ");
            String name = scan.next();

            System.out.print("전화번호를 입력하세요 : ");
            String phone_number = scan.next();

            stmt.setString(1, member_id);
            stmt.setString(2, name);
            stmt.setString(3, phone_number);
            stmt.executeUpdate();

            System.out.println("---> 회원가입에 성공하셨습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    // 회원 목록
    public void getMemberList() {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(MEMBER_LIST);
            rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("현재 등록된 회원 목록입니다.");
                do {
                    String member_id = rs.getString("MEMBER_ID");
                    String name = rs.getString("NAME");
                    String phone_number = rs.getString("PHONE_NUMBER");

                    Member member = new Member(member_id, name, phone_number);
                    System.out.println(member.toString());
                } while (rs.next());
            } else {
                System.out.println("등록된 회원이 없습니다.");
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
    }

    // 회원 상세 목록
    public void getMemberListDetail() {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(MEMBER_LIST);
            rs = stmt.executeQuery();

            String member_id_input = scan.next();

            while (rs.next()) {
                String member_id_origin = rs.getString("MEMBER_ID");

                // 입력한 member_id_input과 기존 db에 저장된 member_id_origin이 동일할 때
                if (member_id_origin.equals(member_id_input)) {
                    System.out.println(member_id_input + "가 이미 존재합니다.");
                } else {
                    insertMember();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            JDBCUtil.close(rs, stmt, conn);
        }
    }
}