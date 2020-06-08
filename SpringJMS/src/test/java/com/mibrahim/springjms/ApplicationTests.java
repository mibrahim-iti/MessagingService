//package com.mibrahim.springjms;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@RunWith(JUnitPlatform.class)
//@SpringBootTest(classes = Application.class)
//public class ApplicationTests {
//
//	// https://codenotfound.com/spring-jms-activemq-example.html
//	
//	@Test
//	public void test() {
//	}
//
////	@Autowired
////	private JmsTemplate jmsTemplate;
////
////	@Test // Failed test
////	public void test() {
////		jmsTemplate.convertAndSend("order-queue", "Hello!");
////		jmsTemplate.setReceiveTimeout(3_000);
////		assertEquals("HELLO!", jmsTemplate.receiveAndConvert("order-queue"));
////	}
////
////	@Test
////	public void receiveAndConvertDefaultDestinationName() {
////		when(this.jmsTemplate.receive("order-queue")).thenReturn(null);
////
////		jmsTemplate.convertAndSend("order-queue", "Hello!");
////		jmsTemplate.setReceiveTimeout(3_000);
////
////		assertEquals("my Payload", jmsTemplate.receiveAndConvert("order-queue"));
////		verify(this.jmsTemplate).receive("order-queue");
////	}
//
//}
