function resetPrice() {
	$('#priceFilter input').each(function() {
		this.value = '';
	});
}

function resetCategories() {
	$('#categoriesFilter input[type=checkbox]').each(function() {
		this.checked = false;
	});
}

function resetManufacturers() {
	$('#manufacturersFilter input[type=checkbox]').each(function() {
		this.checked = false;
	});
}

function addToCart(id) {
	var cartCountItem = document.getElementById('cart_count_id');
	var cartTotalPriceItem = document.getElementById('cart_price_id');
	$.ajax ({
		url : document.getElementById('add_to_cart_url').value,
		data : { addMode : 'APPEND_ONE', productId : id }
	}).done(function (resp) {
		if (resp.success) {
			cartCountItem.innerHTML = resp.totalCount;
			cartTotalPriceItem.innerHTML = resp.totalPrice / 100;
		}
	}).fail(function() {
		alert('cannot add product to cart');
	})
}

function incrementItem(row, id) {
	$.ajax({
		url : $('#add_to_cart_url').value(),
		data : { addMode : 'APPEND_ONE', productId : id }
	}).done(function(resp){
		if (resp.success) {
			$(row).children('')
		}
	}).fail(function() {
		alert('ajax fail');
	});
}

function decrementItem(id) {
	
}

function setTotal(totalCount, totalPrice) {
	
}

