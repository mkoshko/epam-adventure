(() => {
    window.onload = () => {
        let forms = document.getElementsByClassName('needs-validation');
        Array.prototype.filter.call(forms, (form) =>{
            form.addEventListener('submit', function(event) {
                let oldPassword = document.getElementById('oldPassword');
                oldPassword.addEventListener('focus', () => {
                    oldPassword.setAttribute('class', 'form-control');
                });
                let newPassword = document.getElementById('newPassword');
                newPassword.addEventListener('focus', () => {
                    newPassword.setAttribute('class', 'form-control');
                });
                let repeatPassword = document.getElementById('repeat-password');
                repeatPassword.addEventListener('focus', () => {
                    repeatPassword.setAttribute('class', 'form-control');
                });
                if (!checkNewPassword(newPassword, repeatPassword)) {
                    preventSubmit(event);
                }
            }, false);
        }, false);
    }
})();


function preventSubmit(event) {
    event.preventDefault();
    event.stopPropagation();
}

function checkNewPassword(password, repeat) {
    let valid = true;
    if (password.value.length < 8) {
        password.setAttribute('class', 'form-control is-invalid');
        valid = false;
    }
    if (password.value !== repeat.value) {
        repeat.setAttribute('class', 'form-control is-invalid');
        valid = false;
    }
    return valid;
}