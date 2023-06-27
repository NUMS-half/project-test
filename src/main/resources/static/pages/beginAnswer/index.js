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
    let username = $('#userNameInput').val()

    if (!username) return alert('您还没有输入用户名！')
    // else if (username.length > 32) return alert('用户名过长！请在8个字符以内')

    let checkInfo = {
        id: $util.getPageParam("id"),
        respondent: username
    }

    $.ajax({
        url: API_BASE_URL + '/answeredCheck',
        type: "POST",
        data: JSON.stringify(checkInfo),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            if (res.code === "0") {
                alert(res.message)
                location.href = '/pages/login/index.html'
            }
        }
    })

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
                $util.setPageParam("questionnaireId", res.data["id"])
                $util.setPageParam("username", username)
                $util.setPageParam("previewTitle", res.data["questionnaireName"])
                $util.setPageParam("previewDescription", res.data["questionnaireDescription"])
                $util.setPageParam("problems", res.data["questionList"])
                location.href = '/pages/answerQuestionnaire/index.html';
            } else {
                alert(res.message)
            }
        }
    })
}