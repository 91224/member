package com.example.member;

import java.util.Objects;

public class Member { // 회원 정보

    private String memberId;
    private String name;
    private String phoneNumber;

    public Member(String memberId, String name, String phoneNumber) {
        this.memberId = memberId;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public int hashCode() {
        return Objects.hash(memberId, name, phoneNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Member other = (Member) obj;
        return Objects.equals(memberId, other.memberId) && Objects.equals(name, other.name)
                && Objects.equals(phoneNumber, other.phoneNumber);
    }

    @Override
    public String toString() {
        return "---> Member [memberId=" + memberId + ", name=" + name + ", phoneNumber=" + phoneNumber + "]";
    }



}