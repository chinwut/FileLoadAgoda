<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title></title>


    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="/resources/js/jquery.min.js"></script>
    <script src="/resources/js/bootstrap.min.js"></script>
    <script src="/resources/js/jquery.form.js"></script>
    <script src="/resources/js/downloadJQ.js"></script>
</head>
<body>
<div class="container">
    <h1>File Downloader </h1>
    ${message}
    <form  id="DownloadForm" action="/downloadResource" method="post">

        <div id="message"></div>

        <input type="radio" name="typeProtocal" value="url" checked> Url<br>
        <input type="radio" name="typeProtocal" value="ftp"> Ftp<br>

        <div id="divUrl">
            Download Path : <input type="text" name="fileName"><br>
        </div>
        <div id="divFtp">
            <table>
                <tr>
                    <td>Server Url :&nbsp;</td>
                    <td><input type="text" name="ftpUrl"></td>
                    <td>&nbsp;ex. www.myserver.com</td>
                </tr>
                <tr>
                    <td>UserName :&nbsp;</td>
                    <td><input type="text" name="ftpUserName"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Password :&nbsp;</td>
                    <td><input type="password" name="ftpPassword"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Path File :&nbsp;</td>
                    <td><input type="text" name="ftpFileName"/></td>
                    <td></td>
                </tr>
            </table>
        </div>
        <input type="submit" value="Submit">
    </form>
</div>

<script language="JavaScript">
    $(document).ready(function () {
        $("#divFtp").hide();
        $("#divUrl").show();
        $('input[type=radio][name=typeProtocal]').change(function () {
            if (this.value == 'url') {
                $("#divFtp").hide();
                $("#divUrl").show();
            }
            else if (this.value == 'ftp') {
                $("#divUrl").hide();
                $("#divFtp").show();
            }
        });
    });
</script>
</body>
</html>
