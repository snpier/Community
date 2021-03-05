function postCommunity(){
    var questionId = $('#question-id').val();
    var commentContent = $('#comment_content').val();
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "partentId":questionId,
            "comment":commentContent,
            "type":1
        }),
        success: function (response) {
            if (response.code == 200){
                $("#comment_section").hide();
            }else {
                alert(response.message);
            }
            consol.log(response);
        },
        dataType: "json"
    });
}