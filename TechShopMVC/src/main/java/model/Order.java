package model;

public class Order {
	private String id;
	private String date;
	private String userId;
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
	private String shipping;
	private double total;
	
	enum RECEIVEMETHOD{
		PICKUP,
		DELIVERY
	}
	
	enum PAYMENTTYPE{
		PAYATSTORE,
		TRANSFER,
		CARD
	}
	
	enum STATUS{
		PENDING,
		RECEIVED,
		PROCESSED,
		READY,
		FINISHED
	}
	
	public Order(String id, String date, String userId, String checkOutEmail, String checkOutFullname,
			String checkOutPhone, String receiverFullname, String receiverPhone, String receiverAddress,
			String receiveMethod, String paymentType, String paymentDate, String status, String shipping,
			double total) {
		this.id = id;
		this.date = date;
		this.userId = userId;
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
}
