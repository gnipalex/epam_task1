function addToCartInHeader(id) {
	$.ajax ({
		url : $('#add_to_cart_url').val(),
		type : 'POST',
		data : { addMode : 'APPEND_ONE', productId : id }
	}).done(function (resp) {
		if (resp.success) {
			updateCartInHeader(resp.totalCount, resp.totalPrice);
		}
	}).fail(function() {
		alert('cannot add product to cart');
	})
}

function updateCartInHeader(totalCount, totalPrice) {
	$('#cart_count_id').html(totalCount);
	$('#cart_price_id').html(totalPrice / 100);
}

function incrementItem(button, id) {
	updateCartItem($(button).closest('tr').get(0), 
			id, 'APPEND_ONE');
}

function decrementItem(button, id) {
	updateCartItem($(button).closest('tr').get(0), 
			id, 'REMOVE_ONE');
}

function removeItem(button, id) {
	updateCartItem($(button).closest('tr').get(0), 
			id, 'REMOVE_ALL');
}

function updateCartItem(row, id, addMode) {
	$.ajax({
		url : $('#add_to_cart_url').val(),
		type : 'POST',
		data : { addMode : addMode, productId : id }
	}).done(function(resp){
		if (resp.success) {
			if (resp.itemCount > 0) {
			$('.cart_item_count', row).html(resp.itemCount);
			$('.cart_item_total_price', row).html(resp.itemPrice / 100);
			} else {
				$(row).remove();
			}
			$('#id_total_row .cart_item_total_count').html(resp.totalCount);
			$('#id_total_row .cart_item_total_price').html(resp.totalPrice / 100);
			updateCartInHeader(resp.totalCount, resp.totalPrice);
		}
	}).fail(function() {
		alert('can not update cart');
	});
}

function clearCart() {
	$.ajax({
		url : $('#add_to_cart_url').val(),
		type : 'POST',
		data : { addMode : 'CLEAR_CART'}
	}).done(function(resp) {
		if (resp.success) {
			updateCartInHeader(0, 0);
			$('#id_total_row .cart_item_total_count').html(0);
			$('#id_total_row .cart_item_total_price').html(0);
			var total_row = $('#id_total_row').get(0);
			$('#cart_table tbody tr').remove();
			$('#cart_table tbody').append(total_row);
		}
	}).fail(function() {
		alert('can not update cart');
	})
}