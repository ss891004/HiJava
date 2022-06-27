package s02_di;


import lombok.Data;

@Data
public class MenuModel {
    //菜单名称
    private String label;
    //同级别排序
    private Integer theSort;

    @Override
    public String toString() {
        return "MenuModel{" +
                "label='" + label + '\'' +
                ", theSort=" + theSort +
                '}';
    }
}
