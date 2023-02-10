package entity;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import annotation.DBField;
import annotation.Table;
import global.OrderPaymentTypeEnum;
import global.OrderReceiveMethodEnum;
import global.OrderStatusEnum;
import util.Utility;

@Table(name = "orders")
public class Order {

	private String id;

	@DBField(name = "order_number", type = String.class)
	private String orderNumber = "";

	@DBField(name = "order_date", type = String.class)
	private String orderDate = "";

	@DBField(name = "user_id", type = String.class)
	private String userID = "";

	@DBField(name = "checkout_email", type = String.class)
	private String checkOutEmail = "";

	@DBField(name = "checkout_fullname", type = String.class)
	private String checkOutFullname = "";

	@DBField(name = "checkout_phone", type = String.class)
	private String checkOutPhone = "";

	@DBField(name = "receiver_fullname", type = String.class)
	private String receiverFullname = "";

	@DBField(name = "receiver_phone", type = String.class)
	private String receiverPhone = "";

	@DBField(name = "receiver_address", type = String.class)
	private String receiverAddress = "";

	@DBField(name = "receive_method", type = Enum.class)
	private OrderReceiveMethodEnum receiveMethod;

	@DBField(name = "order_status", type = Enum.class)
	private OrderStatusEnum orderStatus;

	@DBField(name = "shipping", type = Double.class)
	private double shippingCost;

	@DBField(name = "total", type = Double.class)
	private double totalCost;

	@DBField(name = "payment_type", type = Enum.class)
	private OrderPaymentTypeEnum paymentType;

	@DBField(name = "payment_date", type = String.class)
	private String paymentDate = "";

	@DBField(name = "payment_id", type = String.class)
	private String paymentID = "";

	public Order(String id, String orderNumber, String orderDate, String checkOutEmail, String checkOutFullname,
			String checkOutPhone, String receiverFullname, String receiverPhone, String receiverAddress,
			OrderReceiveMethodEnum receiveMethod, OrderStatusEnum orderStatus, double shippingCost, double totalCost,
			OrderPaymentTypeEnum paymentType, String paymentDate, String paymentID) {
		this.id = id;
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.checkOutEmail = checkOutEmail;
		this.checkOutFullname = checkOutFullname;
		this.checkOutPhone = checkOutPhone;
		this.receiverFullname = receiverFullname;
		this.receiverPhone = receiverPhone;
		this.receiverAddress = receiverAddress;
		this.receiveMethod = receiveMethod;
		this.orderStatus = orderStatus;
		this.shippingCost = shippingCost;
		this.totalCost = totalCost;
		this.paymentType = paymentType;
		this.paymentDate = paymentDate;
		this.paymentID = paymentID;
	}

	public Order(String userID, String checkOutEmail, String checkOutFullname, String checkOutPhone,
			String receiverFullname, String receiverPhone, String receiverAddress, OrderReceiveMethodEnum receiveMethod,
			OrderStatusEnum orderStatus, double shippingCost, double totalCost, OrderPaymentTypeEnum paymentType) {
		this.userID = userID;
		this.checkOutEmail = checkOutEmail;
		this.checkOutFullname = checkOutFullname;
		this.checkOutPhone = checkOutPhone != null ? checkOutPhone : "";
		this.receiverFullname = receiverFullname;
		this.receiverPhone = receiverPhone != null ? receiverPhone : "";
		this.receiverAddress = receiverAddress;
		this.receiveMethod = receiveMethod;
		this.orderStatus = orderStatus;
		this.shippingCost = shippingCost;
		this.totalCost = totalCost;
		this.paymentType = paymentType;
	}

	public String getId() {
		return id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public String getOrderDate() {
		return Utility.convertYMDToDMY(orderDate);
	}

	public String getCheckOutEmail() {
		return checkOutEmail;
	}

	public String getCheckOutFullname() {
		return checkOutFullname;
	}

	public String getCheckOutPhone() {
		return checkOutPhone;
	}

	public String getReceiverFullname() {
		return receiverFullname;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public OrderReceiveMethodEnum getReceiveMethod() {
		return receiveMethod;
	}

	public OrderPaymentTypeEnum getPaymentType() {
		return paymentType;
	}

	public String getPaymentDate() {
		return Utility.convertYMDToDMY(paymentDate);
	}

	public OrderStatusEnum getOrderStatus() {
		return orderStatus;
	}

	public double getShippingCost() {
		return shippingCost;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getPaymentID() {
		return paymentID;
	}

	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}

	public String getUserID() {
		return userID;
	}

	public void setPaymentDate(String paymentDate) {
		String formattedPaymentDate = paymentDate;

		if (!formattedPaymentDate.equalsIgnoreCase("")) {
			Instant instant = Instant.parse(formattedPaymentDate);
			ZoneId zone = ZoneId.of("Australia/Sydney");
			formattedPaymentDate = LocalDate.ofInstant(instant, zone).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}

		this.paymentDate = formattedPaymentDate;
	}

	public void setOrderStatus(OrderStatusEnum orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public String toString() {
		return "Order [orderNumber=" + orderNumber + ", orderDate=" + orderDate + ", checkOutEmail=" + checkOutEmail
				+ ", checkOutFullname=" + checkOutFullname + ", checkOutPhone=" + checkOutPhone + ", receiverFullname="
				+ receiverFullname + ", receiverPhone=" + receiverPhone + ", receiverAddress=" + receiverAddress
				+ ", receiveMethod=" + receiveMethod + ", paymentType=" + paymentType + ", paymentDate=" + paymentDate
				+ ", orderStatus=" + orderStatus + ", shippingCost=" + shippingCost + ", totalCost=" + totalCost + "]";
	}
}
