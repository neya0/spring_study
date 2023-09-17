package com.example.springstudy.singletone;

public class SingletonMemberService {
    private static final SingletonMemberService instance1 = new SingletonMemberService();

    public static SingletonMemberService getInstance() {
        return instance1;
    }

    private SingletonMemberService(){}
}
