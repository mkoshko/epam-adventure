(() => {
    let form = document.getElementsByClassName('needs-validation')[0];
    let name = document.getElementById('name');
    name.addEventListener('focus', function () {
        name.classList.remove('is-invalid');
    }, false);
    let startDate = document.getElementById('start-date');
    startDate.addEventListener('change', function () {
        startDate.classList.remove('is-invalid');
    }, false);
    let endDate = document.getElementById('end-date');
    endDate.addEventListener('change', function () {
        endDate.classList.remove('is-invalid');
    }, false);
    let prize = document.getElementById('prize');
    prize.addEventListener('change', function () {
        prize.classList.remove('is-invalid');
    });

    form.addEventListener('submit', function (event) {
        let valid = checkDates();
        valid &= checkPrize();
        valid &= checkName();
        if (!valid) {
            preventSubmit(event);
        }
    }, false);
})();

String.prototype.isEmpty = function() {
    return (this.length === 0 || !this.trim());
};

function checkDates() {
    let endDate = document.getElementById('end-date');
    let startDate = document.getElementById('start-date');
    if (new Date(startDate.value) <= new Date(endDate.value)) {
        return true;
    } else {
        startDate.classList.add('is-invalid');
        endDate.classList.add('is-invalid');
        return false;
    }
}

function checkName() {
    let name = document.getElementById('name');
    if (name.value != null && !name.value.isEmpty()) {
        return true;
    } else {
        name.classList.add('is-invalid');
        return false;
    }
}

function checkPrize() {
    let prize = document.getElementById('prize');
    if (!prize.value.isEmpty() && prize.value >= 0) {
        return true;
    } else {
        prize.classList.add('is-invalid');
        return false;
    }
}

function preventSubmit(event) {
    event.preventDefault();
    event.stopPropagation();
}