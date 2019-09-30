(() => {
    document.getElementById("submit").addEventListener("click", () => {
        document.getElementById("regform").action = "signup.html";
        document.getElementById("regform").submit();
    })
})();