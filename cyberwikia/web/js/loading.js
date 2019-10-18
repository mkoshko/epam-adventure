(() => {
    document.getElementById('lang').addEventListener('click', () => {
        document.getElementById('lang').submit();
    })
    document.getElementById('submit-btn').addEventListener('click', () =>{
        document.getElementById('btn-spinner').style.display='inline-block';
        document.getElementById('submit-btn').setAttribute('disabled', '');
        document.getElementById('signup-form').submit();
    });
})();
