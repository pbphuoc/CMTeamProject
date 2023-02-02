const fundingSources = [
	paypal.FUNDING.PAYPAL
]
for (const fundingSource of fundingSources) {
	const paypalButtonsComponent = paypal.Buttons({
		fundingSource: fundingSource,

		// optional styling for buttons
		// https://developer.paypal.com/docs/checkout/standard/customize/buttons-style-guide/
		style: {
			shape: 'pill',
			height: 40,
		},
		// Sets up the transaction when a payment button is clicked
		createOrder: (data, actions) => {
//			const totalItemCost = getPriceValue($('#totalCost').text());
			const totalItemCost = 0.5;
			const shippingCost = 0;
			return actions.order.create({
				purchase_units: [{
					amount: {
						value: totalItemCost, // Can also reference a variable or function
						breakdown: {
							item_total: {
								currency_code: 'AUD',
								value: totalItemCost
							},
							shipping: {
								currency_code: 'AUD',
								value: shippingCost
							}
						}
					}
				}]
			});
		},
		onShippingChange: function(data, actions) {
			if (data.shipping_address.country_code !== 'AU') {
				return actions.reject();
			}
			var shippingCost = data.shipping_address.state === 'QLD' ? '15.00' : '25.00';
			var totalItemCost = getPriceValue($('#totalCost').text());
			$('#shippingCost').text(formatter.format(shippingCost));
			totalItemCost = 0.5;
			shippingCost = 0.5;	
			return actions.order.patch([
				{
					op: 'replace',
					path: '/purchase_units/@reference_id==\'default\'/amount',
					value: {
						currency_code: 'AUD',
						value: (parseFloat(totalItemCost) + parseFloat(shippingCost)).toFixed(2),
						breakdown: {
							item_total: {
								currency_code: 'AUD',
								value: totalItemCost
							},
							shipping: {
								currency_code: 'AUD',
								value: shippingCost
							}
						}
					}
				}
			]);
		},
		// Finalize the transaction after payer approval
		onApprove: (data, actions) => {
			return actions.order.capture().then(function(orderData) {
				// Successful capture! For dev/demo purposes:
				console.log('Capture result', orderData, JSON.stringify(orderData, null, 2));
				const transaction = orderData.purchase_units[0].payments.captures[0];
				const shippingCost = getPriceValue($('#shippingCost').text());
				const totalItemCost = getPriceValue($('#totalCost').text());
				const address2 = orderData.purchase_units[0].shipping.address.address_line_2 == undefined ? '' : orderData.purchase_units[0].shipping.address.address_line_2;
				console.log(`Transaction ${transaction.status}: ${transaction.id}\n\nSee console for all available details`);
				// When ready to go live, remove the alert and show a success message within this page. For example:
				// const element = document.getElementById('paypal-button-container');
				// element.innerHTML = '<h3>Thank you for your payment!</h3>';
				// Or go to another URL:  actions.redirect('thank_you.html');
				function submitOrder() {
					return fetch('/Order?command=submitOrder', {
						method: 'post',
						headers: {
							'content-type': 'application/json'
						},
						body: JSON.stringify({
							orderNumber: orderData.id,
							payerEmail: orderData.payer.email_address,
							payerFullname: orderData.payer.name.given_name + orderData.payer.name.surname,
							receiverFullname: orderData.purchase_units[0].shipping.name.full_name,
							receiverAddress: orderData.purchase_units[0].shipping.address.address_line_1 + ' '
								+ address2 + ', '
								+ orderData.purchase_units[0].shipping.address.admin_area_2 + ', '
								+ orderData.purchase_units[0].shipping.address.admin_area_1 + ', '
								+ orderData.purchase_units[0].shipping.address.country_code + ', '
								+ orderData.purchase_units[0].shipping.address.postal_code,
							shipping: ((shippingCost != '0')) ? orderData.purchase_units[0].amount.breakdown.shipping.value : '0',
							paymentDate: orderData.purchase_units[0].payments.captures[0].create_time,
							paymentID: orderData.purchase_units[0].payments.captures[0].id,
							paymentStatus: transaction.status,
							total: (totalItemCost != 0) ? orderData.purchase_units[0].payments.captures[0].amount.value : '0'
						})
					}).then((response) => {
						return response.text();
					}).then((data) => {
						console.log(data);
						return data;
					}).catch((err) => {
						console.log(err);
					});
				}
				return submitOrder().then((data) => {
					window.location.replace(data);
//					window.document.querySelector('html').innerHTML = data;
				});
			});
		}
	})

	if (paypalButtonsComponent.isEligible()) {
		paypalButtonsComponent
			.render('#paypal-button-container')
			.catch((err) => {
				console.error('PayPal Buttons failed to render')
			})
	} else {
		console.log('The funding source is ineligible')
	}
}