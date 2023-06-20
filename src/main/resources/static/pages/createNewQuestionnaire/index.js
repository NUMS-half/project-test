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

const handleCreateQuestionnaire = ()=> {
    if(!$('#surveyName').val()) return alert('调查名称不能为空！')
    if(!$('#surveyDescription').val()) return alert('调查描述不能为空！')
    if (!($('#startTime').val() && new Date($('#startTime').val()).getTime())) return alert('开始时间不能为空！')
    if (!($('#endTime').val() && new Date($('#endTime').val()).getTime())) return alert('结束时间不能为空！')

    $.ajax({
        url: API_BASE_URL + '/',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            if (res.code === "666"){
                alert('创建成功！')
                location.href = ''
            } else {
                alert(res.message)
            }
        }
    })
}
