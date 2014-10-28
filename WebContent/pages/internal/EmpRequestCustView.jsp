<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SafeMoneyCorp - Emp Request View</title>
</head>
<body>
	<jsp:include page="/pages/sidebar.jsp"></jsp:include>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Send Request</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->
		
		 <div class="col-lg-10" >
		 
				<div class="panel panel-default">
					<div class="panel-heading">
						Pill Tabs
					</div>
					<!-- /.panel-heading -->
					<div class="panel-body" >
							 <div class="form-group">
										
										<label>Enter Amount: </label>
										<label>
										<input class="form-control" >
										</label>
										
							 </div>
							 </div>
			<!-- /#page-wrapper -->
	</div>
	</div>
	</div>
<!-- 								<div class="form-group">
								<br>
								<br>
										<label>Transaction Type:</label>
										<label class="radio-inline">
											<input type="radio" name="optionsRadiosInline" id="optionsRadiosInline1" value="option1" checked>Credit
										</label>
										<label class="radio-inline">
											<input type="radio" name="optionsRadiosInline" id="optionsRadiosInline2" value="option2">Debit
										</label>
										
							 </div> -->
							 

							   
<!-- 							 <p>
							<button type="button" class="btn btn-success">Submit</button>
							button type="button" class="btn btn-default">Default</button>
							<button type="button" class="btn btn-primary">Success</button>
							<button type="button" class="btn btn-info">Info</button>
							<button type="button" class="btn btn-warning">Warning</button>
							<button type="button" class="btn btn-danger">Danger</button>
							<button type="button" class="btn btn-link">Link</button
							</p> -->
							   
							   
<!-- 							</div> -->
<!-- 							<div class="tab-pane fade" id="Transfer">
							<br>
							<br>
										<label>To Account: </label>
										
										<input class="form-control" placeholder="Account No.">
										
							<br>
							<br>
										<label>Enter Amount: </label>
										
										<input class="form-control" placeholder="Amount">
										
							<br>
							<p>
							
							<button type="button" class="btn btn-success">Submit</button>
							button type="button" class="btn btn-default">Default</button>
							<button type="button" class="btn btn-primary">Success</button>
							<button type="button" class="btn btn-info">Info</button>
							<button type="button" class="btn btn-warning">Warning</button>
							<button type="button" class="btn btn-danger">Danger</button>
							<button type="button" class="btn btn-link">Link</button
							</p> 
							</div>
							
							
							<div class="tab-pane fade" id="Payment">
							<br>
								<ul class="nav nav-pills">
								<li class="active"><a href="#Authorize" data-toggle="tab">Authorize</a>
								</li>
								<li><a href="#Initiate" data-toggle="tab">Initiate</a>
								</li>
					   
								</ul>
								<h4>Messages Tab</h4>
								
							</div>
							<div class="tab-pane fade" id="settings-pills">
								<h4>Settings Tab</h4>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
							</div>
							
							
							<div class="tab-pane fade" id="Authorize">
							<br>
							
							
							<label>Requested By </label>
							
							<p class="form-control-static">Merchant Name</p>
							<br>
							<label>Requested Amount </label>
							
							<p class="form-control-static">$ Amount</p>
							<br>
							<label>Enter PKI Token: </label>
							
							<input class="form-control" placeholder="Account No.">
							
							<br>
							<br>
							<p>
							<button type="button" class="btn btn-success">Authorize</button>
							<button type="button" class="btn btn-default">Decline</button>
							button type="button" class="btn btn-default">Default</button>
							<button type="button" class="btn btn-primary">Success</button>
							<button type="button" class="btn btn-info">Info</button>
							<button type="button" class="btn btn-warning">Warning</button>
							<button type="button" class="btn btn-danger">Danger</button>
							<button type="button" class="btn btn-link">Link</button
							</p>
form>
First name: 
<input type="text" name="firstname" />
<br />
Last name: 
<input type="text" name="lastname" />
</form
								
							</div>
							
							<div class="tab-pane fade" id="Initiate">
							
							<br>
							
							
							<label>Customer Account Number: </label>
							
							<input class="form-control" placeholder="Account No.">
							
							
							<br>
							<label>Amount: </label>
							
							<input class="form-control" placeholder="Amount">
							
							<br>
							<label>Description: </label>
							
							<textarea class="form-control" rows="3"></textarea>
							<br>
							<p>
							<button type="button" class="btn btn-success">Initiate Payment Request</button>
							<button type="button" class="btn btn-default">Cancel</button>
							button type="button" class="btn btn-default">Default</button>
							<button type="button" class="btn btn-primary">Success</button>
							<button type="button" class="btn btn-info">Info</button>
							<button type="button" class="btn btn-warning">Warning</button>
							<button type="button" class="btn btn-danger">Danger</button>
							<button type="button" class="btn btn-link">Link</button
							</p>
							</div>
							
							
							
							<div class="tab-pane fade in active in active" id="Summary">
							<br>
							<div class="panel panel-default">
							<div class="panel-heading">
							Basic Table
							</div>
					/.panel-heading
							<div class="panel-body">
							<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th>#</th>
										<th>Name</th>
										<th>Account Number</th>
										<th>Balance</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>1</td>
										<td>Mark</td>
										<td>Otto</td>
										<td>@mdo</td>
									</tr>
									<tr>
										<td>2</td>
										<td>Jacob</td>
										<td>Thornton</td>
										<td>@fat</td>
									</tr>
									<tr>
										<td>3</td>
										<td>Larry</td>
										<td>the Bird</td>
										<td>@twitter</td>
									</tr>
								</tbody>
							</table>
							</div>
							/.table-responsive
							</div>
							/.panel-body
							</div>
							</div>
							
						</div>
					</div>
					/.panel-body
				</div>
				/.panel
 -->			
			
	
	

</body>
</html>