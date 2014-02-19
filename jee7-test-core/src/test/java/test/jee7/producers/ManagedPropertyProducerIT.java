package test.jee7.producers;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.jee7.annotations.ManagedProperty;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ManagedPropertyProducerIT {
    private static final Logger logger = LoggerFactory.getLogger(ManagedPropertyProducerIT.class);

    @Inject
    @ManagedProperty("string.property")
    String stringField;

    @Inject
    @ManagedProperty("int.property")
    int intField;

    @Inject
    @ManagedProperty("double.property")
    double doubleField;

    @Inject
    @ManagedProperty("true.boolean.property")
    boolean trueBooleanField;


    @ManagedProperty("false.boolean.property")
    boolean falseBooleanField;

    @Inject
    @ManagedProperty
    String defaultToFieldName;

    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class).addClass(ManagedProperty.class).addClass(ManagedPropertyProducer.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        logger.debug(jar.toString(true));
        return jar;
    }

    @Test
    public void testContainerManagedPropertyInjection() {
        assertEquals("The quick brown fox jumps over the lazy dog.", stringField);
        assertEquals(1111, intField);
        assertEquals("no value was given in the annotation", defaultToFieldName);
        assertEquals(11111.11, doubleField, 0.001);
        assertTrue(trueBooleanField);
        assertFalse(falseBooleanField);

        logger.debug("INJECTION SUCCESS!");
    }
}
