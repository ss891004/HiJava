package s30_BeanLife;

import lombok.Data;

@Data
public class User {
    private String name;
    private Car car;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", car=" + car +
                '}';
    }
}
