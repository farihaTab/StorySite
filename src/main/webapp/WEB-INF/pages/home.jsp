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

    <style>
        .carousel-control.left, .carousel-control.right {
            background-image:none;
        }

        .img-responsive{
            width:100%;
            height:auto;
        }

        @media (min-width: 992px ) {
            .carousel-inner .active.left {
                left: -25%;
            }
            .carousel-inner .next {
                left:  25%;
            }
            .carousel-inner .prev {
                left: -25%;
            }
        }

        @media (min-width: 768px) and (max-width: 991px ) {
            .carousel-inner .active.left {
                left: -33.3%;
            }
            .carousel-inner .next {
                left:  33.3%;
            }
            .carousel-inner .prev {
                left: -33.3%;
            }
            .active > div:first-child {
                display:block;
            }
            .active > div:first-child + div {
                display:block;
            }
            .active > div:last-child {
                display:none;
            }
        }

        @media (max-width: 767px) {
            .carousel-inner .active.left {
                left: -100%;
            }
            .carousel-inner .next {
                left:  100%;
            }
            .carousel-inner .prev {
                left: -100%;
            }
            .active > div {
                display:none;
            }
            .active > div:first-child {
                display:block;
            }
        }
    </style>

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

    <title>Title</title>
</head>


<body>

<div class="container">
    <div class="carousel slide row" data-ride="carousel" data-type="multi" data-interval="4000" id="myCarousel">

        <div class="carousel-inner">
            <div class="item active">
                <div class="col-md-3 col-sm-4 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/1.jpg"></c:url>" class="img-responsive"></a></div>
            </div>
            <div class="item">
                <div class="col-md-3 col-sm-4 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/1.jpg"></c:url>" class="img-responsive"></a></div>
            </div>
            <div class="item">
                <div class="col-md-3 col-sm-4 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/1.jpg"></c:url>" class="img-responsive"></a></div>
            </div>
            <div class="item">
                <div class="col-md-3 col-sm-4 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/1.jpg"></c:url>" class="img-responsive"></a></div>
            </div>
            <div class="item">
                <div class="col-md-3 col-sm-4 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/1.jpg"></c:url>" class="img-responsive"></a></div>
            </div>
            <div class="item">
                <div class="col-md-3 col-sm-4 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/1.jpg"></c:url>" class="img-responsive"></a></div>
            </div>
            <div class="item">
                <div class="col-md-3 col-sm-4 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/1.jpg"></c:url>" class="img-responsive"></a></div>
            </div>
            <div class="item">
                <div class="col-md-3 col-sm-4 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/1.jpg"></c:url>" class="img-responsive"></a></div>
            </div>
            <div class="item">
                <div class="col-md-3 col-sm-4 col-xs-12"><a href="#"><img src="<c:url value="/resource/images/storycover/1.jpg"></c:url>" class="img-responsive"></a></div>
            </div>
        </div>

        <a class="left carousel-control" href="#myCarousel" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a>

    </div>
</div>

</body>
</html>
