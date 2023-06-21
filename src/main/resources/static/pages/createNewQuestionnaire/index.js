onload = () => {
    $('#headerUsername').text($util.getItem('userInfo').username)
    $('#headerDivB').text('创建调查问卷')

    $('#startTime').datetimepicker({
        language: 'zh-CN', // 显示中文
        format: 'yyyy-mm-dd', // 显示格式
        minView: "month", // 设置只显示到月份
        initialDate: new Date(), // 初始化当前日期
        autoclose: true, // 选中自动关闭
        todayBtn: true // 显示今日按钮
    })
    $('#endTime').datetimepicker({
        language: 'zh-CN', // 显示中文
        format: 'yyyy-mm-dd', // 显示格式
        minView: "month", // 设置只显示到月份
        initialDate: new Date(), // 初始化当前日期
        autoclose: true, // 选中自动关闭
        todayBtn: true // 显示今日按钮
    })
}

const handleCreateQuestionnaire = () => {
    let userInfo = $util.getItem("userInfo")
    let params = {
        questionnaireName: $('#surveyName').val(),
        questionnaireDescription: $('#surveyDescription').val(),
        createdBy: userInfo.username,
        projectId: $util.getPageParam("selectProject"),
        type: $util.getPageParam("selectType"),
        status: 1
    }

    if (!params.questionnaireName) return alert('调查名称不能为空！')
    if (!params.questionnaireDescription) return alert('调查描述不能为空！')
    if (!($('#startDate').val() && new Date($('#startDate').val()).getTime())) return alert('开始时间不能为空！')
    if (!($('#endDate').val() && new Date($('#endDate').val()).getTime())) return alert('结束时间不能为空！')

    params.startTime = $('#startDate').val() && new Date($('#startDate').val()).getTime();
    params.endTime = $('#endDate').val() && new Date($('#endDate').val()).getTime();

    $.ajax({
        url: API_BASE_URL + '/addQuestionnaireInfo',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            if (res.code === "666") {
                alert('创建成功！')
                $util.setPageParam("newId", res.data)
                $util.setPageParam("inputName", params.questionnaireName)
                $util.setPageParam("inputDescription", params.questionnaireDescription)
                location.href = '/pages/designQuestionnaire/index.html'
            } else {
                alert(res.message)
            }
        }
    })
}
