package s04_depend_on;

import org.springframework.beans.factory.DisposableBean;

public class StrongDependenceBean2 {

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

        private Bean1 bean1;

        public Bean2() {
            System.out.println(this.getClass() + " constructor!");
        }

        //        public Bean2(Bean1 bean1) {
//            System.out.println(this.getClass() + " constructor!");
//            this.bean1=bean1;
//        }
        @Override
        public void destroy() throws Exception {
            System.out.println(this.getClass() + " destroy()");
        }

        public Bean1 getBean1() {
            return bean1;
        }

        public void setBean1(Bean1 bean1) {
            this.bean1 = bean1;
        }
    }
    public static class Bean3 implements DisposableBean {

        public Bean3() {
            System.out.println(this.getClass() + " constructor!");
        }

        public Bean2 getBean2() {
            return bean2;
        }

        public void setBean2(Bean2 bean2) {
            this.bean2 = bean2;
        }

        public Bean2 bean2;

//        public Bean3(Bean2 bean2) {
//            System.out.println(this.getClass() + " constructor!");
//            this.bean2=bean2;
//        }
        @Override
        public void destroy() throws Exception {
            System.out.println(this.getClass() + " destroy()");
        }
    }



}
