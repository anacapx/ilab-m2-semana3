package carolcorp.com.ilabexerciciosemana2awssqs.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import carolcorp.com.ilabexerciciosemana2awssqs.services.SqsReceiveService;
import carolcorp.com.ilabexerciciosemana2awssqs.services.SqsSendService;
import software.amazon.awssdk.services.sqs.model.Message;



@RestController
public class SqsController {

    @GetMapping
    public String hello(){
        return "Bem-vindo a entrega do exercicio da segunda semana, modulo 2 da Gama Academy para o iLab!";
    }

    @GetMapping("/send")
    public String sendMessage(){
        String mensagemEnvio = "Este eh o corpo da mensagem. Data: " + LocalDateTime.now();
        SqsSendService.sendMessage(mensagemEnvio);
        String mensagemExibicao = " Mensagem enviada: "  + mensagemEnvio  ;
        return mensagemExibicao;
    }

    @GetMapping("/receive")
    public String receiveMessage(){
        String messagesBody = "";

        List<Message> messages = SqsReceiveService.messageReader();

        for(Message message : messages){
            messagesBody += " ***  MENSAGEM  *** " + message.body();
        }
       
        return messagesBody;
    }


}