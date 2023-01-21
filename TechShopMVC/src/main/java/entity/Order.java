package entity;

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
	private String receiveMethod;
	private String paymentType;
	private String paymentDate;
	private String status;
	private double shipping;
	private double total;
	
	public Order(String id, String orderNumber, String date, String checkOutEmail, String checkOutFullname,
			String checkOutPhone, String receiverFullname, String receiverPhone, String receiverAddress,
			String receiveMethod, String paymentType, String paymentDate, String status, double shipping,
			double total) {
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
		this.paymentType = paymentType;
		this.paymentDate = paymentDate;
		this.status = status;
		this.shipping = shipping;
		this.total = total;
	}

	public String getId() {
		return id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public String getDate() {
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

	public String getReceiveMethod() {
		return receiveMethod;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public String getStatus() {
		return status;
	}

	public double getShipping() {
		return shipping;
	}

	public double getTotal() {
		return total;
	}
}
