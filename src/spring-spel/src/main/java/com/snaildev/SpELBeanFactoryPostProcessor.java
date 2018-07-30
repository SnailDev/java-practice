package com.snaildev;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.expression.StandardBeanExpressionResolver;

public class SpELBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        StandardBeanExpressionResolver resolver = (StandardBeanExpressionResolver) configurableListableBeanFactory.getBeanExpressionResolver();
        resolver.setExpressionPrefix("%{");
        resolver.setExpressionSuffix("}");
    }
}
