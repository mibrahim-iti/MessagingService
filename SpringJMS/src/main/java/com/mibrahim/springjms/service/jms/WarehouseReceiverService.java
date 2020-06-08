package com.mibrahim.springjms.service.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.JmsResponse;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.mibrahim.springjms.pojos.BookOrder;
import com.mibrahim.springjms.pojos.ProcessedBookOrder;

@Service
public class WarehouseReceiverService {

	private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseReceiverService.class);

	@Autowired
	private WarehouseProcessingService warehouseProcessingService;

//    @JmsListener(destination = "book.order.queue")
//    public void receive(BookOrder bookOrder){
//        LOGGER.info("Message received!");
//        LOGGER.info("Message is == " + bookOrder);
//
//        if(bookOrder.getBook().getTitle().startsWith("L")){
//            throw new RuntimeException("bookOrderId=" + bookOrder.getBookOrderId() + " is of a book not allowed!");
//        }
//        warehouseProcessingService.processOrder(bookOrder);
//    }

	// ========================================================Start 1========================================================
//	@JmsListener(destination = "book.order.queue")
//	public void receive(@Payload BookOrder bookOrder, @Header(name = "orderState") String orderState,
//			@Header(name = "bookOrderId") String bookOrderId, @Header(name = "storeId") String storeId, MessageHeaders messageHeaders) {
//		LOGGER.info("Message received!");
//		LOGGER.info("Message is == " + bookOrder);
//		LOGGER.info("Message property orderState = {}, bookOrderId = {}, storeId = {}", orderState, bookOrderId, storeId);
//		LOGGER.info("messageHeaders = {}", messageHeaders);
//
//		if (bookOrder.getBook().getTitle().startsWith("L")) {
//			throw new RuntimeException("bookOrderId=" + bookOrder.getBookOrderId() + " is of a book not allowed!");
//		}
//		warehouseProcessingService.processOrder(bookOrder, orderState, storeId);
//	}
	// ========================================================End 1========================================================

	// ========================================================Start 2========================================================
//	@JmsListener(destination = "book.order.queue")
//    @SendTo("book.order.processed.queue")
//	public ProcessedBookOrder receive(@Payload BookOrder bookOrder, @Header(name = "orderState") String orderState,
//			@Header(name = "bookOrderId") String bookOrderId, @Header(name = "storeId") String storeId, MessageHeaders messageHeaders) {
//		LOGGER.info("Message received!");
//		LOGGER.info("Message is == " + bookOrder);
//		LOGGER.info("Message property orderState = {}, bookOrderId = {}, storeId = {}", orderState, bookOrder, storeId);
//		LOGGER.info("MessageHeaders = " + messageHeaders);
//
//		if (bookOrder.getBook().getTitle().startsWith("L")) {
//			throw new IllegalArgumentException("bookOrderId=" + bookOrder.getBookOrderId() + " is of a book not allowed!");
//		}
//
//		return warehouseProcessingService.processOrder(bookOrder, orderState, storeId);
//	}
	// ========================================================End 2========================================================

	// ========================================================Start 3========================================================
//	@JmsListener(destination = "book.order.queue")
//    @SendTo("book.order.processed.queue")
//	public Message<ProcessedBookOrder> receive(@Payload BookOrder bookOrder, @Header(name = "orderState") String orderState,
//			@Header(name = "bookOrderId") String bookOrderId, @Header(name = "storeId") String storeId, MessageHeaders messageHeaders) {
//		LOGGER.info("Message received!");
//		LOGGER.info("Message is == " + bookOrder);
//		LOGGER.info("Message property orderState = {}, bookOrderId = {}, storeId = {}", orderState, bookOrder, storeId);
//		LOGGER.info("MessageHeaders = " + messageHeaders);
//
//		if (bookOrder.getBook().getTitle().startsWith("L")) {
//			throw new IllegalArgumentException("bookOrderId=" + bookOrder.getBookOrderId() + " is of a book not allowed!");
//		}
//
//		return warehouseProcessingService.processOrder(bookOrder, orderState, storeId);
//	}
	// ========================================================End 3========================================================
	@JmsListener(destination = "book.order.queue")
	public JmsResponse<Message<ProcessedBookOrder>> receive(@Payload BookOrder bookOrder, @Header(name = "orderState") String orderState,
			@Header(name = "bookOrderId") String bookOrderId, @Header(name = "storeId") String storeId) {
		LOGGER.info("Message received!");
		LOGGER.info("Message is == " + bookOrder);
		LOGGER.info("Message property orderState = {}, bookOrderId = {}, storeId = {}", orderState, bookOrder, storeId);

		if (bookOrder.getBook().getTitle().startsWith("L")) {
			throw new IllegalArgumentException("bookOrderId=" + bookOrder.getBookOrderId() + " is of a book not allowed!");
		}

		return warehouseProcessingService.processOrder(bookOrder, orderState, storeId);
	}
}
