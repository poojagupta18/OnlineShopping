<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Book Store Template, Free CSS Template, TemplateMo.com</title>
<meta name="keywords" content="Book Store Template, Free CSS Template, CSS Website Layout, CSS, HTML, TemplateMo.com" />
<meta name="description" content="Book Store Template, Free CSS Template, Download CSS Website from TemplateMo.com" />
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />


<script>
function calculatePrice(product_id)
{
	
	var price = document.getElementById("price"+product_id).value;
	var quantity = document.getElementById("quantity"+product_id).value;
	console.log(" " + product_id);
	console.log(price + " "+document.getElementById("quantity"+product_id).value);
	document.getElementById("total"+product_id).value = parseFloat(price)*parseFloat(quantity);
	
}

</script>
</head>

<body>
<!--  Free CSS Templates from www.templatemo.com -->
<div id="templatemo_container">
	<div id="templatemo_menu">
    	<ul>
            <li><a href="index.html">Home</a></li>
            <li><a href="#" class="current">Search</a></li>
            <li><a href="index.html">Books</a></li>            
            <li><a href="index.html">New Releases</a></li>  
            <li><a href="#">Company</a></li> 
            <li><a href="#">Contact</a></li>
    	</ul>
    </div> <!-- end of menu -->
    
<jsp:include page="header.jsp" />
    
    <div id="templatemo_content">
    	
        <div id="templatemo_content_left">
        	<div class="templatemo_content_left_section">
            	<h1>Categories</h1>
                <ul>
                    <li><a href="index.html">Donec accumsan urna</a></li>
                    <li><a href="index.html">Proin vulputate justo</a></li>
                    <li><a href="#">In sed risus ac feli</a></li>
                    <li><a href="#">Aliquam tristique dolor</a></li>
                    <li><a href="#">Maece nas metus</a></li>
                    <li><a href="#">Sed pellentesque placerat</a></li>
                    <li><a href="#">Suspen disse</a></li>
                    <li><a href="#">Maece nas metus</a></li>
                    <li><a href="#">In sed risus ac feli</a></li>
            	</ul>
            </div>
			<div class="templatemo_content_left_section">
            	<h1>Bestsellers</h1>
                <ul>
                    <li><a href="#">Vestibulum ullamcorper</a></li>
                    <li><a href="#">Maece nas metus</a></li>
                    <li><a href="#">In sed risus ac feli</a></li>
                    <li><a href="#">Praesent mattis varius</a></li>
                    <li><a href="#">Maece nas metus</a></li>
                    <li><a href="#">In sed risus ac feli</a></li>
                    <li><a href="http://www.flashmo.com" target="_parent">Flash Templates</a></li>
                    <li><a href="http://www.templatemo.com" target="_parent">CSS Templates</a></li>
                    <li><a href="http://www.webdesignmo.com" target="_parent">Web Design</a></li>
                    <li><a href="http://www.photovaco.com" target="_parent">Free Photos</a></li>
            	</ul>
            </div>
            
            <div class="templatemo_content_left_section">                
                <a href="http://validator.w3.org/check?uri=referer"><img style="border:0;width:88px;height:31px" src="http://www.w3.org/Icons/valid-xhtml10" alt="Valid XHTML 1.0 Transitional" width="88" height="31" vspace="8" border="0" /></a>
<a href="http://jigsaw.w3.org/css-validator/check/referer"><img style="border:0;width:88px;height:31px"  src="http://jigsaw.w3.org/css-validator/images/vcss-blue" alt="Valid CSS!" vspace="8" border="0" /></a>
			</div>
        </div> <!-- end of content left -->
        
        <div id="templatemo_content_right">
        	<form action="CheckOutController" method="post">
            <h1>Your WhishList......</h1>
            <div class="image_panel"><img src="images/templatemo_image_02.jpg" alt="CSS Template" width="100" height="150" /></div>
          
           <table border=2>
          	 	<tr>
          			 <th>ProductId</th>
          		 	 <th>code</th>
          			 <th>Description</th>
          			 <th>Price</th>
          			 <th>Manufacturing Date</th>
          	 		<th>Quantity</th>
          	 		<th>Total</th>
          	 		<th>Action</th>
          	 	</tr>
          <c:forEach items="${productList}" var="product" varStatus="status">
          	
          	 	<tr>
          			 <td><input type="hidden" name="product_id" value="${product.productId}"/>${product.productId}</td>
          			 <td>${product.productCode}</td>
          			 <td>${product.description}</td>
          			 <td>
          			 <input type="hidden" name="price${product.productId}"  id="price${product.productId}" value="${product.price}"/> 
          			 <%-- ${product.price} --%>
          			 </td>
          			 <td>
          			 <fmt:formatDate value="${product.manfDate}" pattern="dd/MM/yyyy"/>
          			 </td>
          			 <td>
          			 <input type="text" name="quantity${product.productId}" id="quantity${product.productId}" value=1 onblur="calculatePrice(${product.productId})"/>
          			 </td>
          			  <td>
          			 <input type="text" name="total${product.productId}" id="total${product.productId}" value="${product.price}" onblur="calculatePrice(${product.productId})"/>
          			 </td>
          			 <td>
          			 <a href="ProductDBController?action=deleteFromCart&index=${status.index}">Remove</a>
          			 </td>
          	 	</tr>
          </c:forEach>
           <tr>
           
           </tr>
           </table>
           <input type="submit"></input>
            </form>
            <br/>
            <br/>
            <a href="ProductDBController">Back</a>
           
            
             <div class="cleaner_with_height">&nbsp;</div>
            
            <a href="index.html"><img src="images/templatemo_ads.jpg" alt="css template ad" /></a>
            
        </div> <!-- end of content right -->
    
    	<div class="cleaner_with_height">&nbsp;</div>
    </div> <!-- end of content -->
    
    <jsp:include page="footer.jsp" />
<!--  Free CSS Template www.templatemo.com -->
</div> <!-- end of container -->
<div align=center>This template  downloaded form <a href='http://all-free-download.com/free-website-templates/'>free website templates</a></div></body>
</html>