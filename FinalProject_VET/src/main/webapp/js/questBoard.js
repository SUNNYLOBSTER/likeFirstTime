


//const modalOpen = document.querySelector(".open");
//const modalClose = document.querySelector(".modal__closeBtn");
//const modal = document.querySelector(".modal");
//
//function init() {
//  modalOpen.addEventListener("click", function () {
//    modal.classList.remove("hidden");
//    //hidden이라는 클래스가 있으면 없앤다.
//  });
//  modalClose.addEventListener("click", function () {
//    modal.classList.add("hidden");
//  });
//}
//
//init(); //함수 실행!

const modal = document.getElementById("modal")

function modalOn() {
    modal.style.display = "flex"
}

function isModalOn() {
    return modal.style.display === "flex"
}

function modalOff() {
    modal.style.display = "none"
}


const btnModal = document.getElementById("btn-modal")
btnModal.addEventListener("click", e => {
    modalOn()
})

const closeBtn = modal.querySelector(".close-area")
closeBtn.addEventListener("click", e => {
    modalOff()
})

modal.addEventListener("click", e => {
    const evTarget = e.target
    if(evTarget.classList.contains("modal-overlay")) {
        modalOff()
    }
})

window.addEventListener("keyup", e => {
    if(isModalOn() && e.key === "Escape") {
        modalOff()
    }
})
