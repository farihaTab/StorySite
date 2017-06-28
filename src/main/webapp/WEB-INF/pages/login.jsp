<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet"href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
  <title>Login</title>
</head>
<body>

<section class="container">
  <form:form  modelAttribute="user" class="form-horizontal">
    <form:errors path="*" cssClass="alert alert-danger" element="div"/>
    <fieldset>
      <legend>Login</legend>



      <div class="form-group">
        <label class="control-label col-lg-2 col-lg-2" for="Username"> Username </label>
        <div class="col-lg-10">
          <form:input id="username" path="username" type="text" class="form:input-large"/>
        </div>
      </div>


      <div class="form-group">
        <label class="control-label col-lg-2 col-lg-2" for="password"> Password </label>
        <div class="col-lg-10">
          <form:input id="password" path="password" type="password" class="form:input-large"/>
        </div>
      </div>




      <div class="form-group">
        <div class="col-lg-offset-2 col-lg-10">
          <input type="submit" id="btnAdd" class="btn btn-primary" value ="Login"/>
        </div>
      </div>
    </fieldset>
  </form:form>
</section>
</body>
</html>