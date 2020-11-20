package com.ipman.work05sb2.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ipipman on 2020/11/20.
 *
 * @version V1.0
 * @Package com.ipman.work05sb2.hibernate
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/20 3:16 下午
 */
@RestController
public class HibernateDemoController {

    @Autowired
    IDemoVOService iDemoVOService;

    /**
     * Hibernate实践，根据ID查询数据
     *
     * @param id
     * @return
     * @Link http://127.0.0.1:9000/hibernate/demo/get?id=1
     * @Result {"id":1,"name":"q"}
     */
    @GetMapping("/hibernate/demo/get")
    public Object getDemoById(Integer id) {
        return iDemoVOService.findDemoById(id);
    }
}
