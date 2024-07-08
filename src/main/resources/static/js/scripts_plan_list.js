document.addEventListener('DOMContentLoaded', function () {
    const sortSelect = document.getElementById('sort');
    const planList = document.getElementById('plan-list');

    function fetchPlans(sort) {
        fetch(`/plan/list?sort=${sort}`)
            .then(response => response.json())
            .then(plans => {
                planList.innerHTML = '';
                plans.forEach(plan => {
                    const listItem = document.createElement('li');
                    listItem.textContent = `${plan.location} (${new Date(plan.startDate).toLocaleDateString()} - ${new Date(plan.endDate).toLocaleDateString()})`;
                    planList.appendChild(listItem);
                });
            })
            .catch(error => console.error('Error fetching plans:', error));
    }

    sortSelect.addEventListener('change', function () {
        fetchPlans(sortSelect.value);
    });

    // 페이지 로드 시 기본으로 최신순 정렬된 목록을 가져옵니다.
    fetchPlans('latest');
});
