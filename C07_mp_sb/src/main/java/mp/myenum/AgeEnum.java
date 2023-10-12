package mp.myenum;

import com.baomidou.mybatisplus.annotation.IEnum;

public enum AgeEnum implements IEnum<Integer> {
    ONE(1, "一岁"),
    TWO(2, "二岁"),
    THREE(3, "三岁");

    private Integer value;
    private String desc;

    AgeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        //数值作为枚举值保存到数据库
        return this.value;
    }
}