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

    내가 조사했던 단점 중 가장 컸던 단점은
    '멀티 스레드에서의 사용을 주의해야 한다는 단점이었다.'
    싱글톤은 하나의 인스턴스를 모두가 공유하기 때문에 변경 가능한 필드가 있다면 하나의 쓰레드가 종료되기 전에 다른 쓰레드에서 값이 변경될 수 있기 때문에 문제가 생길 수 있다는 것이었다.
    그래서 무상태를 유지하라고 강의에서 말해줬는데..사실 이 부분이 잘 이해가 안 됐다. 그래서 추가로 공부가 필요하다는 것을 느꼈다.
     */
    private static final SingletonService singleton = new SingletonService();

    public static  SingletonService getSingleton() {
        return singleton;
    }

    // 다른 클래스나 패키지에서 클래스 생성 자체를 방지한다.
    private SingletonService(){}
}
