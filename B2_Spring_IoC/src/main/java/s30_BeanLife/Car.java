package s30_BeanLife;


import lombok.Data;

@Data
public class Car {
    private String name;

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                '}';
    }
}