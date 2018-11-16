<%-- 
    Document   : admin
    Created on : Nov 14, 2018, 10:33:10 PM
    Author     : TuanVXM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang quản lý</title>

        <style>
            html {
                font-family: verdana;
            }

            .welcome {
                font-weight: bold;
                font-size: 20px;
                margin-bottom: 30px;
            }

            #notify {
                margin-top: 20px;
            }

            .crawled-notify {
                color: green;
            }
        </style>
    </head>
    <body>
        <div class="welcome">Chào mừng quản lý!</div>
        <button id="crawl-button" onclick="crawl()">
            Cào dữ liệu
        </button>
        <div id="notify"></div>
    </body>

    <script>
        function crawl() {
            document.getElementById("crawl-button").disabled = true;
            document.getElementById("notify").innerHTML =
                    '<div class="crawling-notify">Đang cào dữ liệu, xin vui lòng đợi 5 phút!</div>';
            let xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    document.getElementById("notify").innerHTML =
                            '<div class="crawled-notify">' + this.responseText + '</div>';
                }
            };
            xhttp.open("PUT", "/Presentation/api/product", true);
            xhttp.send();
        }
    </script>
</html>
