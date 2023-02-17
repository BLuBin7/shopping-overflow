// copy menu for mobile
function copyMenu(){
    //copy inside .dpt-cat to .departments
    var dptCategory = document.querySelector('.dpt-cat');
    var dptPlace =document.querySelector('.departments');
    dptPlace.innerHTML = dptCategory.innerHTML;

    //copy inside nav to nav
    var mainNav = document.querySelector('.header-nav nav');
    var navPlace = document.querySelector('.off-canvas nav');
    navPlace.innerHTML = mainNav.innerHTML;

    // copy .header-top .wrapper to .thetop-nav
    var topNav = document.querySelector('.header-top .wrapper');
    var topPlace = document.querySelector('.off-canvas .thetop-nav ');
    topPlace.innerHTML = topNav.innerHTML;
}
copyMenu( );

//show menu in mobile
const menuButton = document.querySelector('.trigger'),
         closeButton = document.querySelector('.t-close'),
         addclass = document.querySelector('.site');
menuButton.addEventListener('click' , function() {
        addclass.classList.toggle('showmenu');
})
closeButton.addEventListener('click' , function() {
    addclass.classList.remove('showmenu');
})



// show sub menu in mobile
const submenu = document.querySelectorAll('.has-child .icon-small');
submenu.forEach((menu)  => menu.addEventListener('click', toggle));

function toggle(e) {
    e.preventDefault();
    submenu.forEach((item) => item != this ? item.closest('.has-child').classList.remove('expand') : null); 
    if(this.closest('.has-child').classList !='expand') ;
    this.closest('.has-child').classList.toggle('expand')
}     

// slider , trượt
const swiper = new Swiper('.swiper', {
    loop: true,
  
    // If we need pagination
    pagination: {
      el: '.swiper-pagination',
    },
 
  });

// menu bottom
// show search
// custom nút search trên moblie menu , khi bấm nút search thì site sẽ thành showsearch
const searchButton = document.querySelector('.t-search'),
        tClose = document.querySelector('.search-close'),
        // site dưới thẻ body
        showClass = document.querySelector('.site');
searchButton.addEventListener('click',function() {
    // tạo 1 class showsearch mới , sau đó vô css custom
        showClass.classList.toggle('showsearch')
})

tClose.addEventListener('click',function (){
        showClass.classList.remove('showsearch')
})

// 8 PAGE-SINGLE 
// show dpt menu

// header-main mobile-hide trong HEADER NAP
const dptButton = document.querySelector('.dpt-cat .dpt-trigger'),
// site dưới body
         dptClass = document.querySelector('.site');
dptButton.addEventListener('click',function(){
    // bấm vô all department thì site part-single sẽ đổi thành site part-single showdpt
        dptClass.classList.toggle('showdpt')
})

// page single image
// product image slider , 

// ảnh nhỏ
var productThumb = new Swiper ('.small-image' , {
    loop: true,
    spaceBetween: 10,
    slidesPerView : 3,
    freeMode : true,
    watchSlidesProgress : true,
    breakpoints : {
        481: {
            spaceBetween: 32,
        }
    }
});

// ảnh lớn
var productBig = new Swiper ('.big-image' , {
    loop: true,
    autoHeight: true,
    navigation: {
        nextEl : '.swiper-button-next',
        prevEl : '.swiper-button-prev',
    },
    thumbs : {
        swiper : productThumb
    }
})

// trang sản phẩm trưng bày sale off
//edit thanh sản phẩm còn lại
var stocks = document.querySelectorAll('.products .stock ');
for(let x= 0 ; x< stocks.length ; x++) {
    let stock = stocks[x].dataset.stock,
    available = stocks[x].querySelector(' .qty-available').innerHTML,
    sold = stocks[x].querySelector(' .qty-sold').innerHTML,
    percent = sold*100/stock;

    stocks[x].querySelector(' .available').style.width=percent + '%';
}

// hiện cart khi nhấn 
const  divtoShow = '.mini-cart';
const divPopup = document.querySelector(divtoShow);
const divTrigger= document.querySelector('.cart-trigger');
divTrigger.addEventListener('click', () =>{
    setTimeout(() => {
        if(!divPopup.classList.contains('show')){
            divPopup.classList.add('show');
        }
    },250)
})
 
// chức năng tắt khi bấm ra ngoài giỏ hàng
document.addEventListener('click', (e) => {
    const isClosest = e.target.closest(divtoShow);
    if(!isClosest && divPopup.classList.contains('show')){
        divPopup.classList.remove('show');
    }
})

// hiển thị modal
window.onload = function(){
    document.querySelector('.site').classList.toggle('showmodal');
}
document.querySelector('.modalclose').addEventListener('click', function(){
    document.querySelector('.site').classList.remove('showmodal');
})