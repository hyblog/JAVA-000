package com.springboot.frame.ioc.ann;

import com.springboot.frame.ioc.xml.Bird;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * Created by ipipman on 2020/11/16.
 *
 * @version V1.0
 * @Package com.springboot.frame.ioc.ann
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/16 11:18 下午
 */
@Component
public class MyBeanImport implements ImportBeanDefinitionRegistrar {

    /**
     * 通过继承ImportBeanDEfinitionRegistrar类，重新registerBeanDefinitions方法，注入自定义Bean
     *
     * @param annotationMetadata
     * @param registry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
        rootBeanDefinition.setBeanClass(Bird.class);
        registry.registerBeanDefinition("myBird", rootBeanDefinition);
    }
}
