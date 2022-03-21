showCate();

function showCate() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/category",
        //xử lý khi thành công
        success: function (data) {
            let str = "";
            console.log(data);
            for (let i = 0; i < data.length; i++) {
                str += "<tr>"
                str += `<td>${data[i].idCategory}</td>`
                str += `<td>${data[i].nameCategory}</td>`
                str += `<td><button onclick="showEditCate(${data[i].idCategory})" type="button" class="btn btn-primary" data-toggle="modal" data-target="#edit">Edit</button></td>`
                str += `<td><button onclick="showDeleteCate(${data[i].idCategory})" type="button" class="btn btn-primary" data-toggle="modal" data-target="#delete">Delete</button></td>`
                str += "</tr>"
            }
            console.log(str)
            document.getElementById("body").innerHTML = str;
        },
        error: function (err) {
            console.log(err)
        }
    })
}

function showEditCate(idCategory) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/category/" + idCategory,
        //xử lý khi thành công
        success: function (data) {
            document.getElementById("editIdCate").value = data.idCategory;
            document.getElementById("editNameCate").value = data.nameCategory;
        },
        error: function (err) {
            console.log(err);
        }
    })
}

function showDeleteCate(idCategory) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/category/" + idCategory,
        //xử lý khi thành công
        success: function (data) {
            document.getElementById("deleteIdCate").value = data.idCategory;
            document.getElementById("deleteNameCate").value = data.nameCategory;
        },
        error: function (err) {
            console.log(err);
        }
    })
}


function searchCate() {
    let nameCategory = document.getElementById("searchNameCate").value;
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/category/search/" + nameCategory,
        //xử lý khi thành công
        success: function (data) {
            let str = "";
            console.log(data);
            for (let i = 0; i < data.length; i++) {
                str += "<tr>"
                str += `<td>${data[i].idCategory}</td>`
                str += `<td>${data[i].nameCategory}</td>`
                str += `<td><button onclick="showEditCate(${data[i].idCategory})" type="button" class="btn btn-primary" data-toggle="modal" data-target="#edit">Edit</button></td>`
                str += `<td><button onclick="showDeleteCate(${data[i].idCategory})" type="button" class="btn btn-primary" data-toggle="modal" data-target="#delete">Delete</button></td>`
                str += "</tr>"
            }
            console.log(str);
            document.getElementById("body").innerHTML = str;
        },
        error: function (err) {
            console.log(err);
        }
    })
}


function createCate() {
    let idCategory = document.getElementById("createIdCate").value;
    let nameCategory = document.getElementById("createNameCate").value;

    let category = {
        idCategory: idCategory,
        nameCategory: nameCategory,
    }
    console.log(category);

    $.ajax({
        type: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: "http://localhost:8080/category",
        data: JSON.stringify(category),
        //xử lý khi thành công
        success: function (data) {
            console.log(data);
            showCate();
        },
        error: function (err) {
            console.log(err)
        }
    })
}


function editCate() {
    let idCategory = document.getElementById("editIdCate").value;
    let nameCategory = document.getElementById("editNameCate").value;

    let category = {
        idCategory: idCategory,
        nameCategory: nameCategory,
    }

    $.ajax({
        type: "PUT",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: "http://localhost:8080/category/" + idCategory,
        data: JSON.stringify(category),
        //xử lý khi thành công
        success: function (data) {
            console.log(data);
            showCate()
        },
        error: function (err) {
            console.log(err)
        }
    })
}

function deleteCate() {
    let idCategory = document.getElementById("deleteIdCate").value;
    $.ajax({
        type: "DELETE",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: "http://localhost:8080/category/" + idCategory,
        //xử lý khi thành công
        success: function () {
            showCate()
        },
        error: function (err) {
            console.log(err)
        }
    })

}

