package carolcorp.com.ilabexerciciosemana2awssqs.services;

import java.util.List;

import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

public class SqsReceiveService {

    public static List<Message> messageReader(){
        AwsCredentialsProvider credentialsProvider = new AwsCredentialsProvider() {
            @Override
            public AwsCredentials resolveCredentials() {
                return new AwsCredentials() {
                    @Override
                    public String accessKeyId() {
                        return System.getenv("AWS_ACCESS_KEY");
                    }
        
                    @Override
                    public String secretAccessKey() {
                        return System.getenv("AWS_SECRET_KEY");
                    }
                };
            }
        };

        SqsClient sqsClient = SqsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(credentialsProvider)
                .build();

        // ===== Busca uma Fila =====
        GetQueueUrlRequest request = GetQueueUrlRequest.builder()
                .queueName("fila-carol.fifo") // ler da fila fifo
                // .queueName("fila-danilo")  // ler da fila padrão
                .queueOwnerAWSAccountId("779360925860").build();
        GetQueueUrlResponse createResult = sqsClient.getQueueUrl(request);
        
        List<Message> messages = receiveMessages(sqsClient, createResult.queueUrl());
        
        for (Message mess : messages) {
            System.out.println("Mensagem: " + mess.body());
        }

        sqsClient.close();

        return messages;
    }

    public static  List<Message> receiveMessages(SqsClient sqsClient, String queueUrl) {
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
            .queueUrl(queueUrl)
            .waitTimeSeconds(20)
            .maxNumberOfMessages(5)
            .build();
        List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).messages();
        return messages;
    }
    
}
