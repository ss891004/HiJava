package s54_junit;

public class MathUtils {
    /**
     * 获取最大的数字
     *
     * @param args
     * @return
     */
    public static int max(int... args) {
        int result = Integer.MIN_VALUE;
        for (int arg : args) {
            result = result > arg ? result : arg;
        }
        return result;
    }
    /**
     * 获取最小的数字
     *
     * @param args
     * @return
     */
    public static int min(int... args) {
        int result = Integer.MAX_VALUE;
        for (int arg : args) {
            result = result < arg ? result : arg;
        }
        return result;
    }


}
