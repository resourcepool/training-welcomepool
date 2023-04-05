import "./jquery.dataTables.min.js";


$(document).ready( function () {
    $("#dtMembers").dataTable({
         "bJQueryUI":true,
          "bSort":true,
          "bPaginate":true,
          "sPaginationType":"full_numbers",
           "iDisplayLength": 10
    });

} );
