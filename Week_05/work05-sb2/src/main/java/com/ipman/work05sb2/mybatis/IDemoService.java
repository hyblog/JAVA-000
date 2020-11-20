package com.ipman.work05sb2.mybatis;

import com.ipman.work05sb2.mybatis.model.Demo;

/**
 * Created by ipipman on 2020/11/20.
 *
 * @version V1.0
 * @Package com.ipman.work05sb2.mybatis
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/20 6:08 下午
 */
public interface IDemoService {

    /**
     * Mybatis实践，根据ID查询数据
     *
     * @param id
     * @return
     */
    public Demo findDemoById(Integer id);

}
