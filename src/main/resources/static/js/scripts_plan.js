document.addEventListener('DOMContentLoaded', () => {
    const modal = document.getElementById("myModal");
    const modalImage = document.getElementById("modalImage");
    const modalDescription = document.getElementById("modalDescription");
    const span = document.getElementsByClassName("close")[0];

    document.querySelectorAll('.destination').forEach(item => {
        item.addEventListener('click', event => {
            const title = item.querySelector('p').textContent;
            const imageUrl = item.querySelector('img').src;
            const description = item.querySelector('p').textContent;

            modalImage.src = imageUrl;
            modalDescription.textContent = description;

            modal.style.display = "block";
        });
    });

    span.onclick = function() {
        modal.style.display = "none";
    }

    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
});
