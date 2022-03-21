function showTrending(page,option) {
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/submovie/showAll?page=${page}&&option=${option}`,
        success: function (pageAble) {
            let data = pageAble.content
            let str = ""
            for (let i = 0; i < data.length; i++) {
                str += `<div class="col-lg-4 col-md-6 col-sm-6">
                                <div class="product__item">
                                    <div  class="product__item__pic set-bg" style="background-image: url('${data[i].img}')">
                                        <div class="ep">${data[i].time} phút</div>
                                        <div class="comment"><i class="fa fa-comment"></i>${data[i].likeM}</div>
                                        <div class="view"><i class="fa fa-eye"></i> ${data[i].viewM}</div>
                                    </div>
                                    <div class="product__item__text">
                                        <ul>
                                            <li>Active</li>
                                            <li>Movie</li>
                                        </ul>
                                        <h5><a href="anime-details.html?id=${data[i].id}">${data[i].name}</a></h5>
                                    </div>
                                </div>
</div>`
            }
            document.getElementById(option).innerHTML = str;
        }
    })

}
function showTopComment(){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/submovie/topcomment",
        success:function (data){
            let str = ""
            for (let i = 0; i < data.length; i++) {
                str+=`<div class="product__sidebar__view__item set-bg mix day years"
                                style="background-image: url('${data[i].img}')">
                                <div class="ep">${data[i].time} phút</div>
                                <div class="view"><i class="fa fa-eye"></i>${data[i].viewM}</div>
                                <h5><a href="anime-details.html?id=${data[i].id}">${data[i].name}</a></h5>
                            </div>`
            }
            document.getElementById("topcomment").innerHTML = str;
        }
    })
}
showTopComment()
function showAllCategory(){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/submovie/category",
        success:function (data){
            let str="";
            for (let i = 0; i < data.length; i++) {
                str+=`<li><a href="./categories.html?idCategory=${data[i].idCategory}">${data[i].nameCategory}</a></li>`
            }
            document.getElementById("allCategory").innerHTML = str;
        }
    })
}
showAllCategory()

showTrending(0,"likeM",)
showTrending(0,"viewM",)

function showByDateUp(){
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/submovie/showAll?page=0&&option=dateUp&&size=3`,
        success: function (pageAble) {
            let data = pageAble.content
            let str = ""
            for (let i = 0; i < data.length; i++) {
                str += ` <div class="hero__items set-bg"   style="background-image: url('${data[i].img}')" >
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="hero__text">
                                <div class="label">${data[i].category.nameCategory}</div>
                                <h2>${data[i].name}</h2>
                                <p>${data[i].description}</p>
                                <a href="anime-watching.html?id=${data[i].id}"><span>Watch Now</span> <i class="fa fa-angle-right"></i></a>
                            </div>
                        </div>
                    </div>
                </div>`
            }
            console.log('str ---> ', str)
            document.getElementById("dateUp").innerHTML = str;
        }
    })
}
function searchByName(){
    let nameFind = document.getElementById("searchByName").value;
    window.open(`search.html?nameFind=${nameFind}`,'_self')
}
// showByDateUp()
function showTop5Comment(){
    $.ajax({
        type: "GET",
        url: `http://localhost:8080/submovie/topcomment`,
        success: function (pageAble) {
            let data = pageAble.content
            let str = ""
            for (let i = 0; i < data.length; i++) {
                str += ` `
            }

            document.getElementById("dateUp").innerHTML = str;
        }
    })
}


