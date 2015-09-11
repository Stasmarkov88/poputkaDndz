<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<!DOCTYPE html>
<html>
<head lang="en">
  <c:set var="css" value="/assets/css/"/>
  <c:set var="js" value="/assets/js/"/>
  <meta charset="UTF-8">
  <%--<title>EasyGo</title>--%>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  <script src="${js}jquery-2.1.3.min.js"></script>
  <script src="${js}bootstrap.js"></script>
  <script src="${js}autoBreadcrumbs.js"></script>
  <script src="${js}bootbox.min.js"></script>
  <!-- CSS  ================================================== -->
  <link rel="stylesheet" href="${css}bootstrap.css">
  <link rel="stylesheet" href="${css}background.css">
  <link rel="stylesheet" href="${css}stickyFooter.css">
  <link rel="stylesheet" href="${css}custom.css">


  <link rel="stylesheet" href="${css}custom.css">
</head>

<body>
<div class="container-fluid" style="max-width: 1480px">
  <%--header--%>
  <t:insertAttribute name="header"/>
  <%--<script type="text/javascript">breadcrumbs()</script>--%>
  <%--body--%>
  <t:insertAttribute name="body"/>
  <%--footer--%>
  <t:insertAttribute name="footer"/>
</div>
</body>
</html>
