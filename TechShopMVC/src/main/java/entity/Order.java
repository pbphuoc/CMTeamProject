package entity;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import constant.OrderPaymentTypeEnum;
import constant.OrderReceiveMethodEnum;
import constant.OrderStatusEnum;

public class Order {
	private String id;
	private String orderNumber;
	private String date;
	private String checkOutEmail;
	private String checkOutFullname;
	private String checkOutPhone;
	private String receiverFullname;
	private String receiverPhone;
	private String receiverAddress;
	private OrderReceiveMethodEnum receiveMethod;
	private OrderPaymentTypeEnum paymentType;
	private String paymentDate;
	private OrderStatusEnum status;
	private double shipping;
	private double total;
	private String billingFullname;
	private String billingAddress;
	private String billingPhone;
	private String paymentName;
	private String paymentSource;

	public Order(String id, String orderNumber, String date, String checkOutEmail, String checkOutFullname,
			String checkOutPhone, String receiverFullname, String receiverPhone, String receiverAddress,
			OrderReceiveMethodEnum receiveMethod, OrderStatusEnum status, double shipping, double total,
			OrderPaymentTypeEnum paymentType, String paymentDate, String paymentName, String paymentSource,
			String billingFullname, String billingAddress, String billingPhone) {
		this.id = id;
		this.orderNumber = orderNumber;
		this.date = date;
		this.checkOutEmail = checkOutEmail;
		this.checkOutFullname = checkOutFullname;
		this.checkOutPhone = checkOutPhone;
		this.receiverFullname = receiverFullname;
		this.receiverPhone = receiverPhone;
		this.receiverAddress = receiverAddress;
		this.receiveMethod = receiveMethod;
		this.status = status;
		this.shipping = shipping;
		this.total = total;
		this.paymentType = paymentType;
		this.paymentDate = paymentDate;
		this.paymentName = paymentName;
		this.paymentSource = paymentSource;
		this.billingFullname = billingFullname;
		this.billingAddress = billingAddress;
		this.billingPhone = billingPhone;
	}

	public String getId() {
		return id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public String getDate() {
		if (!date.equalsIgnoreCase("")) {
			LocalDate originalFormat = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			date = originalFormat.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		}
		return date;
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
		return paymentDate;
	}

	public OrderStatusEnum getStatus() {
		return status;
	}

	public double getShipping() {
		return shipping;
	}

	public double getTotal() {
		return total;
	}

	public String getBillingFullname() {
		return billingFullname;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public String getBillingPhone() {
		return billingPhone;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public String getPaymentSource() {
		return paymentSource;
	}
}
