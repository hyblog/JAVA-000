package com.ipman.work06java8.gof.factory;

/**
 * Created by ipipman on 2020/11/23.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.gof.factory
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/23 10:48 下午
 */
public class SimpleRouJiaMo {

    public AbRouJiaMo createRowJiaMo(String type) {
        AbRouJiaMo rouJiaMo = null;
        if (type.equals("Suan")) {
            rouJiaMo = new SuanRouJiaMo();
        } else if (type.equals("La")) {
            rouJiaMo = new LaRouJiaMo();
        }
        return rouJiaMo;
    }
}
