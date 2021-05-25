$(document).ready(function (){
    $("#buttonAddToCart").on("click", function (){
       addToCart();
    });
    $("#buttonBuyNow").on("click", function (){
        buyNow();
    });
});

function buyNow()
{
    let quantity = $("#quantity" + productId).val();
    let url = "/cart/add/" + productId + "/" + quantity;
    $.ajax({
        type: "POST",
        url: url,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeaderName, csrfValue);
        }
    }).done(function (response) {
        window.location = "/shoppingCart";
    }).fail(function () {
        $("#modalTitle").text("Shopping cart");
        $("#modalBody").text("Error while adding product to cart.");
        $("#myModal").modal();
    });
}

function addToCart()
{
    let quantity = $("#quantity" + productId).val();
    let url = "/cart/add/" + productId + "/" + quantity;
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