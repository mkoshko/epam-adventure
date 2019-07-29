function validateForm(form) {
    const name = form["username"].value;
    const email = form["email"].value;
    const password = form["password"].value;
    const repeatPassword = form["repeat-password"].value;
    let valid = true;
    if (name === "" || name == null) {
        form["username"].setAttribute("class", "form-control is-invalid");
        valid = false;
    }
    if (email === "" || email == null) {
        form["email"].setAttribute("class", "form-control is-invalid");
        valid = false;
    }
    if (password.length < 6) {
        form["password"].setAttribute("class", "form-control is-invalid");
        valid = false;
    }
    if (password !== repeatPassword) {
        valid = false;
    }
    return valid;
}
function validateLogin(form) {
    const name = form["username"].value;
    const password = form["password"].value;
    let valid = true;
    if (name === "" || name == null) {
        form["username"].setAttribute("class", "form-control is-invalid");
        valid = false;
    }
    if (password.length === 0) {
        form["password"].setAttribute("class", "form-control is-invalid");
        valid = false;
    }
    return valid;
}
function resetInvalid(elem) {
    elem.setAttribute("class", "form-control");
}
function validateEditForm(form, oldP, newP, confP) {
    const oldPassword = form[oldP].value;
    const newPassword = form[newP].value;
    const confirmPassword = form[confP].value;
    let valid = true;
    if (oldPassword.length < 6) {
        document.getElementById(oldP).setAttribute("class", "form-control is-invalid");
        valid = false;
    }
    if (newPassword.length < 6) {
        document.getElementById(newP).setAttribute("class", "form-control is-invalid");
        valid = false;
    }
    if (newPassword !== confirmPassword) {
        document.getElementById(confP).setAttribute("class", "form-control is-invalid");
        valid = false;
    }
    return valid;
}
function validatePassword(id1, id2) {
    let p1 = document.getElementById(id1).value;
    let p2 = document.getElementById(id2).value;
    if (p1 !== p2){
        p2 = document.getElementById(id2);
        p2.setAttribute("class", "form-control is-invalid");
    } else {
        p2 = document.getElementById(id2);
        p2.setAttribute("class", "form-control");
    }
}