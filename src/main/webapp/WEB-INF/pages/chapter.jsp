<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet"href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
  <title>Shobdo Kolpo</title>
</head>
<body>

<section class="container">
  <form:form  modelAttribute="chapter" class="form-horizontal">

    <form:hidden path="chapterid"></form:hidden>
    <form:hidden path="belongsto"></form:hidden>

    <fieldset>
      <legend>Write Your Story</legend>

      <div class="form-group">
        <label class="control-label col-lg-2 col-lg-2" for="title"> Title </label>
        <div class="col-lg-10">
          <form:input id="title" path="title" type="text" value="${chapter.title}" class="form:input-large"/>
        </div>
      </div>


      <div class="form-group">
        <label class="control-label col-lg-2" for="chapterBody">Your Story</label>
        <div class="col-lg-10">
          <form:textarea id="chapterbody" path="chapterbody" value="${chapter.chapterbody}" rows = "20" cols="70"/>

        </div>
      </div>

      <div class="form-group">
        <div class="col-lg-offset-2 col-lg-10">
          <input type="submit" name="save" class="btn btn-primary" value ="Save"/>
          <input type="submit" name="publish" class="btn btn-primary" value ="Publish"/>
          <input type="submit" name="nextPart" class="btn btn-primary" value ="Next Part"/>
        </div>
      </div>


    </fieldset>
  </form:form>
</section>
</body>
</html>