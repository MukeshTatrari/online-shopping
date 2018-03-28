$(function() {

	// solving the active menu problem
	switch (menu) {
	case 'About Us':
		$("#about").addClass('active');
		break;

	case 'Contact Us':
		$("#contact").addClass('active');
		break;

	case 'All Products':
		$("#listProducts").addClass('active');
		break;

	case 'Manage Products':
		$("#manageProduct").addClass('active');
		break;

	case 'User Cart':
		$("#userCart").addClass('active');
		break;

	default:
		if (menu == "Home")
			break;
		$("#listProducts").addClass('active');
		$("#a_" + menu).addClass('active');
		break;

	}
	//
	// // data for data Tables
	// // create a dataset dummy
	//
	// var products = [ [ '1', 'ABC' ],
	// [ '2', 'XYZ' ],
	// [ '3', 'MNO' ],
	// [ '4', 'PQR' ],
	// [ '5', 'CGH' ],
	// [ '6', 'FJS' ],
	// [ '7', 'EWYUWE' ],
	// [ '8', 'EJHREW' ],
	// [ '9', 'DSFHD' ]
	// ];
	//	
	// code for jquery dataTable
	var $table = $('#productListTable');

	// execute the below code only where we have this table
	if ($table.length) {
		// console.log('Inside the table!');

		var jsonUrl = '';
		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/products';
		}

		$table
				.DataTable({

					lengthMenu : [ [ 3, 5, 10, -1 ],
							[ '3 Records', '5 Records', '10 Records', 'ALL' ] ],
					pageLength : 5,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'code',
								mRender : function(data, type, row) {
									return '<img src ="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class ="dataTableImg"/>'
								}
							},
							{
								data : 'name'
							},
							{
								data : 'brand'
							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&#8377; ' + data
								}

							},
							{
								data : 'quantity',
								mRender : function(data, type, row) {
									if (data < 1) {
										return '<span style= "color:red">Out Of Stock! </span>'
									}
									return data;
								}
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = ''
									str += '<a href ="'
											+ window.contextRoot
											+ '/show/'
											+ data
											+ '/product" class="btn btn-primary"><span class="glyphicon glyphicon-eye-open"></span></a> &#160;';

									if (userRole == 'ADMIN') {

										str += '<a href ="'
												+ window.contextRoot
												+ '/manage/'
												+ data
												+ '/products" class = "btn btn-warning"><span class="glyphicon glyphicon-pencil"></span></a>';

									}

									else {

										if (row.quantity < 1) {

											str += '<a href = "javascript:void(0)" class = "btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart "></span></a>';

										} else {

											str += '<a href="'
													+ window.contextRoot
													+ '/cart/add/'
													+ data
													+ '/product" class="btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
										}
									}

									return str;
								}
							}

					]
				});

	}

	// dismissing the alert in 3 seconds

	var $alert = $(".alret");

	if ($alert.lenght) {
		setTimeout(function() {
			$alert.fadeOut("slow");
		}, 3000)
	}

	// -----------------------------------------------------------------------------

	$('.switch input[type="checkbox"]')
			.on(
					'change',
					function() {

						var checkbox = $(this);
						var checked = checkbox.prop('checked');

						var dMsg = (checked) ? 'You want to Activate the Product ?'
								: 'You want to Deactivate the Product ?'

						var value = checkbox.prop('value');

						bootbox
								.confirm({

									size : 'medium',
									title : 'Product Activation & Deactivation',
									message : dMsg,
									callback : function(confirmed) {
										if (confirmed) {
											console.log("value :::: " + value);
											bootbox
													.alert({
														size : 'medium',
														title : 'Information',
														message : 'You are going to perform operation on product '
																+ value
													});
										} else {
											checkbox.prop('checked', !checked);
										}
									}
								});

					});

});

// ========================================================================
// =================data table for Admin =================================
// ========================================================================

// //// to hanlde issues with CSRF token

// for handling CSRF token
var token = $('meta[name="_csrf"]').attr('content');
var header = $('meta[name="_csrf_header"]').attr('content');

if ((token != undefined && header != undefined)
		&& (token.length > 0 && header.length > 0)) {
	// set the token header for the ajax request
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
}

// code for jquery dataTable
var $adminProductsTable = $('#productsTable');

// execute the below code only where we have this table
if ($adminProductsTable.length) {
	// console.log('Inside the table!');

	var jsonUrl = window.contextRoot + '/json/data/admin/all/products';

	$adminProductsTable
			.DataTable({

				lengthMenu : [ [ 10, 30, 50, -1 ],
						[ '10 Records', '30 Records', '50 Records', 'ALL' ] ],
				pageLength : 30,
				ajax : {
					url : jsonUrl,
					dataSrc : ''
				},
				columns : [
						{
							data : 'id'
						},
						{
							data : 'code',
							mRender : function(data, type, row) {
								return '<img src ="' + window.contextRoot
										+ '/resources/images/' + data
										+ '.jpg" class ="dataTableImg"/>'
							}
						},
						{
							data : 'name'
						},
						{
							data : 'brand'
						},

						{
							data : 'quantity',
							mRender : function(data, type, row) {
								if (data < 1) {
									return '<span style= "color:red">Out Of Stock! </span>'
								}
								return data;
							}
						},

						{
							data : 'unitPrice',
							mRender : function(data, type, row) {
								return '&#8377; ' + data
							}

						},

						{
							data : 'active',
							bSortable : false,
							mRender : function(data, type, row) {

								var str = '';

								str += '<label class="switch">';
								if (data) {
									str += '<input type="checkbox" checked="checked" value ="'
											+ row.id + '"/>';
								} else {
									str += '<input type="checkbox"  value ="'
											+ row.id + '"/>';
								}

								str += '<div class="slider"></div>';
								str += '</label></td>';

								return str;
							}

						},

						{
							data : 'id',
							bSortable : false,
							mRender : function(data, type, row) {

								var str = '';

								str += '<a href="' + window.contextRoot
										+ '/manage/' + data + '/products"';
								str += 'class="btn btn-warning"> <span class="glyphicon glyphicon-pencil"></span></a>';
								return str;
							}

						}

				],

				// after data table load complete
				// then only we will be able to use the bootbox functionality

				initComplete : function() {

					var api = this.api();
					api
							.$('.switch input[type="checkbox"]')
							.on(
									'change',
									function() {

										var checkbox = $(this);
										var checked = checkbox.prop('checked');

										var dMsg = (checked) ? 'You want to Activate the Product ?'
												: 'You want to Deactivate the Product ?'

										var value = checkbox.prop('value');

										bootbox
												.confirm({

													size : 'medium',
													title : 'Product Activation & Deactivation',
													message : dMsg,
													callback : function(
															confirmed) {
														if (confirmed) {
															var activationUrl = window.contextRoot
																	+ '/manage/products/'
																	+ value
																	+ '/activation';
															console
																	.log("activationUrl :::: "
																			+ activationUrl);
															$
																	.post(
																			activationUrl,
																			function(
																					data) {
																				console
																						.log("value :::: "
																								+ value);
																				bootbox
																						.alert({
																							size : 'medium',
																							title : 'Information',
																							message : data
																						});

																			})
														} else {
															checkbox.prop(
																	'checked',
																	!checked);
														}
													}
												});

									});

				}

			});

}

// =========================================================================

// -------------------------------------------------------------------------
// --------------------------Jquery Validation for Category-----------------

// validating the product form element
// fetch the form element
$categoryForm = $('#categoryForm');

if ($categoryForm.length) {

	$categoryForm.validate({
		rules : {
			name : {
				required : true,
				minlength : 5
			},
			description : {
				required : true,
				minlength : 5
			}
		},
		messages : {
			name : {
				required : 'Please enter product name!',
				minlength : 'Please enter atleast five characters'
			},
			description : {
				required : 'Please enter product Description!',
				minlength : 'Please enter atleast five characters'
			}
		},
		errorElement : "em",
		errorPlacement : function(error, element) {
			errorPlacement(error, element);
		}
	}

	);

}

// jQuery Validation Code

// methods required for validation

function errorPlacement(error, element) {
	// Add the 'help-block' class to the error element
	error.addClass("help-block");

	// add the error label after the input element
	error.insertAfter(element);

	// add the has-feedback class to the
	// parent div.validate in order to add icons to inputs
	// element.parents(".validate").addClass("has-feedback");

}

// ======================================================================
// ====================Jquery Validation for the Login form==============
// ======================================================================

$loginForm = $('#loginForm');

if ($loginForm.length) {

	$loginForm.validate({
		rules : {
			username : {
				required : true,
				email : true,
				minlength : 5
			},
			password : {
				required : true,
				minlength : 5
			}
		},
		messages : {
			username : {
				required : 'Please enter username !',
				email : 'Please enter valid Email Address!',
				minlength : 'Username Must have five characters'
			},
			password : {
				required : 'Please enter  the Password !',
				minlength : 'Password must have five characters'
			}
		},
		errorElement : "em",
		errorPlacement : function(error, element) {
			errorPlacement(error, element);
		}
	}

	);

}

/*------*/
/* for fading out the alert message after 3 seconds */
$alert = $('.alert');
if ($alert.length) {
	setTimeout(function() {
		$alert.fadeOut('slow');
	}, 3000);
}

// ====================================================================
// ==========handling the click event of refresh cart button ==========
// ====================================================================

$('button[name="refreshCart"]')
		.click(
				function() {

					var cartLineId = $(this).attr('value');
					var countElement = $("#count_" + cartLineId);
					var originalCount = countElement.attr('value');
					var currentCount = countElement.val();

					// work only when the count has beeen changed

					if (currentCount != originalCount) {
						console.log("Current count :: " + currentCount
								+ "originalCount :::::: " + originalCount);

						if (currentCount < 1 || currentCount > 3) {
							// reverting back the original count
							countElement.val(originalCount);

							bootbox
									.alert({

										size : 'medium',
										error : 'Error',
										message : 'Product Count should be minimum 1 and maximum 3 !'
									})
						} else {

							var updateUrl = window.contextRoot + '/cart/'
									+ cartLineId + '/update?count='
									+ currentCount;

							window.location.href = updateUrl;
						}

					}

				});

// =======================================================================================================
// =============================================Sending an
// Email==========================================
// =======================================================================================================

function sendMail() {
	$.ajax({
		type : 'POST',
		url : 'https://mandrillapp.com/api/1.0/messages/send.json',
		data : {
			'key' : 'YOUR API KEY HERE',
			'message' : {
				'from_email' : 'mukesh.tatrari@gmail.com',
				'to' : [ {
					'email' : 'mukesh.tatrari@gmail.com',
					'name' : 'RECIPIENT NAME (OPTIONAL)',
					'type' : 'to'
				} ],
				'autotext' : 'true',
				'subject' : 'YOUR SUBJECT HERE!',
				'html' : 'YOUR EMAIL CONTENT HERE! YOU CAN USE HTML!'
			}
		}
	}).done(function(response) {
		console.log(response); // if you're into that sorta thing
	});
}

$contactUSForm = $('#contactUs');

if ($contactUSForm.length) {

	$contactUSForm.validate({
		rules : {
			name : {
				required : true,
				minlength : 5
			},
			email : {
				required : true,
				email : true,
				minlength : 5
			},

			Subject : {
				required : true,
				minlength : 5
			},

			phone : {
				required : true,
				minlength : 10
			},

			message : {
				required : true,
				minlength : 10
			}
		},
		messages : {
			name : {
				required : 'Please enter your name!',
				minlength : 'Please enter atleast five characters'
			},
			email : {
				required : 'Please enter your email!',
				email : 'Please enter valid Email Address!',
				minlength : 'Please enter atleast five characters'
			},

			Subject : {
				required : 'Please enter  a Subject !',
				minlength : 'Please enter atleast five characters'
			},
			phone : {
				required : 'Please enter your phone!',
				minlength : 'phone number should be atleast 10 characters'
			},
			message : {
				required : 'Please enter your message!',
				minlength : 'Message should be atleast 5 characters'
			}

		},
		errorElement : "em",
		errorPlacement : function(error, element) {
			errorPlacement(error, element);
		}
	}

	);

}

// ===============================================================================================\
// ==================================Holiday Plan
// JS=============================================
// ===============================================================================================

$('#travelPlan').hover(function() {
	console.log("Hellloooo");
	$('#travelSubMenu').removeClass('hide');
	$('#travelSubMenu').addClass('visible');
}, function() {
	$('#travelSubMenu').addClass('hide');
	$('#travelSubMenu').removeClass('visible');
});