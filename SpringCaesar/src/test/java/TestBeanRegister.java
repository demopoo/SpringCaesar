import com.cs.ioc.AutowireCapableBeanFactory;import com.cs.ioc.BeanDefinition;import com.cs.ioc.BeanFactory;import com.cs.pojo.Person;import org.junit.Test;/** * @author: demopoo * @Date: Created in 下午12:52 2018/3/19 * @Des: * @Modifyed By: */public class TestBeanRegister {    @Test    public void register(){        Person person = new Person();        person.setAge(28);        person.setName("demopoo");        BeanDefinition beanDefinition = new BeanDefinition();        beanDefinition.setBeanName("com.cs.pojo.Person");        //注入Bean        BeanFactory beanFactory = new AutowireCapableBeanFactory();        beanFactory.registerBeanDefinition("person",beanDefinition);        //获取Bean        BeanDefinition beanDefinition1 = (BeanDefinition) beanFactory.getBean("person");        System.out.println(1);    }}