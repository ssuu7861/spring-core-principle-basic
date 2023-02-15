package Hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance; //static 변수로 미리 만들어둔 값을 반환, 항상 같은 인스턴스를 반환하게 됨
    }

    private SingletonService() {
        //생성자를 private으로 만들어 객체 생성을 막음
    }

    public void logic() {
        System.out.println("Singleton 객체 로직 호출");
    }

}
