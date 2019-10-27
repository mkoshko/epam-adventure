(() => {
    let buttons = document.getElementsByClassName('placement');
    Array.prototype.filter.call(buttons, (button) => {
        button.addEventListener('click', function () {
            let selectedId = button.getAttribute('for');
            document.getElementById('team')
                .setAttribute('value', selectedId);
        });
    });
    let form = document.getElementById('setPlacementForm');
})();