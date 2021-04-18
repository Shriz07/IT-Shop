$(document).ready(function (){
    $("#buttonAddToCart").on("click", function (){
       addToCart();
    });
});

function addToCart()
{
    quantity = $("#quantity" + productId).val();
    url = "/cart/add/" + productId + "/" + quantity;
    $.ajax({
       type: "POST",
       url: url,
       beforeSend: function (xhr) {
           xhr.setRequestHeader(csrfHeaderName, csrfValue);
       }
    }).done(function (response) {
        $("#modalTitle").text("Shopping cart");
        $("#modalBody").text(response);
        $("#myModal").modal();
    }).fail(function () {
        $("#modalTitle").text("Shopping cart");
        $("#modalBody").text("Error while adding product to cart.");
        $("#myModal").modal();
    });
}