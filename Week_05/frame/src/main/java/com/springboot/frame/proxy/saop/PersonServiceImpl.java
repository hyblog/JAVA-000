package com.springboot.frame.proxy.saop;

import com.springboot.frame.proxy.statics.Person;
import org.springframework.stereotype.Component;

/**
 * Created by ipipman on 2020/11/17.
 *
 * @version V1.0
 * @Package com.springboot.frame.proxy.aspectjs
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/17 6:22 下午
 */
@Component
public class PersonServiceImpl {

    /**
     * AspectJ
     */
    @CustomAspectJAnn
    public void doing() {
        Person person = new Person();
        person.doing();
    }
}
