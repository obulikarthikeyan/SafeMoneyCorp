<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Manage Account</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="css/plugins/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome-4.1.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">

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
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">


            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Edit Account Information</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
                                    <form role="form" action="ManageExternalUser" value="ManageExternalUSerValue" id="ManageExternalUSerId" name="ManageExternalUSerName">
		            <div>
                                            <label>Name</label>
                                            <input name ="userName" value= {UserModel.firstName} class="form-control" placeholder="Enter Name" maxlength="30" readonly>
		            </div>
		            <div>
                                            <label>Contact no.</label>
                                            <input name="userContactNo" class="form-control" placeholder="Enter Contact Number" maxlength="10">
		            </div>
		            <div>
                                            <label>E-mail-id</label>
                                            <input name="userEmail" class="form-control" placeholder="Enter e-mail" maxlength="30">
		            </div>
		        <br>
                                        <button type="button" class="btn btn-default">update Account</button>
                                        <button type="button" class="btn btn-default">Delete </button>
                                    </form>

                                                            </div>

    <!-- jQuery Version 1.11.0 -->
    <script src="js/jquery-1.11.0.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="js/plugins/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="js/sb-admin-2.js"></script>

</body>

</html>
