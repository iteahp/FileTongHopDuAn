function getParam(){
    // var url_string = "http://www.example.com/t.html?a=1&b=3&c=m2-m3-m4-m5"; //window.location.href
    var url = new URL(window.location.href);
    var id = url.searchParams.get("id");
    console.log(url)
    console.log(id);
    return id;
}
function showDetail(){
    let id = getParam();
    $.ajax({
        type:"GET",
        url: `http://localhost:8080/submovie/${id}`,
        success:function (data){
            let str="";
            str+=`<div class="col-lg-3">
                        <div class="anime__details__pic set-bg"  style="background-image: url('${data.img}')">
                            <div class="comment"><i class="fa fa-comments"></i> ${data.likeM}</div>
                            <div class="view"><i class="fa fa-eye"></i>${data.viewM}</div>
                        </div>
                    </div>`
            str+=`<div class="col-lg-9">`
            str+=`<div class="anime__details__text">
                            <div class="anime__details__title">
                                <h3>${data.name}</h3>
                         
                            </div>
                          
                            <p>${data.description}</p>
                            <div class="anime__details__widget">
                                <div class="row">
                                    <div class="col-lg-6 col-md-6">
                                        <ul>
                                            <li><span>Like</span>${data.likeM}</li>
                                            <li><span>View</span> ${data.viewM}</li>
                                            <li><span>Date aired:</span> ${data.dateUp}</li>
                                            <li><span>Category:</span> ${data.category.nameCategory}</li>
                                            <li><span>Time</span> ${data.time} phút</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="anime__details__btn">
                                <a href="#" class="follow-btn" onclick="MovieLikeUp()"><i class="fa fa-heart-o"></i> Like</a>
                                <a href="anime-watching.html?id=${data.id}" class="watch-btn" ><span>Watch Now</span> <i
                                    class="fa fa-angle-right"></i></a>
                                </div>
                            </div>`
            str+=`</div>`
            document.getElementById("detail").innerHTML = str

        }
    })
}
showDetail()
function showComment(){
    let id = getParam();
    $.ajax({
        type:"GET",
        url: `http://localhost:8080/submovie/comment/${id}`,
        success:function (data){
            let str="";
            for (let i = 0; i < data.length; i++) {
                str+=` 
                                <div class="anime__review__item__pic">
                                    <img src="${data[i].users.avatar}" alt="">
                                </div>
                                <div class="anime__review__item__text">
                                    <h6>${data[i].users.name} <span></span></h6>
                                    <p>${data[i].comment}</p>
                                </div>
                            `
            }
            document.getElementById("comment").innerHTML = str;
        }
    })
}
showComment();
function showCategory(){
    let id = getParam();
    $.ajax({
        type:"GET",
        url: `http://localhost:8080/submovie/movie/${id}`,
        success:function (data){
            let str="";
            let length = data.length
            if (length>4){
                length = 4
            }
            for (let i = 0; i < length; i++) {
                str+=` <div class="product__sidebar__view__item set-bg" style="background-image: url('${data[i].img}')">
                                <div class="ep">${data[i].time}</div>
                                <div class="view"><i class="fa fa-eye"></i> ${data[i].viewM}</div>
                                <h5 ><a href="anime-details.html?id=${data[i].id}" style="color: red">${data[i].name}</a></h5>
                            </div>`
            }
            console.log(data)
            document.getElementById("category").innerHTML = str;
        }

    })

}
showCategory()
function MovieLikeUp(){
    let id = getParam();
    $.ajax({
        type:"GET",
        url :`http://localhost:8080/submovie/likeUp/${id}`,
        success:function (){
            showDetail()
        }
    })
}

function commentMovie(){
   let idUser = idUserName
    console.log("idUser>>",idUser)
    let idMovie= getParam();
    let commentText = document.getElementById("textComment").value;
    let commentMovie= {
        movie:{id:idMovie},
        comment:commentText,
        users:{
            id:idUser
        }
    }
    console.log('comment>>>',commentMovie)
    $.ajax({
        type:"POST",
        data : JSON.stringify(commentMovie),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url:"http://localhost:8080/submovie/comment",
        success :function (data){
            showComment()
        }
    })
}
let idUserName
function getUserBySesion(){
    let username = window.sessionStorage.getItem('userName');

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/user/findByUsername/" +username,
        success : function (data){
            console.log('data >>>',data)
            console.log("idđsa>>>",data.id)
            idUserName = data.id

        }
    })

}
getUserBySesion()
console.log('id>>>>',idUserName)


function showAllCategoryInDetail(){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/submovie/category",
        success:function (data){
            let str="";
            for (let i = 0; i < data.length; i++) {
                str+=`<li><a href="./categories.html?idCategory=${data[i].idCategory}">${data[i].nameCategory}</a></li>`
            }
            document.getElementById("allCategories").innerHTML = str;
        }
    })
}
showAllCategoryInDetail()




