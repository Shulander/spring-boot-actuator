package us.vicentini.services.jms;

import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;


@Service
@RequiredArgsConstructor
public class JmsTextMessageServiceImpl implements JmsTextMessageService {

    private final Queue textMessageQueue;
    private final JmsTemplate jmsTemplate;


    @Override
    public void sendTextMessage(String msg) {
        this.jmsTemplate.convertAndSend(textMessageQueue, msg);
    }
}
