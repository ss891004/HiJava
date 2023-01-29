package s09_depend_on;

import org.springframework.beans.factory.DisposableBean;

public class StrongDependenceBean {

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

        private final Bean1 bean1;

        public Bean2(Bean1 bean1) {
            System.out.println(this.getClass() + " constructor!");
            this.bean1=bean1;
        }
        @Override
        public void destroy() throws Exception {
            System.out.println(this.getClass() + " destroy()");
        }
    }
    public static class Bean3 implements DisposableBean {

        public Bean2 bean2;

        public Bean3(Bean2 bean2) {
            System.out.println(this.getClass() + " constructor!");
            this.bean2=bean2;
        }
        @Override
        public void destroy() throws Exception {
            System.out.println(this.getClass() + " destroy()");
        }
    }



}
