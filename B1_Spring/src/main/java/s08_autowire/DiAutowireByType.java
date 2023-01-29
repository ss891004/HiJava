package s08_autowire;

/**
 * 按照名称自动注入
 */
public class DiAutowireByType {
    public static class Service1 { //@1
        private String desc1;

        public String getDesc1() {
            return desc1;
        }

        public void setDesc1(String desc1) {
            this.desc1 = desc1;
        }

        @Override
        public String toString() {
            return "Service1{" +
                    "desc1='" + desc1 + '\'' +
                    '}';
        }
    }

    public static class Service2 { //@1
        private String desc2;

        public String getDesc2() {
            return desc2;
        }

        public void setDesc2(String desc2) {
            this.desc2 = desc2;
        }

        @Override
        public String toString() {
            return "Service2{" +
                    "desc1='" + desc2 + '\'' +
                    '}';
        }
    }

    private Service1 service1;//@3
    private Service2 service2;//@4

    public Service1 getService1() {
        return service1;
    }

    public void setService1(Service1 service1) {
        System.out.println("setService1->" + service1);
        this.service1 = service1;
    }

    public Service2 getService2() {
        return service2;
    }

    public void setService2(Service2 service2) {
        System.out.println("setService2->" + service2);
        this.service2 = service2;
    }

    @Override
    public String toString() {
        return "DiAutowireByName{" +
                "service1=" + service1 +
                ", service2=" + service2 +
                '}';
    }
}