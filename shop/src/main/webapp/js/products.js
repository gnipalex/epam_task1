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

