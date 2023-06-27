// 假设您已经有一个名为questions的题目数据数组
const questions = [
    "问题1",
    "问题2",
    "问题3"
];

// 获取问题统计的容器
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
    const questionTitle = document.createElement('h2');
    questionTitle.textContent = '第' + (i + 1) + '题: ' + question;

    // 创建题目统计表格
    const questionTable = document.createElement('table');
    questionTable.innerHTML = `
    <tr>
      <th>选项</th>
      <th>小计</th>
      <th>比例</th>
    </tr>
    <tr>
      <td>选项1</td>
      <td class="count">1507 人次</td>
      <td>15.07%</td>
    </tr>
    <tr>
      <td>选项2</td>
      <td class="count">6895 人次</td>
      <td>68.95%</td>
    </tr>
    <tr>
      <td>选项3</td>
      <td class="count">1598 人次</td>
      <td>15.98%</td>
    </tr>
    <tr>
      <td>本题有效填写人数</td>
      <td id="valid-count" colspan="2"></td>
    </tr>
  `;

    // 将题目统计表格的标题和表格添加到题目统计容器中
    questionStatisticsContainer.appendChild(questionTitle);
    questionStatisticsContainer.appendChild(questionTable);

    // 将题目统计容器添加到问题统计的容器中
    questionsStatisticsContainer.appendChild(questionStatisticsContainer);
}
