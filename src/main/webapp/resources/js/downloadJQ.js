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
        complete : function(response) {
            $("#message").html("<font color='blue'>Your file has been download!</font>");
        },
        error : function() {
            $("#message").html("<font color='red'> ERROR: unable to download files</font>");
        }
    };
    $("#DownloadForm").ajaxForm(options);
});