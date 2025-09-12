/**
 * New Entry page scripts for searchable dropdown functionality
 */
document.addEventListener('DOMContentLoaded', function() {
    // Initialize select2 for team dropdowns
    $('#homeTeamId').select2({
        placeholder: "--Select Option--",
        width: '350px',
        templateResult: formatTeam
    });

    $('#awayTeamId').select2({
        placeholder: "--Select Option--",
        width: '350px',
        templateResult: formatTeam
    });

    // Initialize select2 for venue and competition dropdowns
    $('#groundId').select2({
        placeholder: "--Select Option--",
        width: '350px'
    });

    $('#competitionId').select2({
        placeholder: "--Select Option--",
        width: '350px'
    });

    // Function to format teams in dropdown with logos
    function formatTeam(team) {
        if (!team.id) {
            return team.text;
        }

        var imgSrc = $(team.element).data('img');
        if(!imgSrc) {
            return team.text;
        }

        var $team = $(
            '<span><img src="' + imgSrc + '" height="20px" width="20px" style="margin-right: 10px;" /> ' + team.text + '</span>'
        );

        return $team;
    }

    // Update the badge when selection changes
    $('#homeTeamId').on('change', function() {
        var imgSrc = $(this).find('option:selected').data('img');
        $('#homeBadge').attr('src', imgSrc || '');
    });

    $('#awayTeamId').on('change', function() {
        var imgSrc = $(this).find('option:selected').data('img');
        $('#awayBadge').attr('src', imgSrc || '');
    });
});
