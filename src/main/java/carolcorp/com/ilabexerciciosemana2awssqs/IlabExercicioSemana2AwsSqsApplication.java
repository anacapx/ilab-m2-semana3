package carolcorp.com.ilabexerciciosemana2awssqs;

import carolcorp.com.ilabexerciciosemana2awssqs.services.KafkaService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class IlabExercicioSemana2AwsSqsApplication {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.out.println("Lendo mensagens ...");
		var grupoId = System.getenv("KAFKA_GROUP_ID_READER");
		KafkaService.readMessage(grupoId);
	}

}
