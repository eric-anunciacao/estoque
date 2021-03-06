package com.easys.estoque.entrypoint.listener;

import java.io.IOException;

import javax.jms.JMSException;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.easys.estoque.cross.ObjectMapperUtils;
import com.easys.estoque.usecase.SaveProcessingProblemUseCase;
import com.easys.estoque.usecase.SaveProductUseCase;
import com.easys.estoque.usecase.UpdatePersistedRecordsUseCase;
import com.easys.estoque.usecase.request.SaveProductRequest;

@Component
public class ProductListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductListener.class);

	private final SaveProductUseCase saveProductUseCase;
	private final UpdatePersistedRecordsUseCase updatePersistedRecordsUseCase;
	private final SaveProcessingProblemUseCase saveProcessingProblemUseCase;

	public ProductListener(final SaveProductUseCase saveProductUseCase,
			final UpdatePersistedRecordsUseCase updatePersistedRecordsUseCase,
			final SaveProcessingProblemUseCase saveProcessingProblemUseCase) {
		this.saveProductUseCase = saveProductUseCase;
		this.updatePersistedRecordsUseCase = updatePersistedRecordsUseCase;
		this.saveProcessingProblemUseCase = saveProcessingProblemUseCase;
	}

	@JmsListener(destination = "${product.queue.name}")
	public void read(ActiveMQTextMessage activeMQMessage) throws JMSException, IOException {
		var fileId = (Long) activeMQMessage.getProperty("file_id");
		var message = activeMQMessage.getText();

		try {
			LOGGER.info("Consuming message: {}", message);
			Thread.sleep(1000); // para testar o endpoint que retorna o status do arquivo

			var request = ObjectMapperUtils.MAPPER.readValue(message, SaveProductRequest.class);
			this.saveProductUseCase.save(request);
			this.updatePersistedRecordsUseCase.update(fileId);
			LOGGER.info("Product saved successfully!");
		} catch (Exception e) {
			LOGGER.error("Error processing message", e);
			this.saveProcessingProblemUseCase.save(fileId, message, ExceptionUtils.getStackTrace(e));
		}
	}

}
