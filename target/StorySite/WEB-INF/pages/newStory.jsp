<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link rel="stylesheet"href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
  <title>NEW STORY</title>
</head>
<body>

<section class="container">
  <form:form  modelAttribute="newStory" class="form-horizontal">

    <fieldset>
      <legend>Write Your Story</legend>



      <div class="form-group">
        <label class="control-label col-lg-2 col-lg-2" for="title"> Title </label>
        <div class="col-lg-10">
          <form:input id="title" path="title" type="text" class="form:input-large"/>
          <form:errors path="title" cssClass="text-danger"/>
        </div>
      </div>


      <div class="form-group">
        <label class="control-label col-lg-2" for="description">Description</label>
        <div class="col-lg-10">
          <form:textarea id="description" path="description" rows = "4" cols="30"/>
          <form:errors path="description" cssClass="text-danger"/>
        </div>
      </div>

      <div class="form-group">
        <label class="control-label col-lg-2 col-lg-2" for="tags"> Tag </label>
        <div class="col-lg-10">
          <form:input id="tags" path="tags" type="text" placeholder="Every Word will be considered as a tag" class="form:input-large"/>
        </div>
      </div>



      <div class="dropdown">
        <label class="control-label col-lg-2 col-lg-2"  for="description" > Category </label>
        <div class="col-lg-10">
          <form:select path="categoryname">

            <form:options items="${categoryList}" />
          </form:select>

        </div>
        <label class="control-label col-lg-2 col-lg-2"  for="description" > Language </label>
          <form:select path="lang">
            <form:options items="${languageList}" />
          </form:select>
      </div>


      </div>
      <label class="control-label col-lg-2 col-lg-2"  for="description" > Copyright </label>
      <form:select path="storycopyright">
        <form:options items="${copyrightList}" />
      </form:select>
      </div>






      <div class="form-group">
        <div class="col-lg-offset-2 col-lg-10">
          <input type="submit" name="next" class="btn btn-primary" value ="Next"/>
          <input type="submit" name="save" class="btn btn-primary" value ="Save"/>
          <input type="submit" name="cancel" class="btn btn-primary" value ="Cancel"/>
        </div>
      </div>





    </fieldset>
    </form:form>
</section>
</body>
</html>