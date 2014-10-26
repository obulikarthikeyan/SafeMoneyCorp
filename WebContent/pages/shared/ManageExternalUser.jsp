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
									Account Information
									</div>
							<!-- /.panel-heading -->
									<div class="panel-body">
									<div class="table-responsive">
									<table style="width:60%;height:20%">
										
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
												<td><b>${userDTO.emailId}</b></td>
												
											</tr>
											<tr>
												<td><b>Contact Number</b></td>
												<td><b>${userDTO.contactNo}</b></td>
											</tr>	
												
											
											<tr>
												<td><b>Address1</b></td>
												<td><b>${userDTO.address1}</b></td>
											</tr>
											
											<tr>
												<td><b>Address2</b></td>
												<td><b>${userDTO.address2}</b></td>
											</tr>
											
											<tr>
												<td><b>City</b></td>
												<td><b>${userDTO.city}</b></td>
											</tr>
											
											<tr>
												<td><b>State</b></td>
												<td><b>${userDTO.state}</b></td>
											</tr>
											
											<tr>
												<td><b>Zip</b></td>
												<td><b>${userDTO.zip}</b></td>
											</tr>
											
											<tr>
												<td><b>Date of Birth</b></td>
												<td><b>${userDTO.dateOfBirth}</b></td>
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
</body>
</html>