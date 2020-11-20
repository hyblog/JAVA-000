package com.ipman.work05sb2.hibernate.entity;

import javax.persistence.*;

/**
 * Created by ipipman on 2020/11/20.
 *
 * @version V1.0
 * @Package com.ipman.work05sb2.common.domain.entity
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/20 3:05 下午
 */
@Entity
@Table(name = "demo") //表名
public class DemoVO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DemoVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
