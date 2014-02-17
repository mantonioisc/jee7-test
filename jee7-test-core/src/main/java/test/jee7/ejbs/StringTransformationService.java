package test.jee7.ejbs;

import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class StringTransformationService {
    @Inject
    Logger logger;

    public String reverse(String string) {
        StringBuilder sb = new StringBuilder(string.length());

        for (int i = string.toCharArray().length - 1; i >= 0; i--) {
            char c = string.toCharArray()[i];
            sb.append(c);
        }

        logger.debug("original string: " + string);
        logger.debug("reverse string: " + sb.toString());
        return sb.toString();
    }

    public String uppercase(String string) {
        logger.debug("to uppercase");
        return string.toUpperCase();
    }

    public String lowercase(String string) {
        logger.debug("to lowercase");
        return string.toLowerCase();
    }
}
