package com.aspire.common.querybus.impl;



import com.aspire.common.querybus.utils.Tuple;
import com.aspire.common.querybus.Query;
import com.aspire.common.querybus.QueryBus;
import com.aspire.common.querybus.QueryHandler;
import com.aspire.common.querybus.exception.NoHandlerFoundForQueryException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.SmartLifecycle;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class DefaultQueryBus implements QueryBus, SmartLifecycle, ApplicationContextAware {

    private Map<Class, Tuple<Method, Object>> queryHandlers;

    private boolean isRunning;

    private ApplicationContext applicationContext;

    public DefaultQueryBus() {
        queryHandlers = new ConcurrentHashMap<>();
    }

    @Override
    public <U> U execute(Query query) {
        Tuple<Method, Object> methodAndBean = queryHandlers.get(query.getClass());
        if (methodAndBean == null)
            throw new NoHandlerFoundForQueryException();

        Method method = methodAndBean._1;
        Object bean = methodAndBean._2;

        return (U) ReflectionUtils.invokeMethod(method, bean, query);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void start() {
        Stream.of(applicationContext.getBeanDefinitionNames())
                .map(beanName -> new Tuple<>(beanName, applicationContext.getBean(beanName)))
                .forEach(this::registerQueryHandlerBean);
        isRunning = true;
    }

    private void registerQueryHandlerBean(Tuple<String, Object> tuple) {

        Object bean = tuple._2;

        ReflectionUtils
                .doWithMethods(bean.getClass(), method -> {
                    QueryHandler queryHandler = method.getDeclaredAnnotation(QueryHandler.class);
                    if (queryHandler == null) return;
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length != 1) return;
                    Class<?> queryClass = parameterTypes[0];
                    queryHandlers.put(queryClass, new Tuple<>(method, bean));
                });
    }

    @Override
    public void stop() {
        isRunning = false;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }


}
