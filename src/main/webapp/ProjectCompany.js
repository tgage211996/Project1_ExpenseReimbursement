async function getData(url, id) {
    let response = await fetch(url);
    console.log(response.status);
    console.log(response.statusText);
    document.getElementById(id).innerHTML = "Hello, " + (await response.text());
}

async function resRem(url, id) {
    fetch(url)
        .then((res) => res.json())
        .then((js) => {
            console.log(js);
            for (let i = 0; i <= js.length; i++) {
                let row = `<tr>
                                <th scope="row">${js[i].id}</th>
                                <td>${js[i].amount}</td>
                                <td>${js[i].status}</td>
                                <td>${js[i].date}</td>
                          </tr>`;
                document.getElementById(id).innerHTML += row;
            }
        });
}

async function PenRem(url, id) {
    fetch(url)
        .then((res) => res.json())
        .then((js) => {
            console.log(js);

            for (let i = 0; i <= js.length; i++) {
                let row = `<tr>
                                <th scope="row">${js[i].id}</th>
                                <td>${js[i].amount}</td>
                                <td>${js[i].status}</td>
                                <td>${js[i].date}</td>
                          </tr>`;
                document.getElementById(id).innerHTML += row;
            }
        });
}

async function info(url) {
    fetch(url)
        .then((res) => res.json())
        .then((js) => {
            console.log(js);
            document.getElementById("fname").innerText = "Firstname: " + js.fname;
            document.getElementById("lname").innerText = "Lastname: " + js.lname;
            document.getElementById("email").innerText = "Email: " + js.email;
            document.getElementById("address").innerText = "Address: " + js.address;
            document.getElementById("dep_id").innerText =
                "Department Id: " + js.dep_id;
            document.getElementById("super_id").innerText =
                "Supervisor Id: " + js.super_Id;
        });
}

async function allPenRem(url, id) {
    fetch(url)
        .then((res) => res.json())
        .then((js) => {
            console.log(js);

            for (let i = 0; i <= js.length; i++) {
                let row = `<tr>
                                <th scope="row">${js[i].id}</th>
                                <td>${js[i].amount}</td>
                                <td>${js[i].status}</td>
                                <td>${js[i].date}</td>
                          </tr>`;
                document.getElementById(id).innerHTML += row;
            }
        });
}

async function allResRem(url, id) {
    fetch(url)
        .then((res) => res.json())
        .then((js) => {
            console.log(js);

            for (let i = 0; i <= js.length; i++) {
                let row = `<tr>
                                <th scope="row">${js[i].id}</th>
                                <td>${js[i].amount}</td>
                                <td>${js[i].status}</td>
                                <td>${js[i].date}</td>
                                <td>${js[i].reviewedby}</td>
                          </tr>`;
                document.getElementById(id).innerHTML += row;
            }
        });
}

async function allEmployees(url, id) {
    fetch(url)
        .then((res) => res.json())
        .then((js) => {
            console.log(js);

            for (let i = 0; i < js.length; i++) {
                let row = `<tr>
                                <th scope="row">${js[i].id}</th>
                                <td>${js[i].firstName}</td>
                                <td>${js[i].lastName}</td>
                                <td>${js[i].email}</td>
                                <td>${js[i].address}</td>
                                <td>${js[i].departmentId}</td>
                                <td>${js[i].supervisorId}</td>
                          </tr>`;
                document.getElementById(id).innerHTML += row;
            }
        });
}

async function allEmpReq(id) {
    let email = window.prompt(
        "Enter the employee's email to see all their reimbursements: "
    );
    document.getElementById('table4').innerHTML = email +"'s Reimbursement requests";
    let url = "http://localhost:8080/Stroud_&_Moore/aer?";
    fetch(url + new URLSearchParams({ email: email }))
        .then((res) => res.json())
        .then((js) => {
            if (js.length !== 0) {
                console.log(js);
                document.getElementById(id).innerHTML = "";
                for (let i = 0; i <= js.length; i++) {
                    let row = `<tr>
                                <th scope="row">${js[i].id}</th>
                                <td>${js[i].amount}</td>
                                <td>${js[i].status}</td>
                                <td>${js[i].date}</td>
                                <td>${js[i].reviewedby}</td>
                          </tr>`;
                    document.getElementById(id).innerHTML += row;
                }
            } else {
                window.alert("No employee by that email");
            }
        });
}
