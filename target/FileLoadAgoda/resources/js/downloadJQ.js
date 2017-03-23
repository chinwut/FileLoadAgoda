/**
 * Created by Blazer on 3/23/2017.
 */
$(document).ready(function() {
    var options = {
        beforeSend : function() {
            $("#message").html("<font color='orange'>File download is in progress</font>");
        },
        success : function() {
            $("#message").html("<font color='green'>Success download!</font>");
        },
        complete : function(response,xhr, textStatus) {
            console.log(response.status);
            if(response.status =="200"){ $("#message").html("<font color='green'><b>Your file has been Download!</b></font>");}
            else if(response.status =="500" || response.status =="404")
            {
                $("#message").html("<font color='red'> ERROR: unable to download files</font>");
            }else{
                $("#message").html("<font color='red'> ERROR: unable to download files Please Try Again</font>");
            }

        },
        error : function() {
            $("#message").html("<font color='red'> ERROR: unable to download files</font>");
        }
    };
    $("#DownloadForm").ajaxForm(options);
});