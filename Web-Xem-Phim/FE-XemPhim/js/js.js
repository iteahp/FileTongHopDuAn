show();
getCategory()

function getCategory() {
    $.ajax({
        type: "GET",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: "http://localhost:8080/movie/category",
        //xử lý khi thành công
        success: function (data) {
            let str = "";
            for (let i = 0; i < data.length; i++) {
                str += "<option value=" + data[i].idCategory + ">" + data[i].nameCategory + "</option>";
            }
            console.log(str);
            document.getElementById("createCategory").innerHTML = str;
            document.getElementById("editCategory").innerHTML = str;
            document.getElementById("deleteCategory").innerHTML = str;
        },
        error: function (err) {
            console.log(err)
        }
    })
}

function show() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/movie",
        //xử lý khi thành công
        success: function (da) {
            let str = "";
            let data = da.content;
            console.log(data.category);
            console.log(da);
            for (let i = 0; i < data.length; i++) {
                str += "<tr>"
                str += `<td>${data[i].id}</td>`
                str += `<td>${data[i].name}</td>`
                str += `<td>${data[i].time}</td>`
                str += `<td>${data[i].description}</td>`
                str += `<td>< img src=${data[i].img}></td>`
                str += `<td>< video src=${data[i].video}></td>`
                str += `<td>${data[i].dateUp}</td>`
                str += `<td>${data[i].likeM}</td>`
                str += `<td>${data[i].viewM}</td>`
                if (data[i].category !== null) {
                    str += `<td>${data[i].category.nameCategory}</td>`
                } else {
                    str += "<td></td>"
                }
                str += `<td><button onclick="showEdit(${data[i].id})" type="button" class="btn btn-primary" data-toggle="modal" data-target="#edit">Edit</button></td>`
                str += `<td><button onclick="showDelete(${data[i].id})" type="button" class="btn btn-primary" data-toggle="modal" data-target="#delete">Delete</button></td>`
                str += "</tr>"
            }
            document.getElementById("tbody").innerHTML = str;
        },
        error: function (err) {
            console.log(err)
        }
    })
}

function showEdit(id) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/movie/" + id,
        //xử lý khi thành công
        success: function (data) {
            document.getElementById("editName").value = data.name;
            document.getElementById("editTime").value = data.time;
            document.getElementById("editDescription").value = data.description;
            document.getElementById("editimg").value = data.img;
            document.getElementById("editVideo").value = data.video;
            document.getElementById("editdateUp").value = data.dateUp;
            document.getElementById("editLikeM").value = data.likeM;
            document.getElementById("editViewM").value = data.viewM;
            document.getElementById("editId").value = data.id;
        },
        error: function (err) {
            console.log(err);
        }
    })
}

function showDelete(id) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/movie/" + id,
        //xử lý khi thành công
        success: function (data) {
            document.getElementById("deleteName").value = data.name;
            document.getElementById("deleteTime").value = data.time;
            document.getElementById("deleteDescription").value = data.description;
            document.getElementById("deleteimg").value = data.img;
            document.getElementById("deleteVideo").value = data.video;
            document.getElementById("deletedateUp").value = data.dateUp;
            document.getElementById("deleteLikeM").value = data.likeM;
            document.getElementById("deleteViewM").value = data.viewM;
            document.getElementById("deleteId").value = data.id;
        },
        error: function (err) {
            console.log(err);
        }
    })
}

function searchName() {
    let name = document.getElementById("searchName").value;
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/movie/search/" + name,
        //xử lý khi thành công
        success: function (da) {
            let str = "";
            let data = da.content;
            console.log(data.category);
            console.log(da);
            for (let i = 0; i < data.length; i++) {
                str += "<tr>"
                str += `<td>${data[i].id}</td>`
                str += `<td>${data[i].name}</td>`
                str += `<td>${data[i].time}</td>`
                str += `<td>${data[i].description}</td>`
                str += `<td>< img src=${data[i].img}></td>`
                str += `<td>< video src=${data[i].video}></td>`
                str += `<td>${data[i].dateUp}</td>`
                str += `<td>${data[i].likeM}</td>`
                str += `<td>${data[i].viewM}</td>`
                if (data[i].category !== null) {
                    str += `<td>${data[i].category.nameCategory}</td>`
                } else {
                    str += "<td></td>"
                }
                str += `<td><button onclick="showEdit(${data[i].id})" type="button" class="btn btn-primary" data-toggle="modal" data-target="#edit">Edit</button></td>`
                str += `<td><button onclick="showDelete(${data[i].id})" type="button" class="btn btn-primary" data-toggle="modal" data-target="#delete">Delete</button></td>`
                str += "</tr>"
            }
            document.getElementById("tbody").innerHTML = str;
        },
        error: function (err) {
            console.log(err);
        }
    })
}

function create() {
    let name = document.getElementById("createName").value;
    let time = document.getElementById("createTime").value;
    let description = document.getElementById("createDescription").value;
    let img = document.getElementById("createimg").value;
    let video = document.getElementById("createVideo").value;
    let dateUp = document.getElementById("createdateUp").value;
    let likeM = document.getElementById("createLikeM").value;
    let viewM = document.getElementById("createViewM").value;
    let categoryId = document.getElementById("createCategory").value;

    let movie = {
        name: name,
        time: time,
        description: description,
        img: img,
        video: video,
        dateUp: dateUp,
        likeM: likeM,
        viewM: viewM,
        category: {
            idCategory: categoryId
        }
    }
    console.log(movie);

    $.ajax({
        type: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: "http://localhost:8080/movie",
        data: JSON.stringify(movie),
        //xử lý khi thành công
        success: function (data) {
            console.log(data);
            show();
        },
        error: function (err) {
            console.log(err)
        }
    })
}

function edit() {
    let name = document.getElementById("editName").value;
    let time = document.getElementById("editTime").value;
    let description = document.getElementById("editDescription").value;
    let img = document.getElementById("editimg").value;
    let video = document.getElementById("editVideo").value;
    let dateUp = document.getElementById("editdateUp").value;
    let likeM = document.getElementById("editLikeM").value;
    let viewM = document.getElementById("editViewM").value;
    let CategoryId = document.getElementById("editCategory").value;
    let id = document.getElementById("editId").value;

    let movie = {
        id: id,
        name: name,
        time: time,
        description: description,
        img: img,
        video: video,
        dateUp: dateUp,
        likeM: likeM,
        viewM: viewM,
        category: {
            idCategory: CategoryId
        }
    }

    $.ajax({
        type: "PUT",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: "http://localhost:8080/movie/" + id,
        data: JSON.stringify(movie),
        //xử lý khi thành công
        success: function (data) {
            console.log(data);
            show()
        },
        error: function (err) {
            console.log(err)
        }
    })
}

function deleteMovie() {
    let id = document.getElementById("deleteId").value;
    $.ajax({
        type: "DELETE",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: "http://localhost:8080/movie/" + id,
        //xử lý khi thành công
        success: function () {
            show()
        },
        error: function (err) {
            console.log(err)
        }
    })
}

