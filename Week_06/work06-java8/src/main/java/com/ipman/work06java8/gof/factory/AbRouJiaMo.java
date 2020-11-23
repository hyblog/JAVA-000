package com.ipman.work06java8.gof.factory;

/**
 * Created by ipipman on 2020/11/23.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.gof.factory
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/23 9:53 下午
 */
public abstract class AbRouJiaMo {

    protected String name;

    /**
     * 准备工作
     */
    public void prepare() {
        System.out.println("揉面-剁肉-完成准备工作");
    }

    /**
     * 使用你们的专用袋-包装
     */
    public void pack() {
        System.out.println("肉夹馍-专用袋-包装");
    }

    /**
     * 秘制设备-烘烤2分钟
     */
    public void fire() {
        System.out.println("肉夹馍-专用设备-烘烤");
    }


}
