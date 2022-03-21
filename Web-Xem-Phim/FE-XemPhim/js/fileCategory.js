function getParam(){
    // var url_string = "http://www.example.com/t.html?a=1&b=3&c=m2-m3-m4-m5"; //window.location.href
    var url = new URL(window.location.href);
    var idCategory = url.searchParams.get("idCategory");
    console.log(idCategory);
    return idCategory;
}
function showAllByCategory(page){
    let idCategory = getParam();
    $.ajax({
        type:"GET",
        url: `http://localhost:8080/submovie/category/${idCategory}/${page}`,
        success:function (pageable){
            let data= pageable.content
            let str=""
            for (let i = 0; i < data.length; i++) {
                str+=` <div class="col-lg-4 col-md-6 col-sm-6">
                                <div class="product__item">
                                    <div class="product__item__pic set-bg" style="background-image: url('${data[i].img}')">
                                        <div class="ep">${data[i].time} phút</div>
                                        <div class="comment"><i class="fa fa-comments"></i>${data[i].likeM}</div>
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
            document.getElementById("showByCate").innerHTML = str;
            let pagetotal= pageable.totalPages
            let strPage="";
            for (let i = 1; i <=pagetotal; i++) {
                strPage+=`<button onclick="showAllByCategory(${i-1})">${i}</button>`
            }
            document.getElementById("page").innerHTML = strPage
        }
    })
}
showAllByCategory(0)


function showAllCategoryInHtml(){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/submovie/category",
        success:function (data){
            let str="";
            for (let i = 0; i < data.length; i++) {
                str+=`<li><a href="./categories.html?idCategory=${data[i].idCategory}">${data[i].nameCategory}</a></li>`
            }
            document.getElementById("allCategoryInHtml").innerHTML = str;
        }
    })
}

showAllCategoryInHtml()
function printCategory(){
    let idCategory = getParam();
    $.ajax({
        type: "POST",
        url: `http://localhost:8080/submovie/category/${idCategory}`,
        success:function (data){
          let  str="";
            str+= ` <a href="./categories.html?idCategory=${data.idCategory}">${data.nameCategory}</a>`
            document.getElementById("categoryName").innerHTML = str
            document.getElementById("categoryName2").innerHTML = str

        }
    })
}
printCategory()
