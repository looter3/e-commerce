package com.ecommerce.kafka.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author lex_looter
 *
 *         22 mar 2025
 *
 */
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class ECommerceKafkaRequest /* <REQT extends RequestType > */ {

	@JsonProperty("requestType")
//	private CRUDRequest requestType;
	private RequestType requestType;
//	private RequestCommand requestCommand;
//	private Command requestCommand;

	private Object dataToSend;

	public ECommerceKafkaRequest() {}

	public ECommerceKafkaRequest(final RequestType requestType, final Object dataToSend/* , final Command requestCommand */) {
		this.requestType = requestType;
		this.dataToSend = dataToSend;
//		this.requestCommand = requestCommand;
	}

	/*-
	public Command getRequestCommand() {
		return requestCommand;
	}

	public void setRequestCommand(final Command requestCommand) {
		this.requestCommand = requestCommand;
	}
	*/

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(final RequestType requestType) {
		this.requestType = requestType;
	}

	public Object getDataToSend() {
		return dataToSend;
	}

	public void setDataToSend(final Object dataToSend) {
		this.dataToSend = dataToSend;
	}

}
