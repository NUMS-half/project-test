onload = () => {
    $('#headerDivB').text('项目详情')
    let projectId = $util.getPageParam('seeProject')
    fetchProjectInfo(projectId)
    showQuestionnaires(projectId)
}

let username;

const fetchProjectInfo = (id) => {
    let params = {
        id
    }
    $.ajax({
        url: API_BASE_URL + '/queryProjectList',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            let info = res.data[0]
            // console.log(info, 'res')
            $('#projectName').text(info.projectName)
            $('#createTime').text(info.creationDate)
            $('#personInCharge').text(info.createdBy)
            $('#projectDescription').text(info.projectContent)

            username = info.createdBy
        }
    })
}

const showQuestionnaires = (id) => {
    let params = {
        id
    }
    $.ajax({
        url: API_BASE_URL + '/queryProjectQuestionnaire',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            if (res.code === "666") {
                handleQuestionnaireInfo(res.data)
            } else {
                // alert(res.message)

            }
        }
    })
}

const handleQuestionnaireInfo = (questionnaires) => {
    var tableBody = $("#tableBody");

    // 清空表格数据
    tableBody.empty();

    // 遍历数据列表，生成对应的行数据
    $.each(questionnaires, function (index, item) {
        var row = $("<tr>");
        // 序号列
        $("<td>").text(index + 1).appendTo(row);
        // 试卷名称列
        $("<td>").text(item["questionnaire_name"]).appendTo(row);
        // 发布时间列
        let publishTimeTd = $("<td>").text(item["publish_time"]).appendTo(row);
        // 操作列
        var actionsCell = $("<td>");

        $("<button>")
            .attr("type", "button")
            .addClass("btn btn-link")
            .text("预览")
            .click(function () {
                handleQuestionnairePreview(item["id"]);
            })
            .appendTo(actionsCell);

        let publishButton = $("<button>")
            .attr("type", "button")
            .addClass("btn btn-link").text("发布")
            .click(function () {
                handleQuestionnairePublish(item["id"], item["status"]);
            })
            .appendTo(actionsCell);

        $("<button>")
            .attr("type", "button")
            .addClass("btn btn-link btn-red")
            .text("删除")
            .click(function () {
                handleQuestionnaireDelete(item["id"], item["status"]);
            })
            .appendTo(actionsCell);

        $("<button>")
            .attr("type", "button")
            .addClass("btn btn-link btn-red")
            .text("统计")
            .click(function () {
                handleQuestionnaireStatistics(item["id"]);
            })
            .appendTo(actionsCell);

        if (item["status"] === 0) {
            publishButton.text("查看链接")
            publishButton.attr("title", "该问卷已发布");

            $("<button>")
                .attr("type", "button")
                .addClass("btn btn-link btn-red")
                .text("关闭")
                .click(function () {
                    handleQuestionnaireClose(item["id"]);
                })
                .appendTo(actionsCell);

        } else {
            publishTimeTd.text(null);
            publishButton.attr("title", "点击发布此问卷");
        }

        // 将操作按钮单元格添加到行中
        row.append(actionsCell);

        // 将行添加到表格中
        tableBody.append(row);
    });
}

const handleQuestionnairePreview = (id) => {
    // console.log("点击预览问卷")
    let params = {
        id: id
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
                window.open('/pages/answerQuestionnaire/index.html', '_blank')
            } else {
                alert(res.message)
            }
        }
    })
}

const handleQuestionnairePublish = (id, status) => {
    // console.log("点击发布问卷")
    if ( status === 1 ) {
        if (!confirm("您确定要发布此问卷吗？")) return

        let params = {
            id: id,
            status: 0,
            publishTime: new Date()
        }

        $.ajax({
            url: API_BASE_URL + '/setQuestionnaireStatus',
            type: "POST",
            data: JSON.stringify(params),
            dataType: "json",
            contentType: "application/json",
            success(res) {
                if (res.code === "666") {
                    fetchProjectInfo($util.getPageParam('seeProject'))
                    showQuestionnaires($util.getPageParam('seeProject'))
                    alert(res.message)
                } else {
                    alert(res.message)
                }
            }
        })
    }

    let link = API_BASE_URL + "/questionnaireId/" + id;
    let dialog = $('<div>', { class: 'dialog-container' });
    let linkElement =$('<div>').append(
        $('<p>', { class: 'link-text', text: '问卷链接: ' }),
        $('<a>', { class: 'link-text', href: link, text: link, id: 'questionnaireLink', target: '_blank' })
    );

    let copyButton = $('<button>', { class: 'copy-button', text: '复制链接' }).click(function() {
        const textToCopy = linkElement.find('a').text();
        navigator.clipboard.writeText(textToCopy)
            .then(() => {
                alert('链接已复制到剪贴板');
            })
            .catch((error) => {
                alert('复制链接到剪贴板失败');
                console.error(error);
            });
    });

    dialog.append(linkElement, copyButton);

    dialog.dialog({
        modal: true,
        width: 400,
        height: 'auto',
        resizable: false,
        open: function () {
            $(this).find('.link-text').css('height', 'auto');
            $(document).on('mousedown', function (event) {
                if (!$(event.target).closest('.ui-dialog').length) {
                    dialog.dialog('close');
                }
            });
        },
        close: function () {
            $(document).off('mousedown');
            $(this).dialog('destroy').remove();
        }
    });
}

const handleQuestionnaireDelete = (id, status) => {
    // console.log("点击删除问卷")
    if (status === 0) {
        alert("进行中的问卷不能删除！");
        return;
    }
    if (!confirm("确认要删除本问卷吗？")) return
    let params = {
        id: id
    }
    $.ajax({
        url: API_BASE_URL + '/deleteQuestionnaire',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            if (res.code === "666") {
                alert(res.message)
            } else {
                alert(res.message)
            }
            let projectId = $util.getPageParam('seeProject')
            fetchProjectInfo(projectId)
            showQuestionnaires(projectId)
        }
    })
}

const handleQuestionnaireStatistics = (id) => {
    console.log("点击统计问卷")

    $util.setPageParam("questionnaireId", id)
    location.href = "/pages/questionStatistics/index.html"
}

const handleQuestionnaireClose = (id) => {
    // console.log("点击关闭问卷")
    if (!confirm("您确定要关闭此问卷的发布状态吗？")) return

    let params = {
        id: id,
        status: 1
    }

    $.ajax({
        url: API_BASE_URL + '/setQuestionnaireStatus',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            if (res.code === "666") {
                fetchProjectInfo($util.getPageParam('seeProject'))
                showQuestionnaires($util.getPageParam('seeProject'))
                alert(res.message)
            } else {
                alert(res.message)
            }
        }
    })
}
