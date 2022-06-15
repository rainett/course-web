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
        if (session.getAttribute("username") == null) {
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
        <form action="controller" method="post">
            <input type="hidden" name="command" value="newAirplane">
            <table>
                <tr class="floating-row-s">
                    <td colspan="12">
                        Main information
                    </td>
                </tr>
                <tr class="floating-row">
                    <td colspan="12">
                        <input class="floating-input" type="text" name="airplaneName" placeholder="Lockheed Martin F-16 Fighting Falcon" required>
                    </td>
                </tr>
                <tr class="floating-row">
                    <td colspan="12">
                        <input class="floating-input" type="text" name="airplaneType" placeholder="Multi role fighter" required>
                    </td>
                </tr>
                <tr class="floating-row">
                    <td colspan="12">
                        <textarea maxlength="2048" name="airplaneDescription" placeholder="Description, up to 2048 symbols" required></textarea>
                    </td>
                </tr>
                <tr class="floating-row-s">
                    <td colspan="12">
                        Specifications
                    </td>
                </tr>
                <tr class="floating-row">
                    <td colspan="3">
                        <input class="floating-input" type="number" name="specsCrew" placeholder="Crew amount" required>
                    </td>
                    <td colspan="3">
                        <input class="floating-input" type="number" step="0.01" name="specsLen" placeholder="Length m" required>
                    </td>
                    <td colspan="3">
                        <input class="floating-input" type="number" step="0.01" name="specsWingsSpan" placeholder="Wing span m" required>
                    </td>
                    <td colspan="3">
                        <input class="floating-input" type="number" step="0.01" name="specsHeight" placeholder="Height m" required>
                    </td>
                </tr>
                <tr class="floating-row">
                    <td colspan="4">
                        <input class="floating-input" type="number" name="specsEmptyWeight" placeholder="Empty weight kg" required>
                    </td>
                    <td colspan="4">
                        <input class="floating-input" type="number" name="specsMaxWeight" placeholder="Maximum weight kg" required>
                    </td>
                    <td colspan="4">
                        <input class="floating-input" type="number" name="specsWeight" placeholder="Weight kg" required>
                    </td>
                </tr>
                <tr class="floating-row">
                    <td colspan="3">
                        <input class="floating-input" type="number" name="specsSpeed" placeholder="Speed m/s" required>
                    </td>
                    <td colspan="3">
                        <input class="floating-input" type="number" name="specsRange" placeholder="Range km" required>
                    </td>
                    <td colspan="3">
                        <input class="floating-input" type="number" name="specsCeiling" placeholder="Ceiling m" required>
                    </td>
                    <td colspan="3">
                        <input class="floating-input" type="number" name="specsCombatRange" placeholder="Combat range km" required>
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