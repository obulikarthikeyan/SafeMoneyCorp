<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SafeMoneyCorp - Manage User Account</title>
</head>
<body>
	<jsp:include page="/pages/sidebar.jsp"></jsp:include>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Manage Account</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->


		<div class="tab-pane fade in active in active" id="Summary">
			<br>
			<div class="panel panel-default">
				<div class="panel-heading">
					<strong>Account Information</strong>
				</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="table-responsive">
						<table style="width: 70%; height: 60%">

							<tbody>
								<tr>
									<td><b>Member ID</b></td>
									<td>${userDTO.memberId }</td>

								</tr>
								<tr>
									<td><b>First Name</b></td>
									<td>${userDTO.firstName }</td>

								</tr>
								<tr>
									<td><b>Last Name</b></td>
									<td>${userDTO.lastName }</td>

								</tr>
								<tr>
									<td><b>Email ID</b></td>
									<td>${userDTO.emailId}</td>

								</tr>
								<tr>
									<td><b>Contact Number</b></td>
									<td>${userDTO.contactNo}</td>
								</tr>


								<tr>
									<td><b>Address1</b></td>
									<td>${userDTO.address1}</td>
								</tr>

								<tr>
									<td><b>Address2</b></td>
									<td>${userDTO.address2}</td>
								</tr>

								<tr>
									<td><b>City</b></td>
									<td>${userDTO.city}</td>
								</tr>

								<tr>
									<td><b>State</b></td>
									<td>${userDTO.state}</td>
								</tr>

								<tr>
									<td><b>Zip</b></td>
									<td>${userDTO.zip}</td>
								</tr>

								<tr>
									<td><b>Date of Birth</b></td>
									<td>${userDTO.dateOfBirth}</td>
								</tr>
								<tr>
									<td><br></td>
								</tr>
								<tr>
									<td><button class="btn btn-primary btn-lg"
											data-toggle="modal" data-target="#myModal" style="width: 50%">
											Update Account</button></td>
									<td><button type="button"
											class="btn btn-lg btn-danger btn-block" style="width: 50%">
											Delete Account</button></td>

								</tr>

							</tbody>

						</table>

						<!-- /.table-responsive -->
					</div>

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
										<input class="form-control" placeholder="User Name"
											style="width: 25%" id="emailId" name="emailId"
											tabindex="15" value="${userDTO.emailId}">
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
	</div>

	<!-- /.panel-body -->



	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
</body>
</html>
