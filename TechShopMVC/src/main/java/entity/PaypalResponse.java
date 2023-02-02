package entity;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import constant.GlobalConstant;
import constant.OrderPaymentTypeEnum;

public class PaypalResponse {
	private String orderNumber;
	private String payerEmail;
	private String payerFullname;
	private String receiverFullname;
	private String receiverAddress;
	private double shipping;
	private String paymentDate;
	private String paymentID;
	private String paymentStatus;
	private double total;
	
	public String getOrderOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getPayerEmail() {
		return payerEmail;
	}
	public void setPayerEmail(String payerEmail) {
		this.payerEmail = payerEmail;
	}
	public String getPayerFullname() {
		return payerFullname;
	}
	public void setPayerFullname(String payerFullname) {
		this.payerFullname = payerFullname;
	}
	public String getReceiverFullname() {
		return receiverFullname;
	}
	public void setReceiverFullname(String receiverFullname) {
		this.receiverFullname = receiverFullname;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public double getShipping() {
		return shipping;
	}
	public void setShipping(double shipping) {
		this.shipping = shipping;
	}
	public String getPaymentDate() {
		if (!paymentDate.equalsIgnoreCase("")) {
			Instant instant = Instant.parse(paymentDate);
			ZoneId zone = ZoneId.of("Australia/Sydney");
			paymentDate = LocalDate.ofInstant(instant, zone).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}		
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getPaymentID() {
		return paymentID;
	}
	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public OrderPaymentTypeEnum getPaymentType() {
		return paymentStatus.equals(GlobalConstant.PAYMENT_COMPLETED) ? OrderPaymentTypeEnum.CARD : OrderPaymentTypeEnum.UNPAID;
	}
}
