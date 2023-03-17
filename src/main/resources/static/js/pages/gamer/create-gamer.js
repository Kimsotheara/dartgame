$(document).ready(function(){

    let data = {};
    let baseURL = '/api/v1/gamer';
    const name = $('#name');
    const desc = $('#desc');

    function validateAndAssignData() {
        if (!name) {
            toastAlertInfo("Please Enter Player name");
            return;
        }

        data = {
            "name": name.val(),
            "detail": desc.val()
        };
        return true;
    }

    $('#save').click(function() {
        if(validateAndAssignData()){
            $.ajax({
                url: baseURL,
                data: JSON.stringify(data),
                dataType: 'json',
                type: 'POST',
                contentType: 'application/json',
                success:  function (data) {
                    console.log(data);
                    alert("Player was Created");
                    $(location).attr('href', "/");
                },
                error:
                    e => toastAlertInfo(e.message()),
            });
        }
    });

});