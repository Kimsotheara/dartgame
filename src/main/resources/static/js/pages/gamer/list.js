$(document).ready(function () {

    const baseUrl = "/api/v1/gamer";
    const CTable = $('#data-table').DataTable({
        order: [[ 0, "desc" ]],
        responsive: true,
        screenX: true,
        "ajax" : {
            "url": baseUrl,
            "type": "GET",
            "dataSrc": function (d) {
                return d.data;
            }
        },

        "columnDefs": [
            {"className": "dt-center", "targets": "_all"}
        ],
        "columns": [
            {"data": "id"},
            {"data":"name"},
            {"data":"detail"},
            {"data": null, "width": "5%"}
        ],
        "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
            $('td:eq(0)', nRow).html(iDisplayIndexFull +1);
        },
        "createdRow": function (row, data) {
            // console.log(data);
            const $cell = $(row).find("td");
            const id = data.id;
            const name = data.name;
            const link = "<div class='ellipsis'><a class='link' href='/gamer/update/" + id + "' data-btn='link' title='" + id + "'>" + name + "</a></div>";
            let btnDelete;
            btnDelete = "<button type='button' class='btn btn-danger btn-sm' data-btn='delete' data-seq='" + id + "' title='Delete'>Delete</button>";

            // $cell.eq(0).html(autoIncrNum--);
            // $cell.eq(1).html(link);
            $cell.eq(3).html(btnDelete);
        }
    });

    // reload datatable
    function reLoadTable() {
        CTable.ajax.reload();
    }

    let deleteId = "";
    $(document).on("click", "[data-btn='delete']", function () {
        deleteId = $(this).attr("data-seq");
        toastAlertConfirm(
            'Delete',
            'Do you want to delete this Player?',
            'warning',
            true,
            "Delete",
            "#dc3545")
            .then(result => {
                if (result.value) {
                    deleteRequest("api/v1/player/delete/" + deleteId, response => {
                        if (response.status === 200) {
                            toastAlertSuccessBigNoBtn("Player deleted!").then(()=>reLoadTable())
                        } else {
                            toastAlertError(response.responseJSON.message)
                        }
                        $(this).prop("disabled", false);
                    });
                }
            })
    });

});
