<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type='file' name='uploadFile' multiple>
	<button id="uploadBtn">UPLOAD</button>
	<style>
	
.uploadResult {
	width: 100vw;
	height: 20vh;
	display: flex;
	flex-direction: row;
	justify-content: center;
	background-color: maroon;
}

.uploadUL {
	display: flex;
	flex-direction: row;
	justify-content: center;
	list-style: none;
	margin: 5em;
	margin-top: 5px;
}

.uploadUL li {
	width: 100px;
	height: 150px;
	background-color: pink;
	border: 1px solid black;
	border-radius: 3px;
	margin: 10px;
}
</style>
	<div class="uploadResult">
		<ul class="uploadUL">
		</ul>
	</div>

	<form id="actionForm" action="/downFile" method="get" target="_blank">
		<input type="hidden" name="fname">

	</form>


	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>

	<script>
		
		var $actionForm = $("#actionForm");
	
		function down(fileName){

			$actionForm.find("input[name='fname']").val(fileName);
			$actionForm.submit();
		}
			
		$(document).ready(function() {
			
			var $listUL = $(".uploadUL");
			
			
			$("#uploadBtn").on("click", function(e) {

				var formData = new FormData();

				var inputFile = $("input[name='uploadFile']");

				var files = inputFile[0].files;

				for (var i = 0; i < files.length; i++) {
					formData.append("uploadFile", files[i]);
				}

				$.ajax({
					url : '/uploadAjaxAction',
					processData : false,
					contentType : false,
					data : formData,
					type : 'POST',
					success : function(result) {
						console.log(result);
						
						var str = "";
						
						for (var i = 0; i < result.length; i++) {
							var item = result[i];
							str += "<li onclick='javascript:down(\""+item.uuid+"\")'>";
							
							console.log(result[i]);
							str += item.fileName;
							
							if(item.image){
								str += "<img src='/viewFile?fname=s_"+item.uuid+"'/>"
							}else {
								str += "<div>"+item.uuid+"</div>";
							}
							
							str += "</li>";
						}
						
						$listUL.append(str);
					}
				});
			});

		});
	</script>
</body>
</html>