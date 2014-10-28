<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SafeMoneyCorp - Transactions</title>
<style type="text/css">
	#creditDebit label.error {
		font-weight: 500;
		color:red;
	}
</style>
</head>
<body>
	<jsp:include page="/pages/sidebar.jsp"></jsp:include>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Manage Transaction</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->

		<div class="col-lg-10">

			<div class="panel panel-default">
				<div class="panel-heading">Transaction Services</div>
				<!-- /.panel-heading -->
				<div class="panel-body">

					<!-- Nav tabs -->
					<ul class="nav nav-pills">
						<li class="active"><a href="#Summary" data-toggle="tab">Account
								Summary</a></li>
						<li><a href="#Credit_Debit" data-toggle="tab">Credit/Debit</a>
						</li>
						<li><a href="#Transfer" data-toggle="tab">Transfer</a></li>
						<li><a href="#Payment" data-toggle="tab">Payment</a></li>
						<li><a href="#Authorize" data-toggle="tab">Authorize
								Payment</a></li>
						<li><a href="#Initiate" data-toggle="tab">Initiate
								Payment</a></li>

					</ul>


					<!-- Tab panes -->
					<div class="tab-content">
						<div class="tab-pane fade" id="Credit_Debit">
							<form id="creditDebit" role="form" method="POST"
								action="creditDebit">
								<div class="form-group">
									<br>
									
									<br> <label>Transaction Type:</label> <label
										class="radio-inline"> <input type="radio"
										name="optionsRadiosInline" id="optionsRadiosInline1" value="1"
										checked>Credit
									</label> <label class="radio-inline"> <input type="radio"
										name="optionsRadiosInline" id="optionsRadiosInline2" value="2">Debit
									</label>

								</div>

								<div class="form-group">

									<label>Enter Amount: $ </label> <label> <input
										id="creditDebitAmount" name="creditDebitAmount"
										class="form-control" maxlength="8" placeholder="Amount">
									</label>

								</div>
								<p>
									<button type="submit" class="btn btn-success">Submit</button>

									<!--button type="button" class="btn btn-default">Default</button>
																	<button type="button" class="btn btn-primary">Success</button>
																	<button type="button" class="btn btn-info">Info</button>
																	<button type="button" class="btn btn-warning">Warning</button>
																	<button type="button" class="btn btn-danger">Danger</button>
																	<button type="button" class="btn btn-link">Link</button-->
								</p>
							</form>


						</div>
						<div class="tab-pane fade" id="Transfer">
						<form id="Transform" role="form" method="POST"
								action="transfer">
							<br> <br> <label>To Account: </label> 
							<input id="toAccountNumber" name="toAccountNumber" class="form-control" placeholder="Account No."> <br>
							<br> <label>Enter Amount: </label> 
							<input id="transformAmount" name="transformAmount" class="form-control" placeholder="Amount"> <br>
							<p>

								<button type="submit" class="btn btn-success">Submit</button>
								<!--button type="button" class="btn btn-default">Default</button>
																	<button type="button" class="btn btn-primary">Success</button>
																	<button type="button" class="btn btn-info">Info</button>
																	<button type="button" class="btn btn-warning">Warning</button>
																	<button type="button" class="btn btn-danger">Danger</button>
																	<button type="button" class="btn btn-link">Link</button-->
							</p>
						</form>
						</div>


						<div class="tab-pane fade" id="Payment">
							<br>
							<ul class="nav nav-pills">
								<li class="active"><a href="#Authorize" data-toggle="tab">Authorize</a>
								</li>
								<li><a href="#Initiate" data-toggle="tab">Initiate</a></li>

							</ul>
							<h4>Messages Tab</h4>

						</div>
						<div class="tab-pane fade" id="settings-pills">
							<h4>Settings Tab</h4>
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
								sed do eiusmod tempor incididunt ut labore et dolore magna
								aliqua. Ut enim ad minim veniam, quis nostrud exercitation
								ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis
								aute irure dolor in reprehenderit in voluptate velit esse cillum
								dolore eu fugiat nulla pariatur. Excepteur sint occaecat
								cupidatat non proident, sunt in culpa qui officia deserunt
								mollit anim id est laborum.</p>
						</div>


						<div class="tab-pane fade" id="Authorize">
							<br> <label>Requested By </label>

							<p class="form-control-static">Merchant Name</p>
							<br> <label>Requested Amount </label>

							<p class="form-control-static">$ Amount</p>
							<br> <label>Enter PKI Token: </label> <input
								class="form-control" placeholder="Account No."> <br>
							<br>
							<p>
								<button type="button" class="btn btn-success">Authorize</button>
								<button type="button" class="btn btn-default">Decline</button>
								<!--button type="button" class="btn btn-default">Default</button>
																	<button type="button" class="btn btn-primary">Success</button>
																	<button type="button" class="btn btn-info">Info</button>
																	<button type="button" class="btn btn-warning">Warning</button>
																	<button type="button" class="btn btn-danger">Danger</button>
																	<button type="button" class="btn btn-link">Link</button-->
							</p>
							<!--form>
											First name: 
											<input type="text" name="firstname" />
											<br />
											Last name: 
											<input type="text" name="lastname" />
											</form-->

						</div>

						<div class="tab-pane fade" id="Initiate">

							<br> <label>Customer Account Number: </label> <input
								class="form-control" placeholder="Account No."> <br>
							<label>Amount: </label> <input class="form-control"
								placeholder="Amount"> <br> <label>Description:
							</label>

							<textarea class="form-control" rows="3"></textarea>
							<br>
							<p>
								<button type="button" class="btn btn-success">Initiate
									Payment Request</button>
								<button type="button" class="btn btn-default">Cancel</button>
								<!--button type="button" class="btn btn-default">Default</button>
																	<button type="button" class="btn btn-primary">Success</button>
																	<button type="button" class="btn btn-info">Info</button>
																	<button type="button" class="btn btn-warning">Warning</button>
																	<button type="button" class="btn btn-danger">Danger</button>
																	<button type="button" class="btn btn-link">Link</button-->
							</p>
						</div>



						<div class="tab-pane fade in active in active" id="Summary">
							<br>
							<br>
							<%
										if (request.getAttribute("message") != null) {
									%>
									<p class="label label-success" style="font-size:13px">${message }</p>
									<br>
									<%
										}
									%>
									<%
										if (request.getAttribute("error") != null) {
									%>
									<p class="label label-warning" style="font-size:13px">${error }</p>
									<br>
									<%
										}
									%>
							<br>
							<div class="panel panel-default">
								<div class="panel-heading">Account Information</div>
								<!-- /.panel-heading -->
								<div class="panel-body">
									
									
									
									<div class="table-responsive">
									
										<table style="width: 60%; height: 20%">

											<tbody>
												<tr>
													<td><b>Account Number</b></td>
													<td>${account.accountNo }</td>

												</tr>
												<tr>
													<td><b>First Name</b></td>
													<td>${account.firstName }</td>

												</tr>
												<tr>
													<td><b>Last Name</b></td>
													<td>${account.lastName }</td>

												</tr>
												<tr>
													<td><b>Balance</b></td>
													<td>$ ${account.amount }</td>

												</tr>
											</tbody>
										</table>
									</div>
									<!-- /.table-responsive -->
								</div>
								<!-- /.panel-body -->
							</div>
						</div>

					</div>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
	</div>
	<script type="text/javascript">
	$.validator.addMethod('amount', function( val, element ) {
	    var regexp = new RegExp("^[1-9][0-9]*$");

	    if (!regexp.test(val)) {
	       return false;
	    }
	    return true;
	}, "Please Enter a valid Amount");
	$("#creditDebit").validate({
		rules: {
			creditDebitAmount: {
				required: true,
				amount: true
			}
		}
	})
	</script>
</body>
</html>
