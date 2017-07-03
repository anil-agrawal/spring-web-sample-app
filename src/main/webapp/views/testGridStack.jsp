<!DOCTYPE html>
<html lang="en">

<head>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<c:set var="userService" value="${userService}"/>
    <%@include file="templates/_headerResources.jsp"%>
	
    <link href="../../plugins/gridstack/gridstack.css" rel="stylesheet">
    <link href="../../plugins/gridstack/gridstack-extra.css" rel="stylesheet">
	
</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <%@include file="templates/_header.jsp"%>
			<%@include file="templates/_sidebar.jsp"%>
            
        </nav>

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">GridStack for : ${userService}</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
			
			<div class="row">
                    <div class="grid-stack">
						<div class="grid-stack-item" data-gs-x="1" data-gs-y="0" data-gs-width="5" data-gs-height="2">
							<div class="grid-stack-item-content"  style="height:100%">
								<div  style="height:100%">
									<div class="panel panel-primary" style="height:85%">
										<div class="panel-heading" style="height:75%">
											<div class="row">
												<div class="col-xs-3">
													<i class="fa fa-comments fa-5x"></i>
												</div>
												<div class="col-xs-9 text-right">
													<div class="huge">26</div>
													<div>New Comments!</div>
												</div>
											</div>
										</div>
										<div class="panel-footer" style="height:25%">
												<span class="pull-left">View Details</span>
												<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
												<div class="clearfix"></div>
											</div>
									</div>
								</div>
							</div>
						</div>
						<div class="grid-stack-item" data-gs-x="2" data-gs-y="0" data-gs-width="5" data-gs-height="2">
							<div class="grid-stack-item-content" style="height:100%">
								<div style="height:100%">
									<div class="panel panel-green" style="height:85%">
										<div class="panel-heading" style="height:75%">
											<div class="row">
												<div class="col-xs-3">
													<i class="fa fa-tasks fa-5x"></i>
												</div>
												<div class="col-xs-9 text-right">
													<div class="huge">12</div>
													<div>New Tasks!</div>
												</div>
											</div>
										</div>
										<div class="panel-footer" style="height:25%">
											<span class="pull-left">View Details</span>
											<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
											<div class="clearfix"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="grid-stack-item" data-gs-x="2" data-gs-y="2" data-gs-width="4" data-gs-height="2">
							<div class="grid-stack-item-content"  style="height:100%">
								<div  style="height:100%">
									<div class="panel panel-yellow"  style="height:85%">
										<div class="panel-heading" style="height:75%">
											<div class="row">
												<div class="col-xs-3">
													<i class="fa fa-shopping-cart fa-5x"></i>
												</div>
												<div class="col-xs-9 text-right">
													<div class="huge">124</div>
													<div>New Orders!</div>
												</div>
											</div>
										</div>
										<div class="panel-footer" style="height:25%">
											<span class="pull-left">View Details</span>
											<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
											<div class="clearfix"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="grid-stack-item" data-gs-x="1" data-gs-y="3" data-gs-width="5" data-gs-height="2">
							<div class="grid-stack-item-content"  style="height:100%">
								<div  style="height:100%">
									<div class="panel panel-red"  style="height:85%">
										<div class="panel-heading" style="height:75%">
											<div class="row">
												<div class="col-xs-3">
													<i class="fa fa-support fa-5x"></i>
												</div>
												<div class="col-xs-9 text-right">
													<div class="huge">13</div>
													<div>Support Tickets!</div>
												</div>
											</div>
										</div>
										<div class="panel-footer" style="height:25%">
											<span class="pull-left">View Details</span>
											<span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
											<div class="clearfix"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
                    </div>
                    <!-- /.col-lg-12 -->
            </div>
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
    <%@include file="templates/_footerResources.jsp"%>
	<script src="../../plugins/jquery-ui/jquery-ui.js"></script>
	<script src="../../plugins/lodash/lodash.min.js"></script>
	<script src="../../plugins/gridstack/gridstack.js"></script>
	<script src="../../plugins/gridstack/gridstack.jQueryUI.js"></script>
	
	<script type="text/javascript">
		$(function () {
			$('.grid-stack').gridstack();
		});
	</script>
</body>

</html>
