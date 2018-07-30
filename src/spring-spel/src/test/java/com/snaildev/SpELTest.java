package com.snaildev;

import com.snaildev.pojo.SpELBean;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Properties;

public class SpELTest {
    @Test
    public void helloWorld() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("('Hello' + ' World').concat(#end)");
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("end", "!");

        Assert.assertEquals("Hello World!", expression.getValue(context));
    }

    @Test
    public void testParserContext() {
        ExpressionParser parser = new SpelExpressionParser();
        ParserContext context = new ParserContext() {
            @Override
            public boolean isTemplate() {
                return true;
            }

            @Override
            public String getExpressionPrefix() {
                return "#{";
            }

            @Override
            public String getExpressionSuffix() {
                return "}";
            }
        };

        String template = "#{'Hello '}#{'World!'}";
        Expression expression = parser.parseExpression(template, context);
        Assert.assertEquals("Hello World!", expression.getValue());
    }

    @Test
    public void testClassTypeExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        Class<String> result1 = parser.parseExpression("T(String)").getValue(Class.class);
        Assert.assertEquals(String.class, result1);

        Class result2 = parser.parseExpression("T(com.snaildev.SpELTest)").getValue(Class.class);
        Assert.assertEquals(SpELTest.class, result2);
        //类静态字段访问
        int result3 = parser.parseExpression("T(Integer).MAX_VALUE").getValue(int.class);
        Assert.assertEquals(Integer.MAX_VALUE, result3);
        //类静态方法调用
        int result4 = parser.parseExpression("T(Integer).parseInt('1')").getValue(int.class);
        Assert.assertEquals(1, result4);
    }

    @Test
    public void testConstructorExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        String result1 = parser.parseExpression("new String('haha')").getValue(String.class);
        Assert.assertEquals("haha", result1);
        Date result2 = parser.parseExpression("new java.util.Date()").getValue(Date.class);
        Assert.assertNotNull(result2);
    }

    @Test
    public void testVariableExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("variable", "haha");
        context.setVariable("variable", "haha");
        String result1 = parser.parseExpression("#variable").getValue(context, String.class);
        Assert.assertEquals("haha", result1);

        context = new StandardEvaluationContext("haha");
        String result2 = parser.parseExpression("#root").getValue(context, String.class);
        Assert.assertEquals("haha", result2);
        String result3 = parser.parseExpression("#this").getValue(context, String.class);
        Assert.assertEquals("haha", result3);
    }

    @Test
    public void testFunctionExpression() throws SecurityException, NoSuchMethodException {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        Method parseInt = Integer.class.getDeclaredMethod("parseInt", String.class);
        context.registerFunction("parseInt", parseInt);
        context.setVariable("parseInt2", parseInt);
        String expression1 = "#parseInt('3') == #parseInt2('3')";
        boolean result1 = parser.parseExpression(expression1).getValue(context, boolean.class);
        Assert.assertEquals(true, result1);
    }

    @Test
    public void testAssignExpression() {
        ExpressionParser parser = new SpelExpressionParser();
        //1.给root对象赋值
        EvaluationContext context = new StandardEvaluationContext("aaaa");
        String result1 = parser.parseExpression("#root='aaaaa'").getValue(context, String.class);
        Assert.assertEquals("aaaaa", result1);
        String result2 = parser.parseExpression("#this='aaaa'").getValue(context, String.class);
        Assert.assertEquals("aaaa", result2);

        //2.给自定义变量赋值
        context.setVariable("#variable", "variable");
        String result3 = parser.parseExpression("#variable=#root").getValue(context, String.class);
        Assert.assertEquals("aaaa", result3);
    }

    @Test
    public void testBeanExpression() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext();
        ctx.refresh();
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(new BeanFactoryResolver(ctx));
        Properties result1 = parser.parseExpression("@systemProperties").getValue(context, Properties.class);
        Assert.assertEquals(System.getProperties(), result1);
    }

    @Test
    public void testXmlExpression() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("el1.xml");
        String hello1 = ctx.getBean("hello1", String.class);
        String hello2 = ctx.getBean("hello2", String.class);
        String hello3 = ctx.getBean("hello3", String.class);
        Assert.assertEquals("Hello World!", hello1);
        Assert.assertEquals("Hello World!", hello2);
        Assert.assertEquals("Hello World!", hello3);
    }

    @Test
    public void testAnnotationExpression() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("el2.xml");
        SpELBean helloBean1 = ctx.getBean("helloBean1", SpELBean.class);
        Assert.assertEquals("Hello World!", helloBean1.getValue());
        SpELBean helloBean2 = ctx.getBean("helloBean2", SpELBean.class);
        Assert.assertEquals("haha", helloBean2.getValue());
    }

    @Test
    public void testPrefixExpression() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("el3.xml");
        SpELBean helloBean1 = ctx.getBean("helloBean1", SpELBean.class);
        Assert.assertEquals("#{'Hello' + world}", helloBean1.getValue());
        SpELBean helloBean2 = ctx.getBean("helloBean2", SpELBean.class);
        Assert.assertEquals("Hello World!", helloBean2.getValue());
    }
}
