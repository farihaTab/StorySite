<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 5/21/2017
  Time: 7:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet"href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        jQuery(document).ready(function() {
            jQuery('.carousel[data-type="multi"] .item').each(function(){
                var next = jQuery(this).next();
                if (!next.length) {
                    next = jQuery(this).siblings(':first');
                }
                next.children(':first-child').clone().appendTo(jQuery(this));
                for (var i=0;i<2;i++) {
                    next=next.next();
                    if (!next.length) {
                        next = jQuery(this).siblings(':first');
                    }
                    next.children(':first-child').clone().appendTo($(this));
                }
            });
        });
    </script>

    <%--<style>
        .carousel-control 			 { width:  4%; }
        .carousel-control.left,.carousel-control.right {margin-left:15px;background-image:none;}
        @media (max-width: 767px) {
            .carousel-inner .active.left { left: -100%; }
            .carousel-inner .next        { left:  100%; }
            .carousel-inner .prev		 { left: -100%; }
            .active > div { display:none; }
            .active > div:first-child { display:block; }

        }
        @media (min-width: 767px) and (max-width: 992px ) {
            .carousel-inner .active.left { left: -50%; }
            .carousel-inner .next        { left:  50%; }
            .carousel-inner .prev		 { left: -50%; }
            .active > div { display:none; }
            .active > div:first-child { display:block; }
            .active > div:first-child + div { display:block; }
        }
        @media (min-width: 992px ) {
            .carousel-inner .active.left { left: -25%; }
            .carousel-inner .next        { left:  25%; }
            .carousel-inner .prev		 { left: -25%; }
        }

    </style>--%>

    <title>Title</title>
</head>


<body>


<div class="well">
    <h3>Stories you may like</h3>
    <div class="row">
        <%--<div class="col-sm-offset-1">--%>
        <div class="carousel slide" data-ride="carousel" data-type="multi" data-interval="3000" id="myCarousel">
            <div class="row">
                <div class="col-sm-offset-2 col-sm-9">
                    <div class="carousel-inner">
                        <div class="item active">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#">
                                <img src="<c:url value="/resource/images/storycover/1.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/2.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/3.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/2.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/2.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/2.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/2.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/2.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-1"></div>
            </div>
            <a class="left carousel-control" href="#myCarousel" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
            <a class="right carousel-control" href="#myCarousel" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>
        </div>
        <%--</div>--%>
    </div>
</div>

<div class="well">
    <h3>Profiles you may like</h3>
    <div class="row">
        <%--<div class="col-sm-offset-1">--%>
        <div class="carousel slide" data-ride="carousel" data-type="multi" data-interval="5000" id="myCarousel2">
            <div class="row">
                <div class="col-sm-offset-2 col-sm-9">
                    <div class="carousel-inner">
                        <div class="item active">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/1.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/2.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/3.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/2.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/2.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/2.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/2.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/2.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-1"></div>
            </div>
            <a class="left carousel-control" href="#myCarousel2" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
            <a class="right carousel-control" href="#myCarousel2" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>
        </div>
        <%--</div>--%>
    </div>
</div>

<div class="well">
    <h3>All time popular</h3>
    <div class="row">
        <%--<div class="col-sm-offset-1">--%>
        <div class="carousel slide" data-ride="carousel" data-type="multi" data-interval="4000" id="myCarousel3">
            <div class="row">
                <div class="col-sm-offset-2 col-sm-9">
                    <div class="carousel-inner">
                        <div class="item active">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/1.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/2.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/3.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/2.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/2.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/2.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/2.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/2.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-1"></div>
            </div>
            <a class="left carousel-control" href="#myCarousel3" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
            <a class="right carousel-control" href="#myCarousel3" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>
        </div>
        <%--</div>--%>
    </div>
</div>

<div class="well">
    <h3>Trending now</h3>
    <div class="row">
        <%--<div class="col-sm-offset-1">--%>
        <div class="carousel slide" data-ride="carousel" data-type="multi" data-interval="3000" id="myCarousel4">
            <div class="row">
                <div class="col-sm-offset-2 col-sm-9">
                    <div class="carousel-inner">
                        <div class="item active">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/1.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/2.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/3.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/2.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/2.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/2.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/2.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                        <div class="item">
                            <div class="col-md-3 col-sm-6 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/2.jpg"></c:url>" class="img-responsive" style = "width:160px;height:280px;"></a></div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-1"></div>
            </div>
            <a class="left carousel-control" href="#myCarousel4" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
            <a class="right carousel-control" href="#myCarousel4" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>
        </div>
        <%--</div>--%>
    </div>
</div>



</body>
</html>
