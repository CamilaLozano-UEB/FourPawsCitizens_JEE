<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Servlet</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
<table class="table table-striped">
    <thead>
    <tr>
        <th>Fecha</th>
        <th>Descripción</th>
        <th>Foto</th>
        <th>Descarga</th>
    </tr>
    </thead>
    <tbody id="imageTable">
    </tbody>
</table>
<form method="get" action="DownloadImageServlet">
    <input type="submit" value="Download"/>
</form>
<script>
    function printTable(servlet) {

        const xhr = new XMLHttpRequest();
        const url = '${pageContext.request.contextPath}/' + servlet;
        console.log(window.location.pathname)
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                console.log(xhr.responseText)
                const imageData = JSON.parse(xhr.responseText);
                const tBody = document.getElementById("imageTable");
                const imageAtr = ["uploadDate", "description", "path"];
                imageData.map(image => {
                    let tRow = document.createElement("tr");

                    for (let i = 0; i < imageAtr.length - 1; i++) {
                        let tData = document.createElement("td");
                        tData.textContent = image[imageAtr[i]];
                        tRow.appendChild(tData);
                    }

                    let tableImage = document.createElement("img");
                    tableImage.src = '${pageContext.request.contextPath}/' + "Images/" + image[imageAtr[2]];
                    console.log(image[imageAtr[2]])
                    tRow.appendChild(document.createElement("td").appendChild(tableImage));

                    tBody.appendChild(tRow);
                })
            }
        }
        console.log("asdasfasf")
        xhr.open('GET', '${pageContext.request.contextPath}/' + servlet, true);
        xhr.send();
    }

    printTable("ListImageServlet")

</script>
</body>
</html>

