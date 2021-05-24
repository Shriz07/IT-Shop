/* Set the width of the sidebar to 250px and the left margin of the page content to 250px */
function openNav() {
    document.getElementById("sidebar-btn").style.display = "none";
    document.getElementById("sidebar").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
}

/* Set the width of the sidebar to 0 and the left margin of the page content to 0 */
function closeNav() {
    document.getElementById("sidebar-btn").style.display = "block";
    document.getElementById("sidebar").style.width = "0";
    document.getElementById("main").style.marginLeft = "0";
}

function resize() {
    if($(document).width() < 1000)
        closeNav();
    else
        openNav();
}

window.addEventListener('resize', function (event) {
    resize();
}, true);

resize();