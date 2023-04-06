package me.donglin.leetcode.medium;

import me.donglin.leetcode.BaseTest;
import org.junit.Assert;

/**
 * 1017. 负二进制转换
 * @author donglin
 * @since 2023-04-06
 */
public class BaseNeg2 extends BaseTest {

    public String baseNeg2(int n) {
        // 将n拆解为a-b，其中a的二进制奇数位都是0，而b的二进制偶数位都是0
        // 因此如果n的第i位(奇数)为1，则需要将该位给b，而a则需要在n基础上加上该位
        int b = 0;
        for (int i = 1; n >> i > 0; i += 2) {
            if ((n & 1 << i) == 0) {
                continue;
            }
            n += 1 << i;
            b |= 1 << i;
        }
        return Integer.toBinaryString(n | b);
    }

    @Override
    public void case1() {
        Assert.assertEquals("110", baseNeg2(2));
        Assert.assertEquals("111", baseNeg2(3));
        Assert.assertEquals("100", baseNeg2(4));
    }
}
