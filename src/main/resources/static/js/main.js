window.addEventListener("scroll",function(){
  var header = this.document.querySelector("nav");
  header.classList.toggle("sticky",window.scrollY>1)
})
//login
const signinBtn = document.querySelector('.signinBtn');
const signupBtn = document.querySelector('.signupBtn');
const formBx = document.querySelector('.formBx');
const body = document.querySelector('body');

signupBtn.onclick=function(){
  formBx.classList.add('active');
  body.classList.add('active');
}
signinBtn.onclick=function(){
  formBx.classList.remove('active');
  body.classList.remove('active');
}
