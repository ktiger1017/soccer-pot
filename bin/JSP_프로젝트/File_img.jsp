<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script>
src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"
</script>
<script>
$(document).ready(function() {
        $("#fileUpload").on('change', function() {
          // Get count of selected files
          // 읽어들인 파일의 [0]에 파일 정보가 저장됨
          var countFiles = $(this)[0].files.length;
          var imgPath = $(this)[0].value;
          var extn = imgPath.substring(imgPath.lastIndexOf('.') + 1).toLowerCase();
          var image_holder = $("#image-holder");
          image_holder.empty();
          if (extn == "gif" || extn == "png" || extn == "jpg" || extn == "jpeg") {
            if (typeof(FileReader) != "undefined") {
              //loop for each file selected for uploaded.
              for (var i = 0; i < countFiles; i++) 
              {
                 //파일을 읽어들일 객체생성
                var reader = new FileReader();
               //파일 읽기가 끝날때 이벤트 스크립터
                //e는 현재발생한 이벤트 객체(정보)
               //e.target는 현재 이벤트가 발생한 요소객체(이벤트를 당한 객체)
               //e.target.result는 읽어들인 파일 객체의 고유한 URL
               //thumb-image class로 정의하고 CSS 적용
                reader.onload = function(e) {
                  $("<img />", {
                    "src": e.target.result,
                    "class": "thumb-image"
                  }).appendTo(image_holder);
                }
               
                //파일을 읽어오는 순간 그 파일의 고유일 URL이 생성됨.
                //이 고유한 URL을 <img>태그의 src속성 값으로 활용됨
                //src 속성값을 이용하여 미리보기 image-holder를 통해서
                
                image_holder.show();
                reader.readAsDataURL($(this)[0].files[i]);
              }
            } else {
              alert("This browser does not support FileReader.");
            }
          } else {
            alert("Pls select only images");
          }
        });
      });
</script>

<style type="text/css">
 	.tumb-image{
 		float : left;
 		width:300px;
 		position : relative;
 		padding: 5px;
 	}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>


<body>
<div id="wrapper" style="margin-top: 20px;">
	<input id="fileUpload" multiple="multiple" type="file"/>
	<div id="image-holder"></div>
</div>

</body>
</html>