
onload = () => {
    $('#headerUsername').text($util.getItem('userInfo').username)
    $('#headerDivB').text('创建项目')
}

const handleCreateProject = () => {
    let userInfo = $util.getItem("userInfo")
    let params = {
        userId: userInfo.id,
        createdBy: userInfo.username,
        lastUpdatedBy: userInfo.username,
        projectName: $('#projectName').val(),
        projectContent: $('#projectDescribe').val()
    }
    if (!params.projectName) return alert('项目名称不能为空！')
    if (!params.projectContent) return alert('项目描述不能为空！')
    $.ajax({
        url: API_BASE_URL + '/addProjectInfo',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            if (res.code === "666"){
                alert('创建成功！')
                location.href = '/pages/questionnaire/index.html'
            } else {
                alert(res.message)
            }
        }
    })
}
