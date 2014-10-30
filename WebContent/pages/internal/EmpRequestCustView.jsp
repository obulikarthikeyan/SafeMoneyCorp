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
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="form-group">
						<form id ="sendRequestForm" name="sendRequestForm" target="_self" method="post" action="requestTransactionAccess" style="margin:0px" class="form-inline">
							<ul class="list-unstyled">
								<li><h4><label>Enter Member ID</label></h4></li>
								<li><input id="memberId" name="memberId"  type="text" class="form-control" placeholder="Member ID" maxlength="11"/></li>
								<li><br></li>
								<li><input name="submitRequest" type="submit" value="Submit" class = "btn btn-primary" autofocus="autofocus"/></li>
								</ul>		
						</form>
					</div>
				</div>
				<!-- /#page-wrapper -->
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$.validator.addMethod('numbersOnly', function( val, element ) {
		    var regexp = new RegExp("^[1-9][0-9]*$");
	
		    if (!regexp.test(val)) {
		       return false;
		    }
		    return true;
		}, "Please type numbers only");
		
		$("#sendRequestForm").validate({
			rules: {
				userName: {
					required: true,
					numbersOnly: true,
					maxlength : 11
				}
		}			
		});
	</script>

	<!-- Bootstrap Core JavaScript -->
	<script src="js/bootstrap.min.js"></script>
	
	<script src="http://jqueryvalidation.org/files/dist/additional-methods.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="js/plugins/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="js/sb-admin-2.js"></script>
</body>
</html>