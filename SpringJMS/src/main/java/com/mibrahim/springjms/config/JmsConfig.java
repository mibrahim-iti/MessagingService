package com.mibrahim.springjms.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MarshallingMessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mibrahim.springjms.pojos.Book;
import com.mibrahim.springjms.pojos.BookOrder;
import com.mibrahim.springjms.pojos.Customer;

@EnableJms
@EnableTransactionManagement
@Configuration
public class JmsConfig {// implements JmsListenerConfigurer {

	private static final Logger LOGGER = LoggerFactory.getLogger(JmsConfig.class);
	
//	@Autowired
//	private ConnectionFactory connectionFactory;

	@Value("${spring.activemq.broker-url}")
	private String brokerUrl;

	@Value("${spring.activemq.user}")
	private String user;

	@Value("${spring.activemq.password}")
	private String password;

	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

//	@Bean
	public MessageConverter xmlMarshallingMessageConverter() {
		MarshallingMessageConverter converter = new MarshallingMessageConverter(xmlMarshaller());
		converter.setTargetType(MessageType.TEXT);
		return converter;
	}

//	@Bean
	public XStreamMarshaller xmlMarshaller() {
		XStreamMarshaller marshaller = new XStreamMarshaller();
		marshaller.setSupportedClasses(Book.class, Customer.class, BookOrder.class);
		return marshaller;
	}

//	@Bean
//	public JmsListenerContainerFactory<?> warehouseFactory(ConnectionFactory factory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
//		DefaultJmsListenerContainerFactory containerFactory = new DefaultJmsListenerContainerFactory();
//		configurer.configure(containerFactory, factory);
//		return containerFactory;
//	}

//	@Bean
//	public ActiveMQConnectionFactory connectionFactory() {
//		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(user, password, brokerUrl);
//		return factory;
//	}
	
//	@Bean
//	public SingleConnectionFactory connectionFactory() {
//		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(user, password, brokerUrl);
//		SingleConnectionFactory singleConnectionFactory = new SingleConnectionFactory(factory);
//		singleConnectionFactory.setReconnectOnException(true);
//		singleConnectionFactory.setClientId("StoreFront");
//		return singleConnectionFactory;
//	}
	
	@Bean
	public CachingConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(user, password, brokerUrl);
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(factory);
//		cachingConnectionFactory.setReconnectOnException(true);
		cachingConnectionFactory.setClientId("StoreFront");
		cachingConnectionFactory.setSessionCacheSize(100);
		return cachingConnectionFactory;
	}

//	@Bean
//	public JmsTemplate jmsTemplate() {
//		JmsTemplate jmsTemplate = new JmsTemplate();
//		jmsTemplate.setConnectionFactory(connectionFactory());
//		jmsTemplate.setMessageConverter(jacksonJmsMessageConverter());
//		return jmsTemplate;
//	}
	
	@Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
        jmsTemplate.setMessageConverter(jacksonJmsMessageConverter());
        jmsTemplate.setDeliveryPersistent(true);
        jmsTemplate.setSessionTransacted(true);
        return jmsTemplate;

    }

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		factory.setMessageConverter(jacksonJmsMessageConverter());
//		factory.setMessageConverter(xmlMarshallingMessageConverter());
		factory.setTransactionManager(jmsTransactionManager());
		factory.setErrorHandler(t -> {
            LOGGER.info("Handling error in listening for messages, error: " + t.getMessage());
        });
		return factory;
	}
	
	@Bean
    public PlatformTransactionManager jmsTransactionManager(){
        return new JmsTransactionManager(connectionFactory());
    }

//	@Bean
//	public BookOrderProcessingMessageListener jmsMessageListener() {
//		return new BookOrderProcessingMessageListener();
//	}
//
//	@Override
//	public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
//		SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
//		endpoint.setMessageListener(jmsMessageListener());
//		endpoint.setDestination("book.order.processed.queue");
//		endpoint.setId("book-order-processed-queue");
//		endpoint.setConcurrency("1");
//		endpoint.setSubscription("my-subscription");
//		registrar.registerEndpoint(endpoint, defaultJmsListenerContainerFactory());
//		registrar.setContainerFactory(defaultJmsListenerContainerFactory());
//
//	}
}
