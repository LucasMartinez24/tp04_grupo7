window.addEventListener("scroll",function(){
  var header = this.document.querySelector("nav");
  header.classList.toggle("sticky",window.scrollY>1)
})