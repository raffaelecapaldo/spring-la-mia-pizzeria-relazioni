const buttons = document.querySelectorAll(".delete-button")

if (buttons) {
    buttons.forEach((button) => {
        button.addEventListener('click', (e) => {
            e.preventDefault
            button.parentElement.submit()
        })
    })

}