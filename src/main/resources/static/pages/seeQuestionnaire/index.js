// 假设这里有一个包含问卷数据的数组
var questionnaireData = [
    { name: "问卷1", respondent: "答卷人1", time: "2023-06-01" },
    { name: "问卷2", respondent: "答卷人2", time: "2023-06-02" },
    { name: "问卷3", respondent: "答卷人3", time: "2023-06-03" },
    // 这里可以添加更多数据
];

var currentPage = 1;
var recordsPerPage = 5;

function displayTable() {
    var table = document.getElementById("dataTable");
    var tbody = table.getElementsByTagName("tbody")[0];
    tbody.innerHTML = "";

    var startIndex = (currentPage - 1) * recordsPerPage;
    var endIndex = startIndex + recordsPerPage;
    var recordsToShow = questionnaireData.slice(startIndex, endIndex);

    for (var i = 0; i < recordsToShow.length; i++) {
        var record = recordsToShow[i];
        var row = document.createElement("tr");
        row.innerHTML = "<td>" + record.name + "</td><td>" + record.respondent + "</td><td>" + record.time + "</td><td><button>明细</button></td>";
        tbody.appendChild(row);
    }
}

function displayPagination() {
    var pagination = document.getElementById("pagination");
    pagination.innerHTML = "";

    var totalPages = Math.ceil(questionnaireData.length / recordsPerPage);

    for (var i = 1; i <= totalPages; i++) {
        var link = document.createElement("a");
        link.href = "#";
        link.innerHTML = i;
        link.onclick = function() {
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
    var searchTerm = document.getElementById("searchInput").value;

    // 这里可以根据搜索条件过滤问卷数据，然后更新questionnaireData数组
    // 为了简单起见，这里假设直接使用原始数据

    currentPage = 1;
    displayTable();
    displayPagination();
}

displayTable();
displayPagination();
