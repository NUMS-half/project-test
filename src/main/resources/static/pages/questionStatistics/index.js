let data;
onload = () => {
    let params = {
        id: $util.getPageParam("questionnaireId")
    }
    $.ajax({
        url: API_BASE_URL + '/questionnaireStatistic',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            if (res.code === "666") {
                data = res.data

                const questions = [];

                for (const item of data) {
                    const {
                        questionnaire_name,
                        question_description,
                        id,
                        choose_term,
                        option_count,
                        question_index
                    } = item;

                    const question = questions.find(q => q.question_index === question_index);
                    if (question) {
                        const option = question.options.find(opt => opt.choose_term === choose_term);
                        if (option) {
                            option.option_count += option_count;
                        } else {
                            question.options.push({
                                choose_term,
                                option_count
                            });
                        }
                    } else {
                        questions.push({
                            questionnaire_name,
                            question_description,
                            id,
                            question_index,
                            options: [{
                                choose_term,
                                option_count
                            }]
                        });
                    }
                }
                loadList(questions);
            } else {
                alert(res.message)
            }
        }
    })
}


const loadList = (questions) => {
    $('#questionnaireTitle').text(questions[0].questionnaire_name)

    const questionsStatisticsContainer = document.getElementById('questions-statistics');

    // 清空容器中的内容
    questionsStatisticsContainer.innerHTML = '';

    // 遍历每个题目，生成对应的统计表格
    for (let i = 0; i < questions.length; i++) {
        const question = questions[i];

        // 创建题目统计表格的外层容器
        const questionStatisticsContainer = document.createElement('div');
        questionStatisticsContainer.classList.add('question-statistics');

        // 创建题目统计表格的标题
        const questionTitle = document.createElement('h3');
        questionTitle.textContent = '第' + (i + 1) + '题: ' + question.question_description;

        // 创建题目统计表格
        const questionTable = document.createElement('table');

        // 计算选项的小计和总计
        let optionTotalCount = 0;
        question.options.forEach((option) => {
            optionTotalCount += option.option_count;
        });

        // 生成表格内容
        let tableContent = `
    <tr>
        <th>选项</th>
        <th>小计</th>
        <th>比例</th>
    </tr>
`;
        // 计算总计
        let totalCount = 0;
        // 生成选项行
        question.options.forEach((option) => {
            const optionCount = option.option_count;
            const optionPercentage = ((optionCount / optionTotalCount) * 100).toFixed(2);
            totalCount += optionCount;
            tableContent += `
    <tr>
      <td>${option.choose_term}</td>
      <td class="count">${optionCount} 人次</td>
      <td>${optionPercentage}%</td>
    </tr>
    `;
        });

    // 添加总计行
        tableContent += `
    <tr>
        <td>本题有效填写人数</td>
        <td id="valid-count">${totalCount}人次</td>
        <td></td>
    </tr>
`;
        // 添加表格内容到 questionTable
        questionTable.innerHTML = tableContent;

        // 将题目统计表格的标题和表格添加到题目统计容器中
        questionStatisticsContainer.appendChild(questionTitle);
        questionStatisticsContainer.innerHTML += `
        <div class="chart-menu">
        <button style="color: #0044ee" onclick="generateSimilarQuestionStatistics()">同类问题统计</button>&nbsp;
            <button class="table-btn" onclick="showTable(${i})">表格</button>&nbsp;
            <button class="bar-btn" onclick="showChart(${i})">柱状图</button>
        </div>`
        questionStatisticsContainer.appendChild(questionTable);

        // 将题目统计容器添加到问题统计的容器中
        questionsStatisticsContainer.appendChild(questionStatisticsContainer);

        // 创建柱状图容器
        const chartContainer = document.createElement('div');
        chartContainer.classList.add('chart-container');
        chartContainer.style.display = 'none'; // 初始时隐藏柱状图

        // 将柱状图容器添加到题目统计容器中
        questionStatisticsContainer.appendChild(chartContainer);
        generateChart(question, chartContainer);
    }
}

// 获取选项的总计人数
function getTotalOptionCount(options) {
    let total = 0;
    for (let i = 0; i < options.length; i++) {
        total += options[i].option_count;
    }
    return total;
}

function showTable(index) {
    const questionStatisticsContainers = document.querySelectorAll('.question-statistics');
    const tableContainers = questionStatisticsContainers[index].querySelectorAll('table');
    const chartContainers = questionStatisticsContainers[index].querySelectorAll('.chart-container');

    tableContainers.forEach((container) => {
        container.style.display = 'table';
    });

    chartContainers.forEach((container) => {
        container.style.display = 'none';
    });
}

function showChart(index) {
    const questionStatisticsContainers = document.querySelectorAll('.question-statistics');
    const tableContainers = questionStatisticsContainers[index].querySelectorAll('table');
    const chartContainers = questionStatisticsContainers[index].querySelectorAll('.chart-container');

    tableContainers.forEach((container) => {
        container.style.display = 'none';
    });

    chartContainers.forEach((container) => {
        container.style.display = 'block';
    });
}

function generateChart(question, container) {
    const chartCanvas = document.createElement('canvas');
    container.appendChild(chartCanvas);

    // 获取选项数据
    const optionLabels = question.options.map((option) => option.choose_term);
    const optionCounts = question.options.map((option) => option.option_count);

    // 使用 Chart.js 配置和呈现柱状图
    new Chart(chartCanvas, {
        type: 'bar',
        data: {
            labels: optionLabels,
            datasets: [
                {
                    label: '选项人次',
                    data: optionCounts,
                    backgroundColor: 'rgb(9,142,243)',
                    borderColor: 'rgb(103,185,243)',
                    borderWidth: 1
                }
            ]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                    stepSize: 1
                }
            }
        }
    });
}
