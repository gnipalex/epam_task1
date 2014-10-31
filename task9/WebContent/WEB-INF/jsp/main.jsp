<%@ page language="java" contentType="text/html; charset=cp1251"
    pageEncoding="cp1251"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
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
				<h2>
					Welcome <span class="green">dear</span> customer
				</h2>
				<p>
					Our shop welcomes you. We hope that here you will find
					everything you need, and affordable prices will not make you doubt
					the purchase.<br /> <br />
			</div>
			<div id="rightside">
				<h2>
					Latest <span class="green">News</span>
				</h2>
				<p>
					<span class="date">12.03.2006</span><br /> Proin tincidunt viverra
					eros. Phasellus id felis. Proin dui. Etiam tristique. Aliquam erat
					volutpat. Suspendisse magna est, faucibus et, tincidunt ac,
					vehicula a, mi. <br /> <a class="readm"
						href="http://web-mastery.info/">Read more...</a>
				</p>
				<p>
					<span class="date">11.17.2006</span><br /> Proin tincidunt viverra
					eros. Phasellus id felis. Proin dui. Etiam tristique. Aliquam erat
					volutpat. Suspendisse magna est, faucibus et, tincidunt ac,
					vehicula a, mi. <br /> <a class="readm"
						href="http://web-mastery.info/">Read more...</a>
				</p>
				<p>
					<span class="date">11.05.2006</span><br /> Proin tincidunt viverra
					eros. Phasellus id felis. Proin dui. Etiam tristique. Aliquam erat
					volutpat. Suspendisse magna est, faucibus et, tincidunt ac,
					vehicula a, mi. <br /> <a class="readm"
						href="http://web-mastery.info/">Read more...</a>
				</p>
			</div>
			<div id="leftside">
				<div class="r">
					<h2>
						The 30 Best Recipes <span class="green">Nutella</span>
					</h2>
					<p>From irresistible macaroons to tasty cheesecakes, discover
						new ways of using, cooking and enjoying Nutella with 30
						mouthwatering recipes that are as versatile as they are delicious.</p>
					<p>
						<a class="readm" href="http://web-mastery.info/">Read more...</a>
					</p>
				</div>
				<div class="le">
					<h2>
						Teapot <span class="green">made of chocolate</span>
					</h2>
					<p>Dark chocolate in its composition has a greater amount of
						cocoa powder than milk. Whereby its melting time is needed, more
						than for milk chocolate. The reason is that the milk fat and have
						lower melting points. Typically, the temperature of melting
						chocolate just above 36 degrees. Therefore, if the temperature is
						higher, the mixture will melt the wax.</p>
					<p>
						<a class="readm" href="http://web-mastery.info/">Read more...</a>
					</p>
				</div>
				<div class="pad">
					<h2>
						Red wine and <span class="green">healthy</span> bacteria
					</h2>
					<p>Scientists Spanish Autonomous University of Madrid found
						that red wine (which we so often order using this service as the
						delivery of products at home) in its composition has bacteria that
						may have a beneficial effect on our body. The results showed that
						wine has 11 strains of bacteria, and the harm that Lactobacillus,
						which is in the yogurt. Also, the wine is Pediococcus and
						Oenococcus, the bacteria associated with the process of
						winemaking.</p>
					<p>As stated by the author of the study, Dolores Gonzalez de
						Llano, until now, was considered and confirmed that the best
						products, which contain in their composition probiotics are dairy
						fermented products. And until now, the probiotic properties of
						wine were almost not studied.</p>
					<p>Probiotics - are living microorganisms. They can be
						beneficial to the human body in the case if use them in proper
						amounts. Primarily exert positive effects of probiotics on bowel
						function. It is believed that they also lower cholesterol and have
						anticancer properties.</p>
					<p>In the studies, the researchers studied the ability of
						bacteria, which were isolated from wine, to survive in an
						environment similar to the gastrointestinal tract of man. But this
						does not mean that having a drink in the daily number of glasses
						of wine your body will be provided with the same, than is provided
						by the use of yogurt. Even considering that a certain amount of
						wine contains probiotics and it has certain health benefits of
						wine consumption is not able to provide the body with the
						necessary bacteria fully, because some of them are killed in the
						winemaking process.</p>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/jsp/parts/footer.jsp" />
	</div>
</body>
</html>