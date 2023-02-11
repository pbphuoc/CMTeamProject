package global;

import java.util.ArrayList;
import java.util.List;

public class GlobalConstant {

	public static final String DO_DB_URL = "jdbc:mysql://techiladb-do-user-13228925-0.b.db.ondigitalocean.com:25060/cm_project";
	public static final String DO_DB_USERNAME = "doadmin";
	public static final String DO_DB_PASSWORD = "AVNS_kt3ZJPXPT-M9qh-YgQJ";
	public static final String APP_BASE_URI = "http://170.64.149.41";

	// Paypal API
	public static final String CLIENT_ID = "AZmg1ZSNmhnaCGzKgw_Sl3Smj-PbLF-CMoTFksM5lWWztu_KFn39MM1hKyQ_k9H-FCmyLoOexSk9lrMv";
//	public static final String CLIENT_ID = "AX8CKD2lJ1qKmUNV_JPYuz0XXqkYrEOJpSrgLgkm1-IW8YEI-X8FuWZ18_6vOpE1r4Fp1TJezbrrqcwx";
	public static final String CLIENT_SECRET = "ENIR5vyUWvsn50d-a6x46MKrRGYRpsCCYCnFi8-wvnaC2eq_3b2QzaOCqE9EDxyUcQQ2T3qTTWXy1fgb";
//	public static final String CLIENT_SECRET = "EHRwXQMaP1Maa677fM6UoKhT-mioV5aDGDoYzfxGf24r4HBYSj0WTaRht7h8x7qKlKmrcuDfWgPAQl32";	
	public static final String PAYPAL_API = "https://www.paypal.com/sdk/js?client-id=" + CLIENT_ID
			+ "&currency=AUD&intent=capture";
	public static final String SANDBOX = "sandbox";
	public static final String LIVE = "live";
	public static final String PAYPAL_MODE = SANDBOX;
	public static final String SANDBOX_BASE_URI = "https://api-m.sandbox.paypal.com";
	public static final String LIVE_BASE_URI = "https://api.paypal.com";
	public static final String PAYPAL_BASE_URI = SANDBOX_BASE_URI;
	public static final String PATCH_URI = PAYPAL_BASE_URI + "/v1/payments/payment/";
	public static final String PAYMENT_COMPLETED = "COMPLETED";

	// jsp
	public static final String REGISTER_JSP = "register.jsp";
	public static final String LOGIN_JSP = "login.jsp";
	public static final String CART_JSP = "cart.jsp";
	public static final String CHECKOUT_JSP = "checkout.jsp";
	public static final String CONFIRMATION_JSP = "confirmation.jsp";
	public static final String INDEX_JSP = "index.jsp";
	public static final String ORDER_HISTORY_JSP = "orderhistory.jsp";
	public static final String TRACK_ORDER_JSP = "trackOrder.jsp";
	public static final String PRODUCT_JSP = "product.jsp";
	public static final String SEARCH_JSP = "search.jsp";
	public static final String ALLREF_JSP = "allref.jsp";
	public static final String HEADER_JSP = "header.jsp";
	public static final String FOOTER_JSP = "footer.jsp";
	public static final String ERROR_JSP = "error.jsp";
	public static final String ALLSCRIPT_JSP = "allscript.jsp";
	public static final String LOGIN_BUTTON_JSP = "loginButton.jsp";
	public static final String IS_INVALID = "is-invalid";
	public static final String EMAIL_PATTERN = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";
	public static final String AUS_MOBILE_PATTERN = "^\\({0,1}((0|\\+61)(2|4|3|7|8)){0,1}\\){0,1}( |-){0,1}[0-9]{2}( |-){0,1}[0-9]{2}( |-){0,1}[0-9]{1}( |-){0,1}[0-9]{3}$";

	// shared
	public static final String BLANK = "";
	public static final String COMMAND = "command";
	public static final String PREV_URL = "prevUrl";
	public static final String USER = "user";
	public static final String ERROR = "error";
	public static final String PRODUCT_ID = "productID";
	public static final String CART_ITEM = "cartItems";
	public static final String ORDER_ITEM_DTO = "items";
	public static final String ORDER = "order";
	public static final String GUEST_ID = "-1";
	public static final String DEFAULT_SHIPPING = "0";
	public static final String DEFAULT_SORTBY = SortByEnum.RELEVANCY.toString();
	public static final String DEFAULT_RESULTPERPAGE = "15";
	public static final String DEFAULT_PAGE = "1";

	// error
	public static final String EMAIL_REGISTERED_ALREADY = "This email Address has been registered already";
	public static final String EMAIL_FOUND_PLEASE_LOG_IN = "This Email Address has been registered in the system already. Please login before proceed to checkout";
	public static final String INCORRECT_LOGIN_DETAIL = "Incorrect Email or Password. Please try again";
	public static final String NO_ORDER_FOUND = "No Order Found";

	// Authentication Servlet
	public static final String AUTH_URL = "/Auth";
	public static final String LOGIN = "login";
	public static final String LOGOUT = "logout";
	public static final String GET_LOGIN_FORM = "getLoginForm";
	public static final String GET_REGISTER_FORM = "getRegisterForm";
	public static final String REGISTER = "register";
	public static final String FULLNAME_REGISTER = "fullnameRegister";
	public static final String EMAIL_REGISTER = "emailRegister";
	public static final String PASSWORD_REGISTER = "passwordRegister";
	public static final String MOBILE_REGISTER = "mobileRegister";
	public static final String EMAIL_LOGIN = "emailLogin";
	public static final String PASSWORD_LOGIN = "passwordLogin";

	// Index Servlet
	public static final String HOME_URL = "/Home";

	// Cart Servlet
	public static final String CART_URL = "/Cart";
	public static final String INCREASE = "increase";
	public static final String DECREASE = "decrease";
	public static final String REMOVE = "remove";
	public static final String VIEW_CART = "viewCart";

	// Checkout Servlet
	public static final String CHECKOUT_URL = "/Checkout";
	public static final String DETAIL = "detail";
	public static final String PAYMENT = "payment";
	public static final String CONFIRM = "confirm";
	public static final String SUBMIT_ORDER = "submitOrder";

	// Order Servlet
	public static final String ORDER_URL = "/Order";
	public static final String GET_TRACK_ORDER_FORM = "getTrackOrderForm";
	public static final String TRACK_ORDER = "trackOrder";
	public static final String VIEW_ORDER_DETAIL = "viewOrderDetail";

	// Product Servlet
	public static final String PRODUCT_URL = "/Product";
	public static final String VIEW_PRODUCT_DETAIL = "viewProductDetail";
	public static final String SEARCH = "search";

	// Account Servlet
	public static final String ACCOUNT_URL = "/Account";
	public static final String VIEW_ORDER = "viewOrders";
	public static final String ORDER_LIST = "orderList";

	// Error Handler Servlet
	public static final String ERROR_MESSAGE = "errorMessage";
	public static final String ERROR_CODE = "errorCode";
	public static final String ERROR_NAME = "errorName";
	public static final int ERROR_403 = 403;
	public static final int ERROR_404 = 404;
	public static final int ERROR_500 = 500;
	public static final String ERROR_403_NAME = "Forbidden Error";
	public static final String ERROR_404_NAME = "Not Found Error";
	public static final String ERROR_500_NAME = "Internal Server Error";
	public static final String ERROR_403_MESSAGE = "You do not have permission to access this page";
	public static final String ERROR_404_MESSAGE = "Your requested resources are not found";
	public static final String ERROR_500_MESSAGE = "Unexpected issues from us. Please contact admin@techila.com.au for support";

	public static final String SELECT_FROM_SUB_QUERY = "SELECT * FROM";
	public static final String AND_QUERY = " AND ";
	public static final String OR_QUERY = " OR ";
	public static final String HAVING_QUERY = " HAVING ";

	public static final List<Integer> RESULT_PER_PAGE_LIST = new ArrayList<Integer>() {
		{
			add(15);
			add(30);
			add(60);
			add(120);
		}
	};
}
