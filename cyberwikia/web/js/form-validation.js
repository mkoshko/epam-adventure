(() => {

    window.onload = () => {
        let forms = document.getElementsByClassName('needs-validation');
        Array.prototype.filter.call(forms, (form) =>{
            form.addEventListener('submit', function(event) {
                let login = document.getElementById('login');
                login.addEventListener('focus', () => {
                    login.setAttribute('class', 'form-control');
                });
                let email = document.getElementById('email');
                email.addEventListener('focus', () => {
                    email.setAttribute('class', 'form-control');
                });
                let password = document.getElementById('password');
                password.addEventListener('focus', () => {
                    password.setAttribute('class', 'form-control');
                });
                let repeat = document.getElementById('repeat-password');
                repeat.addEventListener('focus', () => {
                    repeat.setAttribute('class', 'form-control');
                });
                let valid = checkLogin(login);
                valid = checkEmail(email) && valid;
                valid = checkPassword(password, repeat) && valid;
                if (!valid) {
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

function checkLogin(login) {
    if (login.value.length === 0) {
        login.setAttribute('class', 'form-control is-invalid');
        return false;
    }
    return true;
}

function checkEmail(email) {
    const regex = new RegExp('^[\\w-_.+]*[\\w-_.]@([\\w]+.)+[\\w]+[\\w]$');
    if (!regex.test(email.value)) {
        email.setAttribute('class', 'form-control is-invalid');
        return false;

    }
    return true;
}

function checkPassword(password, repeat) {
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