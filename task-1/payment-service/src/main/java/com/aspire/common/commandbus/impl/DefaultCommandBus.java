package com.aspire.common.commandbus.impl;


import com.aspire.common.commandbus.Command;
import com.aspire.common.commandbus.CommandBus;
import com.aspire.common.commandbus.CommandHandler;
import com.aspire.common.commandbus.exception.NoHandlerFoundForCommandException;
import com.aspire.common.commandbus.utils.Tuple;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.SmartLifecycle;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class DefaultCommandBus implements CommandBus, SmartLifecycle, ApplicationContextAware {

    private Map<Class, Tuple<Method, Object>> commandHandlers;

    private boolean isRunning;

    private ApplicationContext applicationContext;

    public DefaultCommandBus() {
        commandHandlers = new ConcurrentHashMap<>();
    }

    @Override
    public <U> U execute(Command command) {
        Tuple<Method, Object> methodAndBean = commandHandlers.get(command.getClass());
        if (methodAndBean == null)
            throw new NoHandlerFoundForCommandException();

        Method method = methodAndBean._1;
        Object bean = methodAndBean._2;

        return (U) ReflectionUtils.invokeMethod(method, bean, command);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void start() {
        Stream.of(applicationContext.getBeanDefinitionNames())
                .map(beanName -> new Tuple<>(beanName, applicationContext.getBean(beanName)))
                .forEach(this::registerCommandHandlerBean);
        isRunning = true;
    }

    private void registerCommandHandlerBean(Tuple<String, Object> tuple) {

        Object bean = tuple._2;

        ReflectionUtils
                .doWithMethods(bean.getClass(), method -> {
                    CommandHandler commandHandler = method.getDeclaredAnnotation(CommandHandler.class);
                    if (commandHandler == null) return;
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length != 1) return;
                    Class<?> commandClass = parameterTypes[0];
                    commandHandlers.put(commandClass, new Tuple<>(method, bean));
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
