<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; ISO-8859-1; charset=UTF-8" language="java" %>

<html>
<head>
    <title>Registrations</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

    <%--    Tabulator   --%>
    <link href="<c:url value="/resources/tabulator/css/tabulator.min.css" />" rel="stylesheet">
    <script type="text/javascript" src="<c:url value="/resources/tabulator/js/tabulator.min.js" />" ></script>

    <%--    Registrations Scripts --%>
    <link href="<c:url value="/resources/registrations.css" />" rel="stylesheet">
    <script src="<c:url value="/resources/registrations.js" />" type="text/javascript"></script>

</head>
<body>
<p/>
<div>
    <button id="addRegistrationBtn" class="btn btn-success" onclick="addRegistrationBtnClick()">Add Registration</button>
    <div id="addRegistrationModal" class="modal">
        <!-- Modal content -->
        <div class="modal-content">
            <span class="addClose">X</span>
            <form  id="newRegistrationForm">
                <div class="form-group">
                    <label for="addPlate">Number Plate</label>
                    <input type="numberPlate" class="form-control" id="addPlate" placeholder="Number Plate">
                </div>
                <div class="form-group">
                    <label for="addType">Type</label>
                    <input type="vehicle.type" class="form-control" id="addType" placeholder="Type">
                </div>
                <div class="form-group">
                    <label for="addMake">Make</label>
                    <input type="vehicle.make" class="form-control" id="addMake" placeholder="Make">
                </div>
                <div class="form-group">
                    <label for="addModel">Model</label>
                    <input type="vehicle.model" class="form-control" id="addModel" placeholder="Model">
                </div>
                <div class="form-group">
                    <label for="addColour">Colour</label>
                    <input type="vehicle.colour" class="form-control" id="addColour" placeholder="Colour">
                </div>
                <div class="form-group">
                    <label for="addInsurer">Insurer</label>
                    <select class="form-select" id="addInsurer">
                        <script>
                            getInsurers();
                        </script>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>

    </div>
    <button id="editRegistrationBtn" class="btn btn-success" onclick="editRegistrationBtnClick()">Edit Registration</button>
    <div id="editRegistrationModal" class="modal">
        <!-- Modal content -->
        <div class="modal-content">
            <span class="editClose">&times;</span>
            <form  id="editRegistrationForm">
                <div class="form-group">
                    <label for="editPlate">Number Plate</label>
                    <input type="numberPlate" class="form-control" id="editPlate" placeholder="Number Plate">
                </div>
                <div class="form-group">
                    <label for="editType">Type</label>
                    <input type="vehicle.type" class="form-control" id="editType" placeholder="Type">
                </div>
                <div class="form-group">
                    <label for="editMake">Make</label>
                    <input type="vehicle.make" class="form-control" id="editMake" placeholder="Make">
                </div>
                <div class="form-group">
                    <label for="editModel">Model</label>
                    <input type="vehicle.model" class="form-control" id="editModel" placeholder="Model">
                </div>
                <div class="form-group">
                    <label for="editColour">Colour</label>
                    <input type="vehicle.colour" class="form-control" id="editColour" placeholder="Colour">
                </div>
                <div class="form-group">
                    <label for="editInsurer">Insurer</label>
                    <select class="form-select" id="editInsurer">
                        <script>
                            getInsurers();
                        </script>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
    <button id="addInsurerBtn" class="btn btn-success" onclick="addInsurerBtnClick()">Add Insurer</button>
    <div id="addInsurerModal" class="modal">
        <!-- Modal content -->
        <div class="modal-content">
            <span class="addInsurerClose">X</span>
            <form  id="addInsurerForm">
                <div class="form-group">
                    <label for="addInsurerName">Insurer Name</label>
                    <input type="insurerName" class="form-control" id="addInsurerName" placeholder="Insurer Name">
                </div>
                <div class="form-group">
                    <label for="addInsurerCode">Code</label>
                    <input type="insurerCode" class="form-control" id="addInsurerCode" placeholder="Code">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
    <button id="deleteInsurerBtn" class="btn btn-success" onclick="deleteInsurerBtnClick()">Delete Insurer</button>
    <div id="deleteInsurerModal" class="modal">
        <!-- Modal content -->
        <div class="modal-content">
            <span class="deleteInsurerClose">X</span>
            <form  id="deleteInsurerForm">
                <div class="form-group">
                    <label for="deleteInsurer">Insurer</label>
                    <select class="form-select" id="deleteInsurer">
                        <script>
                            getInsurers();
                        </script>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
    <button id="deleteRegistrationBtn" class="btn btn-success">Delete Registration(s)</button>
</div>
<p/>
<div id="example-table"></div>
</body>
</html>