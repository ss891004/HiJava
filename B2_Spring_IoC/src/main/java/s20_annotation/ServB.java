package s20_annotation;

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
