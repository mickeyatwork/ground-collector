// Hide / unhide section when button is clicked on
const button = document.getElementById('teamDropdown');

addEventListener('click', function() {

    /*const review = document.getElementById('showTeamUpdates');

    if(review.classList.contains('d-none')){

        review.classList.remove('d-none');
        button.textContent = 'Hide options ^';

    }
    else {

        review.classList.add('d-none');
        button.textContent = 'Show team update options ⌄';

    }

    $("teamFieldUpdate").submit(function(e) {
        e.preventDefault(); // avoid to execute the actual submit of the form.
        var form = $(this);
        var url = form.attr('action');
        $.ajax({
            type: "POST",
            url: url,
            data: form.serialize(), // serializes the form's elements.
            success: function(data)
            {
                alert(data); // show response
                window.location.href = "http://localhost:8080/GroundsCollector/admin/updateTeamField";
            }
        });
    });


    */
});