$(document).ready(function (){
    $("#checkoutButton").on("click", function (){
        $("#modalTitle").text("Checkout");
        $("#modalBody").text("Your order has been placed.");
        $("#myModal").modal();
    });
});