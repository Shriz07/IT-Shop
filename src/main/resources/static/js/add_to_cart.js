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
    });
}