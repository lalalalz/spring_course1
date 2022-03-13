package hello.core.Singletone;

public class StatefulService {

    private int price;

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        // 여기가 문제가 됨
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
