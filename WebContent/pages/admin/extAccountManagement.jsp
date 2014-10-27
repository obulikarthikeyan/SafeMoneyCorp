<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SafeMoneyCorp - External User Account Management</title>
</head>
<body>
	<jsp:include page="/pages/sidebar.jsp"></jsp:include>
	<div id="page-wrapper">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">External User Account Requests</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>

		<div class="col-lg-10">

			<div class="panel panel-default">
				<div class="panel-heading"><strong>REQUESTS FOR ADMIN</strong></div>

					<div class="panel-body">
						<div class="table-responsive">
							<table class="table" style="width: 120%">
								<thead>
									<tr>
										<th>Request ID</th>
										<th>Requesting Member ID</th>
										<th>Request Type</th>
										<th>Status</th>
										<th>Authorizing Authority</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${not empty requestList}">
										<c:forEach var="request" items="${requestList}">
											<tr>
												<td>${request.requestId }</td>
												<td>${request.memberId.memberId }</td>
												<td>${request.requestType }</td>
												<td>${request.status }</td>
												<td>ADMIN</td>
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
	</div>
</body>
</html>