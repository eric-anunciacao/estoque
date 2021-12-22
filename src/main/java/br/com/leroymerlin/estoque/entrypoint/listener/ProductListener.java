package br.com.leroymerlin.estoque.entrypoint.listener;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import br.com.leroymerlin.estoque.cross.ObjectMapperUtils;
import br.com.leroymerlin.estoque.usecase.SaveProductUseCase;
import br.com.leroymerlin.estoque.usecase.request.SaveProductRequest;

@Component
public class ProductListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductListener.class);

	@Value("${queue.name}")
	private String queueName;

	private final JmsTemplate jmsTemplate;
	private final SaveProductUseCase saveProductUseCase;

	public ProductListener(final SaveProductUseCase saveProductUseCase, final JmsTemplate jmsTemplate) {
		this.saveProductUseCase = saveProductUseCase;
		this.jmsTemplate = jmsTemplate;
	}

	@JmsListener(destination = "${queue.name}")
	public void read(String message) {
		try {
			LOGGER.info("Consuming message: {}", message);
			var request = ObjectMapperUtils.MAPPER.readValue(message, SaveProductRequest.class);
			saveProductUseCase.save(request);
			LOGGER.info("Product saved successfully!");
		} catch (Exception e) {
			LOGGER.error("Error converting message", e);
			process(message, e);
		}
	}

	private void process(String message, Exception e) {
		// TODO tratar mensagem
		var destination = "????";
		if (e instanceof SQLException) {
			destination = queueName;
		}
		jmsTemplate.convertAndSend(destination, message);
	}

}
