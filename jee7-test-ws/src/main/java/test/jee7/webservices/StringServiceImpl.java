package test.jee7.webservices;

import com.test.jee7.webservices.StringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.jee7.dao.GamesDao;
import test.jee7.ejbs.BusinessService;
import test.jee7.ejbs.StringTransformationService;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.HandlerChain;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

@SuppressWarnings("unused")
@WebService(serviceName = "StringService", portName = "StringService", targetNamespace = "http://www.jee7.test.com/webservices/", endpointInterface = "com.test.jee7.webservices.StringService", wsdlLocation = "WEB-INF/wsdl/MyService.wsdl")
@HandlerChain(file = "handler-chain.xml")
public class StringServiceImpl implements StringService {
    private static final Logger logger = LoggerFactory.getLogger(StringServiceImpl.class);
    @EJB
    BusinessService businessService;

    @EJB
    StringTransformationService stringTransformationService;

    @Resource
    WebServiceContext webServiceContext;

    @EJB
    GamesDao gamesDao;

    @Override
    public String reverse(@WebParam(name = "Reverse", targetNamespace = "http://www.jee7.test.com/webservices/") String reverse) {
        gamesDao.test();
        businessService.businessOperation();
        return stringTransformationService.reverse(reverse);
    }

    @Override
    public String uppercase(@WebParam(name = "Uppercase", targetNamespace = "http://www.jee7.test.com/webservices/") String uppercase) {
        businessService.businessOperation();
        Object savedValue = webServiceContext.getMessageContext().get("STRING_KEY");

        String message = "FAILED 0000000000000000000000000000";
        if (savedValue instanceof String) {
            message = System.identityHashCode(webServiceContext.getMessageContext().get("random")) + " FOUND!!!!!!!!!!!!! " + savedValue.toString().length();
        }
        logger.info(message);
        return stringTransformationService.uppercase(uppercase);

    }

    @Override
    public String lowercase(@WebParam(name = "Lowercase", targetNamespace = "http://www.jee7.test.com/webservices/") String lowercase) {
        businessService.businessOperation();
        return stringTransformationService.lowercase(lowercase);
    }
}
