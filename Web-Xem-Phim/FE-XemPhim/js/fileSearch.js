function getParam(){
    // var url_string = "http://www.example.com/t.html?a=1&b=3&c=m2-m3-m4-m5"; //window.location.href
    var url = new URL(window.location.href);
    var nameFind = url.searchParams.get("nameFind");
    console.log(nameFind);
    return nameFind;
}
function showBySearch(page){
   var nameFind = getParam();
   $.ajax({
       type:"GET",
       url:`http://localhost:8080/submovie/find?page=${page}&&nameFind=${nameFind}`,
       success:function (pageable){
           let data= pageable.content
           let str=""
           for (let i = 0; i < data.length; i++) {
               str+=` <div class="col-lg-4 col-md-6 col-sm-6">
                                <div class="product__item">
                                    <div class="product__item__pic set-bg" style="background-image: url('${data[i].img}')">
                                        <div class="ep">${data[i].time}</div>
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
           document.getElementById("showBySearch").innerHTML = str;
           let pagetotal= pageable.totalPages
           let strPage="";
           for (let i = 1; i <=pagetotal; i++) {
               strPage+=`<button onclick="showBySearch(${i-1})">${i}</button>`
           }
           document.getElementById("pageBySearch").innerHTML = strPage
       }
   })
}

showBySearch(0)
function showBySearchInWeb(page){
    let nameFind = document.getElementById("searchInWeb").value;
    $.ajax({
        type:"GET",
        url:`http://localhost:8080/submovie/find?page=${page}&&nameFind=${nameFind}`,
        success:function (pageable){
            let data= pageable.content
            let str=""
            for (let i = 0; i < data.length; i++) {
                str+=` <div class="col-lg-4 col-md-6 col-sm-6">
                                <div class="product__item">
                                    <div class="product__item__pic set-bg" style="background-image: url('${data[i].img}')">
                                        <div class="ep">${data[i].time}</div>
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
            document.getElementById("showBySearch").innerHTML = str;
            let pagetotal= pageable.totalPages
            let strPage="";
            for (let i = 1; i <=pagetotal; i++) {
                strPage+=`<button onclick="showBySearchInWeb(${i-1})">${i}</button>`
            }
            document.getElementById("pageBySearch").innerHTML = strPage
        }
    })
}
