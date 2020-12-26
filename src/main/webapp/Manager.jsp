<%--
  Created by IntelliJ IDEA.
  User: thoma
  Date: 12/3/2020
  Time: 5:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Manager Hub</title>
    <meta charset="UTF-8" />
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <script src="ProjectCompany.js"></script>
    <!--bootstrap css-->
    <link
            rel="stylesheet"
            href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
            integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
            crossorigin="anonymous"
    />

    <style>
        col{
            margin-top: 15px;
        }
        .nav-item{
            padding: 5px;
        }
    </style>
</head>
<body>

<!--Navbar-->
<nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
    <h4 style="color: cadetblue">Manager Hub Page</h4>
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
                <button class="nav-link" type="button" onclick="allEmpReq('allEmpReq')" style="background: cadetblue; color: white">Get Employee's Reimbursement Records</button>
            </li>
            <li class="nav-item">
                <button
                        type="button"
                        class="dropdown-item"
                        data-toggle="modal"
                        data-target="#ReimbursementModal"
                        style="background: cadetblue;color: white;"
                >Approve or Deny Reimbursement Request</button
                >
            </li>
            <li class="nav-item">
                <form method="post" action="Logout" >
                    <input type="submit" value="logout" style="background: cadetblue; color: white;" />
                </form>
            </li>
        </ul>
    </div>
    <div>
        <p id="name" style="color: beige"></p>
        <script>getData("http://localhost:8080/Stroud_&_Moore/Welcome", "name");</script>
    </div>
</nav>

<div class="container">
    <div class="row row-cols-1">
        <div class="col">
            <h3>All Pending Reimbursements</h3>
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">amount</th>
                    <th scope="col">status</th>
                    <th scope="col">date submitted</th>
                </tr>
                </thead>
                <tbody id="allPending-Req">
                </tbody>
            </table>
            <script>allPenRem("http://localhost:8080/Stroud_&_Moore/allpending", "allPending-Req")</script>
        </div>
        <div class="w-100"></div>
        <div class="col">
            <h3>All Resolved Reimbursements</h3>
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">amount</th>
                    <th scope="col">status</th>
                    <th scope="col">date submitted</th>
                    <th scope="col">reviewed by</th>
                </tr>
                </thead>
                <tbody id="allResolved-Req">
                </tbody>
            </table>
            <script>
                allResRem("http://localhost:8080/Stroud_&_Moore/allresolved", "allResolved-Req")</script>
        </div>
        <div class="w-100"></div>
        <div class="col">
            <h3>All Employees</h3>
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">id</th>
                    <th scope="col" >fname</th>
                    <th scope="col" >lname</th>
                    <th scope="col" >email</th>
                    <th scope="col" >address</th>
                    <th scope="col" >dep_id</th>
                    <th scope="col" >super_id</th>
                </tr>
                </thead>
                <tbody id="allEmps">
                </tbody>
            </table>
            <script>allEmployees("http://localhost:8080/Stroud_&_Moore/allEmp","allEmps")</script>
        </div>
        <div class="w-100"></div>
        <div class="col">
            <h3 id="table4">Employee's Reimbursements</h3>
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">amount</th>
                    <th scope="col">status</th>
                    <th scope="col">date submitted</th>
                    <th scope="col">reviewed by</th>
                </tr>
                </thead>
                <tbody id="allEmpReq">
                </tbody>
            </table>
        </div>
    </div>
</div>

<!-- approve/deny reimbursement Modal -->
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
                <form method="post" action="aprdnyrem">
                    <div class="form-group">
                        <input
                                type="number"
                                id="id1"
                                name="id"
                                value="0"
                                required
                        /><br />
                        <label for="id1">Form ID</label>
                    </div>
                    <div class="form-group">
                        <input type="radio" id="status1" name="status" value="approved"/>
                        <label for="status1">Approve</label><br/>
                        <input type="radio" id="status2" name="status" value="denied" required/>
                        <label for="status2">Deny</label>
                    </div>
                    <input type="submit" value="Submit" />
                    <input type="reset" value="Reset"/>
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