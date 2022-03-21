function getParam(){
    // var url_string = "http://www.example.com/t.html?a=1&b=3&c=m2-m3-m4-m5"; //window.location.href
    var url = new URL(window.location.href);
    var id = url.searchParams.get("id");
    console.log(id);
    return id;
}
function showCommentWatching(){
    console.log("okk")
    let id = getParam();
    $.ajax({
        type:"GET",
        url: `http://localhost:8080/submovie/comment/${id}`,
        success:function (data){
            let str="";
            for (let i = 0; i < data.length; i++) {
                str+=` 
                                <div class="anime__review__item__pic">
                                    <img src="img/anime/review-1.jpg" alt="">
                                </div>
                                <div class="anime__review__item__text">
                                    <h6>Trần Xuân Đạt  <span>1 Hour ago</span></h6>
                                    <p>${data[i].comment}</p>
                                </div>
                            `
            }
            document.getElementById("commentWatching").innerHTML = str;
        }
    })
}
showCommentWatching()
function showVideo(){
    let id = getParam();
    $.ajax({
        type:"GET",
        url: `http://localhost:8080/submovie/${id}`,
        success:function (data){
            let str="";
            str+= ` <video id="player" playsinline controls data-poster="./videos/anime-watch.jpg">
                            <source src="${data.video}" type="video/mp4" />
                            <!-- Captions are optional -->
                            <track kind="captions" label="English captions" src="#" srclang="en" default />
                        </video>`
            document.getElementById("videoMovie").innerHTML = str
            let strName =""
            strName+= ` <a href="./index.html"><i class="fa fa-home"></i> Home</a>
                        <a href="./categories.html">Categories</a>
                        <a href="./categories.html?idCategory=${data.category.idCategory}">${data.category.nameCategory}</a>
                        <span>${data.name}</span>`
            document.getElementById("HomeMovie").innerHTML = strName;
        }

    })
}
showVideo()
function commentMovieWatching(){
    console.log("abc")
    let idMovie= getParam();
    let commentText = document.getElementById("textCommentWatching").value;
    let commentMovie= {
        movie:{id:idMovie},
        comment:commentText
    }
    $.ajax({
        type:"POST",
        data : JSON.stringify(commentMovie),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url:"http://localhost:8080/submovie/comment",
        success :function (data){
            showCommentWatching()
        }
    })
}
function showAllCategoryInWatching(){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/submovie/category",
        success:function (data){
            let str="";
            for (let i = 0; i < data.length; i++) {
                str+=`<li><a href="./categories.html?idCategory=${data[i].idCategory}">${data[i].nameCategory}</a></li>`
            }
            document.getElementById("categoryWatching").innerHTML = str;
        }
    })
}
function MovieViewUp(){
    let id = getParam();
    $.ajax({
        type:"GET",
        url :`http://localhost:8080/submovie/viewUp/${id}`,
        success:function (){

        }
    })
}
MovieViewUp()
showAllCategoryInWatching()