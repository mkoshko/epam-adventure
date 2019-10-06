(() => {
    document.getElementById('submit-button').addEventListener('click', () =>{
        document.getElementById('btn-spinner').style.display='block';
        document.getElementById('btn-text').style.display='none';
        document.getElementById('submit-button').setAttribute('disabled', '');
        document.getElementById('form').submit();
    });
})();
