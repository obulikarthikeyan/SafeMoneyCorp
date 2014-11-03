
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>SafeMoneyCorp - Landing</title>

<!-- Bootstrap Core CSS -->
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link
	href="<%=request.getContextPath()%>/css/plugins/metisMenu/metisMenu.min.css"
	rel="stylesheet">

<!-- Timeline CSS -->
<link href="<%=request.getContextPath()%>/css/plugins/timeline.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="<%=request.getContextPath()%>/css/sb-admin-2.css"
	rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="<%=request.getContextPath()%>/css/plugins/morris.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="<%=request.getContextPath()%>/font-awesome-4.1.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.html"
					style="font-size: 24px; color: #5E5E5E"><strong>SafeMoneyCorp</strong></a>
			</div>
			<!-- /.navbar-header -->

			<ul class="nav navbar-top-links navbar-right">
				Logged in as
				<i><strong> ${ sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username }</strong></i>
				<!-- /.dropdown -->

				<!-- /.dropdown -->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-bell fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-alerts">
						<li><a href="#">
								<div>
									<i class="fa fa-comment fa-fw"></i> New Comment <span
										class="pull-right text-muted small">4 minutes ago</span>
								</div>
						</a></li>
						<li class="divider"></li>
						<li><a href="#">
								<div>
									<i class="fa fa-twitter fa-fw"></i> 3 New Followers <span
										class="pull-right text-muted small">12 minutes ago</span>
								</div>
						</a></li>
						<li class="divider"></li>
						<li><a href="#">
								<div>
									<i class="fa fa-envelope fa-fw"></i> Message Sent <span
										class="pull-right text-muted small">4 minutes ago</span>
								</div>
						</a></li>
						<li class="divider"></li>
						<li><a href="#">
								<div>
									<i class="fa fa-tasks fa-fw"></i> New Task <span
										class="pull-right text-muted small">4 minutes ago</span>
								</div>
						</a></li>
						<li class="divider"></li>
						<li><a href="#">
								<div>
									<i class="fa fa-upload fa-fw"></i> Server Rebooted <span
										class="pull-right text-muted small">4 minutes ago</span>
								</div>
						</a></li>
						<li class="divider"></li>
						<li><a class="text-center" href="#"> <strong>See
									All Alerts</strong> <i class="fa fa-angle-right"></i>
						</a></li>
					</ul> <!-- /.dropdown-alerts --></li>
				<!-- /.dropdown -->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
						<i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-user">
						<li><a href="#"><i class="fa fa-user fa-fw"></i> User
								Profile</a></li>
						<li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
						</li>
						<li class="divider"></li>
						<li><a href="<c:url value="/j_spring_security_logout" />"><i
								class="fa fa-sign-out fa-fw"></i> Logout</a></li>
					</ul> <!-- /.dropdown-user --></li>
				<!-- /.dropdown -->
			</ul>
			<!-- /.navbar-top-links -->

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
						<sec:authorize access="hasAnyRole('EXT_IND_CUST', 'EXT_MERCHANT')">
							<li><a class="active"
								href="<%=request.getContextPath()%>/external/transactions"><i
									class="fa fa-dashboard fa-fw"></i> Manage Transactions</a></li>
							<li><a
								href="<%=request.getContextPath()%>/external/displayExternalUserDetails"><i
									class="fa fa-edit fa-fw"></i> Manage Account</a></li>
							<li><a href="">
							<i class="fa fa-table fa-fw"></i> View
									Transaction History</a></li>
							<li><a href="#"><i class="fa fa-tasks fa-fw"></i> Manage
									Requests<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level">
									<li><a href="#">View Bank Requests</a></li>
									<li><a href="#">Authorize Requests</a></li>
								</ul> <!-- /.nav-second-level --></li>
							<li><a href="<%=request.getContextPath()%>/external/review"><i class="fa fa-gear fa-fw"></i>
									Transactional Requests / Review</a></li>

						</sec:authorize>
						<sec:authorize access="hasRole('EXT_MERCHANT')">
							<li><a href="#"><i class="fa fa-gear fa-fw"></i> Payment
									Requests</a></li>
						</sec:authorize>


						<sec:authorize access="hasRole('INT_BANK_ADMIN')">
							<li><a href="#"><i class="fa fa-tasks fa-fw"></i> Manage Accounts<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level">
									<li><a href="#">Internal Users</a></li>
									<li><a href="<%=request.getContextPath() %>/admin/extUserAccount">External Users</a></li>
								</ul> <!-- /.nav-second-level --></li>
							<li><a
								href="<%=request.getContextPath() %>/admin/piiAuthorization"><i
									class="fa fa-edit fa-fw"></i> View PII</a></li>
							<li><a href="#"><i class="fa fa-table fa-fw"></i> View
									System Log</a></li>
							<li><a href="<%=request.getContextPath()%>/admin/viewTransactionHistoryPage"><i class="fa fa-gear fa-fw"></i>
									View Transactions</a></li>
							<li><a href="#"><i class="fa fa-gear fa-fw"></i>
									Authorize Critical Transactions</a></li>		

						</sec:authorize>
						<sec:authorize access="hasRole('INT_BANK_EMP')">
							<li><a href="#"><i class="fa fa-tasks fa-fw"></i> Manage External User Accounts<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level">
									<li><a href="<%=request.getContextPath()%>/internal/sendViewRequests">Send View Request</a></li>
									<li><a href="<%=request.getContextPath()%>/internal/viewAccounts">View Accounts</a></li>
								</ul> <!-- /.nav-second-level --></li>
							<li><a href="#"><i class="fa fa-tasks fa-fw"></i> Manage External User Transactions<span class="fa arrow"></span></a>
								<ul class="nav nav-second-level">
									<li><a href="<%=request.getContextPath()%>/internal/sendViewTransactionRequests">Send View Transaction Requests</a></li>
									<li><a href="<%=request.getContextPath()%>/internal/viewTransactionRequests">View Transactions</a></li>
									<li><a href="#">Process Transaction Review Requests</a></li>
									<li><a href="<%=request.getContextPath()%>/internal/manageTransactionRequest">Authorize Transactions</a></li>
								</ul> <!-- /.nav-second-level --></li>	

						</sec:authorize>


					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>

		<!-- /#page-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- jQuery Version 1.11.0 -->
	<script src="<%=request.getContextPath()%>/js/jquery-1.11.0.js"></script>
	
	<script src="<%=request.getContextPath() %>/js/jquery-ui.js"></script>
	
	<script src="<%=request.getContextPath() %>/js/jquery-ui.min.js"></script>
	
	<script src="<%=request.getContextPath() %>/js/jquery.validate.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script
		src="<%=request.getContextPath()%>/js/plugins/metisMenu/metisMenu.min.js"></script>



	<!-- Custom Theme JavaScript -->
	<script src="<%=request.getContextPath()%>/js/sb-admin-2.js"></script>

</body>
</html>

