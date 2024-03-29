package s10;

public class SetterBean {
    public interface IService {} //@1
    public static class ServiceA implements IService {} //@2
    public static class ServiceB implements IService {} //@3
    private IService service;
    public void setService(IService service) {
        this.service = service;
    }
    @Override
    public String toString() {
        return "SetterBean{" +
                "service=" + service +
                '}';
    }
}