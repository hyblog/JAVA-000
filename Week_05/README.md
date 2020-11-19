# 学习笔记

#### 1、IOC基础BeanFactory

------------

![](https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_05/note/IOC%E5%9F%BA%E7%A1%80-BF.png)

------------

#### 2、IOC基础-ApplicationContext

------------

![](https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_05/note/ICO%E5%9F%BA%E7%A1%80-AC.png)

------------

#### 3、IOC-工作机制

------------

![](https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_05/note/IOC%E5%9F%BA%E7%A1%80-%E5%B7%A5%E4%BD%9C%E6%9C%BA%E5%88%B6.png)

------------

#### 4、AOP基础

------------

![](https://raw.githubusercontent.com/hyblog/JAVA-000/main/Week_05/note/AOP%E5%9F%BA%E7%A1%80.png)

------------

#### 5、第九节作业，选做，使Java里的动态代理，实现一个简单的AOP
#####  代码：[https://github.com/hyblog/JAVA-000/tree/main/Week_05/frame/src/main/java/com/springboot/frame/proxy](https://github.com/hyblog/JAVA-000/tree/main/Week_05/frame/src/main/java/com/springboot/frame/proxy "https://github.com/hyblog/JAVA-000/tree/main/Week_05/frame/src/main/java/com/springboot/frame/proxy")
- 方式一：基于静态代理方式
- 方式二：基于JDK1.3后Java Proxy的InvocationHandler接口实现
- 方式三：基于CGLIB的MethodInterceptor接口实现
- 方式四：基于Javassist的MethodHandler接口实现
- 方式五：基于SpringAOP的@Aspect与自定义注解实现

------------

#### 6、第九节作业，必做，写代码实现Spring Bean的装配，方式越多越好(XML、Annotation都可以),提 交到Github。
#####  ANN方式：[https://github.com/hyblog/JAVA-000/tree/main/Week_05/frame/src/main/java/com/springboot/frame/ioc](https://github.com/hyblog/JAVA-000/tree/main/Week_05/frame/src/main/java/com/springboot/frame/ioc "https://github.com/hyblog/JAVA-000/tree/main/Week_05/frame/src/main/java/com/springboot/frame/ioc")
- 通过注解：@Configuration、@Component、@Service、@Bean、@Qualifier、@Controller
- 继承BeanFactoryPostProcessor的Bean后处理器postProcessBeanFactory方法，获取BeanDefinition后修改Bean属性
- 继承ImportBeanDEfinitionRegistrar接口，重新registerBeanDefinitions方法，注入自定义Bean，通过@Import注解注入Bean
- 继承InstantiationAwareBeanPostProcessor接口，实现postProcessBeforeInstantiation和postProcessAfterInstantiation方法，修改Bean的属性
- 继承BeanDefinitionRegistryPostProcessor接口，实现postProcessBeanDefinitionRegistry方法，完成自定义Bean注入
- 继承FactoryBean<T>接口，实现getObject方法，返回Bean工厂对象，完成自定义Bean注入

------------

#### 7、第九节作业，选做，实现一个Spring XML自定义配置，配置一组Bean，例如Student/Klass/School。
#####  XML方式：[https://github.com/hyblog/JAVA-000/tree/main/Week_05/frame/src/main/resources/ioc](https://github.com/hyblog/JAVA-000/tree/main/Week_05/frame/src/main/resources/ioc "https://github.com/hyblog/JAVA-000/tree/main/Week_05/frame/src/main/resources/ioc")
- 方式一：通过property创建一个无参Bean
- 方式二：通过construct-arg创建一个有参的Bean
- 方式三：通过class创建一个静态Bean
- 方式四：通过factory-bean创建一个非静态Bean

------------

#### 8、总结一下，单例的各种写法，比较它们的优劣。
#####  代码：[https://github.com/hyblog/JAVA-000/tree/main/Week_05/work05-sb2/src/main/java/com/ipman/work05sb2/common/singletons](https://github.com/hyblog/JAVA-000/tree/main/Week_05/work05-sb2/src/main/java/com/ipman/work05sb2/common/singletons "https://github.com/hyblog/JAVA-000/tree/main/Week_05/work05-sb2/src/main/java/com/ipman/work05sb2/common/singletons")

```java
    /**
     * 工厂类实现方式测试
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {

        List<Future<Integer>> futureList = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            Future<Integer> task = executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    // 懒汉模式，在多线程下对象的HashCode是不一样的
                    // return Singleton1.getInstance().hashCode();

                    // 懒汉线程安全模式，通过synchroized锁解决并发问题
                    // return Singleton2.getInstance().hashCode();

                    // 饿汉方式，静态成员，在classloader类装载期完成初始化，避免线程安全问题
                    // return Singleton3.getInstance().hashCode();

                    // 饿汉方式，静态内部类，在classloader类被装载了，instance不被初始化，初始化再第一次调用，避免线程安全问题
                    return Singleton4.getInstance().hashCode();
            }
        });
        futureList.add(task);
    }

    Map<Integer, Boolean> mapping = new HashMap<>();
        for (Future<Integer> future : futureList) {
        if (mapping.isEmpty()) {
            mapping.put(future.get(), true);
            } else if (!mapping.containsKey(future.get())) {
                System.out.println("懒汉模式，实例变了，线程不安全，hashcode=" + future.get());
            }
        }
    }
```