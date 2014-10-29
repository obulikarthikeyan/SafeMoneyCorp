<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SafeMoneyCorp - Transaction History</title>
</head>
<body>
<jsp:include page="/pages/sidebar.jsp"></jsp:include>
<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Transaction History</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>

		<div class="col-lg-10">

			<div class="panel panel-default">
				<div class="panel-heading"><strong>All Transactions</strong></div>

					<div class="panel-body">
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
						<div class="table-responsive">
							<table class="table" style="width: 120%">
								<thead>
									<tr>
										<th>Transaction ID</th>
										<th>Requesting Member ID</th>
										<th>Request Type</th>
										<th>Status</th>
										<th>Authorizing Authority</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${not empty requestList}">
										<c:forEach var="request" items="${requestList}" varStatus="status">
											<tr>
												
												<td>${request.requestId }</td>
												<td>${request.memberId.memberId }</td>
												<td>${request.requestType }</td>
												<td><strong>${request.status }</strong></td>
												<td>ADMIN</td>
												
												<c:if test="${request.status == 'NEW' }">
													<td><button id="viewButton${request.requestId}" class="btn btn-success" 
											data-toggle="modal" data-target="#viewUser">View</button></td>
												<script type="text/javascript">
													$('#viewButton${request.requestId}').click(function(){
													var firstName = '${request.memberId.firstName}';
													var lastName = '${request.memberId.lastName}';
													var contactNo = '${request.memberId.contactNo}';
													var emailId = '${request.memberId.emailId}';
													var isCustomer = '${request.memberId.isCustomer}';
													var requestId = '${request.requestId}';
													var requestType = '${request.requestType}';
													var type = 'Customer';
													if(isCustomer == 'false')
														type = 'Merchant';
														
												   	 $('#firstName').text(firstName);
												   	 $('#lastName').text(lastName);
												   	 $('#contactNo').text(contactNo);
												   	 $('#emailId').text(emailId);
												   	 $('#type').text(type);
												   	 $('#requestId').val(requestId);
												   	 $('#requestType').val(requestType);
													});
												</script>
												</c:if>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>


</body>
</html>