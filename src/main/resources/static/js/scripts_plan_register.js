document.addEventListener('DOMContentLoaded', () => {
    const selectedLocationNameElem = document.getElementById('selectedLocationName');
    const selectedPeriodElem = document.getElementById('selectedPeriod');
    const selectLocationBtn = document.getElementById('selectLocationBtn');
    const registerNewLocationBtn = document.getElementById('registerNewLocationBtn');
    const mapElem = document.getElementById('map');

    // 임시 데이터 (API에서 가져온 것으로 대체 필요)
    const locations = [
        { name: '제주', coordinates: [33.489011, 126.498302] },
        { name: '부산', coordinates: [35.1795543, 129.0756416] },
        { name: '서울', coordinates: [37.5666791, 126.9782914] }
    ];

    // 지도 초기화
    const map = L.map(mapElem).setView(locations[0].coordinates, 10);

    // 지도 타일 레이어 추가
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 18,
        attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

    // 장소 선택 버튼 이벤트 리스너
    selectLocationBtn.addEventListener('click', () => {
        alert('장소 선택 기능을 구현하세요.');
    });

    // 신규 등록 버튼 이벤트 리스너
    registerNewLocationBtn.addEventListener('click', () => {
        alert('신규 등록 기능을 구현하세요.');
    });
});