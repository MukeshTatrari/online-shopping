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
		$("#manageProducts").addClass('active');
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

									if (row.quantity < 1) {

										str += '<a href = "javascript:void(0)" class = "btn btn-success disabled"><span class="glyphicon glyphicon-shopping-cart "></span></a>';

									} else {
										str += '<a href ="'
												+ window.contextRoot
												+ '/show/'
												+ data
												+ '/product" class = "btn btn-success"><span class="glyphicon glyphicon-shopping-cart"></span></a>';
									}
									return str;
								}
							}

					]
				});

	}
	
	//dismissing the alert in 3 seconds
	
	
	var $alert = $(".alret");
	
	if($alert.lenght)
		{
		  setTimeout(function(){
			  $alert.fadeOut("slow");
		  },3000)
		}
	
	
	
	//-----------------------------------------------------------------------------
	
	
	$('.switch input[type="checkbox"]').on('change', function(){
		
		var checkbox = $(this);
		var checked = checkbox.prop('checked');
		
		var dMsg = (checked)?'You want to Activate the Product ?':
							 'You want to Deactivate the Product ?'
			
		var value = checkbox.prop('value');
		
		bootbox.confirm({
			
			size :'medium',
			title :'Product Activation & Deactivation',
			message :dMsg,
			callback:function(confirmed)
			{
				if(confirmed)
				{
					console.log("value :::: "+value);
					bootbox.alert({
						size :'medium',
						title :'Information',
						message :'You are going to perform operation on product '+value
					});					
				}
				else
				{
					checkbox.prop('checked',!checked);
				}
			}
		});
		
	});
	
});





//========================================================================
// =================data table for Admin =================================
//========================================================================



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
				        	   data:'id'
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
							bSortable:false,
							mRender : function(data, type, row) {

								var str='';

								str += '<label class="switch">';
								if(data)
								{
									str += '<input type="checkbox" checked="checked" value ="'+row.id+'"/>';
								}
								else
								{
									str += '<input type="checkbox"  value ="'+row.id+'"/>';
								}

								str += '<div class="slider"></div>';
								str += '</label></td>';
								
								return str;
							}


						},
						
						{
							data:'id',
							bSortable:false,
							mRender : function(data, type, row) {

								var str='';

								str+= '<a href="'+window.contextRoot+'/manage/'+data+'/products"';
								str+= 'class="btn btn-warning"> <span class="glyphicon glyphicon-pencil"></span></a>';
								return str;
							}

						}

				],
				
				// after data table load complete 
				//then only we will be able to use the bootbox functionality 
				
				initComplete:function(){
					
					var api = this.api();
					api.$('.switch input[type="checkbox"]').on('change', function(){
						
						var checkbox = $(this);
						var checked = checkbox.prop('checked');
						
						var dMsg = (checked)?'You want to Activate the Product ?':
											 'You want to Deactivate the Product ?'
							
						var value = checkbox.prop('value');
						
						bootbox.confirm({
							
							size :'medium',
							title :'Product Activation & Deactivation',
							message :dMsg,
							callback:function(confirmed)
							{
								if(confirmed)
								{
									var activationUrl = window.contextRoot + '/manage/products/'+value+'/activation';
									console.log("activationUrl :::: "+activationUrl);
									$.post(activationUrl, function(data){
										console.log("value :::: "+value);
										bootbox.alert({
											size :'medium',
											title :'Information',
											message :data
										});	
										
									})				
								}
								else
								{
									checkbox.prop('checked',!checked);
								}
							}
						});
						
					});
					
				}
				
				
				
			});

}






//=========================================================================


