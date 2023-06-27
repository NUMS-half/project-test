onload = () => {
    $('#headerDivB').text('分类统计')

    const data = $util.getPageParam("answerInfo")
    $('#questionTitle').text("题目名称：" + data[0].question_description)

    // 合并统计数据的对象
    const mergedData = {};

    // 遍历原始数据进行合并统计
    data.forEach(item => {
        const chooseTerm = item.choose_term;
        if (mergedData[chooseTerm]) {
            // 已存在相同choose_term的项，累加option_count
            mergedData[chooseTerm].option_count += item.option_count;
        } else {
            // 新的choose_term项，直接赋值
            mergedData[chooseTerm] = { ...item };
        }
    });

    // 将合并后的数据转为数组形式
    const mergedArray = Object.values(mergedData);

    // 按照choose_term的取值排序合并后的数据
    mergedArray.sort((a, b) => {
        const chooseTermA = a.choose_term.toLowerCase();
        const chooseTermB = b.choose_term.toLowerCase();
        if (chooseTermA < chooseTermB) return -1;
        if (chooseTermA > chooseTermB) return 1;
        return 0;
    });

    // 计算总回答数
    let totalResponses = 0;
    mergedArray.forEach(item => {
        totalResponses += item.option_count;
    });

    // 生成表格内容
    let tableHTML = '';
    mergedArray.forEach(item => {
        const optionCount = item.option_count;
        const proportion = ((optionCount / totalResponses) * 100).toFixed(2);

        tableHTML += `
    <tr>
      <td>${item.choose_term}</td>
      <td>${optionCount}</td>
      <td>${proportion}%</td>
    </tr>
  `;
    });

    // 在表格的最后一行显示总回答数
    tableHTML += `
  <tr>
    <td>本题有效填写人次</td>
    <td>${totalResponses}</td>
    <td></td>
  </tr>
`;

    // 将表格内容插入到页面中的<table>元素中
    const tableElement = document.querySelector('.question-statistics table');
    tableElement.innerHTML += tableHTML;

    function generateChart(chartType) {
        const ctx = document.getElementById('chartCanvas').getContext('2d');

        // 销毁之前的图表实例
        if (window.chartInstance) {
            window.chartInstance.destroy();
        }

        let chart;

        switch (chartType) {
            case 'table':
                // 显示表格
                document.querySelector('.question-statistics').style.display = 'block';
                document.querySelector('.chart-container').style.display = 'none';
                break;

            case 'pie':
                // 生成饼状图
                document.querySelector('.question-statistics').style.display = 'none';
                document.querySelector('.chart-container').style.display = 'block';
                chart = new Chart(ctx, {
                    type: 'pie',
                    data: {
                        labels: mergedArray.map(item => item.choose_term),
                        datasets: [
                            {
                                label: '选项人次',
                                data: mergedArray.map(item => item.option_count),
                                backgroundColor: [
                                    '#FF6384',
                                    '#36A2EB',
                                    '#FFCE56',
                                    '#4BC0C0',
                                    '#9966FF',
                                    '#FF9F40'
                                ]
                            }
                        ]
                    },
                    options: {
                        plugins: {
                            legend: {
                                position: 'center'
                            },
                            tooltip: {
                                callbacks: {
                                    label: function (context) {
                                        const label = context.label || '';
                                        const value = context.parsed || 0;
                                        const dataset = context.dataset || {};
                                        const total = dataset.data.reduce((accumulator, currentValue) => accumulator + currentValue, 0);
                                        const percentage = value / total * 100;
                                        return label + ': ' + percentage.toFixed(2) + '%';
                                    }
                                }
                            }
                        }
                    }
                });
                break;

            case 'doughnut':
                // 生成圆环图
                document.querySelector('.question-statistics').style.display = 'none';
                document.querySelector('.chart-container').style.display = 'block';
                chart = new Chart(ctx, {
                    type: 'doughnut',
                    data: {
                        labels: mergedArray.map(item => item.choose_term),
                        datasets: [
                            {
                                label: '选项人次',
                                data: mergedArray.map(item => item.option_count),
                                backgroundColor: [
                                    '#FF6384',
                                    '#36A2EB',
                                    '#FFCE56',
                                    '#4BC0C0',
                                    '#9966FF',
                                    '#FF9F40'
                                ]
                            }
                        ]
                    },
                    options: {
                        plugins: {
                            legend: {
                                position: 'center'
                            },
                            tooltip: {
                                callbacks: {
                                    label: function (context) {
                                        const label = context.label || '';
                                        const value = context.parsed || 0;
                                        const dataset = context.dataset || {};
                                        const total = dataset.data.reduce((accumulator, currentValue) => accumulator + currentValue, 0);
                                        const percentage = value / total * 100;
                                        return label + ': ' + percentage.toFixed(2) + '%';
                                    }
                                }
                            }
                        }
                    }
                });
                break;

            case 'bar':
                // 生成柱状图
                document.querySelector('.question-statistics').style.display = 'none';
                document.querySelector('.chart-container').style.display = 'block';
                chart = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: mergedArray.map(item => item.choose_term),
                        datasets: [
                            {
                                label: '选项人次',
                                data: mergedArray.map(item => item.option_count),
                                backgroundColor: '#36A2EB'
                            }
                        ]
                    },
                    options: {
                        legend: {
                            display: false
                        }
                    }
                });
                break;

            case 'line':
                // 生成折线图
                document.querySelector('.question-statistics').style.display = 'none';
                document.querySelector('.chart-container').style.display = 'block';
                chart = new Chart(ctx, {
                    type: 'line',
                    data: {
                        labels: mergedArray.map(item => item.choose_term),
                        datasets: [
                            {
                                label: '选项人次',
                                data: mergedArray.map(item => item.option_count),
                                borderColor: '#4BC0C0',
                                fill: false
                            }
                        ]
                    },
                    options: {
                        legend: {
                            display: false
                        }
                    }
                });
                break;
        }

        // 保存新的图表实例
        window.chartInstance = chart;

        return chart;
    }

    // 默认显示为表格
    generateChart('table');

    // 监听radio按钮的change事件
    const chartTypeRadios = document.getElementsByName('chartType');
    chartTypeRadios.forEach(radio => {
        radio.addEventListener('change', event => {
            const selectedChartType = event.target.value;
            generateChart(selectedChartType);
        });
    });
}
