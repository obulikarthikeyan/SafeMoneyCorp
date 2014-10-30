
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SafeMoneyCorp - Transaction Review</title>
</head>
<body>
	<jsp:include page="/pages/sidebar.jsp"></jsp:include>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Transactional Review</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->


			<br>
			<div class="panel panel-default">
				<div class="panel-heading">
					<strong>Transactional Review</strong>
				</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
				
				<ul class="nav nav-pills">
						<li class="active"><a href="#transactionalReview" data-toggle="tab">Transaction History</a></li>
						<li><a href="#createTransactionRequest" data-toggle="tab">Create Transaction Request</a>
						</li>
						

					</ul>
				
				

					<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">Modal title</h4>
								</div>
								
								
								<form id="updateUser" role="form" method="POST"
									action="updateExternalUserDetails">
									<div class="modal-body">
									
										<input class="form-control" type="hidden" style="width: 25%" id="memberId" name="memberId" placeholder="memberId"
											tabindex="25" value="${userDTO.memberId}">
										<br>	
										<label>email_Id</label>
										<input class="form-control" style="width: 25%" id="emailId" name="emailId" placeholder="emailId"
											tabindex="15" value="${userDTO.emailId}">
										<br>	
										<label>Contact_No</label>
										<input class="form-control" style="width: 25%" id="contactNo" name="contactNo"
											tabindex="10" maxlength="10" value="${userDTO.contactNo}">
										<br>	
										<label>Address_1</label>
										<input class="form-control" style="width: 35%" id="address1" name="address1"
											tabindex="50" value="${userDTO.address1}">
										<br>	
										<label>Address_2</label>
										<input class="form-control" style="width: 35%" id="address2" name="address2"
											tabindex="50" value="${userDTO.address2}">
										<br>	
										<label>City</label>	
										<input class="form-control" style="width: 25%" id="city" name="city"
											tabindex="15" value="${userDTO.city}">
										
										<br>	
										<label>State</label>
										<input class="form-control" style="width: 25%" id="state" name="state"
											tabindex="2" maxlength="2" value="${userDTO.state}">
										
										<br>	
										<label>Zip</label>
										<input class="form-control" style="width: 25%" id="zip" name="zip"
											tabindex="5" maxlength="5" value="${userDTO.zip}">
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
										<button type="submit" class="btn btn-primary">Save
											changes</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
	</div>

	<!-- /.panel-body -->



		
	<script type=text/javascript >
	$.validator.addMethod('addressField', function( val, element ) {
	    var regexp = new RegExp("^[a-zA-Z0-9,. _;-]+$");

	    if (!regexp.test(val)) {
	       return false;
	    }
	    return true;
	}, "Please use characters [a-zA-Z], [0-9], [,][.][_][;][-]");
	
	$.validator.addMethod('alphabetsOnly', function( val, element ) {
	    var regexp = new RegExp("^[a-zA-Z]+$");

	    if (!regexp.test(val)) {
	       return false;
	    }
	    return true;
	}, "Please type alphabets only");

	$.validator.addMethod('numbersOnly', function( val, element ) {
	    var regexp = new RegExp("^[0-9]+$");

	    if (!regexp.test(val)) {
	       return false;
	    }
	    return true;
	}, "Please type numbers only");
	
	$('#updateUser').validate({
		rules:{
			emailId:{
				required: true,
				email: true,
				maxlength: 25
			},
			
			contactNo:{
				required: true,
				numbersOnly: true,
				maxlength: 10
			},
			
			address1:{
				required: true,
				addressField: true,
				maxlength: 50
			},
			
			address2:{
				required: true,
				addressField: true,
				maxlength: 50
			},
			
			city:{
				required: true,
				alphabetsOnly: true,
				maxlength: 15
			},
			state:{
				required: true,
				alphabetsOnly: true,
				maxlength: 2,
				minlength:2
			},
			
			zip:{
				required:true,
				numbersOnly: true,
				maxlength:5,
				minlength:5
			},
		messages:{
			emailId: "Please Enter a valid Email ID",
			contactNo: "Please Enter a valid contact no",
			address1: "Address1 should be less than 50 characters",
			address2: "Address2 should be less than 50 characters",
			city: "Please enter a valid city",
			state: "Please enter a valid state(Should be 2 characters)",
			zip: "The Zip code should be a 5 digit number"
			
		}
			
		}
	})
	</script>	
</body>
</html>
