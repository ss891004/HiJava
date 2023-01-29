package s09_depend_on;

import org.springframework.beans.factory.DisposableBean;

public class NormalBean {

    public static class Bean1 implements DisposableBean {
        public Bean1() {
            System.out.println(this.getClass() + " constructor!");
        }
        @Override
        public void destroy() throws Exception {
            System.out.println(this.getClass() + " destroy()");
        }
    }
    public static class Bean2 implements DisposableBean {
        public Bean2() {
            System.out.println(this.getClass() + " constructor!");
        }
        @Override
        public void destroy() throws Exception {
            System.out.println(this.getClass() + " destroy()");
        }
    }
    public static class Bean3 implements DisposableBean {
        public Bean3() {
            System.out.println(this.getClass() + " constructor!");
        }
        @Override
        public void destroy() throws Exception {
            System.out.println(this.getClass() + " destroy()");
        }
    }

}
