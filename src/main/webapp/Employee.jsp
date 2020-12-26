
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Employee Hub</title>
    <meta charset="UTF-8" />
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />

    <!--bootstrap css-->
    <link
            rel="stylesheet"
            href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
            integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
            crossorigin="anonymous"
    />
    <script src="ProjectCompany.js"></script>
</head>
<body>
<!--Navbar-->
<nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
    <h4 style="color: cadetblue">Employee Hub Page</h4>
    <button
            class="navbar-toggler"
            type="button"
            data-toggle="collapse"
            data-target="#navbarNavDropdown"
            aria-controls="navbarNavDropdown"
            aria-expanded="false"
            aria-label="Toggle navigation"
    >
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a
                        class="dropdown-item"
                        data-toggle="modal"
                        data-target="#ReimbursementModal"
                        style="color: cadetblue"
                >Submit Reimbursement</a
                >
            </li>
            <li class="nav-item">
                <a
                        class="dropdown-item"
                        data-toggle="modal"
                        data-target="#UpdateInfoModal"
                        style="color: cadetblue"
                >Update Information</a
                >
            </li>
            <li class="nav-item">
                <form method="post" action="Logout">
                    <input type="submit" value="logout" />
                </form>
            </li>
        </ul>
    </div>
    <div>
        <p id="name" style="color: beige"></p>
        <script>
            getData("http://localhost:8080/Stroud_&_Moore/Welcome", "name");
        </script>
    </div>
</nav>

<!-- Reimbursement Modal -->
<div
        class="modal fade"
        id="ReimbursementModal"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true"
>
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle1">
                    Reimbursement Form
                </h5>
                <button
                        type="button"
                        class="close"
                        data-dismiss="modal"
                        aria-label="Close"
                >
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post" action="ReimbursementSub">
                    <div class="form-group">
                        <label for="amount">Reimbursement Amount</label>
                        <input
                                type="number"
                                id="amount"
                                name="amount"
                                value="0.00"
                                step=".01"
                                required
                        /><br />
                    </div>
                    <div class="form-group">
                        <label for="date">Password</label>
                        <input type="date" id="date" name="date" required /><br />
                    </div>
                    <input type="submit" value="Submit" />
                </form>
            </div>
            <div class="modal-footer">
                <button
                        type="button"
                        class="btn btn-secondary"
                        data-dismiss="modal"
                >
                    Close
                </button>
            </div>
        </div>
    </div>
</div>

<!-- Update Info Modal -->
<div
        class="modal fade"
        id="UpdateInfoModal"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalCenterTitle"
        aria-hidden="true"
>
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle2">
                    Reimbursement Form
                </h5>
                <button
                        type="button"
                        class="close"
                        data-dismiss="modal"
                        aria-label="Close"
                >
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post" action="update">
                    <div class="form-group">
                        <label for="fn">firstName: </label>
                        <input type="text" id="fn" name="fn" value="" required /><br />
                    </div>
                    <div class="form-group">
                        <label for="ln">lastName: </label>
                        <input type="text" id="ln" name="ln" required /><br />
                    </div>
                    <div class="form-group">
                        <label for="pass">password: </label>
                        <input
                                type="password"
                                id="pass"
                                name="pass"
                                required
                                pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                                title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
                        /><br />
                    </div>
                    <div class="form-group">
                        <label for="addr">Current Address: </label>
                        <input type="text" id="addr" name="addr" /><br />
                    </div>
                    <input type="submit" value="Submit" id="event" />
                </form>
            </div>
            <div class="modal-footer">
                <p>
                    <b>***You will have to login again once you click submit***</b>
                </p>
                <button
                        type="button"
                        class="btn btn-secondary"
                        data-dismiss="modal"
                >
                    Close
                </button>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-lg">
            <h3>Your Pending Reimbursements</h3>
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">amount</th>
                    <th scope="col">status</th>
                    <th scope="col">date submitted</th>
                </tr>
                </thead>
                <tbody id="Pending-Req"></tbody>
            </table>
            <script>
                PenRem(
                    "http://localhost:8080/Stroud_&_Moore/pendingReim",
                    "Pending-Req"
                );
            </script>
        </div>
        <div class="col-lg">
            <h3>Your Resolved Reimbursements</h3>
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">amount</th>
                    <th scope="col">status</th>
                    <th scope="col">date submitted</th>
                </tr>
                </thead>
                <tbody id="resolved-Req"></tbody>
            </table>
            <script>
                resRem(
                    "http://localhost:8080/Stroud_&_Moore/resolvedReim",
                    "resolved-Req"
                );
            </script>
        </div>
        <div class="col-lg">
            <div id="personal_info">
                <h3><u>Your Personal Information</u></h3>
                <p id="fname"></p>
                <p id="lname"></p>
                <p id="email"></p>
                <p id="address"></p>
                <p id="dep_id"></p>
                <p id="super_id"></p>
                <script>
                    info("http://localhost:8080/Stroud_&_Moore/info");
                </script>
            </div>
        </div>
    </div>
</div>

<script
        src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"
></script>
<script
        src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"
></script>
<script
        src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"
></script>
</body>
</html>
