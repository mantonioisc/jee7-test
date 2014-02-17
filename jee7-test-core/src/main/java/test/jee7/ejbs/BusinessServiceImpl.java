package test.jee7.ejbs;

import org.slf4j.Logger;
import test.jee7.annotations.ManagedProperty;

import javax.ejb.Stateless;
import javax.inject.Inject;


@Stateless
public class BusinessServiceImpl implements BusinessService {
    @Inject
    Logger logger;


    @Inject
    @ManagedProperty("sample.property")
    private String containerManagedProperty;


    @Override
    public void businessOperation() {
        logger.info("DO HEAVYLIFTING!!!!!!!!!!!!!!!!!!!!!");
        logger.info("PROPERTY INJECTED: " + containerManagedProperty);
    }
}
