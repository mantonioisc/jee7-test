package test.jee7.handlers;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.ws.LogicalMessage;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;
import java.io.StringWriter;

public class TestLogicalHandler implements LogicalHandler<LogicalMessageContext> {
    public static final String STRING_KEY = "STRING_KEY";

    @Override
    public boolean handleMessage(LogicalMessageContext context) {
        LogicalMessage logicalMessage = context.getMessage();
        StringWriter ldModel = new StringWriter();

        boolean outMessage = (boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (!outMessage) {
            try {
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.transform(logicalMessage.getPayload(), new StreamResult(ldModel));

            } catch (TransformerException e) {
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
    public boolean handleFault(LogicalMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {

    }
}
