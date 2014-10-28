<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Admin - Internal Users</title>

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
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Welcome Admin</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="col-lg-10">

                <div class="panel panel-default">
                    <div class="panel-body">

                        <!-- Nav tabs -->
                        <ul class="nav nav-pills">
                            <li class="active"><a href="#Add" data-toggle="tab">Add</a>
                            </li>
                            <li ><a href="#Modify" data-toggle="tab">Modify</a>
                            </li>
                            <li ><a href="#Delete" data-toggle="tab">Delete</a>
                            </li>
                        </ul>

                        <div class="tab-content">
                        <div class="tab-pane fade active in" id="Add">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <form role="form">
                                    <div class="form-group">
                                        <br>
                                        <br>
                                        <ul class="list-unstyled">
                                            <li><label>Name*</label><input class="form-control" style="width:25%;" placeholder="Name"></li>
                                            <li><label>Employee ID</label><input class="form-control" style="width:25%;" placeholder="Address"></li>
                                            <li><label>User ID*</label><input class="form-control" style="width:25%;" placeholder="UserID"></li>
                                            <li><label>Designation*</label><select style="width:25%;" name="dataTables-example_length" aria-controls="dataTables-example" class="form-control input-sm"><option value="Customer">Customer</option><option value="Merchant">Merchant</option></select></li>
                                            <li><label>Email ID</label><input class="form-control" style="width:25%;" placeholder="EmailID"></li>
                                            <li><label>Contact No</label><input class="form-control" style="width:25%;" placeholder="Contact No"></li>
                                            <li><label>Address</label><input class="form-control" style="width:25%;" placeholder="Address"></li>
                                            </ul>                                        
                                    </div>
                                        <button type="submit" class="btn btn-default"> Add User </button>
                                        <button type="reset" class="btn btn-default"> Reset </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                            <div class="tab-pane fade" id="Modify">
                                                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <form role="form">
                                    <div class="form-group">
                                        <br>
                                        <br>
                                        <ul class="list-unstyled">
                                            <li><label>Name*</label><input class="form-control" style="width:25%;" placeholder="Name"></li>
                                            <li><label>Employee ID</label><input class="form-control" style="width:25%;" placeholder="Address"></li>
                                            <li><label>User ID*</label><input class="form-control" style="width:25%;" placeholder="UserID"></li>
                                            <li><label>Designation*</label><select style="width:25%;" name="dataTables-example_length" aria-controls="dataTables-example" class="form-control input-sm"><option value="Customer">Customer</option><option value="Merchant">Merchant</option></select></li>
                                            <li><label>Email ID</label><input class="form-control" style="width:25%;" placeholder="EmailID"></li>
                                            <li><label>Contact No</label><input class="form-control" style="width:25%;" placeholder="Contact No"></li>
                                            <li><label>Address</label><input class="form-control" style="width:25%;" placeholder="Address"></li>
                                            </ul>                                                               
                                    </div>
                                        <button type="submit" class="btn btn-default"> Save </button>
                                        <button type="reset" class="btn btn-default"> Reset </button>
                                    </form>
                                </div>
                            </div>
                            </div>
                            <div class="tab-pane fade" id="Delete">
                                <div class="panel panel-default">
                                <div class="panel-body">
                                    <form role="form">
                                    <div class="form-group">
                                        <br>
                                        <br>
                                        <ul class="list-unstyled">
                                            <li><label>Name*</label><input class="form-control" style="width:25%;" placeholder="Name"></li>
                                            <li><label>Employee ID*</label><input class="form-control" style="width:25%;" placeholder="AccountNo"></li>                                            
                                            </ul>                                        
                                    </div>
                                        <button type="button" class="btn btn-default"> Delete </button>
                                    </form>
                                </div>
                            </div>
                            </div>
                            </div>
                    </div>
                </div>
            </div>
            <!-- /#page-wrapper -->
</div>

        </div>
        <!-- /#wrapper -->

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

