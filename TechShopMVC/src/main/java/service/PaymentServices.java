package service;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import constant.GlobalConstant;
import model.OrderItemDTO;
import util.Utility;

public class PaymentServices {

	public static PaymentServices paymentServices;
	private static final Logger logger = LogManager.getLogger(PaymentServices.class);

	private PaymentServices() {
	}

	public static PaymentServices getPaymentServices() {
		if (paymentServices == null)
			paymentServices = new PaymentServices();

		return paymentServices;
	}

	public String authorizePayment(List<OrderItemDTO> orderItems) throws PayPalRESTException {
		RedirectUrls redirectUrls = getRedirectURLs();
		List<Transaction> transactions = getTransactionInformation(orderItems);
		Payment requestPayment = new Payment();

		requestPayment.setPayer(new Payer().setPaymentMethod("paypal"));
		requestPayment.setTransactions(transactions);
		requestPayment.setRedirectUrls(redirectUrls);
		requestPayment.setIntent("authorize");

		APIContext apiContext = new APIContext(GlobalConstant.CLIENT_ID, GlobalConstant.CLIENT_SECRET,
				GlobalConstant.PAYPAL_MODE);

		Payment approvedPayment = requestPayment.create(apiContext);

		return getApprovalLink(approvedPayment);
	}

	private RedirectUrls getRedirectURLs() {
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl("http://localhost:8080/Checkout?command=payment");
		redirectUrls.setReturnUrl("http://localhost:8080/Checkout?command=confirm");

		return redirectUrls;
	}

	private List<Transaction> getTransactionInformation(List<OrderItemDTO> orderItems) {
		Details details = new Details();
		details.setSubtotal(Utility.get2DFPrice(Utility.calculateTotalCost(orderItems)));
		details.setShipping(Utility.get2DFPrice(0));

		Amount amount = new Amount();
		amount.setCurrency("AUD");
		amount.setTotal(Utility.get2DFPrice(Utility.calculateTotalCost(orderItems)));
		amount.setDetails(details);

		Transaction transaction = new Transaction();
		transaction.setAmount(amount);

		ItemList itemList = new ItemList();
		List<Item> items = new ArrayList<>();

		for (OrderItemDTO orderItem : orderItems) {
			Item item = new Item();
			item.setCurrency("AUD");
			item.setName(orderItem.getProduct().getName());
			item.setDescription(orderItem.getProduct().getDescription());
			item.setPrice(Utility.get2DFPrice(orderItem.getProduct().getNewPrice()));
			item.setTax(Utility.get2DFPrice(0));
			item.setQuantity(orderItem.getQuantity() + "");
			items.add(item);
		}

		itemList.setItems(items);
		transaction.setItemList(itemList);

		List<Transaction> transactions = new ArrayList<>();
		transactions.add(transaction);

		return transactions;

	}

	private String getApprovalLink(Payment approvedPayment) {
		List<Links> links = approvedPayment.getLinks();
		String approvalLink = null;

		for (Links link : links) {
			if (link.getRel().equalsIgnoreCase("approval_url")) {
				approvalLink = link.getHref();
				break;
			}
		}

		return approvalLink;
	}

	public Payment getPaymentDetails(String paymentId) {
		try {
			APIContext apiContext = new APIContext(GlobalConstant.CLIENT_ID, GlobalConstant.CLIENT_SECRET,
					GlobalConstant.PAYPAL_MODE);
			return Payment.get(apiContext, paymentId);
		} catch (PayPalRESTException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public Payment executePayment(String paymentId, String payerId) {
		try {
			PaymentExecution paymentExecution = new PaymentExecution();
			paymentExecution.setPayerId(payerId);

			Payment payment = new Payment().setId(paymentId);

			APIContext apiContext = new APIContext(GlobalConstant.CLIENT_ID, GlobalConstant.CLIENT_SECRET,
					GlobalConstant.PAYPAL_MODE);

			payment.execute(apiContext, paymentExecution);
			
			return Payment.get(apiContext, paymentId);
		} catch (PayPalRESTException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public boolean updateShippingCost(String shippingCost, String subTotal, String paymentId) {
		try {
			APIContext apiContext = new APIContext(GlobalConstant.CLIENT_ID, GlobalConstant.CLIENT_SECRET,
					GlobalConstant.PAYPAL_MODE);
			String total = (Double.parseDouble(subTotal) + Double.parseDouble(shippingCost)) + "";

			String url = GlobalConstant.PATCH_URI + paymentId;
			CloseableHttpClient client = HttpClients.createDefault();
			HttpPatch httpPatch = new HttpPatch(url);

			httpPatch.setHeader("Content-Type", "application/json");
			httpPatch.setHeader("Authorization", apiContext.fetchAccessToken());
			String patchBody = "[{\"op\":\"replace\",\"path\":\"/transactions/0/amount\",\"value\":{\"total\":\""
					+ total + "\",\"currency\":\"AUD\",\"details\":{\"subtotal\":\"" + subTotal + "\",\"shipping\":\""
					+ shippingCost + "\"}}}]";

			StringEntity entity = new StringEntity(patchBody);
			httpPatch.setEntity(entity);
			CloseableHttpResponse response = client.execute(httpPatch);

			logger.debug(response.getStatusLine().getStatusCode());
			logger.debug(EntityUtils.toString(response.getEntity()));
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return false;
	}
}
