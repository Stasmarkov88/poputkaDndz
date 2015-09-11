<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="sec"
          uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>

<!-- Navbar -->
<div class="bs-docs-section clearfix">
  <div class="row">
    <div class="col-lg-12">
      <div class="bs-component">
        <nav class="navbar navbar-default">
          <div class="container-fluid">
            <div class="navbar-header">
              <a class="navbar-brand navbar-right" href="/">EasyGoAdmin <span class="sr-only">(current)</span></a>
            </div>
            <a class="pull-right" href="<c:url value="/j_spring_security_logout" />">
              <button type="button" class="navbar-brand navbar-right">Logout</button>
            </a>
          </div>
        </nav>
      </div>
    </div>
  </div>
</div>

