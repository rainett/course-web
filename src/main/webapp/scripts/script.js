
function validate() {

    const usernameInput = document.getElementById("username-input");
    const emailInput = document.getElementById("email-input");
    const passwordInput = document.getElementById("password-input");
    const repeatedPassword = document.getElementById("repeated-password-input");

    if (validateUsername(usernameInput) || validateEmail(emailInput) || validatePassword(passwordInput) || validateRepeatedPassword(repeatedPassword)) {
        return false;
    }
    return true;



    function validateUsername(usernameInput) {
        if (usernameInput.value.length < 4 || usernameInput.value.length > 50) {
            addError('Length of username should be between 4 and 50 characters');
            return true;
        }
        const regex = new RegExp("^\\w+?$", "gi");
        const found = regex.test(usernameInput.value);
        console.log(found);
        if (!found) {
            addError('Username should contain only latin letters, numbers or underscores');
            return true;
        }
        removeError('Username should contain only latin letters, numbers or underscores');
        removeError('Length of username should be between 4 and 50 characters');
        return false;
    }

    function validateEmail(emailInput) {
        const regex = new RegExp("\\w+@\\w+.\\w+", "g");
        const found = regex.test(emailInput.value);
        console.log(found);
        if (!found) {
            addError('Incorrect e-mail');
            return true;
        }
        removeError('Incorrect e-mail');
        return false;
    }

    function validatePassword(passwordInput) {
        if (passwordInput.value.length < 6) {
            addError('Password should have at least 6 characters');
            return true;
        }
        const regex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}$", "g");
        const found = regex.test(passwordInput.value);
        console.log(found);
        if (!found) {
            addError('Password should have at least 1 capital, 1 lower letters and 1 number');
            return true;
        }
        removeError('Password should have at least 6 characters');
        removeError('Password should have at least 1 capital, 1 lower letters and 1 number');
        return false;
    }

    function validateRepeatedPassword(passwordInput, passwordRepeated) {
        if (passwordInput.value !== passwordRepeated.value) {
            addError('Passwords don`t match!');
            return true;
        }
        removeError('Passwords don`t match!');
        return false;
    }
}


function isNumeric(str) {
    if (typeof str != "string") {
        return false
    }
    return !isNaN(str) && !isNaN(parseFloat(str))
}

function addError(message) {
    let parent = document.getElementById("table");
    let tr = document.createElement("tr");
    parent.appendChild(tr);
    tr.setAttribute("class", "floating-row");
    tr.setAttribute("id", message);
    let td = document.createElement("td");
    tr.appendChild(td);
    let div = document.createElement("div");
    td.appendChild(div);
    div.setAttribute("class", "error-message");
    div.innerHTML = message;
}

function removeError(id) {
    document.getElementById(id).remove();
}