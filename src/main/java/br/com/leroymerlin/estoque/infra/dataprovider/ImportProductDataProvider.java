package br.com.leroymerlin.estoque.infra.dataprovider;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import br.com.leroymerlin.estoque.domain.dto.ImportProductDto;
import br.com.leroymerlin.estoque.domain.gateway.ImportProductGateway;

@Component
class ImportProductDataProvider implements ImportProductGateway {

	@Value("${product.queue.name}")
	private String queueName;

	private final JmsTemplate jmsTemplate;

	public ImportProductDataProvider(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@Override
	public void sendToQueue(List<ImportProductDto> products) {
		if (CollectionUtils.isNotEmpty(products)) {
			products.stream().forEach(p -> this.jmsTemplate.convertAndSend(queueName, p.toString()));
		}
	}

}
