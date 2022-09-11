package com.ms.email.consumers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.email.models.EmailModel;
import com.ms.email.services.EmailService;
import org.example.dto.EmailDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(String message) throws JsonProcessingException, InterruptedException {

       try {
           EmailModel emailModel = new EmailModel();
           EmailDTO emailDTO = new ObjectMapper().readValue(message, EmailDTO.class);
           BeanUtils.copyProperties(emailDTO, emailModel);
           emailService.sendEmail(emailModel);
           System.out.println("Email Status: " + emailModel.getStatusEmail().toString());
       } catch (Exception e) {
           throw new IllegalArgumentException("Argumento inválido!");
       }

    }

}
