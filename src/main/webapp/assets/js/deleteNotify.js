var deleteLink = document.querySelector('#deleteLink');

deleteLink.addEventListener('click', function (event) {
    event.preventDefault();

    var choice = confirm(this.getAttribute('data-confirm'));

    if (choice) {
        window.location.href = this.getAttribute('href');
    }
});
