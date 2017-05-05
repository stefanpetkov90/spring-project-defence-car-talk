(function($){
    var googleUrl = "http://www.google.com?";

    function fetchAndInsertResults(inputText){
        $.ajax({
            url: googleUrl+inputText,
            success: function(data){
                $('#result').html(data);
            }
        });
    }

    $('#searchbutton').bind('submit',function(){
        this.preventDefault();
        var inputText = $('#searchForm').serialize();
        fetchAndInsertResults(inputText);
    });
})(jQuery);