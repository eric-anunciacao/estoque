package com.easys.estoque.infra.dataprovider;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import com.easys.estoque.domain.dto.ImportProductDto;
import com.easys.estoque.domain.gateway.ImportProductGateway;

@Component
class ImportProductDataProvider implements ImportProductGateway {

	@Value("${product.queue.name}")
	private String queueName;

	private final JmsTemplate jmsTemplate;

	public ImportProductDataProvider(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@Override
	public void sendToQueue(Long fileId, List<ImportProductDto> products) {
		if (CollectionUtils.isNotEmpty(products)) {
			products.stream()
					.forEach(p -> this.jmsTemplate.convertAndSend(queueName, p.toString(), getProperties(fileId)));
		}
	}

	private MessagePostProcessor getProperties(Long fileId) {
		return new MessagePostProcessor() {
			@Override
			public Message postProcessMessage(Message message) throws JMSException {
				message.setLongProperty("file_id", fileId);
				return message;
			}
		};
	}

}
