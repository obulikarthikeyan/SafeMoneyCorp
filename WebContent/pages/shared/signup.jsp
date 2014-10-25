<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>SafeMoneyCorp - New User Signup</title>

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/sb-admin-2.css" rel="stylesheet">

<link href="css/jquery-ui.css" rel="stylesheet">

<link href="css/jquery-ui.min.css" rel="stylesheet">

<link href="css/jquery-ui.theme.css" rel="stylesheet">

<link href="css/jquery-ui.theme.min.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="font-awesome-4.1.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <script src="<%=request.getContextPath() %>/js/jquery-1.11.0.js"></script>
	
	<script src="<%=request.getContextPath() %>/js/jquery-ui.js"></script>
	
	<script src="<%=request.getContextPath() %>/js/jquery-ui.min.js"></script>
	
	<script src="<%=request.getContextPath() %>/js/jquery.validate.js"></script>
	
	<style type="text/css">
	#signUpForm label.error {
		font-weight: 500;
		color:red;
	}
	
	</style>
</head>

<body>

	<div class="container">
		<div class="row">
			<div class="col-md-10">
				<div>
					<br> <br> <br>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h2 class="panel-title">
							<strong>New User - Sign Up</strong>
						</h2>
					</div>


					<div class="panel-body">
					<form:form id="signUpForm" commandName="signUpForm" role="form" method="POST" action="userSignUp">
						<fieldset>
						<div class="row">
									<div class="col-md-10 col-md-offset-1">
										
											<div>
												<div class="radio">
													<label> <input type="radio" name="userType"
														id="userType" value="indCust" checked autofocus tabindex="1">Individual
														Customer
													</label>&nbsp;&nbsp;&nbsp;&nbsp; <label> <input
														type="radio" name="userType" id="userType"
														value="merchantOrg" tabindex="2">Merchant Org
													</label>
												</div>
												<div>
													<br>
													<h4>Personal Information</h4>
													<br>
												</div>
												
										<div class="col-md-5">
												<div class="form-group">
													<input class="form-control" placeholder="First Name" maxLength="25"
														name="firstName" tabindex="3" required>
												</div>




												
												<div class="form-group">
													<input id="contactNo" class="form-control"
														placeholder="Contact No" name="contactNo" tabindex="5" maxLength="10" required>
												</div>
												<div class="form-group">
													<input id="address1" class="form-control"
														placeholder="Address Line 1" name="address1" tabindex="7" required>
												</div>
												<div class="form-group">
													<input id="city" class="form-control"
														placeholder="City" name="city" tabindex="9" required>
												</div>
												<div class="form-group">
												
													<label>Date of Birth</label>
													<input id="dateOfBirth" class="form-control datepicker"
														placeholder="Date of Birth" name="dateOfBirth" tabindex="12" required>
														<script type="text/javascript">
															$('.datepicker').datepicker({
	 														autoclose: true,
	    													format: 'mm/dd/yyyy',
	    													minDate: "-50Y",
	    													changeYear: true,
	   														maxDate: "0D",
	   													 yearRange:'-30:+0'
															});
 														</script>
												</div>
												<div class="form-group">
													<input id="ssn" class="form-control"
														placeholder="Social Security" name="ssn" tabindex="14" required>
												</div>
												<div>
													<br>
												</div>
												<div>
													<br>
												</div>
												
												
										</div>
										<div class="col-md-5 col-md-offset-1">
											
											<div class="form-group">
												<input class="form-control" placeholder="Last Name"
													name="lastName" tabindex="4" required>
											</div>
											<div class="form-group">
													<input id="emailId" class="form-control" 
														placeholder="Email ID" name="emailId" tabindex="6" required>
											</div>
											<div class="form-group">
													<input id="address2" class="form-control"
														placeholder="Address Line 2" name="address2" tabindex="8">
											</div>
											<div class="col-md-5 col-md-offset-0">
												<div class="form-group">
													<input id="state" class="form-control"
														placeholder="State" name="state" tabindex="10" required>
												</div>
											</div>
											<div class="col-md-5 col-md-offset-0">
												<div class="form-group">
													<input id="zip" class="form-control"
														placeholder="Zip" name="zip" tabindex="11" required>
												</div>
												<br>
											</div>
											
											<div class="form-group">
											
													<input id="age" class="form-control"
														placeholder="Age" name="age" tabindex="13">
											</div>
											<div>
											<br>
											
											</div>
											

										</div>
									</div>
									</div>
							
							
						</div>
						<div class="row">
							
									<div class="col-md-10 col-md-offset-1">
										
											<div>
												
												<div>
													<br>
													<h4><strong>Login Information</strong></h4>
													<br>
												</div>
												
												<div class="form-group">
													<input class="form-control" placeholder="User Name" style="width:25%"
														name="userName" tabindex="15">
												</div>
											<div class="col-md-5">
												<div class="form-group">
													<input id="password" class="form-control" type="password"
														placeholder="Password" name="password" tabindex="16">
												</div>
												<div class="form-group">
													<input id="secQuestion1" class="form-control"
														placeholder="Security Question 1" name="secQuestion1" tabindex="18">
												</div>
												<div class="form-group">
													<input id="secQuestion2" class="form-control"
														placeholder="Security Question 2" name="secQuestion2" tabindex="20">
												</div>
												<div class="form-group">
												
													<input id="secQuestion3" class="form-control"
														placeholder="Security Question 3" name="secQuestion3" tabindex="22">
												</div>
												<div class="form-group">
													<input id="siteKey" class="form-control"
														placeholder="NetBanking SiteKey" name="siteKey" tabindex="24">
												</div>
												<div>
													<br>
												</div>
												<div>
													<br>
												</div>
												
												<div>

												<!-- Change this to a button or input when using this as a form -->
												<button type="submit"
													class="btn btn-lg btn-success btn-block" tabindex="25">Sign Up</button>

											</div>
										</div>
										<div class="col-md-5 col-md-offset-1 ">
											
											
											<div class="form-group">
											
													<input id="password1" class="form-control" type="password"
														placeholder="Confirm Password" name="password1" tabindex="17">
											</div>
										
											
											
											<div class="form-group">
													<input id="secAnswer1" class="form-control"
														placeholder="Security Answer 1" name="secAnswer1" tabindex="19">
												</div>
												<div class="form-group">
													<input id="secAnswer2" class="form-control"
														placeholder="Security Answer 2" name="secAnswer2" tabindex="21">
												</div>
												<div class="form-group">
												
													<input id="secAnswer3" class="form-control"
														placeholder="Security Answer 3" name="secAnswer3" tabindex="23">
												</div>
											<div>
											<br>
											
											</div>
											

										</div>
									</div>
									</div>
						</div>
						</fieldset>
						</form:form>
					</div>
								
				</div>
			</div>
		</div>
		
	</div>
	
	<script type="text/javascript">
	$("#signUpForm").validate({
		rules: {
			emailId: {
				required: true,
				email: true
			},
		messages: {
			emailId: "Please Enter a valid Email ID"
		}
			
		}
	})
	</script>
	
	<!-- jQuery Version 1.11.0 -->
	
	
	
	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="js/plugins/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="js/sb-admin-2.js"></script>
</body>

</html>
