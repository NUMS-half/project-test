onload = () => {
    $.ajax({
        url: "/getSessionData",
        type: "GET",
        success: function(response) {
            $util.setPageParam("id", response.questionnaireId)
        }
    })
}
const goToAnswer = () => {
    let params = {
        id: $util.getPageParam("id")
    }

    $.ajax({
        url: API_BASE_URL + '/queryQuestionnaireInfo',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            if (res.code === "666") {
                $util.setPageParam("previewTitle", res.data["questionnaireName"])
                $util.setPageParam("previewDescription", res.data["questionnaireDescription"])
                $util.setPageParam("problems", res.data["questionList"])
                location.href = '/pages/answerQuestionnaire/index.html'
            } else {
                alert(res.message)
            }
        }
    })
}