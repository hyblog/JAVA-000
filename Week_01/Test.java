package Week_01;

/**
 * Created by ipipman on 2020/10/17.
 *
 * @version V1.0
 * @Package Week_01
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/10/17 12:27 下午
 */
public class Test {


    /**
     * 四则运算+流程控制
     *
     * @param args
     */
    public static void main(String[] args) {
        int num1 = 1;
        int num2 = 10;
        float num3;
        for (int i = 0; i < 3; i++) {
            num1 = num1 + 1;
            num2 = num2 - 1;
            num3 = num1 * num2;
            if (num3 > num2) {
                num3 = num3 / num2;
            }
        }
    }
}
