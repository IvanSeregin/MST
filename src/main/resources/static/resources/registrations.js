var registrationService = "registration";
var insurerService = "insurer";
var table = null;

window.addEventListener("load", function() {

    table = new Tabulator("#example-table", {
        ajaxURL:registrationService + "/getAll",
        ajaxContentType:"json",
        ajaxConfig:"GET",
        height:"311px",
        columns:[
            {
                title:"Registration List",
                columns:[
                    {formatter:"rowSelection", titleFormatter:"rowSelection", hozAlign:"center", headerSort:false, cellClick:function(e, cell){
                            cell.getRow().toggleSelect();
                        }},
                    {title: "ID", field: "id", width: "50"},
                    {title: "Number Plate", field: "numberPlate", sorter: "alphanum", width: "150"},
                    {title: "Insurer Name", field: "insurer.name", width: "200"},
                    {
                        title: "Vehicle",
                        columns:[
                            {title: "Type", field: "vehicle.type", width: "200"},
                            {title: "Make", field: "vehicle.make", width: "200"},
                            {title: "Model", field: "vehicle.model", width: "200"},
                            {title: "Colour", field: "vehicle.colour", width: "200"},
                        ]
                    },
                ]
            }
        ],
        cellEdited:function(cell){
            var row = cell.getRow();
            var data = row.getData();
            data = JSON.stringify(data);
            $.ajax({
                type: 'PUT',
                url: registrationService + "/update",
                contentType: 'application/json; charset=utf-8',
                data: data,
                async: false,
                success: function (result) {
                    alert("Registration was saved in database");
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("Registration was not saved in database" + jqXHR + textStatus + errorThrown);
                    $('#response').html(JSON.stringify(jqXHR));
                }
            });
        },
    });

    document.getElementById('newRegistrationForm').addEventListener('submit', addRegistrationSubmitClick);
    document.getElementById('editRegistrationForm').addEventListener('submit', editRegistrationSubmitClick);
    document.getElementById("addRegistrationBtn").addEventListener("click", addRegistrationBtnClick);
    document.getElementById("deleteRegistrationBtn").addEventListener("click", deleteRegistrationBtnClick);
    document.getElementById("addInsurerBtn").addEventListener("click", addInsurerBtnClick);
    document.getElementById('addInsurerForm').addEventListener('submit', addInsurerSubmitClick);
    document.getElementById("deleteInsurerBtn").addEventListener("click", deleteInsurerBtnClick);
    document.getElementById('deleteInsurerForm').addEventListener('submit', deleteInsurerSubmitClick);
 },false);


function deleteRegistrationBtnClick() {
    var selectedRows = [];
    selectedRows = table.getSelectedRows();
    var selectedRowsURL = "";

    for (var i = 0; i < selectedRows.length - 1; i++){
        selectedRowsURL += selectedRows[i].getIndex() + ",";
    }
    selectedRowsURL += selectedRows[selectedRows.length - 1].getIndex();

    $.ajax({
        type: 'DELETE',
        url: registrationService + "/delete/" + selectedRowsURL,
        contentType: 'application/json; charset=utf-8',
        async: false,
        success: function (result) {
            alert("Registration(s) deleted from database");
            for (var i = 0; i < selectedRows.length; i++){
                selectedRows[i].delete();
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $('#response').html(JSON.stringify(jqXHR));
        }
    });
}

function addRegistrationBtnClick(event){
    var modal = document.getElementById("addRegistrationModal");
    var btn = document.getElementById("addRegistrationBtn");
    var span = document.getElementsByClassName("addClose")[0];
    btn.onclick = function() {
        modal.style.display = "block";
    }
    span.onclick = function() {
        modal.style.display = "none";
    }
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
};

function addRegistrationSubmitClick(event) {
    var reg = new Object();
    reg.numberPlate = $('input#addPlate').val();

    var vehicle = new Object();
    vehicle.type  = $('input#addType').val();
    vehicle.make = $('input#addMake').val();
    vehicle.model = $('input#addModel').val();
    vehicle.colour = $('input#addColour').val();
    
    var insurer = new Object();
    insurer.name = $('select#addInsurer').val();

    reg.vehicle = vehicle;
    reg.insurer = insurer;

    var data = JSON.stringify(reg);
    $.ajax({
        type: 'POST',
        url: registrationService + "/add",
        contentType: 'application/json; charset=utf-8',
        data: data,
        async: false,
        success: function (result) {
            alert("Registration added in database");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $('#response').html(JSON.stringify(jqXHR));
        }
    });
}

function editRegistrationBtnClick(event){
    var modal = document.getElementById("editRegistrationModal");
    var btn = document.getElementById("editRegistrationBtn");
    var span = document.getElementsByClassName("editClose")[0];

    btn.onclick = function () {
        var selectedRows = [];
        selectedRows = table.getSelectedRows();

        if (selectedRows.length == 1) {
            var row = selectedRows[0].getData();

            var modal = document.getElementById("editRegistrationModal");
            modal.style.display = "block";

            var plate = document.getElementById("editPlate");
            plate.value = row.numberPlate;

            var type = document.getElementById("editType");
            type.value = row.vehicle.type;

            var make = document.getElementById("editMake");
            make.value = row.vehicle.make;

            var model = document.getElementById("editModel");
            model.value = row.vehicle.model;

            var colour = document.getElementById("editColour");
            colour.value = row.vehicle.colour;

            var insurer = document.getElementById("editInsurer");
            insurer.value = row.insurer.name;



        } else if (selectedRows.length > 1){
            alert("Select only one record to edit.");
        } else {
            alert("No records to edit.");
        }
    }

    span.onclick = function () {
        modal.style.display = "none";
    }

    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }


};

function editRegistrationSubmitClick(event) {
    var selectedRows = [];
    selectedRows = table.getSelectedRows();
    var row = selectedRows[0].getData();

    var reg = new Object();
    reg.id = row.id;
    reg.numberPlate = $('input#editPlate').val();

    var vehicle = new Object();
    vehicle.id = row.vehicle.id;
    vehicle.type  = $('input#editType').val();
    vehicle.make = $('input#editMake').val();
    vehicle.model = $('input#editModel').val();
    vehicle.colour = $('input#editColour').val();

    var insurer = new Object();
    insurer.id = row.insurer.id;
    insurer.name = $('select#editInsurer').val();
    insurer.code = row.insurer.code;

    reg.vehicle = vehicle;
    reg.insurer = insurer;

    var data = JSON.stringify(reg);
    $.ajax({
        type: 'PUT',
        url: registrationService + "/update",
        contentType: 'application/json; charset=utf-8',
        data: data,
        async: false,
        success: function (result) {
            alert("Registration updated in database");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $('#response').html(JSON.stringify(jqXHR));
        }
    });
}

function addInsurerBtnClick() {
    var modal = document.getElementById("addInsurerModal");
    var btn = document.getElementById("addInsurerBtn");
    var span = document.getElementsByClassName("addInsurerClose")[0];
    btn.onclick = function() {
        modal.style.display = "block";
    }
    span.onclick = function() {
        modal.style.display = "none";
    }
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
}

function addInsurerSubmitClick(event) {
    var insurer = new Object();
    insurer.name = $('input#addInsurerName').val();
    insurer.code = $('input#addInsurerCode').val();

    var data = JSON.stringify(insurer);
    $.ajax({
        type: 'POST',
        url: insurerService + "/add",
        contentType: 'application/json; charset=utf-8',
        data: data,
        async: false,
        success: function (result) {
            alert("Insurer added in database");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $('#response').html(JSON.stringify(jqXHR));
        }
    });
}

function deleteInsurerSubmitClick(event) {
    var insurer = new Object();
    insurer = $('select#deleteInsurer').val();

    $.ajax({
        type: 'DELETE',
        url: insurerService + "/delete/" + insurer,
        contentType: 'application/json; charset=utf-8',
        async: false,
        success: function (result) {
            alert("Insurer deleted from database");
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(insurer + " is used in registrations. Failed to delete.");
            $('#response').html(JSON.stringify(jqXHR));
        }
    });
}

function deleteInsurerBtnClick() {
    var modal = document.getElementById("deleteInsurerModal");
    var btn = document.getElementById("deleteInsurerBtn");
    var span = document.getElementsByClassName("deleteInsurerClose")[0];
    btn.onclick = function() {
        modal.style.display = "block";
    }
    span.onclick = function() {
        modal.style.display = "none";
    }
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
}

function getInsurers() {
    $.ajax({
        type: 'GET',
        url: insurerService + "/getAll",
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false,
        success: function (result) {
            document.writeln('<option value="' + result[0].name + '" selected>' + result[0].name + '</option>');
            for (var i = 1; i < result.length; i++) {
                document.writeln('<option value="' + result[i].name + '">' + result[i].name + '</option>');
            }
        },
        error: function (jqXHR, textStatus, errorThrown) {
            $('#response').html(JSON.stringify(jqXHR));
        }
    });
}