

$1(document).ready(function(){
    $1("#myInput").on("keyup", function() {
        var value = $1(this).val().toLowerCase();
        $1("#filterTable tr").filter(function() {
            $1(this).toggle($1(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});