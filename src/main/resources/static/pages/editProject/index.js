let project = {}

onload = () => {
    $('#headerUsername').text($util.getItem('userInfo').username)
    $('#headerDivB').text('编辑项目')

    project = $util.getPageParam('editProject')

    $('#projectName').val(project.projectName)
    $('#projectDescribe').val(project.projectContent)
}

const handleSaveChange = () => {
    let userInfo = $util.getItem("userInfo")
    let params = {
        id: project.id,
        projectName: $('#projectName').val(),
        projectContent: $('#projectDescribe').val(),
        lastUpdatedBy: userInfo.username
    }
    if (!params.projectName) return alert('项目名称不能为空！')
    if (!params.projectContent) return alert('项目描述不能为空！')
    $.ajax({
        url: API_BASE_URL + '/modifyProjectInfo',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            if (res.code === "666") {
                alert("编辑成功！")
                location.href = "/pages/questionnaire/index.html"
            } else {
                alert(res.message)
            }
        }
    })
}
