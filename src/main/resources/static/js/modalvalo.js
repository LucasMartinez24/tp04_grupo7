var exampleModal = document.getElementById('modalValorar')
const openModal = document.querySelector('.boton');
console.log(openModal);
const modal = document.querySelector('.modal');
const closeModal = document.querySelector('.modal_close');
openModal.addEventListener('click', (e)=>{
    e.preventDefault();
    modal.classList.add('modal--show');
});
closeModal.addEventListener('click', (e)=>{
    e.preventDefault();
    modal.classList.remove('modal--show');
});

function confirmarvalorar(id) {
	$("#identificadorDePelicula").val(id);
}

function valorarPelicula() {
	var id = $("#identificadorDePelicula").val();
    window.location = "/eliminarPeli/" + id;
}