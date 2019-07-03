

$(document).ready(function(){

	$('ul.dropdown').superfish({
		autoArrows: true,
		animation: {height:'show'}
	});  

});

$1(document).ready(function(){
	$1("#myInput").on("keyup", function() {
		var value = $1(this).val().toLowerCase();
		$1("#filterTable tr").filter(function() {
			$1(this).toggle($1(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
});

$("#krakow").click(function () {
	var rows = $("#myTable").find("tr").hide();
	rows.filter(":contains('Kraków')").show();
});

$(function(){
	$('#krakow').live('click', function() {
		$("#myInput").val($(this).html());
	});
});

$("#poznan").click(function () {
	var rows = $("#myTable").find("tr").hide();
	rows.filter(":contains('Kraków')").show();
});

$("#warszawa").click(function () {
	var rows = $("#myTable").find("tr").hide();
	rows.filter(":contains('Kraków')").show();
});

$("#gdansk").click(function () {
	var rows = $("#myTable").find("tr").hide();
	rows.filter(":contains('Kraków')").show();
});

(function() {

	var table = document.querySelector('#myTable'),
		ths = table.querySelectorAll('thead th'),
		trs = table.querySelectorAll('tbody tr'),
		asc = 'sorttable__th--asc',
		desc = 'sorttable__th--desc';

	function makeArray(nodeList) {

		var arr = [];

		for (var i = 0; i < nodeList.length; i++) {
			arr.push(nodeList[i]);
		}

		return arr;
	}

	function clearClassName(nodeList) {

		for (var i = 0; i < nodeList.length; i++) {
			nodeList[i].classList.remove(asc);
			nodeList[i].classList.remove(desc);
		}
	}

	function sortBy(e) {

		var target = e.target,
			thsArr = makeArray(ths),
			trsArr = makeArray(trs),
			index = thsArr.indexOf(target),
			df = document.createDocumentFragment(),
			order = target.classList.contains(desc) || !target.classList.contains(asc) ? asc : desc;

		clearClassName(ths);

		trsArr.sort(function(a, b) {

			var tdA = a.children[index].textContent,
				tdB = b.children[index].textContent;

			if (tdA < tdB) { return order === asc ? -1 : 1; }
			else if (tdA > tdB) { return order === asc ? 1 : -1; }
			else { return 0; }

		});

		trsArr.forEach(function(tr) {
			df.appendChild(tr);
		});

		target.classList.add(order);
		table.querySelector('tbody').appendChild(df);
	}

	for (var i = 0; i < ths.length; i++) {
		ths[i].onclick = sortBy;
	}

})();