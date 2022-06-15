<%@ page import="main.Path" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Airliner</title>
    <link href="css/startStyles.css" rel="stylesheet" type="text/css">
</head>
<body>

    <%
        if (session.getAttribute("username") == null || session.getAttribute("airplane") == null) {
            response.sendRedirect(Path.PAGE__LOGIN);
        }
    %>

    <div id="header">
        <table>
            <tr>
                <td><a href="<%=Path.PAGE__START%>" class="logo">Airliner</a></td>
            </tr>
        </table>
    </div>

    <div class="floating-div">
        <form action="newPhoto" method="post" enctype="multipart/form-data">
            <table>
                <tr class="floating-row-s">
                    <td colspan="12">
                        <label class="custom-file-upload">
                            Choose image
                            <input type="file" name="photoFile" required>
                        </label>
                    </td>
                </tr>
                <tr class="floating-row">
                    <td colspan="12">
                        <button class="floating-button">Confirm</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>

</body>
</html>