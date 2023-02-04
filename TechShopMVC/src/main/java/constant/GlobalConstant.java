package constant;

import java.util.LinkedHashMap;
import java.util.Map;

public class GlobalConstant {

	// db connection
// 	private static final String DB_URL ="jdbc:mysql://localhost:3306/cm_project";
//	private static final String DB_USERNAME = "projectuser";	
//	private static final String DB_PASSWORD = "codingmentor";		
	public static final String DO_DB_URL = "jdbc:mysql://techiladb-do-user-13228925-0.b.db.ondigitalocean.com:25060/cm_project";
	public static final String DO_DB_USERNAME = "doadmin";
	public static final String DO_DB_PASSWORD = "AVNS_kt3ZJPXPT-M9qh-YgQJ";

	// Paypal API
	public static final String CLIENT_ID = "AZmg1ZSNmhnaCGzKgw_Sl3Smj-PbLF-CMoTFksM5lWWztu_KFn39MM1hKyQ_k9H-FCmyLoOexSk9lrMv";
//	public static final String CLIENT_ID = "AX8CKD2lJ1qKmUNV_JPYuz0XXqkYrEOJpSrgLgkm1-IW8YEI-X8FuWZ18_6vOpE1r4Fp1TJezbrrqcwx";
//	public static final String CLIENT_SECRET = "ENIR5vyUWvsn50d-a6x46MKrRGYRpsCCYCnFi8-wvnaC2eq_3b2QzaOCqE9EDxyUcQQ2T3qTTWXy1fgb";
	public static final String PAYPAL_API = "https://www.paypal.com/sdk/js?client-id=" + CLIENT_ID
			+ "&currency=AUD&intent=capture";
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
	public static final String ALLSCRIPT_JSP = "allscript.jsp";
	public static final String LOGIN_BUTTON_JSP = "loginButton.jsp";
	public static final String IS_INVALID = "is-invalid";
	public static final String EMAIL_PATTERN = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$";

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
	public static final String DEFAULT_SORTBY = "0";
	public static final String DEFAULT_RESULTPERPAGE = "16";
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
	public static final String PAYMENT = "payment";

	// Order Servlet
	public static final String ORDER_URL = "/Order";
	public static final String GET_TRACK_ORDER_FORM = "getTrackOrderForm";
	public static final String SUBMIT_ORDER = "submitOrder";
	public static final String TRACK_ORDER = "trackOrder";
	public static final String VIEW_ORDER_DETAIL = "viewOrderDetail";

	// Product Servlet
	public static final String PRODUCT_URL = "/Product";
	public static final String VIEW_PRODUCT_DETAIL = "viewProductDetail";
	public static final String SEARCH = "search";

	// Account Servlet
	public static final String ACCOUNT_URL = "/Account";
	public static final String VIEW_ORDER = "viewOrders";

	public static final String SELECT_FROM_SUB_QUERY = "SELECT * FROM";
	public static final String AND_QUERY = " AND ";
	public static final String OR_QUERY = " OR ";
	public static final String WHERE_QUERY = " WHERE ";
	public static final String STOCKSTATUS_OUTOFSTOCK = "0";
	public static final String STOCKSTATUS_INSTOCK = "1";

	public static final Map<String, String> AVAILABILITY_MAP = new LinkedHashMap<String, String>() {
		{
			put(STOCKSTATUS_OUTOFSTOCK, "Out Of Stock");
			put(STOCKSTATUS_INSTOCK, "In Stock");
		}
	};

	// refactor soon
	public static final Map<String, String> SORTBY_MAP = new LinkedHashMap<String, String>() {
		{
			put("0", "Relevancy"); // what is this ?? dont understand
			put("5", "Price Low To High");
			put("-5", "Price High To Low");
			put("2", "Name A To Z");
			put("-2", "Name Z To A");
			put("1", "Old To New");
			put("-1", "New To Old");
		}
	};

	// refactor soon
	public static final Map<String, String> RESULTPERPAGE_MAP = new LinkedHashMap<String, String>() {
		{
			put("16", "16"); // why this happen??
			put("32", "32");
			put("64", "64");
			put("128", "128");
		}
	};
}
