import "./jquery.dataTables.min.js";


$(document).ready( function () {
    $("#dtMembers").dataTable({
         "bJQueryUI":true,
          "bSort":false,
          "bPaginate":true,
          "sPaginationType":"full_numbers",
           "iDisplayLength": 10
    });

} );
