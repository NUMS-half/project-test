onload = () => {
    let params = {
        id: $util.getPageParam("showId"),
        respondent: $util.getPageParam("showRespondent")
    }

    $.ajax({
        url: API_BASE_URL + '/seeAnswerSheet',
        type: "POST",
        data: JSON.stringify(params),
        dataType: "json",
        contentType: "application/json",
        success(res) {
            if (res.code === "666") {
                $('#usernameH3').text("答卷人：" + params.respondent)
                $('#questionnaireTitle').text(res.data["questionnaireName"])
                $('#questionnaireDescription').text(res.data["questionnaireDescription"])
                const problemArray = res.data["questionOptionList"];
                const answerList = res.data["answerList"]

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
              <input disabled type="radio" id="${optionId}" name="chooseTerm${index + 1}">${chooseTerm}
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
              <input disabled type="checkbox" id="${optionId}" name="chooseTerm${index + 1}">${chooseTerm}
            </label>
          </div>
        `;
                        });
                    } else if (type === 3) {
                        problemHTML += `
        <textarea disabled class="form-control" placeholder="请输入" rows="4" style="width: 70%;"></textarea>
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
                                problemHTML += `<td><input disabled type="radio" id="${optionId}" name="chooseTerm${index + 1}_${rowIndex + 1}" /></td>`;
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
              <input disabled type="radio" id="${optionId}" name="fraction${index + 1}" />${fraction}
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

                answerList.forEach(answer => {
                    const {questionnaireId, questionId, optionId, fillContent, leftTitle, respondent} = answer;
                    const questionElement = document.getElementById(questionId);

                    if (!questionElement) return;

                    const type = parseInt(questionElement.getAttribute("data-type"));

                    const optionElement = questionElement.querySelector(`input[id="${optionId}"]`);
                    if (optionElement) {
                        optionElement.checked = true;
                        optionElement.disabled = true;
                    }

                    if (type === 3) {
                        const textareaElement = questionElement.querySelector("textarea");
                        if (textareaElement) {
                            textareaElement.value = fillContent;
                            textareaElement.disabled = true;
                        }
                    }

                    if (type === 4) {
                        const leftTitleIndex = leftTitle ? parseInt(leftTitle) - 1 : -1;
                        const radioInputs = questionElement.querySelectorAll("input[type='radio']");
                        if (leftTitleIndex >= 0 && radioInputs.length > leftTitleIndex) {
                            const radioElement = radioInputs[leftTitleIndex];
                            radioElement.checked = true;
                            radioElement.disabled = true;
                        }
                    }

                    if (type === 5) {
                        const scaleRadioElement = questionElement.querySelector(`input[id="${optionId}"]`);
                        if (scaleRadioElement) {
                            scaleRadioElement.checked = true;
                            scaleRadioElement.disabled = true;
                        }
                    }
                });
            } else if (confirm(res.message)) {
                history.back();
            }
        }
    })
}