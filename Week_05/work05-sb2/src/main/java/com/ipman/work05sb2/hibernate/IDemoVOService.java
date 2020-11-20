package com.ipman.work05sb2.hibernate;

import com.ipman.work05sb2.hibernate.entity.DemoVO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.Id;

/**
 * Created by ipipman on 2020/11/20.
 *
 * @version V1.0
 * @Package com.ipman.work05sb2.hibernate
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/20 3:12 下午
 */
public interface IDemoVOService extends CrudRepository<DemoVO, Integer> {

    /**
     * Hibernate实践，用Id查询数据
     *
     * @param id
     * @return
     */
    @Query("select d from DemoVO d where d.id=:id")
    public DemoVO findDemoById(@Param("id") Integer id);

}
