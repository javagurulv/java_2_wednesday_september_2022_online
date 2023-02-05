function getTasks(event) {
    event.preventDefault();

    document.getElementById("msg-error").style.display = "none";

    const searchTaskForm = document.getElementById("taskQueryForm");
         const FD = new FormData(searchTaskForm);
         const baseUrl = "http://localhost:8080/api/searchTasks";

         const customUrl = baseUrl + "/" + FD.get("searchPhrase");
         const viewParameters = {orderBy:FD.get("orderBy"), ordering:FD.get("orderDirection"),
                                 pageNumber:FD.get("pageNumber"), pageSize:FD.get("recordOnPage")};

    sendTaskData(customUrl, viewParameters).then((dataObj) => {
        _data = dataObj.data;
        _hasErrors = dataObj.hasErrors;
        let text = "";
        if (_hasErrors == false) {
            console.log(_data.message);
            _data.forEach(task => {
                text += "<tr>";
                text += "<td>"
                text += task.description;
                text += "</td>";
                text += "<td>"
                text += task.regularity;
                text += "</td>";
                text += "<td>"
                text += task.dueDate;
                text += "</td>";
                text += "<td>"
                text += task.endDate;
                text += "</td>";
                text += "</tr>";
            })
            document.getElementById("tasksListTBody").innerHTML =  text;
        }
        else {
            _data.forEach(error => {
                console.log(error.error + ": " + error.msg);
                text += error.error + ": " + error.msg + "<br>";
                })
            document.getElementById("msg-error").style.display = "initial";
            document.getElementById("msg-error").innerHTML =  text;
        }
    })
}

function addTask(event) {
     event.preventDefault();
     document.getElementById("msg-error").style.display = "none";
     document.getElementById("msg-succeed").style.display = "none";

     const newTaskForm = document.getElementById("newTaskInfo");
     const FD = new FormData(newTaskForm);
     const baseUrl = "http://localhost:8080/api/addTask";
     const taskInfo = {taskDescription:FD.get("taskDescription"), regularity:FD.get("regularity"),
                             dueDate:FD.get("dueDate"), endDate:FD.get("endDate"),
                             dueTime:FD.get("dueTime"), endTime:FD.get("endTime")};

     sendTaskData(baseUrl, taskInfo).then((dataObj) => {
             _data = dataObj.data;
             _hasErrors = dataObj.hasErrors;
             let text = "";
             if (_hasErrors == false) {
                 console.log(_data.message);
                 _data.forEach(msg => {
                     text += msg.message;
                 })
                 document.getElementById("msg-succeed").style.display = "initial";
                 document.getElementById("msg-succeed").innerHTML =  text;
             }
             else {
                 _data.forEach(error => {
                     console.log(error.error + ": " + error.msg);
                     text += error.error + ": " + error.msg + "<br>";
                 })
                 document.getElementById("msg-error").style.display = "initial";
                 document.getElementById("msg-error").innerHTML =  text;
             }
         })
}

async function sendTaskData(url, dataToSend) {
    const response = await fetch(url, {method:"POST",
        							            headers:{"Content-Type":"application/json",
        							                            "Accept":"application/json"},
        							            body:JSON.stringify(dataToSend)});
    let data, hasErrors;
    if (response.status == 200) {
        data = await response.json();
      	hasErrors = false
    }
    else if (response.status == 400) {
        data = await response.json()
        	hasErrors = true;
    }

    return {data, hasErrors};
}

function registerUser(event) {
    event.preventDefault();
    document.getElementById("msg-error").style.display = "none";
    document.getElementById("msg-succeed").style.display = "none";


    send().then((dataObj) => {
        _data = dataObj.data;
        _hasErrors = dataObj.hasErrors;
        let text = "";
        if (_hasErrors == false) {
            console.log(_data.message);
            _data.forEach(msg => {
                text += msg.message;
            })
            document.getElementById("msg-succeed").style.display = "initial";
            document.getElementById("msg-succeed").innerHTML =  text;
        }
        else {
            _data.forEach(error => {
                console.log(error.error + ": " + error.msg);
                text += error.error + ": " + error.msg + "<br>";
            })
            document.getElementById("msg-error").style.display = "initial";
            document.getElementById("msg-error").innerHTML =  text;
        }
    })
}

async function send() {
    const userRegistrationForm = document.getElementById("userInfoInput");
    const FD = new FormData(userRegistrationForm);

    const baseUrl = "http://localhost:8080/api/registerUser";

    const userInfo = {username:FD.get("username"), userPassword:FD.get("userPassword"),
                        userEmail:FD.get("userEmail"), sendReminders:FD.get("sendReminders")};

    const response = await fetch(baseUrl, {method:"POST",
    							            headers:{"Content-Type":"application/json",
    							                            "Accept":"application/json"},
    							            body:JSON.stringify(userInfo)});
    let data, hasErrors;
    if (response.status == 200) {
        data = await response.json();
    	hasErrors = false
    }
    else if (response.status == 400) {
        data = await response.json()
    	hasErrors = true;
    }

    return {data, hasErrors};
}

function getUsersNames (event) {
		event.preventDefault();
		get().then((dataObj) => {
			_data = dataObj.data;
			_hasErrors = dataObj.hasErrors;

			let text = "";
			if (_hasErrors == false) {
				document.getElementById("msg-error").style.display = "none";
				_data.forEach(user => {
				console.log(user.username);
				text += "<li>" + user.username + "</li>";
				})
			document.getElementById("userNamesList").innerHTML =  text;
			}
			else if (_hasErrors == true) {
				document.getElementById("msg-error").style.display = "initial";
				_data.forEach(error => {
				console.log(error.error + ": " + error.msg);
				text += error.error + ": " + error.msg + "<br>";
				})
			document.getElementById("msg-error").innerHTML =  text;
			}
		})
	}
async function get() {
    const userNameQueryForm = document.getElementById("userQueryForm");
	const FD = new FormData(userNameQueryForm);

    const baseUrl = `http://localhost:8080/api/getUsersNames`;
	let namedUrl = baseUrl+"/"+FD.get("username");
	const viewParameters = {ordering:FD.get("orderDirection"), pageNumber:FD.get("pageNumber"),
	                                    pageSize:FD.get("recordOnPage")};

	const response = await fetch(namedUrl, {method:"POST",
							headers:{"Content-Type":"application/json", "Accept":"application/json"},
							body:JSON.stringify(viewParameters)})
	let data, hasErrors;
	if (response.status == 200) {
		data = await response.json();
		hasErrors = false
	}
	else if (response.status == 400) {
		data = await response.json()
		hasErrors = true;
	}

	return {data, hasErrors};
}
