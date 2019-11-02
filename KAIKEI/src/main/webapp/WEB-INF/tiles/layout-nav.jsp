<%@ page language="java" contentType="text/html; charset=Shift_JIS"
    pageEncoding="Shift_JIS"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="Shift_JIS">
    <title>KAIKEI</title>
    <link rel= "stylesheet" type="text/css" href="/kaikei/css/layout.css">
</head>
<script type="text/javascript" src="/kaikei/js/common.js" ></script>
<script type="text/javascript">

	function menuExtend(menu) {
		var list = document.getElementsByClassName(menu);
		
		if(list[0].style.display == "block") {
			for(var i=0; i < list.length; i++)
				list[i].style.display = "none";
		} else {
			for(var i=0; i < list.length; i++)
				list[i].style.display = "block";
		}
	}
	
</script>
<body style="max-width: 100%">
    <div id="header">
	    <div class="div-header"><tiles:insertAttribute name="header" /></div>
    </div>
    <div id="nav">
	    <div class="div-nav"><tiles:insertAttribute name="nav" /></div>
    </div>
    <div id="section">
    	<div class="div-section">
    		<div class="div-section-contents"><tiles:insertAttribute name="section" /></div>
   		</div>
    </div>    
    <div id="footer">
	    <div class="div-footer"><tiles:insertAttribute name="footer" /></div>
   	</div>    
</body>
</html>