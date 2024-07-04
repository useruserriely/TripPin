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
    const calendarContainer = document.getElementById("calendarContainer");

    let startDate = null;
    let endDate = null;

    document.querySelectorAll('.destination').forEach(item => {
        item.addEventListener('click', event => {
            const title = item.querySelector('p').textContent;
            const imageUrl = item.querySelector('img').src;
            const description = item.querySelector('p').textContent;

            modalImage.src = imageUrl;
            modalDescription.textContent = description;

            modal.style.display = "block";
            calendarContainer.style.display = "none"; // 기본적으로 달력 숨기기
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

    registerButton.addEventListener('click', () => {
        // 모달 내의 달력을 보여줍니다.
        calendarContainer.style.display = "block";

        // 달력 초기화 또는 필요한 로직 추가
        renderCalendar(); // 달력을 렌더링하는 함수 호출
    });

    function renderCalendar() {
        const calendar = document.querySelector('.calendar');
        calendar.innerHTML = ''; // 기존 달력 내용 초기화

        // 현재 날짜 객체
        const currentDate = new Date();

        // 시작 월부터 종료 월까지 달력 생성
        for (let monthOffset = 0; monthOffset < 2; monthOffset++) { // 2달치 생성 예시
            const month = new Date(currentDate.getFullYear(), currentDate.getMonth() + monthOffset);

            const monthElement = document.createElement('div');
            monthElement.classList.add('month');
            monthElement.textContent = `${month.getFullYear()}년 ${month.getMonth() + 1}월`;
            calendar.appendChild(monthElement);

            const daysElement = document.createElement('div');
            daysElement.classList.add('days');
            calendar.appendChild(daysElement);

            const daysInMonth = new Date(month.getFullYear(), month.getMonth() + 1, 0).getDate(); // 현재 달의 마지막 날짜

            // 각 날짜 버튼 생성
            for (let day = 1; day <= daysInMonth; day++) {
                const dayElement = document.createElement('div');
                dayElement.classList.add('day');
                dayElement.textContent = day;
                dayElement.addEventListener('click', () => {
                    handleDayClick(month.getFullYear(), month.getMonth(), day);
                });
                daysElement.appendChild(dayElement);
            }
        }
    }

    function handleDayClick(year, month, day) {
        const selectedDate = new Date(year, month, day);

        if (!startDate) {
            startDate = selectedDate;
            highlightSelectedDay(startDate);
        } else if (!endDate && selectedDate > startDate) {
            endDate = selectedDate;
            highlightSelectedDay(endDate);
            // 여기서 선택된 날짜 범위를 사용하거나 처리하는 로직을 추가하세요.
            alert(`선택된 기간: ${formatDate(startDate)} ~ ${formatDate(endDate)}`);
        } else {
            // 다시 선택하거나 초기화할 경우 처리
            startDate = null;
            endDate = null;
            clearHighlightedDays();
        }
    }

    function highlightSelectedDay(date) {
        const days = document.querySelectorAll('.day');
        days.forEach(day => {
            day.classList.remove('selected');
            const dayDate = new Date(date.getFullYear(), date.getMonth(), parseInt(day.textContent));
            if (dayDate >= startDate && (!endDate || dayDate <= endDate)) {
                day.classList.add('selected');
            }
        });
    }

    function clearHighlightedDays() {
        const days = document.querySelectorAll('.day');
        days.forEach(day => {
            day.classList.remove('selected');
        });
    }

    function formatDate(date) {
        const year = date.getFullYear();
        const month = date.getMonth() + 1;
        const day = date.getDate();
        return `${year}-${month < 10 ? '0' + month : month}-${day < 10 ? '0' + day : day}`;
    }
});
