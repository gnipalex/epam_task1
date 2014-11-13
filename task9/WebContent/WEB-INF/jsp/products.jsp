<%@ page language="java" contentType="text/html; charset=cp1251"
    pageEncoding="cp1251"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>Shop - Main</title>
<meta http-equiv="content-type" content="text/html;charset=cp1251" />
<link rel="stylesheet" href="stylesheet.css" type="text/css" />
</head>
<body>
	<div id="content">
		<jsp:include page="/WEB-INF/jsp/parts/header.jsp" />
		<jsp:include page="/WEB-INF/jsp/parts/menu.jsp" />
		<jsp:include page="/WEB-INF/jsp/parts/modules.jsp" />
		<div id="maincontent">
			<div id="introduction">
				<h2>Products</h2>
			</div>
			<div id="prodConfig">
				<div class="item">
					<p>Elements on page</p>
					<select id="elementsOnPage" name="elementsOnPage">
						<option value="">fdfdf</option>
						<option value="">fdfdf</option>
						<option value="">fdfdf</option>
					</select>
				</div>
				<div class="item">
					<p class="label">Sort by</p>
					<select id="sortBy" name="sortBy">
						<option value="">fdfdf</option>
						<option value="">fdfdf</option>
						<option value="">fdfdf</option>
					</select>
				</div>
				<div style="clear: both;"></div>
			</div>
			<div id="filter">
				<div class="f">
					<div class="title">
						<span class="text">Price</span> <span class="btn">(reset)</span>
					</div>
					<input class="price" type="text" maxlength="10" name="priceLow" value=""/> low
					<input class="price" type="text" maxlength="10" name="priceHigh" value=""/> high
				</div>
				<div class="f">
					<div class="title">
						<span class="text">Categories</span> <span class="btn">(reset)</span>
					</div>
						<ul>
							<li><input type="checkbox" name="cat" value="2" /> Category dgsggdfggggggggggggggggggggggggd fFFf FDFdfF
								A</li>
							<li><input type="checkbox" name="cat" value="2" /> Category
								A</li>
							<li><input type="checkbox" name="cat" value="2" /> Category
								A</li>
							<li><input type="checkbox" name="cat" value="2" /> Category
								A</li>
						</ul>
				</div>
				<div class="f">
					<div class="title">
						<span class="text">Categories</span> <span class="btn">(reset)</span>
					</div>
						<ul>
							<li><input type="checkbox" name="cat" value="2" /> Category
								A</li>
							<li><input type="checkbox" name="cat" value="2" /> Category
								A</li>
							<li><input type="checkbox" name="cat" value="2" /> Category
								A</li>
							<li><input type="checkbox" name="cat" value="2" /> Category
								A</li>
							<li><input type="checkbox" name="cat" value="2" /> Category
								A</li>
							<li><input type="checkbox" name="cat" value="2" /> Category
								A</li>
							<li><input type="checkbox" name="cat" value="2" /> Category
								A</li>
							<li><input type="checkbox" name="cat" value="2" /> Category
								A</li>
						</ul>
				</div>
			</div>
			<div id="leftside" class="productside">
				<div class="paginationBlock">
					<button>&lt;</button>
					<span class="pgnNumber">1</span>
					<span class="pgnNumber">2</span>
					<span class="pgnNumber">3</span>
					<span class="pgnNumber">...</span>
					<span class="pgnNumber">8</span>
					<button>&gt;</button>
				</div>
				<div class="products_holder">
					<ul>
						<li><img src="http://lorempixum.com/100/100/nature/1"/>
								<h3>Headline</h3>
								<p>
									<span class="price">99$</span>
								</p>
								<p>Lorem ipsum dolor sit amet...</p>
								<button title="add to cart" />
						</li>

						<li><img src="http://lorempixum.com/100/100/nature/2" />
								<h3>Headline</h3>
								<p>
									<span class="price">99$</span>
								</p>
								<p>Lorem ipsum dolor sit amet...</p>
								<button title="add to cart" /></li>

						<li><img src="http://lorempixum.com/100/100/nature/3" />
								<h3>Headline</h3>
								<p>
									<span class="price">99$</span>
								</p>
								<p>Lorem ipsum dolor sit amet...</p>
								<button title="add to cart" /></li>

						<li><img src="http://lorempixum.com/100/100/nature/4" />
								<h3>Headline</h3>
								<p>
									<span class="price">99$</span>
								</p>
								<p>Lorem ipsum dolor sit amet...</p>
								<button title="add to cart" /></li>
					</ul>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/jsp/parts/footer.jsp" />
	</div>
</body>
</html>