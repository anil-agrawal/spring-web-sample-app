<!DOCTYPE html>
<html lang="en">

<head>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
    <%@include file="templates/_headerResources.jsp"%>

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
                        <h1 class="page-header">Blank</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <%@include file="templates/_footerResources.jsp"%>

</body>

</html>
