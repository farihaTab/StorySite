<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet"href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
  <title>Signup</title>
</head>
<body>

<section class="container">
    <form:form  modelAttribute="newUser" class="form-horizontal">
        <fieldset>
            <legend>Sign Up</legend>



                <div class="form-group">
                    <label class="control-label col-lg-2 col-lg-2" for="username"> Username </label>
                    <div class="col-lg-10">
                        <form:input id="username" path="username" type="text" class="form:input-large"/>
                        <form:errors path="username" cssClass="text-danger"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-lg-2 col-lg-2" for="email"> Email </label>
                    <div class="col-lg-10">
                      <form:input id="email" path="email" type="email" class="form:input-large"/>
                      <form:errors path="email" cssClass="text-danger"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-lg-2 col-lg-2" for="password"> Password </label>
                    <div class="col-lg-10">
                      <form:input id="password" path="password" type="password" class="form:input-large"/>
                      <form:errors path="password" cssClass="text-danger"/>
                    </div>
                </div>


      <div class="form-group">
        <fieldset>
            <div class="col-xs-1">
            </div>
          <div class="col-xs-1">
            <label class="control-label col-lg-2 col-lg-2" for="firstname"> First Name </label>
          </div>
            <div class="col-xs-3">
          <form:input id="firstname" path="firstname" type="text" class="form:input-large"/>
          <form:errors path="firstname" cssClass="text-danger"/>
            </div>
            <div class="col-xs-1">
        <label class="control-label col-lg-2 col-lg-2" for="lastname"> Last Name </label>
            </div>
            <div class="col-xs-1">
          <form:input id="lastname" path="lastname" type="text" class="form:input-large"/>
          <form:errors path="lastname" cssClass="text-danger"/>
            </div>
        </fieldset>


      </div>





      <div class="form-group">
        <div class="col-lg-offset-2 col-lg-10">
          <input type="submit" id="btnAdd" class="btn btn-primary" value ="Submit"/>
        </div>
      </div>

    </fieldset>
  </form:form>
</section>
</body>
</html>