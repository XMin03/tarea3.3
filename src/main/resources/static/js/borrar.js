/*js para confirmacion de borrar*/
function mostrarConfirmacion(button) {
    document.getElementById("confirmacion-"+button.id).style.display = "flex";
}
function cerrarConfirmacion(button) {
    document.getElementById("confirmacion-"+button.id).style.display = "none";
}