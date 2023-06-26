let answers = [];

onload = () => {
    $('#usernameMarquee').text("当前问卷作答人：" + $util.getPageParam("username"))
    $('#questionnaireTitle').text($util.getPageParam("previewTitle"))
    $('#questionnaireDescription').text($util.getPageParam("previewDescription"))
    const problemArray = $util.getPageParam("problems");

    problemArray.forEach((problem, index) => {
        const questionTitle = problem.problemName;
        const mustAnswer = problem.mustAnswer;
        const type = problem.type;
        const questionId = problem.questionId;

        let problemHTML = `
      <div class="question" id="${questionId}" data-type="${type}" data-problemIndex="${index + 1}">
        <div class="top">
          <span class="question-title">${index + 1}.${questionTitle}</span>
          <span class="must-answer">${mustAnswer ? '必答题' : '非必答题'}</span>
        </div>
        <div class="bottom">
    `;

        if (type === 1) {
            const options = problem.option;

            options.forEach((option) => {
                const chooseTerm = option["choose_term"];
                const optionId = option["id"];
                problemHTML += `
          <div style="display: flex; align-items: center; margin-bottom: 3px;">
            <label class="radio-inline">
              <input type="radio" id="${optionId}" name="chooseTerm${index + 1}">${chooseTerm}
            </label>
          </div>
        `;
            });
        } else if (type === 2) {
            const options = problem.option;

            options.forEach((option) => {
                const chooseTerm = option["choose_term"];
                const optionId = option["id"];
                problemHTML += `
          <div style="display: flex; align-items: center; margin-bottom: 3px;">
            <label class="checkbox-inline">
              <input type="checkbox" id="${optionId}" name="chooseTerm${index + 1}">${chooseTerm}
            </label>
          </div>
        `;
            });
        } else if (type === 3) {
            problemHTML += `
        <textarea class="form-control" placeholder="请输入" rows="4" style="width: 70%;"></textarea>
      `;
        } else if (type === 4) {
            const leftTitle = problem.leftTitle ? problem.leftTitle.split(',') : [];
            const options = problem.option;

            problemHTML += `
        <table class="table">
          <thead>
            <tr>
              <th></th>
      `;

            options.forEach((option) => {
                const chooseTerm = option["choose_term"];
                problemHTML += `<th>${chooseTerm}</th>`;
            });

            problemHTML += `
            </tr>
          </thead>
          <tbody>
      `;

            leftTitle.forEach((title, rowIndex) => {
                problemHTML += `
          <tr>
            <td class="left-title">${title}</td>
        `;

                options.forEach((option) => {
                    const optionId = option["id"];
                    problemHTML += `<td><input type="radio" id="${optionId}" name="chooseTerm${index + 1}_${rowIndex + 1}" /></td>`;
                });

                problemHTML += `
          </tr>
        `;
            });

            problemHTML += `
          </tbody>
        </table>
      `;
        } else if (type === 5) {
            problemHTML += `
        <div style="display: flex; align-items: center; justify-content: space-between;">
      `;

            const options = problem.option;
            options.forEach((option) => {
                const fraction = option["fraction"];
                const optionId = option["id"];
                problemHTML += `
          <div>${option["choose_term"]}</div>
          <div>
            <label class="radio-inline">
              <input type="radio" id="${optionId}" name="fraction${index + 1}" />${fraction}
            </label>
          </div>
        `;
            });

            problemHTML += `
        </div>
      `;
        }
        problemHTML += `
        </div>
      </div>
    `;
        $('#problem').append(problemHTML);
    });
}

const handleRealCommit = () => {
    if(!confirm("您确定要提交问卷吗？提交后不可修改作答！")) return;

    let answers = []; // 存储回答的数组

    // 获取问题元素
    let questions = document.getElementsByClassName('question');

    // 遍历每个问题
    for (let i = 0; i < questions.length; i++) {
        let question = questions[i];
        let type = question.getAttribute('data-type'); // 获取问题类型
        let questionId = question.getAttribute('id'); // 获取问题ID

        // 根据问题类型处理回答
        let answer = {};
        answer.questionnaireId = $util.getPageParam("questionnaireId");
        answer.type = type;
        answer.questionId = questionId;
        answer.respondent = $util.getPageParam("username");

        if (type === '1') { // 单选题
            let selectedOption = question.querySelector('input[type="radio"]:checked');
            if (selectedOption) {
                answer.optionId = selectedOption.getAttribute('id');
            }
        } else if (type === '2') { // 多选题
            let selectedOptions = question.querySelectorAll('input[type="checkbox"]:checked');
            let optionIds = [];
            for (let j = 0; j < selectedOptions.length; j++) {
                optionIds.push(selectedOptions[j].getAttribute('id'));
            }
            answer.optionId = optionIds;
        } else if (type === '3') { // 填空题
            answer.fillContent = question.querySelector('textarea').value;
        } else if (type === '4') { // 矩阵题
            let matrixOptions = question.querySelectorAll('input[type="radio"]:checked');
            let optionIds = [];
            for (let j = 0; j < matrixOptions.length; j++) {
                let matrixOption = matrixOptions[j];
                let optionId = matrixOption.getAttribute('id');
                let leftTitle = matrixOption.closest('tr').querySelector('td:first-child').textContent; // 获取左标题内容
                optionIds.push({ id: optionId, leftTitle: leftTitle });
            }
            answer.optionId = optionIds;

             // 获取左标题内容
            // answer.leftTitle = question.querySelector('.left-title').textContent;
        } else if (type === '5') { // 量表题
            let scaleOption = question.querySelector('input[type="radio"]:checked');
            if (scaleOption) {
                answer.optionId = scaleOption.getAttribute('id');
            }
        }

        answers.push(answer);
    }

    console.log(answers);

    $.ajax({
        url: API_BASE_URL + '/saveCommitAnswer',
        type: "POST",
        data: JSON.stringify(answers),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            if (res.code === "666") {
                alert("提交成功，答题界面即将关闭！")
                window.close();
            } else {
                alert(res.message)
                window.close();
            }
        }
    })
}

