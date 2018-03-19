import com.cs.ioc.BeanDefinition;import com.cs.ioc.BeanFactory;import org.junit.Test;/** * @author: demopoo * @Date: Created in 下午12:52 2018/3/19 * @Des: * @Modifyed By: */public class TestBeanRegister {    @Test    public void register(){        Person person = new Person();        person.setAge(28);        person.setName("demopoo");        BeanDefinition beanDefinition = new BeanDefinition(person);        BeanFactory beanFactory = new BeanFactory();        //Bean注入        beanFactory.registerBeanDefinition(person.getName(),beanDefinition);        //Bean查找        BeanDefinition beanDefinition1 = beanFactory.getBean(person.getName());        System.out.println(1);    }}