onload = () => {
    fetchQuestionnaireList($util.getPageParam("projectId"), '')
}

// 假设这里有一个包含问卷数据的数组
let questionnaireData = [];

const fetchQuestionnaireList = (id, respondent) => {
    let params = {
        projectId: id,
        respondent: respondent
    }

    // let map = new Map([['projectId', id], ['respondent', '']]);
    $.ajax({
        url: API_BASE_URL + '/queryQuestionnaireRespondent',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            if (res.code === "666") {
                questionnaireData = [];
                res.data.map(item => {
                    questionnaireData.push({
                        id: item["id"],
                        name: item["questionnaire_name"],
                        respondent: item["respondent"],
                        time: item["answer_time"]
                    })
                })
                console.log(questionnaireData);
                displayTable();
            } else {
                alert(res.message)
            }
        }
    })
}

let currentPage = 1;
let recordsPerPage = 5;

function displayTable() {
    let table = document.getElementById("dataTable");
    let tbody = table.getElementsByTagName("tbody")[0];

    tbody.innerHTML = "";

    let startIndex = (currentPage - 1) * recordsPerPage;
    let endIndex = startIndex + recordsPerPage;
    let recordsToShow = questionnaireData.slice(startIndex, endIndex);

    // for (let i = 0; i < recordsToShow.length; i++) {
    //     let record = recordsToShow[i];
    //     let row = document.createElement("tr");
    //     row.innerHTML = "<td>" + record.name + "</td><td>" + record.respondent + "</td><td>" + record.time + "</td><td><button>明细</button></td>";
    //
    //     let detailButton = document.createElement("button");
    //     detailButton.onclick = function() {
    //         handleShowDetail(record.id);
    //     };
    //     tbody.appendChild(row);
    // }
    for (let i = 0; i < recordsToShow.length; i++) {
        let record = recordsToShow[i];
        let row = document.createElement("tr");

        let nameCell = document.createElement("td");
        nameCell.textContent = record.name;
        row.appendChild(nameCell);

        let respondentCell = document.createElement("td");
        respondentCell.textContent = record.respondent;
        row.appendChild(respondentCell);

        let timeCell = document.createElement("td");
        timeCell.textContent = record.time;
        row.appendChild(timeCell);

        let buttonCell = document.createElement("td");
        let detailButton = document.createElement("button");
        detailButton.textContent = "明细";
        // 添加点击事件处理程序
        detailButton.onclick = function () {
            handleShowDetail(record.id, record.respondent);
        };
        buttonCell.appendChild(detailButton);
        row.appendChild(buttonCell);

        tbody.appendChild(row);
    }
}

const handleShowDetail = (id,respondent) => {
    console.log("点击明细" + id + "答卷人" + respondent)
    $util.setPageParam("showId", id);
    $util.setPageParam("showRespondent", respondent)
    location.href = "/pages/seeAnswerSheet/index.html"
}

function displayPagination() {
    let pagination = document.getElementById("pagination");
    pagination.innerHTML = "";

    let totalPages = Math.ceil(questionnaireData.length / recordsPerPage);

    for (let i = 1; i <= totalPages; i++) {
        let link = document.createElement("a");
        link.href = "#";
        link.innerHTML = i;
        link.onclick = function () {
            currentPage = parseInt(this.innerHTML);
            displayTable();
            displayPagination();
        };

        if (i === currentPage) {
            link.className = "active";
        }

        pagination.appendChild(link);
    }
}

function search() {
    let searchTerm = document.getElementById("searchInput").value;

    // 这里可以根据搜索条件过滤问卷数据，然后更新questionnaireData数组
    // 为了简单起见，这里假设直接使用原始数据

    currentPage = 1;
    fetchQuestionnaireList($util.getPageParam("projectId"), searchTerm)
    displayPagination();
}

displayTable();
displayPagination();
