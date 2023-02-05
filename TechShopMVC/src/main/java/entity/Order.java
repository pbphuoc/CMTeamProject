package entity;

import constant.OrderPaymentTypeEnum;
import constant.OrderReceiveMethodEnum;
import constant.OrderStatusEnum;
import util.Utility;

public class Order {
	private String id;
	private String orderNumber = "";
	private String orderDate = "";
	private String checkOutEmail = "";
	private String checkOutFullname = "";
	private String checkOutPhone = "";
	private String receiverFullname = "";
	private String receiverPhone = "";
	private String receiverAddress = "";
	private OrderReceiveMethodEnum receiveMethod;
	private OrderPaymentTypeEnum paymentType;
	private String paymentDate;
	private OrderStatusEnum orderStatus;
	private double shippingCost;
	private double totalCost;
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
	
	public Order(String checkOutEmail, String checkOutFullname,
			String checkOutPhone, String receiverFullname, String receiverPhone, String receiverAddress,
			OrderReceiveMethodEnum receiveMethod, OrderStatusEnum orderStatus, double shippingCost, double totalCost,
			OrderPaymentTypeEnum paymentType, String paymentDate, String paymentID) {
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

	public String getPaymentID() {
		return paymentID;
	}

}
