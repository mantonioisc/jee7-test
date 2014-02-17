package test.jee7.producers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.jee7.annotations.ManagedProperty;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.Properties;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Lock(LockType.READ)
@Startup
public class ManagedPropertyProducer {
    private static final Logger logger = LoggerFactory.getLogger(ManagedPropertyProducer.class);

    @Resource(name = "project.properties")
    private Properties properties;


    @PostConstruct
    public void logProperties() {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Singleton started with container managed properties to inject: %s", properties.stringPropertyNames()));
        }
    }


    @Produces
    @ManagedProperty
    public String injectStringProperty(InjectionPoint injectionPoint) {
        ManagedProperty managedProperty = getPropertyAnnotation(injectionPoint);
        String propertyName = managedProperty.value();

        if (propertyName == null || propertyName.isEmpty()) {
            propertyName = getMemberName(injectionPoint);
            logger.debug(String.format("No property name specified in annotation, attempting to use the target field name [%s] ", propertyName));
        }

        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Requested property value for: [%s] to be injected in field [%s] of [%s]. ", propertyName, getMemberName(injectionPoint), getDeclaringClass(injectionPoint)));
        }

        if (!getProperties().containsKey(propertyName)) {
            throw new IllegalArgumentException("You must specify a valid property name, property name is empty.");
        }

        String propertyValue = getProperties().getProperty(propertyName);

        if (propertyValue == null) {
            throw new IllegalArgumentException(String.format("You must specify a valid property name, [%s] is not a valid property.", propertyName));
        }
        return propertyValue;
    }

    @Produces
    @ManagedProperty
    public int injectIntProperty(InjectionPoint injectionPoint) {
        String stringValue = injectStringProperty(injectionPoint);
        int value;

        try {
            value = Integer.parseInt(stringValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("The property you specified [%s], has no int value ", stringValue));
        }
        return value;
    }

    @Produces
    @ManagedProperty
    public double injectDoubleProperty(InjectionPoint injectionPoint) {
        String stringValue = injectStringProperty(injectionPoint);
        double value;

        try {
            value = Double.parseDouble(stringValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("The property you specified [%s], has no double value ", stringValue));
        }
        return value;
    }

    @Produces
    @ManagedProperty
    public boolean injectBooleanProperty(InjectionPoint injectionPoint) {
        String stringValue = injectStringProperty(injectionPoint);
        boolean value;

        try {
            value = Boolean.parseBoolean(stringValue);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("The property you specified [%s], has no boolean value ", stringValue));
        }
        return value;
    }


    ManagedProperty getPropertyAnnotation(InjectionPoint injectionPoint) {
        return injectionPoint.getAnnotated().getAnnotation(ManagedProperty.class);
    }

    Class<?> getDeclaringClass(InjectionPoint injectionPoint) {
        return injectionPoint.getMember().getDeclaringClass();
    }

    String getMemberName(InjectionPoint injectionPoint) {
        return injectionPoint.getMember().getName();
    }

    Properties getProperties() {
        return properties;
    }
}

