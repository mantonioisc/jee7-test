package test.jee7.producers;

import org.junit.Test;
import test.jee7.annotations.ManagedProperty;

import javax.enterprise.inject.spi.InjectionPoint;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ManagedPropertyProducerTest {
    private static final Properties PROPERTIES = new Properties() {{
        put("string.property", "string value");
        put("int.property", "1000");
        put("empty.property", "");
        put("defaultToFieldName", "no value was passed to @ManagedProperty");
    }};


    @Test
    public void testInjectStringProperty() throws Exception {
        ManagedPropertyProducer managedPropertyProducer = createStubPropertyProducer("string.property");
        String stringProperty = managedPropertyProducer.injectStringProperty(null);

        assertEquals("string value", stringProperty);
    }

    @Test
    public void testInjectEmptyStringProperty() throws Exception {
        ManagedPropertyProducer managedPropertyProducer = createStubPropertyProducer("empty.property");
        String stringProperty = managedPropertyProducer.injectStringProperty(null);

        assertTrue(stringProperty.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInjectNonExistingStringProperty() throws Exception {
        ManagedPropertyProducer managedPropertyProducer = createStubPropertyProducer("does.not.exist");
        managedPropertyProducer.injectStringProperty(null);
    }

    @Test
    public void testInjectIntProperty() throws Exception {
        ManagedPropertyProducer managedPropertyProducer = createStubPropertyProducer("int.property");
        int intProperty = managedPropertyProducer.injectIntProperty(null);

        assertEquals(1000, intProperty);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInjectInvalidIntProperty() throws Exception {
        ManagedPropertyProducer managedPropertyProducer = createStubPropertyProducer("string.property");

        managedPropertyProducer.injectIntProperty(null);
    }

    @Test
    public void testInjectDefaultToFieldNameProperty() {
        ManagedPropertyProducer managedPropertyProducer = createStubPropertyProducer(null);
        String stringProperty = managedPropertyProducer.injectStringProperty(null);

        assertEquals("no value was passed to @ManagedProperty", stringProperty);
    }


    private ManagedPropertyProducer createStubPropertyProducer(final String key) {
        return new ManagedPropertyProducer() {
            @Override
            Properties getProperties() {
                return PROPERTIES;
            }

            @Override
            ManagedProperty getPropertyAnnotation(InjectionPoint injectionPoint) {
                ManagedProperty mock = mock(ManagedProperty.class);

                when(mock.value()).thenReturn(key);

                return mock;
            }

            @Override
            Class<?> getDeclaringClass(InjectionPoint injectionPoint) {
                return ManagedPropertyProducerTest.class;
            }

            @Override
            String getMemberName(InjectionPoint injectionPoint) {
                return "defaultToFieldName";
            }
        };
    }

}
