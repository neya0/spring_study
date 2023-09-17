package com.example.springstudy.singletone;

public class SingletonService {
    /*
    <단점>
    매번 이런 코드를 작성해야하는 문제가 있다.
    클라이언트가 구체 클래스에 의존해야 한다.
    테스트가 어렵다.
    프라이빗을 사용하면서 유연성이 떨어진다.

    <장점>
    객체가 확실하게 보장된다.
    매번 모든 요청에 새로운 인스턴스를 만드는게 아니라서 메모리 낭비를 막을 수 있다.
     */
    private static final SingletonService singleton = new SingletonService();

    public static  SingletonService getSingleton() {
        return singleton;
    }

    // 다른 클래스나 패키지에서 클래스 생성 자체를 방지한다.
    private SingletonService(){}
}
