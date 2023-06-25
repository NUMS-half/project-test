onload = () => {
    $('#usernameMarquee').text("当前问卷作答人：" + $util.getPageParam("username"))
    $('#questionnaireTitle').text($util.getPageParam("previewTitle"))
    $('#questionnaireDescription').text($util.getPageParam("previewDescription"))
    const problemArray = $util.getPageParam("problems");

    problemArray.forEach((problem, index) => {
        const questionTitle = problem.problemName;
        const mustAnswer = problem.mustAnswer;
        const type = problem.type;

        let problemHTML = `
      <div class="question" id="question${index + 1}" data-type="${type}" data-problemIndex="${index + 1}">
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
                problemHTML += `
          <div style="display: flex; align-items: center; margin-bottom: 3px;">
            <label class="radio-inline">
              <input type="radio" name="chooseTerm${index + 1}">${chooseTerm}
            </label>
          </div>
        `;
            });
        } else if (type === 2) {
            const options = problem.option;

            options.forEach((option) => {
                const chooseTerm = option["choose_term"];
                problemHTML += `
          <div style="display: flex; align-items: center; margin-bottom: 3px;">
            <label class="checkbox-inline">
              <input type="checkbox" name="chooseTerm${index + 1}">${chooseTerm}
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
            <td>${title}</td>
        `;

                options.forEach(() => {
                    problemHTML += `<td><input type="radio" name="chooseTerm${index + 1}_${rowIndex + 1}" /></td>`;
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
                problemHTML += `
          <div>${option["choose_term"]}</div>
          <div>
            <label class="radio-inline">
              <input type="radio" name="fraction${index + 1}" />${fraction}
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
    alert("提交成功！")
    window.close();
}

