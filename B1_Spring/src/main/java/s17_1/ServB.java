package s17_1;

public class ServB {

    private final ServA servA;

    public ServB(ServA servA) {
        this.servA = servA;
    }

    @Override
    public String toString() {
        return "ServB{" +
                "servA=" + servA +
                '}';
    }
}
