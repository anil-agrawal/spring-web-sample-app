<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<%@page import="com.san.to.SideBarDataTO"%>
<jstl:set var="sideBarData" value="${sessionScope.sideBarData}"/>
<div class="navbar-default sidebar" role="navigation">
	<div class="sidebar-nav navbar-collapse">
		<ul class="nav" id="side-menu">
			<!-- /.nav-first-level -->
			<li class="sidebar-search">
				<div class="input-group custom-search-form">
					<input type="text" class="form-control" placeholder="Search..."/>
					<span class="input-group-btn">
						<button class="btn btn-default" type="button">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div>
			</li>
			<jstl:forEach items="${sideBarData.itemList}" var="sideBarDataItem">
				<li>
					<jstl:choose>
						<jstl:when test="${sideBarDataItem.hasChildItems==false}">
							<a href="${sideBarDataItem.link}"><i class="fa ${sideBarDataItem.iconClass} fa-fw"></i> ${sideBarDataItem.label}</a>
						</jstl:when>
						<jstl:otherwise>
							<a href="#"><i class="fa ${sideBarDataItem.iconClass} fa-fw"></i> ${sideBarDataItem.label}<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<!-- /.nav-second-level -->
								<jstl:forEach items="${sideBarDataItem.items}" var="sideBarItem">
									<li>
										<jstl:choose>
											<jstl:when test="${sideBarItem.hasChildItems==false}">
												<a href="${sideBarItem.link}">${sideBarItem.label}</a>
											</jstl:when>
											<jstl:otherwise>
												<a href="#">${sideBarItem.label} <span class="fa arrow"></span></a>
												<ul class="nav nav-third-level">
													<!-- /.nav-third-level -->
													<jstl:forEach items="${sideBarItem.items}" var="sideBarSubItem">
														<li>
															<jstl:choose>
																<jstl:when test="${sideBarSubItem.hasChildItems==false}">
																	<a href="${sideBarSubItem.link}">${sideBarSubItem.label}</a>
																</jstl:when>
																<jstl:otherwise>
																	<jstl:forEach items="${sideBarSubItem.items}" var="sideBarSubSubItem">
																		<!-- /.nav-fourth-level -->
																		<!-- Do we really need such depth in side bar? -->
																	</jstl:forEach>
																</jstl:otherwise>
															</jstl:choose>
														</li>
													</jstl:forEach>
												</ul>
											</jstl:otherwise>
										</jstl:choose>
									</li>
								</jstl:forEach>
							</ul> 
						</jstl:otherwise>
					</jstl:choose>
				</li>
			</jstl:forEach>
			
		</ul>
	</div>
	<!-- /.sidebar-collapse -->
</div>
<!-- /.navbar-static-side -->