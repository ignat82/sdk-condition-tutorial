package ru.samokat.atlassian.jira.tutorials.sdkcondition.postprocessor;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

@Slf4j
@RequiredArgsConstructor
public class SdkConditionAnnotationBeanPostProcessor implements BeanPostProcessor {
    private final SdkChecker sdkConditionChecker;

    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        log.debug("postProcessAfterInitialization running for bean.getClass() {} with name {}",
                  bean.getClass(), beanName);

        Class<?> beanClass = bean.getClass();
        if (beanClass.isAnnotationPresent(SdkCondition.class)) {
            log.trace("Creating a proxy for @SdkOnly bean {}", beanName);
            if (AopUtils.isAopProxy(bean)) {
                log.warn("The bean {} is already a proxy.", beanName);
                return bean;
            }
            ProxyFactory proxyFactory = new ProxyFactory(bean);
            proxyFactory.addAdvice((MethodInterceptor) invocation -> {
                if (!sdkConditionChecker.isSdkEnvironment()) {
                    log.warn("Method \"{}()\" should be called on SDK environment only. Intercepting and returning null.",
                             invocation.getMethod().getName());
                    return null;
                }
                return invocation.proceed();
            });
            return proxyFactory.getProxy();
        }
        return bean;
    }
}

