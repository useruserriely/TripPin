document.getElementById('searchBtn').addEventListener('click', function() {
    var searchInput = document.getElementById('searchInput').value.toLowerCase();
    var destinations = document.querySelectorAll('.destination p');

    destinations.forEach(function(destination) {
       var text = destination.textContent.toLowerCase();
       var parent = destination.parentElement;

         if (text.includes(searchInput)) {
                parent.style.display = 'block';
         } else {
                parent.style.display = 'none';
            }
        });
    });

document.addEventListener('DOMContentLoaded', () => {
    const modal = document.getElementById("myModal");
    const modalImage = document.getElementById("modalImage");
    const modalDescription = document.getElementById("modalDescription");
    const registerButton = document.getElementById("registerButton");
    const span = document.getElementsByClassName("close")[0];

    document.querySelectorAll('.destination').forEach(item => {
        item.addEventListener('click', event => {
            const title = item.querySelector('p').textContent;
            const imageUrl = item.querySelector('img').src;
            const description = item.querySelector('p').textContent; // 예시 설명으로 임시 처리

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

    registerButton.onclick = function() {
        window.location.href = "/plan";
    }
});
