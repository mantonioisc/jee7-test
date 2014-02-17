package test.jee7.handlers;

import org.w3c.dom.Node;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.StringWriter;
import java.util.Set;


public class TestSoapHandler implements SOAPHandler<SOAPMessageContext> {
    public static final String STRING_KEY = "STRING_KEY";

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        SOAPMessage soapMessage = context.getMessage();
        StringWriter ldModel = new StringWriter();

        boolean outMessage = (boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (!outMessage) {
            try {
                SOAPBody soapBody = soapMessage.getSOAPBody();
                Node ldModelNode = soapBody.getFirstChild();
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.transform(new DOMSource(ldModelNode), new StreamResult(ldModel));

            } catch (SOAPException | TransformerException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

            context.put(STRING_KEY, ldModel.toString());
            context.setScope(STRING_KEY, MessageContext.Scope.APPLICATION);
            context.put("random", Math.random());
            context.setScope("random", MessageContext.Scope.APPLICATION);
        }


        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {

    }
}
